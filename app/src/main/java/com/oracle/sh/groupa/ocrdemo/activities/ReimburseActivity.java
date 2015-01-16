package com.oracle.sh.groupa.ocrdemo.activities;

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

import com.oracle.sh.groupa.ocrdemo.ocr.OcrAsyncTask;
import com.oracle.sh.groupa.ocrdemo.ocr.OcrUtils;
import com.oracle.sh.groupa.ocrdemo.R;
import com.oracle.sh.groupa.ocrdemo.ocr.RecogData;
import com.oracle.sh.groupa.ocrdemo.dataStructure.LocalReceiptInfo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by lliyu on 12/26/2014.
 */
public class ReimburseActivity extends Activity {

    private static final int TAKE_PHOTO = 1;
    private static final int CROP_PHOTO = 2;

    private Button button;
    private ImageView imageView;
    private EditText editTextTitle;
    private EditText editTextPrice;
    private EditText editTextTime;

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

                    editTextTitle.setText(localReceiptInfo.getTitle());
                    editTextPrice.setText(String.valueOf(localReceiptInfo.getPrice()));
                    editTextTime.setText(localReceiptInfo.getDateTime());

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
        imageView = (ImageView) findViewById(R.id.imageView);
        editTextTitle = (EditText) findViewById(R.id.editTextTitle);
        editTextPrice = (EditText) findViewById(R.id.editTextPrice);
        editTextTime = (EditText) findViewById(R.id.editTextTime);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(localReceiptInfo == null)
                    return;
                localReceiptInfo.setDateTime(editTextTime.getText().toString());
                localReceiptInfo.setPrice(Double.parseDouble(editTextPrice.getText().toString()));
                localReceiptInfo.setTitle(editTextTitle.getText().toString());

                Intent intent = new Intent(ReimburseActivity.this, ShowReceiptActivity.class);
                intent.putExtra("data", localReceiptInfo);
                startActivity(intent);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoFileName = String.valueOf(System.currentTimeMillis()) + ".jpg";
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
}
