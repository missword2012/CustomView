package com.fire.custom.two;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/*
 *  项目名：  CustomView 
 *  包名：    com.fire.custom.two
 *  文件名:   StarView
 *  创建者:   lsy
 *  创建时间:  2016/12/5 16:32
 *  描述：    TODO
 */
public class StarView extends View {


    private Paint mPaint;
    private Context mContext;
    private Bitmap bitmap;

    private int x, y;
    private boolean isClick;

    public StarView(Context context) {
        this(context, null);
    }

    public StarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        initPaint();

        initRes();


        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isClick) {
                    mPaint.setColorFilter(null);
                    isClick = false;
                } else {
                    mPaint.setColorFilter(new LightingColorFilter(0xFFFFFFFF, 0X00FFFF00));
                    isClick = true;
                }

                invalidate();
            }
        });

    }

    private void initRes() {

        bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.star);

             /*
         * 计算位图绘制时左上角的坐标使其位于屏幕中心
         * 屏幕坐标x轴向左偏移位图一半的宽度
         * 屏幕坐标y轴向上偏移位图一半的高度
         */
        x = MeasureUtil.getScreenSize((Activity) mContext)[0] / 2 - bitmap.getWidth() / 2;
        y = MeasureUtil.getScreenSize((Activity) mContext)[1] / 2 - bitmap.getHeight() / 2;


    }

    //初始化画笔
    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bitmap, x, y, mPaint);
    }
}
