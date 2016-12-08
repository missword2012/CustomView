package com.fire.custom.two;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/*
 *  项目名：  CustomView 
 *  包名：    com.fire.custom.two
 *  文件名:   EraserView
 *  创建者:   lsy
 *  创建时间:  2016/12/8 15:04
 *  描述：    来自爱哥自定义控件2
 *              http://blog.csdn.net/aigestudio/article/details/41316141
 */
public class EraserView extends View {


    private static final int MIN_MOVE_DIS = 5;// 最小的移动距离：如果我们手指在屏幕上的移动距离小于此值则不会绘制

    private Context mContext;
    private int screenW;
    private int screenH;
    private Path mPath;
    private Bitmap fgBitmap;
    private Canvas mCanvas;
    private Bitmap bgBitmap;
    private Paint mPaint;
    private float preX;
    private float preY;

    public EraserView(Context context) {
        this(context, null);
    }

    public EraserView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        //计算参数
        cal();

        //初始化对象
        init();


    }


    private void cal() {

        int[] screenSize = MeasureUtil.getScreenSize((Activity) mContext);
        screenW = screenSize[0];
        screenH = screenSize[1];

    }


    private void init() {

        //实例化路径对象
        mPath = new Path();

        //开启抗锯齿和抗抖动
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

        //设置画笔透明度为0
        mPaint.setARGB(128, 255, 0, 0);

        //设置混合模式
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        //设置画笔风格为描边
        mPaint.setStyle(Paint.Style.STROKE);

        //设置路径结合处样式
        mPaint.setStrokeJoin(Paint.Join.ROUND);

        //设置笔触类型
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        //设置描边宽度
        mPaint.setStrokeWidth(50);

        //生成前景图bitmap
        fgBitmap = Bitmap.createBitmap(screenW, screenH, Bitmap.Config.ARGB_8888);

        //注入画布
        mCanvas = new Canvas(fgBitmap);

        //绘制画布为灰色
        mCanvas.drawColor(0xFF808080);

        //获取背景地图的bitmap
        bgBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.a4);

        //缩放背景bitmap到屏幕大小
        bgBitmap = Bitmap.createScaledBitmap(bgBitmap, screenW, screenH, true);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制背景
        canvas.drawBitmap(bgBitmap, 0, 0, null);

        //绘制前景
        canvas.drawBitmap(fgBitmap, 0, 0, null);

        /**
         * 这里要注意canvas和mCanvas是两个不同的画布对象
         * 当我们在屏幕上移动手指绘制路径时会把路径通过mCanvas绘制到fgBitmap上
         * 每当我们手指移动一次均会将路径mPath作为目标图像绘制到mCanvas上，而在上面我们先在mCanvas上绘制了中性灰色
         * 两者会因为DST_IN模式的计算只显示中性灰，但是因为mPath的透明，计算生成的混合图像也会是透明的
         * 所以我们会得到“橡皮擦”的效果
         */
        mCanvas.drawPath(mPath, mPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        /**
         * 获取当前点击事件坐标位置
         */
        float x = event.getX();
        float y = event.getY();


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(x, y);
                preX = x;
                preY = y;
                break;

            case MotionEvent.ACTION_MOVE:

                float dx = Math.abs(x - preX);
                float dy = Math.abs(y - preY);

                if (dx >= MIN_MOVE_DIS || dy >= MIN_MOVE_DIS) {
                    mPath.quadTo(preX, preY, (x + preX) / 2, (y + preY) / 2);
                    preX = x;
                    preY = y;
                }


                break;

        }
        invalidate();
        return true;
    }
}
