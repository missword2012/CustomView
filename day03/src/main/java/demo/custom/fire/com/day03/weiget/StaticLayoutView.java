package demo.custom.fire.com.day03.weiget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/*
 *  项目名：  CustomView 
 *  包名：    demo.custom.fire.com.day03.weiget
 *  文件名:   StaticLayoutView
 *  创建者:   lsy
 *  创建时间:  2016/12/8 17:36
 *  描述：    TODO
 */
public class StaticLayoutView extends View {

    private static final String TEXT = "This is used by widgets to control text layout. You should not need to use this class directly unless you are implementing your own widget or custom display object, or would be tempted to call Canvas.drawText() directly.";
    private TextPaint mTextPaint;
    private StaticLayout mStaticLayout;

    public StaticLayoutView(Context context) {
        this(context, null);
    }

    public StaticLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPaint();

    }

    private void initPaint() {

        //实例化画笔
        mTextPaint = new TextPaint();
        mTextPaint.setTextSize(50);
        mTextPaint.setColor(Color.BLACK);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mStaticLayout = new StaticLayout(TEXT, mTextPaint, canvas.getWidth(), Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, false);
        //居中
//        mStaticLayout = new StaticLayout(TEXT, mTextPaint, 200, Layout.Alignment.ALIGN_CENTER, 1.0F, 0.0F, false);
        //右对齐
//        mStaticLayout = new StaticLayout(TEXT, mTextPaint, 200, Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, false);
        //
        mStaticLayout = new StaticLayout(TEXT, mTextPaint, 200, Layout.Alignment.ALIGN_OPPOSITE, 1.0F, 0.0F, false);
        mStaticLayout.draw(canvas);

//        canvas.restore();

        //直接restore()会崩溃 先调用save
        //java.lang.IllegalStateException: Underflow in restore - more restores than saves
        canvas.save();
        canvas.restore();

    }
}
