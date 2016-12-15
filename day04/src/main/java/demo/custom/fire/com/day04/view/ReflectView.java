package demo.custom.fire.com.day04.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import demo.custom.fire.com.day04.R;

/*
 *  项目名：  CustomView 
 *  包名：    demo.custom.fire.com.day04.view
 *  文件名:   ReflectView
 *  创建者:   lsy
 *  创建时间:  2016/12/12 14:56
 *  描述：
 */
public class ReflectView extends View {

    private Bitmap mSrcBitmap;
    private Bitmap mRefBitmap;
    private Paint mPaint;
    private int x;
    private int y;
    private PorterDuffXfermode mXfermode;
    private Paint mColorPaint;

    public ReflectView(Context context) {
        this(context, null);
    }

    public ReflectView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        initRes(context);

    }

    private void initRes(Context context) {

        //获取源图片
        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gril);

        //实例化矩阵对象
        Matrix matrix = new Matrix();
        matrix.setScale(1F, -1F);

        int[] screenSize = MeasureUtil.getScreenSize((Activity) context);

        int screenW = screenSize[0];
        int screenH = screenSize[1];

        x = screenW / 2 - mSrcBitmap.getWidth() / 2;
        y = screenH / 2 - mSrcBitmap.getHeight() / 2;


        //生成倒影
        mRefBitmap = Bitmap.createBitmap(mSrcBitmap, 0, 0, mSrcBitmap.getWidth(), mSrcBitmap.getHeight(), matrix, true);

        mColorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mColorPaint.setColor(Color.RED);
        //
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setShader(new LinearGradient(x, y + mSrcBitmap.getHeight(), x, y + mSrcBitmap.getHeight() + mSrcBitmap.getHeight() / 4, 0xAA000000, Color.TRANSPARENT, Shader.TileMode.CLAMP));

        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);

    }


    @Override
    protected void onDraw(Canvas canvas) {

//        canvas.drawBitmap(mRefBitmap, x, y, mPaint);

        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(mSrcBitmap, x, y, null);

        int sc = canvas.saveLayer(x, y + mSrcBitmap.getHeight(), x + mRefBitmap.getWidth(), y + mSrcBitmap.getHeight() * 2, null, Canvas.ALL_SAVE_FLAG);

//        canvas.rotate(90);

//
        canvas.drawBitmap(mRefBitmap, x, y + mSrcBitmap.getHeight(), null);
//
        mPaint.setXfermode(mXfermode);
//
//        canvas.drawRect(x, y + mSrcBitmap.getHeight(), x + mRefBitmap.getWidth(), y + mSrcBitmap.getHeight() * 2, mColorPaint);
        canvas.drawRect(x, y + mSrcBitmap.getHeight(), x + mRefBitmap.getWidth(), y + mSrcBitmap.getHeight() * 2, mPaint);

        mPaint.setXfermode(null);
        canvas.restoreToCount(sc);


    }
}
