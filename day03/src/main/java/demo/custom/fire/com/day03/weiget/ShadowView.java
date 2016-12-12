package demo.custom.fire.com.day03.weiget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import demo.custom.fire.com.day03.MeasureUtil;

/*
 *  项目名：  CustomView 
 *  包名：    demo.custom.fire.com.day03.weiget
 *  文件名:   ShadowView
 *  创建者:   lsy
 *  创建时间:  2016/12/9 17:24
 *  描述：    TODO
 */
public class ShadowView extends View {


    private static final int RECT_SIZE = 800;// 方形大小

    private Paint mPaint;
    private int left;
    private int top;
    private int right;
    private int bottom;

    public ShadowView(Context context) {
        super(context);
    }

    public ShadowView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //setShadowLayer 不支持HW(硬件加速)
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        initPaint();

        initRes(context);

    }


    private void initPaint() {

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL);

        mPaint.setShadowLayer(10, 3, 3, Color.DKGRAY);

    }


    private void initRes(Context context) {


          /*
         * 计算位图绘制时左上角的坐标使其位于屏幕中心
         */
        left = MeasureUtil.getScreenSize((Activity) context)[0] / 2 - RECT_SIZE / 2;
        top = MeasureUtil.getScreenSize((Activity) context)[1] / 2 - RECT_SIZE / 2;
        right = MeasureUtil.getScreenSize((Activity) context)[0] / 2 + RECT_SIZE / 2;
        bottom = MeasureUtil.getScreenSize((Activity) context)[1] / 2 + RECT_SIZE / 2;


    }

}
