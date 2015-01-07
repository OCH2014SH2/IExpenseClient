package com.oracle.sh.groupa.ocrdemo;

import android.graphics.Bitmap;
import android.graphics.Matrix;


/**
 * Created by lliyu on 1/6/2015.
 */
public class PicUtils {
    // 该函数实现对图像进行二值化处理
    public static Bitmap gray2Binary(Bitmap graymap) {
        //得到图形的宽度和长度
        int width = graymap.getWidth();
        int height = graymap.getHeight();
        //创建二值化图像
        Bitmap binarymap = null;
        binarymap = graymap.copy(Bitmap.Config.ARGB_8888, true);
        //依次循环，对图像的像素进行处理
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                //得到当前像素的值
                int col = binarymap.getPixel(i, j);
                //得到alpha通道的值
                int alpha = col & 0xFF000000;
                //得到图像的像素RGB的值
                int red = (col & 0x00FF0000) >> 16;
                int green = (col & 0x0000FF00) >> 8;
                int blue = (col & 0x000000FF);
                // 用公式X = 0.3×R+0.59×G+0.11×B计算出X代替原来的RGB
                int gray = (int) ((float) red * 0.3 + (float) green * 0.59 + (float) blue * 0.11);
                //对图像进行二值化处理
                if (gray <= 95) {
                    gray = 0;
                } else {
                    gray = 255;
                }
                // 新的ARGB
                int newColor = alpha | (gray << 16) | (gray << 8) | gray;
                //设置新图像的当前像素值
                binarymap.setPixel(i, j, newColor);
            }
        }
        return binarymap;
    }

    public static Bitmap getScaleBitmap(Bitmap mBitmap) {

        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        float rate = width*height > 1000*1000? (float) Math.sqrt((double) 1000000 / width / height) : (float) 1.0;

        Matrix matrix = new Matrix();
        matrix.preScale(rate, rate);

        return Bitmap.createBitmap(mBitmap, 0, 0, width, height, matrix, true);
    }
    public static Bitmap preProcess(Bitmap bitmap){
        bitmap = getScaleBitmap(bitmap);
        //bitmap = gray2Binary(bitmap);
        return bitmap;
    }

}
