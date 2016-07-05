package sen.com.customerviewtest.test1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.AttributeSet;
import android.view.View;

import sen.com.customerviewtest.MeasureUtil;
import sen.com.customerviewtest.R;

/**
 * Created by Administrator on 2016/6/7.
 */

public class CustomerViewBitmap extends View  {
    private Paint mPaint;
    private Context mContext;
    private Bitmap mBitmap;
    boolean isClick;
    private int x,y;// 位图绘制时左上角的起点坐标
    public CustomerViewBitmap(Context context) {
        this(context, null);


    }

    public CustomerViewBitmap(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        //初始化画笔
        initPaint();

        //初始化资源
        initRes(context);



    }

    private void initRes(Context context) {
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.test);

         /*
         * 计算位图绘制时左上角的坐标使其位于屏幕中心
         * 屏幕坐标x轴向左偏移位图一半的宽度
         * 屏幕坐标y轴向上偏移位图一半的高度
         */
        x = MeasureUtil.getScreenSize((Activity) mContext)[0] / 2 - mBitmap.getWidth() / 2;
        y = MeasureUtil.getScreenSize((Activity) mContext)[1] / 2 - mBitmap.getHeight() / 2;
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);




        //增加颜色矩阵 ,1 就是不变颜色值
        ColorMatrix colorMatrix  = new ColorMatrix(new float[]{
                -1, 0, 0, 1, 1,
                0, -1, 0, 1, 1,
                0, 0, -1, 1, 1,
                0, 0, 0, 1, 0,
        });
        // 设置颜色过滤
      //  mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));

       // mPaint.setColorFilter(new LightingColorFilter(0xFFFF00FF, 0x00000000));

        // 设置颜色过滤
        mPaint.setColorFilter(new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.DARKEN));



    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       canvas.drawBitmap(mBitmap,x,y,mPaint);
    }


}
