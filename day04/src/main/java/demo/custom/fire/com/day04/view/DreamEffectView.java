package demo.custom.fire.com.day04.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import demo.custom.fire.com.day04.R;

/*
 *  项目名：  CustomView 
 *  包名：    demo.custom.fire.com.day04.view
 *  文件名:   DreamEffectView
 *  创建者:   lsy
 *  创建时间:  2016/12/13 10:31
 *  描述：    TODO
 */
public class DreamEffectView extends View {

    private Bitmap mBitmap;
    private PorterDuffXfermode mXfermode;
    private int screenW;
    private int screenH;
    private int x;
    private int y;
    private Paint mBitmapPaint;
    private Paint mShaderPaint;
    private Bitmap darkCornerBitmap;

    public DreamEffectView(Context context) {
        this(context, null);
    }

    public DreamEffectView(Context context, AttributeSet attrs) {
        super(context, attrs);


        initRes(context);

        initPaint();

    }

    private void initPaint() {

        mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        //设置画笔颜色过滤
        mBitmapPaint.setColorFilter(new ColorMatrixColorFilter(new float[]{0.8587F, 0.2940F, -0.0927F, 0, 6.79F, 0.0821F, 0.9145F, 0.0634F, 0, 6.79F, 0.2019F, 0.1097F, 0.7483F, 0, 6.79F, 0, 0, 0, 1, 0}));

        //实例化Shader画笔
        mShaderPaint = new Paint();

//        // 中心颜色为透明而边缘颜色为黑色
//        mShaderPaint.setShader(new RadialGradient(screenW / 2, screenH / 2, mBitmap.getHeight(), Color.TRANSPARENT, Color.BLACK, Shader.TileMode.CLAMP));

        //根据源图生成暗礁bitmap
        darkCornerBitmap = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);

        //将暗角bitmap注入Canvas
        Canvas canvas = new Canvas(darkCornerBitmap);

        //计算径向渐变半径
        float radiu = canvas.getHeight() * (2F / 3F);

        //实例化径向半径
        RadialGradient radialGradient = new RadialGradient(canvas.getWidth() / 2F, canvas.getHeight() / 2F, radiu, new int[]{0, 0, 0xAA000000}, new float[]{0F, 0.7F, 1.0F}, Shader.TileMode.CLAMP);


        //实例化矩阵
        Matrix matrix = new Matrix();

        //设置矩阵缩放
        matrix.setScale(canvas.getWidth() / (radiu * 2F), 1.0F);

        //设置矩阵平移
        matrix.preTranslate(((radiu * 2F) - canvas.getWidth()) / 2F, 0);

        //将矩阵注入渐变
        radialGradient.setLocalMatrix(matrix);

        //设置画笔
        mShaderPaint.setShader(radialGradient);

        //绘制矩形
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), mShaderPaint);


    }


    private void initRes(Context context) {

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gril);

        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SCREEN);

        int[] screenSize = MeasureUtil.getScreenSize((Activity) context);

        screenW = screenSize[0];
        screenH = screenSize[1];

        x = screenW / 2 - mBitmap.getWidth() / 2;
        y = screenH / 2 - mBitmap.getHeight() / 2;

    }

    @Override
    protected void onDraw(Canvas canvas) {

        //绘制背景颜色
        canvas.drawColor(Color.BLACK);

        //新建图层
        int sc = canvas.saveLayer(x, y, x + mBitmap.getWidth(), y + mBitmap.getHeight(), null, Canvas.ALL_SAVE_FLAG);

        //绘制的混合颜色
        canvas.drawColor(0xcc1c093e);

        //设置混合模式
        mBitmapPaint.setXfermode(mXfermode);

        //绘制位图
        canvas.drawBitmap(mBitmap, x, y, mBitmapPaint);

        //还原混合模式
        mBitmapPaint.setXfermode(null);

        //还原画布
        canvas.restoreToCount(sc);

        //绘制一个跟图片一样大小的矩形
//        canvas.drawRect(x, y, x + mBitmap.getWidth(), y + mBitmap.getHeight(), mShaderPaint);

        canvas.drawBitmap(darkCornerBitmap, x, y, null);


    }
}
