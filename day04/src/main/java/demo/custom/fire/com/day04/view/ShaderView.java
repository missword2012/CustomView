package demo.custom.fire.com.day04.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import demo.custom.fire.com.day04.R;

/*
 *  项目名：  CustomView 
 *  包名：    demo.custom.fire.com.day04.view
 *  文件名:   ShaderView
 *  创建者:   lsy
 *  创建时间:  2016/12/12 11:17
 *  描述：
 */
public class ShaderView extends View {

    private static final int RECT_SIZE = 350;

    private int left, top, right, bottom;
    private Paint mPaint;
    private int screenX;
    private int screenY;


    public ShaderView(Context context) {
        this(context, null);
    }

    public ShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //获取屏幕数据
        int[] screenSize = MeasureUtil.getScreenSize((Activity) context);

        //屏幕中心点坐标
        screenX = screenSize[0] / 2;
        screenY = screenSize[1] / 2;

        left = screenX - RECT_SIZE;
        top = screenY - RECT_SIZE;
        right = screenX + RECT_SIZE;
        bottom = screenY + RECT_SIZE;


        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        //获取bitmap资源
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.a);

        //设置着色器
//        mPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.CLAMP));

//        mPaint.setShader(new LinearGradient(left, top, right, bottom, Color.RED, Color.BLUE, Shader.TileMode.REPEAT));
//        mPaint.setShader(new LinearGradient(left, top, right, bottom, new int[] { Color.RED, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE }, new float[] { 0, 0.3F, 0.5F, 0.7F, 0.8F }, Shader.TileMode.MIRROR));

//        mPaint.setShader(new SweepGradient(screenX,screenY,Color.RED,Color.YELLOW));

        //设置矩阵变换
        Matrix matrix = new Matrix();
        matrix.setTranslate(500, 500);
        matrix.setRotate(5);

        // 实例化一个Shader
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        bitmapShader.setLocalMatrix(matrix);

        mPaint.setShader(bitmapShader);

//        mPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(left, top, right, bottom, mPaint);
    }
}
