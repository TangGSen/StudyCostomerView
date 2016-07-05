package sen.com.customerviewtest.test1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import sen.com.customerviewtest.MeasureUtil;

/**
 * Created by Administrator on 2016/6/7.
 */

public class CustomerView02 extends View  {
    private Paint mPaint;
    private Context mContext;
    private int radiu = 200;//圆形半径

    public CustomerView02(Context context) {
        this(context, null);


    }

    public CustomerView02(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);

        //描边
        mPaint.setStyle(Paint.Style.FILL);

        // 设置画笔颜色为浅灰色
        mPaint.setColor(Color.argb(255, 255, 128, 103));

        //增加颜色矩阵 ,1 就是不变颜色值
        ColorMatrix colorMatrix  = new ColorMatrix(new float[]{
                0.5f,0,0,0,0,
                0,0.5f,0,0,0,
                0,0,0.5f,0,0,
                0,0,0,1,0,
        });
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));

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


}
