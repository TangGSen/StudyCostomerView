package sen.com.customerviewtest.test2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/6/13.
 */

public class CustomerPathView01 extends View {
    private PathEffect[] mEffects;
    private float mPhase;// 偏移值
    private Paint mPaint;
    private Path mPath;

    //    private PathEffect
    public CustomerPathView01(Context context) {
        super(context);
    }

    public CustomerPathView01(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setColor(Color.DKGRAY);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);

        mPath = new Path();

        //定义起点
        mPath.moveTo(10, 0);

        for (int i = 0; i <= 30; i++) {
            mPath.lineTo(i * 35, (float) (Math.random() * 100));
        }
        // 创建路径效果数组  
        mEffects = new PathEffect[7];
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
 /*
         * 实例化各类特效
         */
        mEffects[0] = null;
        mEffects[1] = new CornerPathEffect(20);
//        segmentLength指定最大的段长，deviation指定偏离量。
        mEffects[2] = new DiscretePathEffect(3.0F, 10.0F);
//        将Path的线段虚线化,，phase为绘制时的偏移量

        mEffects[3] = new DashPathEffect(new float[]{1, 10, 15, 20, 25, 30}, mPhase);
        Path path = new Path();
/**
 Path.Direction.CCW：是counter-clockwise缩写，指创建逆时针方向的矩形路径；
 Path.Direction.CW：是clockwise的缩写，指创建顺时针方向的矩形路径；
 问：从效果图中，看不出顺时针生成和逆时针生成的任何区别，怎么会没区别呢？
 答：当然没区别啦，无论正时针还是逆时针，仅仅是生成方式不同而已，矩形就那么大画出来的路径矩形当然与矩形一样大了。
 问：那生成方式有什么区别呢？
 答：生成方式的区别在于，依据生成方向排版的文字！后面我们会讲到文字，文字是可以依据路径排版的，那文字的行走方向就是依据路径的生成方向；
 */

        path.addRect(0, 0, 8, 8, Path.Direction.CW);

/**
 *  PathDashPathEffect (Path shape, float advance, float phase,PathDashPathEffect.Stylestyle)。
 shape则是指填充图形，advance指每个图形间的间距，phase为绘制时的偏移量，
 style为该类自由的枚举值，有三种情况：Style.ROTATE、Style.MORPH和Style.TRANSLATE。
 其中ROTATE的情况下，线段连接处的图形转换以旋转到与下一段移动方向相一致的角度进行转转，
 MORPH时图形会以发生拉伸或压缩等变形的情况与下一段相连接，
 TRANSLATE时，图形会以位置平移的方式与下一段相连接。
 */

        mEffects[4] = new PathDashPathEffect(path, 20, mPhase, PathDashPathEffect.Style.ROTATE);
/**
 ComposePathEffect (PathEffect outerpe,PathEffect innerpe)，
 表现时，会首先将innerpe表现出来，然后再在innerpe的基础上去增加outerpe的效果。
 */
        mEffects[5] = new ComposePathEffect(mEffects[2], mEffects[4]);
        mEffects[6] = new SumPathEffect(mEffects[4], mEffects[5]);

        /*
         * 绘制路径
         */
        for (int i = 0; i < mEffects.length; i++) {
            mPaint.setPathEffect(mEffects[i]);
            canvas.drawPath(mPath, mPaint);

            // 每绘制一条将画布向下平移250个像素
            canvas.translate(0, 250);
        }

        // 刷新偏移值并重绘视图实现动画效果
        mPhase += 1;
         invalidate();

    }
}
