package sen.com.customerviewtest.test2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2016/6/7.
 */
public class CustomerViewText extends View {
    private Paint.FontMetrics mFontMetrics;// 文本测量对象
    private Paint mPaint;
    private static final String TEXT = "apSen森ξτβбп\nшㄎㄊěǔぬも┰┠№＠↓";


    public CustomerViewText(Context context) {
        this(context, null);


    }

    public CustomerViewText(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 实例化画笔并设置抗锯齿
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(50);
        mPaint.setColor(Color.BLACK);
        mFontMetrics = mPaint.getFontMetrics();

        Log.e("sen", "ascent：" + mFontMetrics.ascent);
        Log.e("sen", "top：" + mFontMetrics.top);
        Log.e("sen", "leading：" + mFontMetrics.leading);
        Log.e("sen", "descent：" + mFontMetrics.descent);
        Log.e("sen", "bottom：" + mFontMetrics.bottom);


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //把画布设置黑色，方便观察
        canvas.drawText(TEXT, 0, Math.abs(mFontMetrics.top), mPaint);


    }


}
