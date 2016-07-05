package sen.com.customerviewtest.test6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/6/22.
 */

public class SweepGradient01 extends View {
    private Paint mPaint ;
    private int changeRudis =1 ;

    private boolean add = true ;
    public SweepGradient01(Context context) {
        super(context);
    }

    public SweepGradient01(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG) ;
       // mPaint.setShader(new SweepGradient(300, 350, Color.RED, Color.YELLOW));
//        mPaint.setShader(new SweepGradient(250, 250, new int[] { Color.GREEN, Color.WHITE, Color.GREEN ,Color.YELLOW},null));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setShader(new RadialGradient(400 ,400 ,changeRudis , Color.RED, Color.YELLOW, Shader.TileMode.MIRROR));

        canvas.drawCircle(400 ,400 ,200 ,mPaint);

        new Thread(){
            @Override
            public void run() {
                super.run();

                if ( changeRudis <=30){
                    add = true ;
                }

                if (changeRudis>=200){
                    add = false ;
                }

                if (add){
                    changeRudis++;
                }else {
                    changeRudis --;
                }
                postInvalidate();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }


}
