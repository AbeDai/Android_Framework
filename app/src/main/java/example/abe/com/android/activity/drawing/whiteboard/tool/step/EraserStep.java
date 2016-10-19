package example.abe.com.android.activity.drawing.whiteboard.tool.step;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by abe on 16/8/18.
 */
public class EraserStep implements IStep {
    private Path mPath;
    private Paint mPaint;

    public EraserStep(Path path, Paint paint) {
        mPath = new Path(path);
        mPaint = new Paint(paint);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawPath(mPath, mPaint);
    }

    public void moveTo(float x, float y) {
        mPath.moveTo(x, y);
    }

    public void lineTo(float x, float y) {
        mPath.lineTo(x, y);
    }
}
