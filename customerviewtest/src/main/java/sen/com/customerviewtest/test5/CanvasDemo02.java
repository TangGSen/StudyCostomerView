package sen.com.customerviewtest.test5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/6/20.
 */

public class CanvasDemo02 extends View {

    private static final int LAYER_FLAGS = Canvas.MATRIX_SAVE_FLAG | Canvas.CLIP_SAVE_FLAG
            | Canvas.HAS_ALPHA_LAYER_SAVE_FLAG | Canvas.FULL_COLOR_LAYER_SAVE_FLAG
            | Canvas.CLIP_TO_LAYER_SAVE_FLAG;

    private Paint mPaint;

    public CanvasDemo02(Context context) {
        super(context);
    }

    public CanvasDemo02(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }




    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        canvas.translate(10, 10);
        mPaint.setColor(Color.RED);
        canvas.drawCircle(75, 75, 75, mPaint);
        canvas.saveLayerAlpha(0, 0, 200, 200, 0xff, LAYER_FLAGS);
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(125, 125, 75, mPaint);
        canvas.restore();
        mPaint.setColor(Color.YELLOW);
        mPaint.setAlpha(0xff);
        canvas.drawCircle(130, 130, 75, mPaint);
    }

}
