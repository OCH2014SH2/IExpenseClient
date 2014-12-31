package com.oracle.sh.groupa.ocrdemo;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.IOException;

/**
 * Created by lliyu on 12/26/2014.
 */
public class MainActivity extends Activity {

    private static final int TAKE_PHOTO = 1;
    private static final int CROP_PHOTO = 2;
    private Button takePhoto;
    private ImageView picture;
    private Uri imageUri;
    private TextView textView;

    private static final String TESSBASE_PATH = "/mnt/sdcard/";
    private static final String DEFAULT_LANGUAGE = "eng";
    private static final String CHINESE_LANGUAGE = "chi_sim";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        takePhoto = (Button) findViewById(R.id.take_photo);
        picture = (ImageView) findViewById(R.id.photo);
        textView = (TextView) findViewById(R.id.recognized_text);

        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File outputImage = new File(Environment.getExternalStorageDirectory(),"tempImage.jpg");
                try{
                    if(outputImage.exists()){
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageUri = Uri.fromFile(outputImage);
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(intent,TAKE_PHOTO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode){
            case TAKE_PHOTO:
                if(resultCode==RESULT_OK){
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri,"image/*");
                    intent.putExtra("scale",true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                    startActivityForResult(intent,CROP_PHOTO);
                }
                break;
            case CROP_PHOTO:
                if(resultCode==RESULT_OK){
                    try{
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        Log.d("a","a");
                        picture.setImageBitmap(bitmap);
                        TessBaseAPI baseAPI = new TessBaseAPI();
                        baseAPI.init(TESSBASE_PATH,DEFAULT_LANGUAGE);
                        //baseAPI.setPageSegMode(TessBaseAPI.PageSegMode.PSM_SINGLE_LINE);
                        baseAPI.setImage(bitmap);
                        final String outputText = baseAPI.getUTF8Text();
                        Log.d("outputText",outputText);
                        baseAPI.end();

                    } catch (Exception e) {
                        Log.d("bb","bb");
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }

}
