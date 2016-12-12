package demo.custom.fire.com.day03.weiget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.StaticLayout;
import android.util.AttributeSet;
import android.view.View;

/*
 *  项目名：  CustomView 
 *  包名：    demo.custom.fire.com.day03.weiget
 *  文件名:   FontView2
 *  创建者:   lsy
 *  创建时间:  2016/12/8 16:30
 *  描述：    TODO
 */
public class FontView2 extends View {

    private Paint textPaint;
    private Paint linePaint;

    private static final String TEXT = "ap爱哥ξτβбпшㄎㄊ大魔王";
    private int baseX;
    private int baseY;

    public FontView2(Context context) {
        this(context, null);
    }

    public FontView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        //初始化画笔
        initPaint();
    }

    private void initPaint() {
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(40);
        textPaint.setColor(Color.BLACK);

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(1);
        linePaint.setColor(Color.RED);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //计算baseline绘制的起点x轴坐标
        baseX = (int) (canvas.getWidth() / 2 - textPaint.measureText(TEXT) / 2);

        //计算baselineY轴坐标
        baseY = (int) (canvas.getHeight() / 2 - (textPaint.descent() + textPaint.ascent() / 2));

        canvas.drawText(TEXT, baseX, baseY, textPaint);

        //画布中心绘制线
        canvas.drawLine(0, canvas.getHeight() / 2, canvas.getWidth(), canvas.getHeight()/2, linePaint);

    }
}
