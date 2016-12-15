package demo.custom.fire.com.day04.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import demo.custom.fire.com.day04.R;

/*
 *  项目名：  CustomView 
 *  包名：    demo.custom.fire.com.day04.view
 *  文件名:   MatrixImageView
 *  创建者:   lsy
 *  创建时间:  2016/12/14 9:46
 *  描述：    TODO
 */
public class MatrixImageView extends ImageView {

    private static final int MODE_NONE = 0x00123;// 默认的触摸模式
    private static final int MODE_DRAG = 0x00321;// 拖拽模式
    private static final int MODE_ZOOM = 0x00132;// 缩放or旋转模式

    private Context mContext;
    private Matrix currentMatrix;
    private Matrix savedMatrix;
    private PointF start;
    private PointF mid;
    private int mode;
    private int screenW;
    private int screenH;

    private float[] preEventCoor;// 上一次各触摸点的坐标集合
    private float preMove = 1F;// 上一次手指移动的距离
    private float saveRotate = 0F;// 保存了的角度值
    private float rotate = 0F;// 旋转的角度


    public MatrixImageView(Context context) {
        this(context, null);
    }

    public MatrixImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        init();

    }

    private void init() {

        //初始化对象
        currentMatrix = new Matrix();
        savedMatrix = new Matrix();
        start = new PointF();
        mid = new PointF();

        //初始化模式
        mode = MODE_NONE;

        //获取屏幕数据
        int[] screenSize = MeasureUtil.getScreenSize((Activity) mContext);
        screenW = screenSize[0];
        screenH = screenSize[1];


        //设置图片资源
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.name);
        //缩小bitmap到合适尺寸
        bitmap.createScaledBitmap(bitmap, screenW, screenH, true);

        setImageBitmap(bitmap);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //第一个点按下
                savedMatrix.set(currentMatrix);
                start.set(event.getX(), event.getY());
                mode = MODE_DRAG;
                preEventCoor = null;

                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                preMove = calSpacing(event);
                if (preMove > 10F) {
                    savedMatrix.set(currentMatrix);
                    calMidPoint(mid, event);
                    mode = MODE_ZOOM;
                }

                preEventCoor = new float[4];
                preEventCoor[0] = event.getX(0);
                preEventCoor[1] = event.getX(1);
                preEventCoor[2] = event.getY(0);
                preEventCoor[3] = event.getY(1);
                saveRotate = calRotation(event);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                mode = MODE_NONE;
                preEventCoor = null;
                break;
            case MotionEvent.ACTION_MOVE:
                //触摸点移动

                /**
                 * 单调拖拽平移
                 */
                if (mode == MODE_DRAG) {
                    currentMatrix.set(savedMatrix);
                    float dx = event.getX() - start.x;
                    float dy = event.getY() - start.y;
                    currentMatrix.postTranslate(dx, dy);
                } else if (mode == MODE_ZOOM && event.getPointerCount() == 2) {
                    /**
                     * 两点拖放旋转
                     */
                    float currentMove = calSpacing(event);
                    currentMatrix.set(savedMatrix);

                    /**
                     * 指尖距离大于10F进行缩放
                     */
                    if (currentMove > 3F) {
                        float scale = currentMove / preMove;
                        currentMatrix.postScale(scale, scale, mid.x, mid.y);
                    }

                    /**
                     * 保持两点旋转
                     */
                    if (preEventCoor != null) {
                        float rotate = calRotation(event);
                        float r = rotate - saveRotate;
                        currentMatrix.postRotate(r, getMeasuredWidth() / 2, getMeasuredHeight() / 2);
                    }
                }
                break;

        }
        setImageMatrix(currentMatrix);
        return true;
    }

    /**
     * 计算旋转的角度
     *
     * @param event
     * @return
     */
    private float calRotation(MotionEvent event) {
        double deltaX = event.getX(0) - event.getX(1);
        double deltaY = event.getY(0) - event.getY(1);
        double radius = Math.atan2(deltaY, deltaX);
        return (float) Math.toDegrees(radius);

    }

    /**
     * 计算两点的中点坐标
     *
     * @param point
     * @param event
     */
    private void calMidPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        point.set(x / 2, y / 2);
    }

    /**
     * 计算两点的触摸距离
     *
     * @param event
     * @return
     */
    private float calSpacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }
}
