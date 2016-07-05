package sen.com.customerviewtest.test7;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

import sen.com.customerviewtest.MeasureUtil;
import sen.com.customerviewtest.R;

/**
 * Created by Administrator on 2016/6/24.
 */

public class MyInageViewChange extends ImageView {
    private Context mContext;
    private Matrix mCurrentMatrix, mSavaMatrix; //当前和保存
    private float[] mPreEventPoints;//上一次各触点的集合

    private PointF mStartPointF, mMidPointF;

    private float mPreMove = 1F; //上次移动的距离
    private float mSavaRoate = 0F;//保存上次的角度值
    private float mCurrentRoate = 0F;//保存当前的角度值

    //触摸模式
    private static final int MODE_NONE = 0x00121;
    private static final int MODE_DRAG = 0x00122;
    private static final int MODE_ZOOM = 0x00123;

    private int mCurrentMode;


    public MyInageViewChange(Context context) {
        super(context);

    }

    public MyInageViewChange(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.mContext = context;

        init();
    }

    private void init() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.test);
        bitmap = Bitmap.createScaledBitmap(bitmap, MeasureUtil.getScreenSize((Activity) mContext)[0], MeasureUtil.getScreenSize((Activity) mContext)[1], true);
        setImageBitmap(bitmap);

        mCurrentMatrix = new Matrix();
        mSavaMatrix = new Matrix();

        mStartPointF = new PointF();
        mMidPointF = new PointF();
        mCurrentMode = MODE_NONE;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:  //单点触摸
                mSavaMatrix.set(mCurrentMatrix);
                mStartPointF.set(event.getX(), event.getY());
                mCurrentMode = MODE_DRAG;
                mPreEventPoints = null;
                break;
            //多点触碰
            case MotionEvent.ACTION_POINTER_DOWN:
                mPreMove = calSpaing(event);
                if (mPreMove > 10F) {
                    mSavaMatrix.set(mCurrentMatrix);
                    calMidPoint(mMidPointF, event);
                    mCurrentMode = MODE_ZOOM;
                }
                mPreEventPoints = new float[4];
                mPreEventPoints[0] = event.getX(0);
                mPreEventPoints[1] = event.getX(1);
                mPreEventPoints[2] = event.getY(0);
                mPreEventPoints[3] = event.getY(1);

                mSavaRoate = calRotation(event);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:// 第二个点离开屏幕时
                mCurrentMode = MODE_NONE;
                mPreEventPoints = null;
                break;
            case MotionEvent.ACTION_MOVE:
                 /*
             * 单点触控拖拽平移
             */
                if (mCurrentMode == MODE_DRAG) {
                    mCurrentMatrix.set(mSavaMatrix);
                    float dx = event.getX() - mStartPointF.x;
                    float dy = event.getY() - mStartPointF.y;
                    mCurrentMatrix.postTranslate(dx, dy);
                }else if (mCurrentMode ==MODE_ZOOM && event.getPointerCount() == 2){
                    Log.e("sen","移动");
                    float currentMove = calSpaing(event);
                    mCurrentMatrix.set(mSavaMatrix);

                    /*
                 * 指尖移动距离大于10F缩放
                 */
                    if (currentMove > 10F) {
                        Log.e("sen","伸缩");
                        float scale = currentMove / mPreMove;
                        mCurrentMatrix.postScale(scale, scale, mMidPointF.x, mMidPointF.y);
                    }
                    //保持两点旋转
                    if (mPreEventPoints!=null){
                        Log.e("sen","旋转");
                        mCurrentRoate = calRotation(event);
                        float dr = mCurrentRoate - mSavaRoate ;
                        mCurrentMatrix.postRotate(dr ,getMeasuredWidth()/2,getMeasuredHeight()/2);
                    }
                }
                break;
        }

        setImageMatrix(mCurrentMatrix);
        return true;
    }

    //计算旋转角度
    private float calRotation(MotionEvent event) {
        double dx = event.getX(0) - event.getX(1);
        double dy = event.getY(0) - event.getY(1);
        double rudis = Math.atan2(dy, dx);
        return (float) Math.toDegrees(rudis);
    }

    //计算两触点的坐标
    private void calMidPoint(PointF mMidPointF, MotionEvent event) {
        float dx = event.getX(0) + event.getX(1);
        float dy = event.getY(0) + event.getY(1);
        mMidPointF.set(dx / 2, dy / 2);
    }

    //计算两个触点的距离
    private float calSpaing(MotionEvent event) {
        float dx = event.getX(0) - event.getX(1);
        float dy = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

}
