package demo.custom.fire.com.customview.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import demo.custom.fire.com.customview.utils.DeviceInfoUtils;

/*
 *  项目名：  CustomView 
 *  包名：    demo.custom.fire.com.customview
 *  文件名:   CustomView1
 *  创建者:   lsy
 *  创建时间:  2016/12/2 10:22
 *  描述：    TODO
 */
public class CustomView1 extends View implements Runnable {


    private Paint mPaint;

    private int radius = 50;

    public CustomView1(Context context) {
        this(context, null);
    }

    public CustomView1(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPaint();
        DeviceInfoUtils.getInstance().initScreenInfo((Activity) context);

    }

    private void initPaint() {
        //打开抗锯齿
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mPaint.setAntiAlias(true);同上FLAG

        /**
         * 设置画笔样式
         * Paint.Style.STROKE  描边
         * Paint.Style.FILL   填充
         * Paint.Style.FILL_AND_STROKE 描边并填充
         */
        mPaint.setStyle(Paint.Style.STROKE);

        //设置画笔颜色
        mPaint.setColor(Color.RED);

        /**
         * 设置描边粗细 单位px
         * 注意:当
         */
        mPaint.setStrokeWidth(0);
//        mPaint.setStrokeWidth(10);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(DeviceInfoUtils.screenWidth / 2, DeviceInfoUtils.screenHeight / 2, radius, mPaint);
//        canvas.drawCircle(500, 500, radius, mPaint);


    }

    public void setRadius(int radius){
        this.radius = radius;
        invalidate();
    }


    @Override
    public void run() {
        while (true) {
            try {
                if (radius <= 200) {
                    radius += 10;
                    //刷新view
                    postInvalidate();
//                            invalidate();
                } else {
                    radius = 0;
                }
                Thread.sleep(60);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
