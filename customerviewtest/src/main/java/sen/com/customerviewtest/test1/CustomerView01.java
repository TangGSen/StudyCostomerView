package sen.com.customerviewtest.test1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import sen.com.customerviewtest.MeasureUtil;

/**
 * Created by Administrator on 2016/6/7.
 */

public class CustomerView01 extends View implements Runnable {
    private Paint mPaint;
    private Context mContext;
    private int radiu;//圆形半径

    public CustomerView01(Context context) {
        super(context);


    }

    public CustomerView01(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);

        //描边
        mPaint.setStyle(Paint.Style.STROKE);

        // 设置画笔颜色为浅灰色
        mPaint.setColor(Color.BLACK);

    /*
     * 设置描边的粗细，单位：像素px
     * 注意：当setStrokeWidth(0)的时候描边宽度并不为0而是只占一个像素
     */
        mPaint.setStrokeWidth(8);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制圆环
        canvas.drawCircle(MeasureUtil.getScreenSize((Activity) mContext)[0] / 2, MeasureUtil.getScreenSize((Activity) mContext)[1] / 2, radiu, mPaint);
    }

    @Override
    public void run() {
        /*
     * 确保线程不断执行不断刷新界面
     */
        while (true) {
            try {
            /*
             * 如果半径小于200则自加否则大于200后重置半径值以实现往复
             */

                if (radiu < 200) {
                    radiu += 10;
                } else {
                    radiu = 0;
                }
// 刷新View
                postInvalidate();
                // 每执行一次暂停40毫秒
                Thread.sleep(100);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
