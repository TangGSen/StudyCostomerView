package sen.com.customerviewtest.test1;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import sen.com.customerviewtest.MeasureUtil;

/**
 * Created by Administrator on 2016/6/7.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class CustomerViewPorterDuff01 extends View {
    private Paint mPaint;
    private PorterDuffBO porterDuffBO;
    private PorterDuffXfermode porterDuffXfermode;
    private static final int RECT_SIZE_SMALL = 400;// 左右上方示例渐变正方形的尺寸大小
    private static final int RECT_SIZE_BIG = 800;// 中间测试渐变正方形的尺寸大小

    private static final PorterDuff.Mode MODE = PorterDuff.Mode.SRC_IN;
    private int rectX, rectY, samll_st, small_sl, small_dl, small_dt;
    private int screenW;
    private int screenH;

    public CustomerViewPorterDuff01(Context context) {
        this(context, null);


    }

    public CustomerViewPorterDuff01(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 实例化画笔并设置抗锯齿
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 实例化业务对象
        porterDuffBO = new PorterDuffBO();

        //实例化混合模式
        porterDuffXfermode = new PorterDuffXfermode(MODE);

        //计算坐标

        cucle(context);


    }

    private void cucle(Context context) {
        int screenSize[] = MeasureUtil.getScreenSize((Activity) context);
        screenW = screenSize[0];
        screenH = screenSize[1];

        small_sl = 0;
        samll_st = 0;

        small_dl = screenW - RECT_SIZE_SMALL;
        small_dt = 0;

        // 计算中间方正方形原点坐标
        rectX = screenW / 2 - RECT_SIZE_BIG / 2;
        rectY = RECT_SIZE_SMALL + (screenH - RECT_SIZE_SMALL) / 2 - RECT_SIZE_BIG / 2;

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //把画布设置黑色，方便观察
        canvas.drawColor(Color.BLACK);

        // 设置业务对象尺寸值计算生成左右上方的渐变方形
        porterDuffBO.setSize(RECT_SIZE_SMALL);

        /*
         * 画出左右上方两个正方形
         * 其中左边的的为src右边的为dis
         */
        canvas.drawBitmap(porterDuffBO.initSrcBitmap(), small_sl, samll_st, mPaint);
        canvas.drawBitmap(porterDuffBO.initDisBitmap(), small_dl, small_dt, mPaint);

         /* 
         * 将绘制操作保存到新的图层（更官方的说法应该是离屏缓存）我们将在1/3中学习到Canvas的全部用法这里就先follow me 
         */
        int sc = canvas.saveLayer(0, 0, screenW, screenH, null, Canvas.ALL_SAVE_FLAG);
        // 重新设置业务对象尺寸值计算生成中间的渐变方形
        porterDuffBO.setSize(RECT_SIZE_BIG);

        // 先绘制dis目标图
        canvas.drawBitmap(porterDuffBO.initDisBitmap(), rectX, rectY, mPaint);

        // 设置混合模式
       mPaint.setXfermode(porterDuffXfermode);
        // 再绘制src源图
        canvas.drawBitmap(porterDuffBO.initSrcBitmap(), rectX, rectY, mPaint);
        // 还原混合模式
        mPaint.setXfermode(null);

        // 还原画布
        canvas.restoreToCount(sc);
    }


}
