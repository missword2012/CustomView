package demo.custom.fire.com.day04.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/*
 *  项目名：  CustomView 
 *  包名：    demo.custom.fire.com.day04.view
 *  文件名:   MultiCircleView
 *  创建者:   lsy
 *  创建时间:  2016/12/14 14:26
 *  描述：    TODO
 */
public class MultiCircleView extends View {

    private static final float STROKE_WIDTH = 1F / 256F,//描边的宽度
            SPACE = 1F / 64F,
            LINE_LENGTH = 3F / 32F,  //线段长度
            CIRCLE_LARGER_RADIU = 3F / 32F, //大圆半径
            CIRCLE_SMALL_RADIU = 5F / 64F,
            ARC_RADIU = 1F / 8F, //圆弧半径
            ARC_TEXT_RADIU = 5F / 32F;//弧环绕文字半径
    private Paint mStrokePaint;
    private int size;   //控件的边长
    private float mStrokeWidth;
    private float largeCircleRadiu;
    private int ccX;
    private float ccY;
    private float lineLength;// 线段长度
    private float mSmallCircleRadiu; //小圆半径
    private float space; //大小圆两端端点间隔
    private Paint mPointPaint;
    private Paint textPaint;
    private float textOffsetY;
    private Paint arcPaint;


    public MultiCircleView(Context context) {
        this(context, null);
    }

    public MultiCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        /**
         * 初始化描边画笔
         */
        mStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setColor(Color.WHITE);
        mStrokePaint.setStrokeCap(Paint.Cap.ROUND);

        //描点的画笔
//        mPointPaint = new Paint();
//        mPointPaint.setColor(Color.BLACK);

        //初始化绘制文字的画笔

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.SUBPIXEL_TEXT_FLAG);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(20);
        textPaint.setTextAlign(Paint.Align.CENTER);

        textOffsetY = (textPaint.descent() + textPaint.ascent()) / 2;

        //绘制弧形的画笔
        arcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);


    }


    /**
     * 强制宽高一致
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //获取控件边长
        size = w;

        //参数计算
        calculation();

    }

    private void calculation() {
        //计算描边宽度
        mStrokeWidth = STROKE_WIDTH * size;

        //计算大圆半径
        largeCircleRadiu = size * CIRCLE_LARGER_RADIU;

        //计算小圆半径
        mSmallCircleRadiu = size * CIRCLE_SMALL_RADIU;

        //计算线段长度
        lineLength = size * LINE_LENGTH;

        //计算大小圆线段两端端点间隔
        space = size * SPACE;

        //计算中心圆坐标
        ccX = size / 2;
        ccY = size / 2 + size * CIRCLE_LARGER_RADIU;

        //设置参数
        setPara();

    }

    private void setPara() {
        //设置描边的宽度
        mStrokePaint.setStrokeWidth(mStrokeWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //绘制背景
        canvas.drawColor(0xFFF29B76);

        //绘制中心圆
        canvas.drawCircle(ccX, ccY, largeCircleRadiu, mStrokePaint);
        canvas.drawText("HelloWorld", ccX, ccY - textOffsetY, textPaint);

        //绘制左上方
        drawTopLeft(canvas);

        //绘制右上方
        drawTopRight(canvas);

        //绘制左下方
        drawBottomLeft(canvas);

        //绘制正下方
        drawBottom(canvas);

        //绘制右下方
        drawBottomRight(canvas);


    }

    private void drawBottomRight(Canvas canvas) {

        canvas.save();

        canvas.translate(ccX, ccY);
        canvas.rotate(100);

        canvas.drawLine(0, -largeCircleRadiu - space, 0, -lineLength * 2 - space, mStrokePaint);
        canvas.drawCircle(0, -lineLength * 2 - mSmallCircleRadiu - space * 2, mSmallCircleRadiu, mStrokePaint);

        canvas.save();

        canvas.translate(0, -lineLength * 2 - mSmallCircleRadiu - space * 2);
        canvas.rotate(-100);
        canvas.drawText("Vibrators", 0, 0, textPaint);

//        canvas.drawText("Vibrators", 0, -lineLength * 2 - mSmallCircleRadiu - space * 2 - textOffsetY, textPaint);
        canvas.restore();

        canvas.restore();

    }

    private void drawBottom(Canvas canvas) {

        canvas.save();

        canvas.translate(ccX, ccY);
        canvas.rotate(180);

        canvas.drawLine(0, -largeCircleRadiu - space, 0, -lineLength * 2 - space, mStrokePaint);
        canvas.drawCircle(0, -lineLength * 2 - mSmallCircleRadiu - space * 2, mSmallCircleRadiu, mStrokePaint);

        canvas.restore();

        canvas.drawText("Cucumber", ccX, ccY + lineLength + mSmallCircleRadiu
                + largeCircleRadiu + space * 2 + mStrokeWidth * 2, textPaint);


    }

    private void drawBottomLeft(Canvas canvas) {

        canvas.save();

        canvas.translate(ccX, ccY);
        canvas.rotate(-100);

        canvas.drawLine(0, -largeCircleRadiu - space, 0, -lineLength * 2 - space, mStrokePaint);
        canvas.drawCircle(0, -lineLength * 2 - mSmallCircleRadiu - space * 2, mSmallCircleRadiu, mStrokePaint);

        canvas.save();

        canvas.translate(0, -lineLength * 2 - mSmallCircleRadiu - space * 2);
        canvas.rotate(100);
        canvas.drawText("Banana", 0, 0, textPaint);
//        canvas.drawText("Banana", 0, -lineLength * 2 - mSmallCircleRadiu - space * 2 - textOffsetY, textPaint);

        canvas.restore();

        canvas.restore();


    }

    private void drawTopRight(Canvas canvas) {
        float circleY = -lineLength * 3;

        //锁定画布
        canvas.save();

        canvas.translate(ccX, ccY);
        canvas.rotate(30);

        canvas.drawLine(0, -largeCircleRadiu, 0, -lineLength * 2, mStrokePaint);
        canvas.drawCircle(0, -lineLength * 3, largeCircleRadiu, mStrokePaint);



        canvas.save();
        canvas.translate(0,-lineLength * 3 );
        canvas.rotate(-30);
        canvas.drawText("Tropical", 0, 0, textPaint);
//        canvas.drawText("Tropical", 0, circleY - textOffsetY, textPaint);

        canvas.restore();

        //画右上角弧形
        drawTopRightArc(canvas, circleY);


        canvas.restore();

    }

    private void drawTopRightArc(Canvas canvas, float circleY) {

        canvas.save();

        canvas.translate(0, circleY);
        canvas.rotate(-30);

        float arcRadiu = size * ARC_RADIU;

        RectF oval = new RectF(-arcRadiu, -arcRadiu, arcRadiu, arcRadiu);

        arcPaint.setStyle(Paint.Style.FILL);
        arcPaint.setColor(0x55EC6941);
        canvas.drawArc(oval, -22.5F, -135, true, arcPaint);
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setColor(Color.WHITE);
        canvas.drawArc(oval, -22.5F, -135, false, arcPaint);

        float arcTextRadiu = size * ARC_TEXT_RADIU;

        canvas.save();
        //将画布选择到扇形的左端
        canvas.rotate(-135F / 2F);

        for (float i = 0; i < 5 * 33.75F; i += 33.75F) {
            canvas.save();
            canvas.rotate(i);

            canvas.drawText("Fire", 0, -arcTextRadiu, textPaint);
            canvas.restore();
        }

        canvas.restore();

        canvas.restore();
    }

    private void drawTopLeft(Canvas canvas) {

        //锁定画布
        canvas.save();


//        //画布平移和旋转
        canvas.translate(ccX, ccY);
//        Log.d("MultiCircleView", "ccX : " + ccX + "  ccY :" + ccY);
        canvas.rotate(-30);

        canvas.drawLine(0, -largeCircleRadiu, 0, -lineLength * 2, mStrokePaint);
        canvas.drawCircle(0, -lineLength * 3, largeCircleRadiu, mStrokePaint);

        //绘制正常文字
        canvas.save();
        canvas.translate(0, -lineLength * 3);
        canvas.rotate(30);
        canvas.drawText("Apple", 0, 0, textPaint);
//        canvas.drawText("Apple", 0, -lineLength * 3 - textOffsetY, textPaint);
        canvas.restore();

        canvas.drawLine(0, -largeCircleRadiu * 4, 0, -lineLength * 5, mStrokePaint);
        canvas.drawCircle(0, -lineLength * 6, largeCircleRadiu, mStrokePaint);

        canvas.save();
        canvas.translate(0, -lineLength * 6);
        canvas.rotate(30);

        canvas.drawText("Orange", 0, 0, textPaint);
//        canvas.drawText("Orange", 0, -lineLength * 6 - textOffsetY, textPaint);
        canvas.restore();
        //释放画布
        canvas.restore();

    }
}
