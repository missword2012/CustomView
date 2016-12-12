package demo.custom.fire.com.day03.weiget;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Locale;

/*
 *  项目名：  CustomView 
 *  包名：    demo.custom.fire.com.day03.weiget
 *  文件名:   PaintView
 *  创建者:   lsy
 *  创建时间:  2016/12/9 11:26
 *  描述：    主要测试Paint中的方法
 */
public class PaintView extends View {

    private Paint mPaint;

    public PaintView(Context context) {
        this(context,null);
    }

    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPaint();

    }

    private void initPaint() {

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);


        //获取上坡度
        mPaint.descent();
        //获取下坡度
        mPaint.ascent();



    }
}
