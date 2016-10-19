package example.abe.com.android.activity.drawing.whiteboard.tool.step;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by abe on 16/8/18.
 */
public class RectStep implements IStep {
    private RectF mRect;
    private Paint mPaint;
    private float originX;
    private float originY;
    private float modifyX;
    private float modifyY;

    public RectStep(float x1, float y1, Paint paint) {
        originX = x1;
        originY = y1;
        mRect = new RectF();
        mPaint = new Paint(paint);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(mRect, mPaint);
    }

    /**
     * 设置矩形Rect，两个点确定矩形（左上点，右下点）
     */
    public void setPoint(float x2, float y2) {
        modifyX = x2;
        modifyY = y2;

        mRect.left = originX > modifyX ? modifyX : originX;
        mRect.right = modifyX > originX ? modifyX : originX;
        mRect.top = originY > modifyY ? modifyY : originY;
        mRect.bottom = modifyY > originY ? modifyY : originY;
    }
}
