package demo.custom.fire.com.day03.weiget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/*
 *  项目名：  CustomView 
 *  包名：    demo.custom.fire.com.day03.weiget
 *  文件名:   FontView
 *  创建者:   lsy
 *  创建时间:  2016/12/8 16:09
 *  描述：    TODO
 */
public class FontView extends View {

    private static final String TEXT = "ap爱哥ξτβбпшㄎㄊ大魔王";
    private Paint mPaint;
    private Paint.FontMetrics mFontMetrics;

    public FontView(Context context) {
        this(context, null);
    }

    public FontView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //初始化画笔
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mPaint.setTextSize(70);
        mPaint.setColor(Color.BLACK);



        mFontMetrics = mPaint.getFontMetrics();

        Log.d("FontMetrics", "ascent :" + mFontMetrics.ascent);
        Log.d("FontMetrics", "bottom :" + mFontMetrics.bottom);
        Log.d("FontMetrics", "descent :" + mFontMetrics.descent);
        Log.d("FontMetrics", "leading :" + mFontMetrics.leading);
        Log.d("FontMetrics", "top :" + mFontMetrics.top);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(TEXT, 0, Math.abs(mFontMetrics.top), mPaint);
        //完全显示不出来,因为baseline的Y轴坐标为0
//        canvas.drawText(TEXT, 0, 0, mPaint);
    }
}
