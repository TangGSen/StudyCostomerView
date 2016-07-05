package sen.com.customerviewtest.test8;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/7/4.
 */

public class MutilCircleView extends View {
    // 描边宽度占比
    private static final float STROKE_WIDTH = 1f / 256f,
            CRICLE_LARGER_RADIU = 4F / 44F,// 大圆半径
            CRICLE_SMALL_RADIU = 5F / 64F,// 小圆半径
            ARC_RADIU = 1F / 8F,// 弧半径
            ARC_TEXT_RADIU = 5F / 32F;// 弧围绕文字半径 ;


    private Paint mStorkePaint;
    private float strokeWidth;// 描边宽度
    private int size;
    private float largeCricleRadiu;
    private int ccX;
    private float ccY;
    private TextPaint mTextPaint;

    public MutilCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPaint(context);
    }

    private void initPaint(Context context) {
        mStorkePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mStorkePaint.setStyle(Paint.Style.STROKE);
        mStorkePaint.setColor(Color.WHITE);
        mStorkePaint.setStrokeCap(Paint.Cap.ROUND);

        //初始化文字画笔
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG |Paint.DITHER_FLAG | Paint.SUBPIXEL_TEXT_FLAG);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(26);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //控件地边长
        size = w;
        //参数的计算
        calculation();

    }

    private void calculation() {
        // 计算描边宽度
        strokeWidth = STROKE_WIDTH * size;

        // 计算大圆半径
        largeCricleRadiu = size * CRICLE_LARGER_RADIU;

        // 计算中心圆圆心坐标，这个数据目的是为了这么大的圆能显示全部，自己调整的
        ccX = size *2/ 3-50;
        ccY = size * 2/ 3 -40+ size * CRICLE_LARGER_RADIU;


        // 设置参数
        setPara();
    }

    private void setPara() {
        // 设置描边宽度
        mStorkePaint.setStrokeWidth(strokeWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(0xFFF29B76);
        canvas.drawCircle(ccX, ccY, largeCricleRadiu, mStorkePaint);
        canvas.drawText("JseoniY",ccX ,ccY,mTextPaint);
//把360 平均分成5分，每份72 度
        drawLineCircle(canvas,-78,false, new String[]{"JseonttT"});
        drawLineCircle(canvas,-6,false, new String[]{"Jseondd"});
        drawLineCircle(canvas,66,false, new String[]{"JseonionTT"});
        drawLineCircle(canvas,138,false, new String[]{"JseonYY"});
        drawLineCircle(canvas,210,true, new String[]{"Jseon","senTang"});


    }

    private void drawLineCircle(Canvas canvas, int r, boolean isMore, String[] strings) {
        //锁定画布
        canvas.save();
        canvas.translate(ccX, ccY);
        canvas.rotate(r);
        canvas.drawLine(largeCricleRadiu, 0, largeCricleRadiu * 2, 0, mStorkePaint);
        canvas.drawCircle(largeCricleRadiu * 3, 0, largeCricleRadiu, mStorkePaint);

        
        canvas.drawText(strings[0],largeCricleRadiu * 3,0,mTextPaint);
        if (isMore){
            canvas.drawLine(largeCricleRadiu*4, 0, largeCricleRadiu * 5, 0, mStorkePaint);
            canvas.drawCircle(largeCricleRadiu * 6, 0, largeCricleRadiu, mStorkePaint);
            canvas.drawText(strings[1],largeCricleRadiu * 6,0,mTextPaint);
        }

        // 释放画布
        canvas.restore();
    }






}
