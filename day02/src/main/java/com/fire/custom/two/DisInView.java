package com.fire.custom.two;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/*
 *  项目名：  CustomView 
 *  包名：    com.fire.custom.two
 *  文件名:   DisInView
 *  创建者:   lsy
 *  创建时间:  2016/12/8 10:46
 *  描述：    TODO
 */
public class DisInView extends View {

    private static final String tag = "DisInView";

    private Context mContext;
    private Paint mPaint;
    private Bitmap bitmapDis;
    private int screenW;
    private int screenH;
    private int x;
    private int y;
    private PorterDuffXfermode porterDuffXfermode;
    private Bitmap bitmapSrc;

    public DisInView(Context context) {
        this(context, null);
    }

    public DisInView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        //初始化画笔
        initPaint();

        //初始化资源
        initRes();

        //实例化混合模式
//        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
//        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SCREEN);


    }


    private void initRes() {

        //获取bitmap
        bitmapDis = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.b);
//        bitmapSrc = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.c);

        //获取屏幕尺寸
        int[] screenSize = MeasureUtil.getScreenSize((Activity) mContext);

        screenW = screenSize[0];
        screenH = screenSize[1];

        Log.d(tag, "screenW : " + screenW + " screenH : " + screenH);

        /**
         * 计算位图绘制时左上角的坐标位于屏幕中心
         * 是屏幕坐标x,y向坐上偏移一半，位图居中
         */

        x = screenW / 2 - bitmapDis.getWidth() / 2;
        y = screenH / 2 - bitmapDis.getHeight() / 2;
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawBitmap(bitmapDis,x,y,mPaint);

        /**
         * 离屏缓存
         */
        int sc = canvas.saveLayer(0, 0, screenW, screenH, null, Canvas.ALL_SAVE_FLAG);

        //绘制dis目标图
//        canvas.drawBitmap(bitmapDis, x, y, mPaint);

        //绘制颜色
//        canvas.drawColor(0xFF8f66DA);

        //绘制透明色
        canvas.drawColor(0xcc1c093e);

        //设置混合模式
        mPaint.setXfermode(porterDuffXfermode);

        //绘制src源图
        canvas.drawBitmap(bitmapDis, x, y, mPaint);

        //还原混合模式
        mPaint.setXfermode(null);

        //还原画布
        canvas.restoreToCount(sc);


    }
}
