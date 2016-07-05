package sen.com.customerviewtest.test3;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import sen.com.customerviewtest.MeasureUtil;

/**
 * Created by Administrator on 2016/6/14.
 */
public class ShaderView03 extends View {
    private static final int RECT_SIZE = 600;

    private Paint mPaint;// 画笔

    private int left, right, bottom, top;


    public ShaderView03(Context context) {
        super(context);
    }

    public ShaderView03(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 获取屏幕尺寸数据
        int[] screenSize = MeasureUtil.getScreenSize((Activity) context);

        // 获取屏幕中点坐标
        int screenX = screenSize[0] / 2;
        int screenY = screenSize[1] / 2;

        // 计算矩形左上右下坐标值
        left = screenX - RECT_SIZE;
        top = screenY - RECT_SIZE;
        right = screenX + RECT_SIZE;
        bottom = screenY + RECT_SIZE;

        // 实例化画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        // 设置着色器
//        LinearGradient(float x0, float y0, float x1, float y1, int color0, int color1, Shader.TileMode tile)

//        mPaint.setShader(new LinearGradient(left,top,right-RECT_SIZE, bottom-RECT_SIZE, Color.RED, Color.YELLOW, Shader.TileMode.REPEAT));

        mPaint.setShader(new LinearGradient(0,(bottom -top)/2,right, bottom, Color.RED, Color.YELLOW, Shader.TileMode.REPEAT));

//    LinearGradient(float x0, float y0, float x1, float y1, int[] colors, float[] positions, Shader.TileMode tile)
//        mPaint.setShader(new LinearGradient(left, top, right, bottom, new int[] {Color.RED, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE }, new float[] { 0, 0.1F, 0.5F, 0.7F, 0.8F }, Shader.TileMode.MIRROR));
    }



    @Override
    protected void onDraw(Canvas canvas) {
        // 绘制矩形
        canvas.drawRect(left, top, right, bottom, mPaint);
    }
}
