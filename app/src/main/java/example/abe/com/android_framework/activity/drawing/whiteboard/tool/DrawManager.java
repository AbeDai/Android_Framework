package example.abe.com.android_framework.activity.drawing.whiteboard.tool;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

import java.util.Iterator;

import example.abe.com.android_framework.activity.drawing.whiteboard.tool.memory.DrawStepMemory;
import example.abe.com.android_framework.activity.drawing.whiteboard.tool.paint.PaintFactory;
import example.abe.com.android_framework.activity.drawing.whiteboard.tool.step.DrawStep;
import example.abe.com.android_framework.activity.drawing.whiteboard.tool.step.IStep;

/**
 * Created by abe on 16/8/16.
 */
public class DrawManager {

    public static final int TOUCH_TOLERANCE = 4;

    private DrawStepMemory mStepMemory;
    private View mView;
    private WhiteboardStatus mStatus;
    private Paint mCurPaint;
    private IStep mCurStep;
    private float posX, posY;

    public DrawManager(View view) {
        mView = view;

        initData();
    }

    private void initData() {
        mStepMemory = new DrawStepMemory();
        mStatus = new WhiteboardStatus();
        mCurPaint = PaintFactory.getDefaultPaint();
    }

    public void drawSavedStep(Canvas canvas) {
        Iterator<IStep> i = mStepMemory.getSaveStepIterator();
        while (i.hasNext()) {
            IStep step = i.next();
            step.draw(canvas);
        }
    }

    public void drawAllStep(Canvas canvas){
        drawSavedStep(canvas);

        if (mCurStep != null){
            mCurStep.draw(canvas);
        }
    }

    public void savePath(IStep step){
        if (step == null){
            return;
        }

        mStepMemory.saveStep(step);

        mStepMemory.clearDeleteStep();
    }

    public void handleEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mStatus.type == WhiteboardStatus.Type.DRAW){
                    posX = x;
                    posY = y;
                    DrawStep step = new DrawStep(new Path(), mCurPaint);
                    step.moveTo(posX, posY);
                    mCurStep = step;
                }
                else if (mStatus.type == WhiteboardStatus.Type.ERASER){
                    posX = x;
                    posY = y;
                    DrawStep step = new DrawStep(new Path(), mCurPaint);
                    step.moveTo(posX, posY);
                    mCurStep = step;
                }

                break;

            case MotionEvent.ACTION_MOVE:
                if (mStatus.type == WhiteboardStatus.Type.DRAW){
                    if (Math.abs(x - posX) > TOUCH_TOLERANCE
                            || Math.abs(y - posY) > TOUCH_TOLERANCE) {
                        ((DrawStep) mCurStep).quadTo(posX, posY, (x + posX) / 2, (y + posY) / 2);
                        posX = x;
                        posY = y;
                    }
                }
                else if (mStatus.type == WhiteboardStatus.Type.ERASER){
                    if (Math.abs(x - posX) > TOUCH_TOLERANCE
                            || Math.abs(y - posY) > TOUCH_TOLERANCE) {
                        ((DrawStep) mCurStep).quadTo(posX, posY, (x + posX) / 2, (y + posY) / 2);
                        posX = x;
                        posY = y;
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                if (mStatus.type == WhiteboardStatus.Type.DRAW){
                    savePath(mCurStep);
                    mCurStep = null;
                }
                else if (mStatus.type == WhiteboardStatus.Type.ERASER){
                    savePath(mCurStep);
                    mCurStep = null;
                }
                break;
        }

        mView.postInvalidate();
    }

    public void restore(){
        mStepMemory.restore();
        mView.postInvalidate();
    }

    public void unRestore(){
        mStepMemory.unRestore();
        mView.postInvalidate();
    }

    public void setStatusColor(WhiteboardStatus.Color color){
        mStatus.color = color;
        mStatus.type = WhiteboardStatus.Type.DRAW;
        mCurPaint = PaintFactory.getPaint(mStatus);
    }

    public void setStatusWidth(WhiteboardStatus.Width width){
        mStatus.width = width;
        mStatus.type = WhiteboardStatus.Type.DRAW;
        mCurPaint = PaintFactory.getPaint(mStatus);
    }

    public void setStatusEraserSize(WhiteboardStatus.EraserSize eraserSize){
        mStatus.eraserSize = eraserSize;
        mStatus.type = WhiteboardStatus.Type.ERASER;
        mCurPaint = PaintFactory.getPaint(mStatus);
    }

}
