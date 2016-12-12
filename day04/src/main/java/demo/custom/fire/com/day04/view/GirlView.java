package demo.custom.fire.com.day04.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import demo.custom.fire.com.day04.R;

/*
 *  项目名：  CustomView 
 *  包名：    demo.custom.fire.com.day04.view
 *  文件名:   GrilView
 *  创建者:   lsy
 *  创建时间:  2016/12/12 14:02
 *  描述：
 */
public class GirlView extends View {

    private Paint mStrokePaint;
    private Paint mFillPaint;
    private float posX;
    private float posY;

    public GirlView(Context context) {
        this(context, null);
    }

    public GirlView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPaint();
    }

    private void initPaint() {

        //实例化描边画笔
        mStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mStrokePaint.setColor(0xFF000000);
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setStrokeWidth(5);

        //实例化填充画笔
        mFillPaint = new Paint();

        //设置bitmapShader
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gril);
        BitmapShader mBitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        mFillPaint.setShader(mBitmapShader);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /**
         * 手指触碰点的坐标刷新视图
         */

        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            posX = event.getX();
            posY = event.getY();

            invalidate();
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //设置背景颜色
        canvas.drawColor(Color.DKGRAY);

        //绘制圆和描边
        canvas.drawCircle(posX, posY, 200, mFillPaint);
        canvas.drawCircle(posX, posY, 200, mStrokePaint);


    }
}
