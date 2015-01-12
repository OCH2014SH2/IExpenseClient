package com.oracle.sh.groupa.ocrdemo;

import android.app.Activity;
import android.graphics.Bitmap;

/**
 * Created by lliyu on 1/7/2015.
 */
public class RecogData {


    private Bitmap bitmap;
    private String recognizedText;
    private Activity activity;


    private String picFileName;

    public RecogData(Activity activity) {
        this.activity = activity;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setRecognizedText(String recognizedText) {
        this.recognizedText = recognizedText;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public String getRecognizedText() {
        return recognizedText;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setPicFileName(String picFileName) {
        this.picFileName = picFileName;
    }

    public String getPicFileName() {

        return picFileName;
    }


}
