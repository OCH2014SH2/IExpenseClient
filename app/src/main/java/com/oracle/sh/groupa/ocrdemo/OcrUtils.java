package com.oracle.sh.groupa.ocrdemo;

import android.graphics.Bitmap;
import android.util.Log;

import com.googlecode.tesseract.android.TessBaseAPI;

/**
 * Created by lliyu on 1/6/2015.
 */
public class OcrUtils {

    private static final String TESSBASE_PATH = "/mnt/sdcard/";
    private static final String DEFAULT_LANGUAGE = "eng";
    private static final String CHINESE_LANGUAGE = "chi_sim";

    public static String recognizePic(Bitmap bitmap){
        TessBaseAPI baseAPI = new TessBaseAPI();
        baseAPI.init(TESSBASE_PATH,CHINESE_LANGUAGE);
        //baseAPI.setPageSegMode(TessBaseAPI.PageSegMode.PSM_SINGLE_LINE);
        baseAPI.setImage(bitmap);
        final String ret = baseAPI.getUTF8Text();
        Log.d("recognized text", ret);
        baseAPI.end();
        return ret;
    }
}
