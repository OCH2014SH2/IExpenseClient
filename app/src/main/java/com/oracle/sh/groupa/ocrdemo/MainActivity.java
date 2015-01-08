package com.oracle.sh.groupa.ocrdemo;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.oracle.sh.groupa.ocrdemo.dataStructure.ReceiptInfo;

import java.io.File;
import java.io.IOException;

/**
 * Created by lliyu on 12/26/2014.
 */
public class MainActivity extends Activity {

    private static final int TAKE_PHOTO = 1;
    private static final int CROP_PHOTO = 2;

    private Button takePhotoButton;
    private ImageView picture;
    private ProgressDialog dialog;
    private TextView textView;

    private String recognizedText;
    private Bitmap bitmap;
    private Uri imageUri;

    private RecogData recogData;
    private ReceiptInfo receiptInfo;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case OcrAsyncTask.OCR_START:
                    dialog.show();
                    break;
                case OcrAsyncTask.OCR_END:
                    recognizedText = recogData.getRecognizedText();
                    receiptInfo = OcrUtils.getDataFromRecogText(recogData);
                    String str = DataInfo.FAPIAO_TITLE+": "+receiptInfo.getTitle()+"\n"+
                            DataInfo.FAPIAO_PRICE+": "+receiptInfo.getPrice()+"\n"+
                            DataInfo.FAPIAO_DATE+": "+receiptInfo.getDateTime()+"\n";

                    textView.setText(str);
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
        setContentView(R.layout.activity_main);

        //check language package
        new OcrUtils.LangImportAsyncTask().execute(MainActivity.this);

        initActivity();

        takePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File outputImage = new File(Environment.getExternalStorageDirectory(), "tempImage.jpg");
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
    }

    private void initActivity() {
        recogData = new RecogData(this);
        takePhotoButton = (Button) findViewById(R.id.take_photo);
        picture = (ImageView) findViewById(R.id.photo);
        textView = (TextView) findViewById(R.id.recognized_text);
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
                        bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));

                        recogData.setBitmap(bitmap);
                        picture.setImageBitmap(bitmap);

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
