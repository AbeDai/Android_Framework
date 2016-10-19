package example.abe.com.android.activity.drawing.whiteboard.tool.step;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by abe on 16/8/17.
 */
public class DrawStep implements IStep{
    private Path mPath;
    private Paint mPaint;

    public DrawStep(Path path, Paint paint){
        mPath = new Path(path);
        mPaint = new Paint(paint);
    }

    @Override
    public void draw(Canvas canvas){
        canvas.drawPath(mPath, mPaint);
    }

    public void moveTo(float x, float y) {
        mPath.moveTo(x, y);
    }

    public void quadTo(float x1, float y1, float x2, float y2) {
        mPath.quadTo(x1, y1, x2, y2);
    }
}
