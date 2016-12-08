package com.fire.custom.two;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import com.fire.custom.two.bean.PorterDuffBO;

/*
 *  项目名：  CustomView 
 *  包名：    com.fire.custom.two
 *  文件名:   ProterDuffView
 *  创建者:   lsy
 *  创建时间:  2016/12/7 9:38
 *  描述：    TODO
 */
public class PorterDuffView extends View {


    private Context mContext;
    /**
     * porterDuff模式常量，为了方便更换测试
     */
    private static final PorterDuff.Mode MODE = PorterDuff.Mode.DST_OUT;
    private PorterDuffBO porterDuffBO;
    private PorterDuffXfermode porterDuffXfermode;
    private int screenW;
    private int screenH;
    private int s_l;
    private int s_t;

    private static final int RECT_SIZE_SMALL = 400;// 左右上方示例渐变正方形的尺寸大小
    private static final int RECT_SIZE_BIG = 800;// 中间测试渐变正方形的尺寸大小
    private int d_l;
    private int d_t;
    private int rectX;
    private int rectY;
    private Paint mPaint;


    public PorterDuffView(Context context) {
        this(context, null);
    }

    public PorterDuffView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initPaint();

    }


    private void initPaint() {
        //初始化画笔并设置抗锯齿
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        //实例化业务对象
        porterDuffBO = new PorterDuffBO();

        //实例化混合模式
        porterDuffXfermode = new PorterDuffXfermode(MODE);

        //计算坐标
        calu(mContext);

    }

    private void calu(Context mContext) {
        int[] screenSize = MeasureUtil.getScreenSize((Activity) mContext);
        screenW = screenSize[0];
        screenH = screenSize[1];

        //计算左上方正方形原点坐标
        s_l = 0;
        s_t = 0;

        //计算右上方正方形原点坐标
        d_l = screenW - RECT_SIZE_SMALL;
        d_t = 0;

        //计算正方形原点坐标
        rectX = screenW / 2 - RECT_SIZE_BIG / 2;
        rectY = RECT_SIZE_SMALL + (screenH - RECT_SIZE_SMALL) / 2 - RECT_SIZE_BIG / 2;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //设置画布颜色
        canvas.drawColor(Color.BLACK);

        //设置尺寸
        porterDuffBO.setSize(RECT_SIZE_SMALL);

        /**
         * 画出左右上方2个正方形
         * 左边为src 右边为dis
         */
        canvas.drawBitmap(porterDuffBO.initSrcBitmap(), s_l, s_t, mPaint);
        canvas.drawBitmap(porterDuffBO.initDisBitmap(), d_l, d_t, mPaint);

        //将绘制操作保持到新的图层(离屏缓存)
        int sc = canvas.saveLayer(0, 0, screenW, screenH, null, Canvas.ALL_SAVE_FLAG);

        //绘制dis目标图
        canvas.drawBitmap(porterDuffBO.initDisBitmap(), rectX, rectY, mPaint);

        //设置混合模式
        mPaint.setXfermode(porterDuffXfermode);

        //绘制src源图
        canvas.drawBitmap(porterDuffBO.initSrcBitmap(), rectX, rectY, mPaint);

        //还原混合模式
        mPaint.setXfermode(null);

        //还原画布
        canvas.restoreToCount(sc);


    }
}
