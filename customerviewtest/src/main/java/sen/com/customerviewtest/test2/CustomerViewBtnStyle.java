package sen.com.customerviewtest.test2;

import android.app.Activity;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import sen.com.customerviewtest.MeasureUtil;

/**
 * Created by Administrator on 2016/6/13.
 */

public class CustomerViewBtnStyle extends View {
    private static final int RECT_SIZE = 800;
    private Context mContext ;
    private Paint mPaint ;
    private int left ,top ,right ,bottom;

    public CustomerViewBtnStyle(Context context) {
        super(context);
    }

    public CustomerViewBtnStyle(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context ;
        
        initPaint();
        // 初始化资源
        initRes();
    }

    private void initRes() {
         /*
         * 计算位图绘制时左上角的坐标使其位于屏幕中心
         */
        left = MeasureUtil.getScreenSize((Activity) mContext)[0] / 2 - RECT_SIZE / 2;
        top = MeasureUtil.getScreenSize((Activity) mContext)[1] / 2 - RECT_SIZE / 2;
        right = MeasureUtil.getScreenSize((Activity) mContext)[0] / 2 + RECT_SIZE / 2;
        bottom = MeasureUtil.getScreenSize((Activity) mContext)[1] / 2 + RECT_SIZE / 2;

    }

    private void initPaint() {
        // 实例化画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(0xFF603811);

        mPaint.setMaskFilter(new BlurMaskFilter(20, BlurMaskFilter.Blur.NORMAL));
        //关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        // 画一个矩形
        canvas.drawRect(left, top, right, bottom, mPaint);
    }
}
