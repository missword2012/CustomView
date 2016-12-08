package com.fire.custom.two.bean;

import android.graphics.Bitmap;
import android.graphics.Point;

/*
 *  项目名：  CustomView 
 *  包名：    com.fire.custom.two.bean
 *  文件名:   PorterDuffBO
 *  创建者:   lsy
 *  创建时间:  2016/12/7 10:03
 *  描述：    TODO
 */
public class PorterDuffBO implements IBO {
    private int size;
    private Point mPoint;

    public PorterDuffBO() {
        mPoint = new Point();
    }

    public void setSize(int size) {
        this.size = size;
        mPoint.set(size, size);
    }

    public Bitmap initSrcBitmap() {
        //根据像素设置数组大小
        int[] pixels = new int[mPoint.x * mPoint.y];
        int dst = 0;
        for (int row = 0; row < mPoint.y; ++row) {
            for (int col = 0; col < mPoint.x; ++col) {
                pixels[dst++] = color((float) (mPoint.y - row) / mPoint.y, (float) (mPoint.x - col) / mPoint.x, (float) (mPoint.x - col) / mPoint.x, (float) col / mPoint.x);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(pixels, size, size, Bitmap.Config.ARGB_8888);
        return bitmap;
    }

    public Bitmap initDisBitmap() {
        int[] pixels = new int[mPoint.x * mPoint.y];
        int dst = 0;
        for (int row = 0; row < mPoint.y; ++row) {
            for (int col = 0; col < mPoint.x; ++col) {
                pixels[dst++] = color((float) (mPoint.x - col) / mPoint.x, (float) (mPoint.y - row) / mPoint.x, (float) row / mPoint.y, (float) row / mPoint.y);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(pixels, size, size, Bitmap.Config.ARGB_8888);
        return bitmap;
    }



    private int color(float Alpha, float R, float G, float B) {
        return (int) (Alpha * 255) << 24 | (int) (R * Alpha * 255) << 16 | (int) (G * Alpha * 255) << 8 | (int) (B * Alpha * 255);
    }


}
