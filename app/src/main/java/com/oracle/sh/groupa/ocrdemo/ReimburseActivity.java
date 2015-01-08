package com.oracle.sh.groupa.ocrdemo;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.oracle.sh.groupa.ocrdemo.dataStructure.LocalReceiptInfo;

import java.io.File;
import java.io.IOException;

/**
 * Created by lliyu on 12/26/2014.
 */
public class ReimburseActivity extends Activity {

    private static final int TAKE_PHOTO = 1;
    private static final int CROP_PHOTO = 2;

    private Button button;
    private ImageView imageView;
    private EditText et01;
    private EditText et02;
    private EditText et03;

    private ProgressDialog dialog;

    private String photoFileName;

    private Uri imageUri;

    private RecogData recogData;
    private LocalReceiptInfo localReceiptInfo;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case OcrAsyncTask.OCR_START:
                    dialog.show();
                    break;
                case OcrAsyncTask.OCR_END:
                    localReceiptInfo = OcrUtils.getDataFromRecogText(recogData);

                    et01.setText(localReceiptInfo.getTitle());
                    et02.setText(String.valueOf(localReceiptInfo.getPrice()));
                    et03.setText(localReceiptInfo.getDateTime());

                    dialog.dismiss();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.reimburse);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(1);

        initActivity();
        imageView=(ImageView)findViewById(R.id.imageView);
        et01=(EditText)findViewById(R.id.editText01);
        et02=(EditText)findViewById(R.id.editText02);
        et03=(EditText)findViewById(R.id.editText03);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ReimburseActivity.this,ShowrecipeActivity.class);
                intent.putExtra("data", localReceiptInfo);
                startActivity(intent);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoFileName = String.valueOf(System.currentTimeMillis())+".jpg";
                //File dir = Environment.getExternalStorageDirectory();

                File outputImage = new File(OcrUtils.PHOTO_DIR, photoFileName);

                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageUri = Uri.fromFile(outputImage);
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);
            }
        });

        //check language package
        new OcrUtils.LangImportAsyncTask().execute(ReimburseActivity.this);

    }

    private void initActivity() {
        recogData = new RecogData(this);
        dialog = new ProgressDialog(this);
        dialog.setTitle("info");
        dialog.setMessage("recognizing...");
        dialog.setCancelable(false);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri, "image/*");
                    intent.putExtra("scale", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, CROP_PHOTO);
                }
                break;
            case CROP_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        recogData.setPicFileName(photoFileName);
                        recogData.setBitmap(bitmap);
                        imageView.setImageBitmap(bitmap);

                        new OcrAsyncTask(handler).execute(recogData);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }

}
