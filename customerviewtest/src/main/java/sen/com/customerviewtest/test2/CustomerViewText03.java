package sen.com.customerviewtest.test2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/6/7.
 */
public class CustomerViewText03 extends View {
    private static final String TEXT = "This is used by widgets to control text layout. You should not need to use this class directly unless you are implementing your own widget or custom display object, or would be tempted to call Canvas.drawText() directly.";
    private TextPaint mTextPaint;// 文本的画笔
    private StaticLayout mStaticLayout;// 文本布局

    public CustomerViewText03(Context context) {
        this(context, null);
    }

    public CustomerViewText03(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 初始化画笔
        initPaint();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        // 实例化画笔
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(50);
        mTextPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mStaticLayout = new StaticLayout(TEXT, mTextPaint, canvas.getWidth(), StaticLayout.Alignment.ALIGN_CENTER, 1.0F, 0.0F, false);
        mStaticLayout.draw(canvas);
        canvas.restore();
    }


}
