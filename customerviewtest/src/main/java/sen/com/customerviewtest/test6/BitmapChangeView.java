package sen.com.customerviewtest.test6;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import sen.com.customerviewtest.R;
import sen.com.customerviewtest.MeasureUtil;

/**
 * Created by Administrator on 2016/6/22.
 */

public class BitmapChangeView extends View {
    private Bitmap mBitmap;
    private PorterDuffXfermode mXfermode;
    private int screenW ,screenH ,x ,y;
    private Paint mBitmapPaint,mBitmapPaint2;

    public BitmapChangeView(Context context) {
        super(context);
    }

    public BitmapChangeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initRes(context);

        initPaint(context);
    }

    private void initPaint(Context context) {
        // 实例化画笔
        mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitmapPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);

        // 去饱和、提亮、色相矫正
        mBitmapPaint.setColorFilter(new ColorMatrixColorFilter(new float[] { 0.8587F, 0.2940F, -0.0927F, 0, 6.79F, 0.0821F, 0.9145F, 0.0634F, 0, 6.79F, 0.2019F, 0.1097F, 0.7483F, 0, 6.79F, 0, 0, 0, 1, 0 }));

    }

    private void initRes(Context context) {
        // 获取位图
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.test3);

        // 实例化混合模式
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SCREEN);

        screenW = MeasureUtil.getScreenSize((Activity) context)[0];
        screenH = MeasureUtil.getScreenSize((Activity) context)[1];

        x = screenW / 2 - mBitmap.getWidth() / 2;
        y = screenH / 2 - mBitmap.getHeight() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);


        // 新建图层
        int sc = canvas.saveLayer(x, y, x + mBitmap.getWidth(), y + mBitmap.getHeight(), null, Canvas.ALL_SAVE_FLAG);

        // 绘制混合颜色
        canvas.drawColor(0xcc1c093e);

        // 设置混合模式
        mBitmapPaint.setXfermode(mXfermode);

        // 绘制位图
        canvas.drawBitmap(mBitmap, x, y, mBitmapPaint);

        // 还原混合模式
        mBitmapPaint.setXfermode(null);

        // 还原画布
        canvas.restoreToCount(sc);

        canvas.drawBitmap(mBitmap,100,100,mBitmapPaint2);
    }
}
