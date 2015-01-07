package com.oracle.sh.groupa.ocrdemo;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

/**
 * Created by lliyu on 1/7/2015.
 */

public class OcrAsyncTask extends AsyncTask<RecogData,Void,Void> {

    public static final int OCR_START = 3;
    public static final int OCR_END = 4;

    private Handler handler = null;

    public OcrAsyncTask(Handler handler) {
        this.handler = handler;
    }

    @Override
    protected Void doInBackground(RecogData... params) {
        RecogData recogData = params[0];
        if(recogData != null) {

            Bitmap bitmap = recogData.getBitmap();
            bitmap = PicUtils.preProcess(bitmap);
            recogData.setRecognizedText(OcrUtils.recognizePic(bitmap));
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        Message message = new Message();
        message.what = OCR_START;
        handler.sendMessage(message);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Message message = new Message();
        message.what = OCR_END;
        handler.sendMessage(message);
    }
}
