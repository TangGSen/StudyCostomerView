package sen.com.customerviewtest.test7;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ListView;

/**
 * Created by Administrator on 2016/7/4.
 */

public class MatrixStyleListView extends ListView {
    private Camera mCamera;
    private Matrix mMatrix;
    private int current;
    public MatrixStyleListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCamera = new Camera();
        mMatrix = new Matrix();
    }


    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.skew(0.2f, 0.2f);//扭曲了,这个效果不好看
        Log.e("sen",current++ +"");
        mCamera.save();
        mCamera.rotate(30, 30, 30);
        mCamera.getMatrix(mMatrix);
        mMatrix.preTranslate(-getWidth() / 2, -getHeight() / 2);
        mMatrix.postTranslate(getWidth() / 2, getHeight() / 2);
        canvas.concat(mMatrix);
        super.onDraw(canvas);
        mCamera.restore();
    }

}
