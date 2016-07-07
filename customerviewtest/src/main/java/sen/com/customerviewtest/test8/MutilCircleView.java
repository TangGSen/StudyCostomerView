package sen.com.customerviewtest.test8;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
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
    private float textOffsetY;
    private Paint mArcPaint;

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
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.SUBPIXEL_TEXT_FLAG);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(26);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        textOffsetY = (mTextPaint.descent() + mTextPaint.ascent()) / 2;

        //弧形画笔
        mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

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
        ccX = size * 2 / 3 - 50;
        ccY = size * 2 / 3 - 40 + size * CRICLE_LARGER_RADIU;


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
        canvas.drawText("JseoniY", ccX, ccY - textOffsetY, mTextPaint);
//把360 平均分成5分，每份72 度
        drawLineCircle(canvas, -78, false, new String[]{"JseonttT"}, true);
        //在这个画弧形
        drawLineCircle(canvas, -6, false, new String[]{"Jseondd"}, false);
        drawLineCircle(canvas, 66, false, new String[]{"JseonionTT"}, false);
        drawLineCircle(canvas, 138, false, new String[]{"JseonYY"}, false);
        drawLineCircle(canvas, 210, true, new String[]{"Jseon", "senTang"}, false);


    }

    private void drawLineCircle(Canvas canvas, int r, boolean isMore, String[] strings, boolean isDrawArc) {
        //锁定画布
        canvas.save();
        canvas.translate(ccX, ccY);
        canvas.rotate(r);
        canvas.drawLine(largeCricleRadiu, 0, largeCricleRadiu * 2, 0, mStorkePaint);
        canvas.drawCircle(largeCricleRadiu * 3, 0, largeCricleRadiu, mStorkePaint);

        canvas.save();
        canvas.translate(largeCricleRadiu * 3, 0);
        canvas.rotate(-r);
        canvas.drawText(strings[0], 0, 0 - textOffsetY, mTextPaint);
        canvas.restore();
        if (isMore) {
            canvas.drawLine(largeCricleRadiu * 4, 0, largeCricleRadiu * 5, 0, mStorkePaint);
            canvas.drawCircle(largeCricleRadiu * 6, 0, largeCricleRadiu, mStorkePaint);
            //为了纠正文字水平
            canvas.save();
            canvas.translate(largeCricleRadiu * 6, 0);
            canvas.rotate(-r);
            canvas.drawText(strings[1], 0, 0 - textOffsetY, mTextPaint);
            canvas.restore();
        }
        if (isDrawArc) {
            //弧形的圆心就是这个圆心
            drawArc(canvas, largeCricleRadiu * 3, r);
        }

        // 释放画布
        canvas.restore();
    }

    private void drawArc(Canvas canvas, float arcX, float r) {
        canvas.save();
        //把圆心作为起点
        canvas.translate(arcX, 0);

        canvas.rotate(-r);

        float arcRudic = size * ARC_RADIU;
        //这个 弧形在这个矩形内
        RectF oval = new RectF(-arcRudic, -arcRudic, arcRudic, arcRudic);

        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeWidth(3.0f);
        mArcPaint.setColor(Color.WHITE);
        // 第四个参数是true的时候画扇形，是false的时候画弧线
        canvas.drawArc(oval, -30, -120, false, mArcPaint);

        mArcPaint.setStyle(Paint.Style.FILL);
        mArcPaint.setColor(0x55EC6941);
        // 第四个参数是true的时候画扇形，是false的时候画弧线
        canvas.drawArc(oval, -30, -120, true, mArcPaint);

        //再画弧形上的字体,等分五分，那么每30度 画文字
        canvas.save();
        //把画布旋转到最左
        canvas.rotate(-120 / 2);
        float arcTextRadiu = size * ARC_TEXT_RADIU;

        for (int i = 0; i < 5; i++) {
            canvas.save();
            canvas.rotate(i * 30);
//            canvas.drawText("Jsen", 0, -arcTextRadiu, mTextPaint);
            canvas.drawText("Jsen", 0, -arcTextRadiu, mTextPaint);
            canvas.restore();
        }
        canvas.restore();

        canvas.restore();

    }


}
