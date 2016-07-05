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

public class CanvasDemo01 extends View {

    private Paint mPaint ;

    public CanvasDemo01(Context context) {
        super(context);
    }

    public CanvasDemo01(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint bgPaint = new Paint();

        Paint lintPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

        bgPaint.setColor(Color.BLACK);

        lintPaint.setColor(Color.WHITE);
        lintPaint.setStrokeWidth(4);

        int width = 500 ;
        int height = 500 ;
        canvas.drawRect(0,0 ,width ,height,bgPaint);

        canvas.save();

        /**
         *       --------------
         *
         */
        canvas.rotate(90 ,width ,height);

        canvas.drawLine(width/2 ,0 ,width ,height /2,lintPaint);
        canvas.drawLine(width ,height /2 ,width / 2 ,height,lintPaint);
        canvas.drawLine( 0 , height/2,width ,height /2,lintPaint);

    }
}
