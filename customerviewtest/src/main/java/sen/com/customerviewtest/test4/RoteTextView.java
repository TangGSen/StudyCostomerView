package sen.com.customerviewtest.test4;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/6/16.
 */

public class RoteTextView extends TextView {
    public RoteTextView(Context context) {
        super(context);
    }

    public RoteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.rotate(90);
        canvas.translate(0,-100);
        super.onDraw(canvas);
    }
}
