package demo.custom.fire.com.day03.weiget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.util.AttributeSet;
import android.view.View;

/*
 *  项目名：  CustomView 
 *  包名：    demo.custom.fire.com.day03.weiget
 *  文件名:   PathEffectView
 *  创建者:   lsy
 *  创建时间:  2016/12/9 13:48
 *  描述：    TODO
 */
public class PathEffectView extends View {

    private Paint mPaint;
    private Path mPath;
    private PathEffect[] mPathEffect;

    private float mPhase;// 偏移值

    public PathEffectView(Context context) {
        this(context, null);
    }

    public PathEffectView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPaint();

//        initEffect();

    }

    private void initEffect() {
        mPathEffect[0] = null;

        mPathEffect[1] = new CornerPathEffect(10);

        mPathEffect[2] = new DiscretePathEffect(3.0F, 5.0F);

        mPathEffect[3] = new DashPathEffect(new float[]{20, 10, 5, 10}, mPhase);

        Path path = new Path();
        path.addRect(0, 0, 8, 8, Path.Direction.CCW);

        mPathEffect[4] = new PathDashPathEffect(path, 12, mPhase, PathDashPathEffect.Style.ROTATE);

        mPathEffect[5] = new ComposePathEffect(mPathEffect[2], mPathEffect[4]);

        mPathEffect[6] = new SumPathEffect(mPathEffect[4], mPathEffect[3]);
    }

    private void initPaint() {

        //初始化画笔属性
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.DKGRAY);

        //实例化路径
        mPath = new Path();

        //设置路径起点
        mPath.moveTo(0, 0);

        //设置路径随机点
        for (int i = 0; i <= 30; i++) {
            mPath.lineTo(i * 35, (float) (Math.random() * 100));
        }

        //创建路径效果数组
        mPathEffect = new PathEffect[7];

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * 实例化效果
         */
//
        mPathEffect[0] = null;

        mPathEffect[1] = new CornerPathEffect(70);

        mPathEffect[2] = new DiscretePathEffect(10.0f, 5.0F);

        mPathEffect[3] = new DashPathEffect(new float[]{20, 200,10,100}, mPhase);

        Path path = new Path();

//        path.addRect(0, 0, 8, 8, Path.Direction.CCW);
        path.addCircle(0,0,3, Path.Direction.CCW);

        mPathEffect[4] = new PathDashPathEffect(path, 12, mPhase, PathDashPathEffect.Style.ROTATE);

        mPathEffect[5] = new ComposePathEffect(mPathEffect[2], mPathEffect[4]);

        mPathEffect[6] = new SumPathEffect(mPathEffect[4], mPathEffect[3]);


        //绘制路径
        for (int i = 0; i < mPathEffect.length; i++) {
            mPaint.setPathEffect(mPathEffect[i]);
            canvas.drawPath(mPath, mPaint);

            //每绘制一条将画布向下移动
            canvas.translate(0, 250);
        }

        //刷新偏移值实现动画效果
        mPhase += 1;
        invalidate();

    }
}
