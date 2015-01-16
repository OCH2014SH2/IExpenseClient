package com.oracle.sh.groupa.ocrdemo.ocr;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.googlecode.tesseract.android.TessBaseAPI;
import com.oracle.sh.groupa.ocrdemo.R;
import com.oracle.sh.groupa.ocrdemo.dataStructure.LocalReceiptInfo;

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

    public static final String PHOTO_DIR = Environment.getExternalStorageDirectory().toString() + "/";

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
                InputStream ins = context.getResources().openRawResource(R.raw.chi_sim);// 通过raw得到数据资源
                FileOutputStream fos = new FileOutputStream(file);
                byte[] buffer = new byte[8192];
                int count = 0;// 循环写出
                while ((count = ins.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                ins.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class LangImportAsyncTask extends AsyncTask<Context, Void, Void> {

        @Override
        protected Void doInBackground(Context... params) {

            OcrUtils.createFile(params[0]);
            return null;
        }
    }

    public static LocalReceiptInfo getDataFromRecogText(RecogData recogData) {
        if (recogData == null)
            return null;
        ReceiptDataInfo receiptDataInfo = new ReceiptDataInfo();
        String[] items = {ReceiptDataInfo.FAPIAO_DATE, ReceiptDataInfo.FAPIAO_PRICE, ReceiptDataInfo.FAPIAO_TITLE};
        String recognizedText = recogData.getRecognizedText();
        String[] lines = recognizedText.split("\n");
        for (String line : lines) {
            for (String item : items) {
                if (line.contains(item)) {
                    String temp = null;
                    int index;
                    switch (item) {
                        case ReceiptDataInfo.FAPIAO_TITLE:
                            temp = line.substring(line.indexOf(item) + item.length(), line.length() - 1);
                            receiptDataInfo.setName(temp);
                            break;
                        case ReceiptDataInfo.FAPIAO_PRICE:
                            index = line.indexOf(item);
                            temp = "";
                            while (index > 0) {
                                char ch = line.charAt(--index);
                                if ((ch <= '9' && ch >= '0') || (ch == '.' || ch == ','))
                                    temp += ch;
                                else
                                    break;
                            }
                            StringBuffer sb = new StringBuffer(temp);
                            temp = sb.reverse().toString();
                            receiptDataInfo.setPrice(temp);
                            break;
                        case ReceiptDataInfo.FAPIAO_DATE:
                            index = line.indexOf(item) + item.length();
                            temp = "";
                            while (index < line.length()) {
                                char ch = line.charAt(index++);
                                if ((ch <= '9' && ch >= '0') || (ch == '.' || ch == ','||ch=='-' || ch == ' '))
                                    temp += ch;
                                else
                                    break;
                            }
                            receiptDataInfo.setDate(temp);
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        double price = 0.0;
        try{
            price=Double.parseDouble(receiptDataInfo.getPrice());
        }
        catch (Exception e){
            price=0.0;
            Log.e("parse double error",e.toString());
        }

        String picAbsolutePath = PHOTO_DIR + recogData.getPicFileName();
        return new LocalReceiptInfo(receiptDataInfo.getName(), receiptDataInfo.getDate(), price, picAbsolutePath);

    }

}
