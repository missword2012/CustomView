package com.fire.custom.two;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

/*
 *  项目名：  CustomView 
 *  包名：    com.fire.custom.two
 *  文件名:   CutsomView
 *  创建者:   lsy
 *  创建时间:  2016/12/5 10:21
 *  描述：    TODO
 */
public class CustomView2 extends View {

    private Paint mPaint;
    private Context mContext;
    private Bitmap bitmap;
    private int x;
    private int y;

    public CustomView2(Context context) {
        this(context, null);
    }

    public CustomView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;


        initRes();

        //画圆
//        initPaint1();

        //画bitmap 测试ColorMatrixColorFilter
//        initPaint2();

        //画bitmap 测试LightingColorFilter
        initPaint3();

    }

    private void initPaint3() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //设置颜色过滤
//        mPaint.setColorFilter(new LightingColorFilter(0xFFFF00FF, 0x00000000));

        //设置PorterDuffColorFilter
//        mPaint.setColorFilter(new PorterDuffColorFilter(Color.GREEN, PorterDuff.Mode.DARKEN));

        /*
         * 当画布中有跟0XFFFFFFFF色不一样的地方时候才“染”色
         */


    }

    /**
     * 初始化资源
     */
    private void initRes() {

        DeviceInfoUtils.getInstance().initScreenInfo((Activity) mContext);

        bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.a);

        x = MeasureUtil.getScreenSize((Activity) mContext)[0] / 2 - bitmap.getWidth() / 2;
        y = MeasureUtil.getScreenSize((Activity) mContext)[1] / 2 - bitmap.getHeight() / 2;

    }

    private void initPaint2() {

        mPaint = new Paint();

        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                0.33F, 0.59F, 0.11F, 0, 0,
                0.33F, 0.59F, 0.11F, 0, 0,
                0.33F, 0.59F, 0.11F, 0, 0,
                0, 0, 0, 1, 0,
        });

        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));

    }

    private void initPaint1() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        //设置颜色
        mPaint.setColor(Color.argb(123, 111, 128, 103));
        //设置描边粗细
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.FILL);

        //设置颜色过滤器

        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                -1, 1, 0, 1, 0,
                0, -1, 1, 0, 0,
                1, 0, -1, 0, 0,
                0, 0, 1, -1, 0,
        });

        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画圆
//        canvas.drawCircle(300, 600, 100, mPaint);

        canvas.drawBitmap(bitmap, x, y, mPaint);


    }
}
