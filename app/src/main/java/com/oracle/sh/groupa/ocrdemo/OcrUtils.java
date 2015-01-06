package com.oracle.sh.groupa.ocrdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by lliyu on 1/6/2015.
 */
public class OcrUtils {

    private static final String TESSBASE_PATH = "/mnt/sdcard/";
    private static final String DEFAULT_LANGUAGE = "eng";
    private static final String CHINESE_LANGUAGE = "chi_sim";

    private static String tessDataDirPath = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()// 得到外部存储卡的数据库的路径名
            + "/tessdata/";// 我要存储的目录
    private static String chiDataName = "chi_sim.traineddata";// 要存储的文件名

    public static String recognizePic(Bitmap bitmap) {
        TessBaseAPI baseAPI = new TessBaseAPI();
        baseAPI.init(TESSBASE_PATH, CHINESE_LANGUAGE);
        //baseAPI.setPageSegMode(TessBaseAPI.PageSegMode.PSM_SINGLE_LINE);
        baseAPI.setImage(bitmap);
        final String ret = baseAPI.getUTF8Text();
        Log.d("recognized text", ret);
        baseAPI.end();
        return ret;
    }


    public static void createFile(final Context context) {
        String filePath = tessDataDirPath + chiDataName;// 文件路径
        try {
            File dir = new File(tessDataDirPath);// 目录路径
            if (!dir.exists()) {// 如果不存在，则创建路径名
                if (dir.mkdirs()) {// 创建该路径名，返回true则表示创建成功
                    System.out.println("已经创建文件存储目录");
                } else {
                    System.out.println("创建目录失败");
                }
            }
            // 目录存在，则将apk中raw中的需要的文档复制到该目录下
            File file = new File(filePath);
            if (!file.exists()) {// 文件不存在
                Toast.makeText(context, "language data does not exists", Toast.LENGTH_SHORT).show();
                InputStream ins = context.getResources().openRawResource(R.raw.chi_sim);// 通过raw得到数据资源
                FileOutputStream fos = new FileOutputStream(file);
                byte[] buffer = new byte[8192];
                int count = 0;// 循环写出
                while ((count = ins.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                ins.close();
                Toast.makeText(context, "language package import success!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
