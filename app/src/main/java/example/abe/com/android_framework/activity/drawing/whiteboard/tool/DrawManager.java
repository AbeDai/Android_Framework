package example.abe.com.android_framework.activity.drawing.whiteboard.tool;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

import java.util.Iterator;

import example.abe.com.android_framework.activity.drawing.whiteboard.tool.memory.StepMemory;
import example.abe.com.android_framework.activity.drawing.whiteboard.tool.paint.PaintFeatures;
import example.abe.com.android_framework.activity.drawing.whiteboard.tool.paint.PaintWrapper;
import example.abe.com.android_framework.activity.drawing.whiteboard.tool.step.DrawStep;
import example.abe.com.android_framework.activity.drawing.whiteboard.tool.step.EraserStep;
import example.abe.com.android_framework.activity.drawing.whiteboard.tool.step.IStep;
import example.abe.com.android_framework.activity.drawing.whiteboard.tool.step.TextStep;

/**
 * Created by abe on 16/8/16.
 */
public class DrawManager {

    public static final int TOUCH_TOLERANCE = 4;

    //存储操作步骤
    private StepMemory mStepMemory;

    //包装画笔
    private PaintWrapper mPaintWrapper;

    //当前类型
    private Type mType;

    //绘制对于View
    private View mView;

    //当前操作步骤
    private IStep mCurStep;

    //前景位图
    private Bitmap mBitmap;

    //前景画布
    private Canvas mCanvas;

    public DrawManager(View view, Type status) {
        mView = view;
        mType = status;
        mPaintWrapper = new PaintWrapper();
        mStepMemory = new StepMemory();
    }

    public void drawAllStep(Canvas canvas){
        if (mCanvas == null || mBitmap == null){
            createFgBitmap();
        }

        drawSavedStep(mCanvas);
        if (mCurStep != null){
            mCurStep.draw(mCanvas);
        }

        canvas.drawBitmap(mBitmap, 0, 0, null);
    }

    public void drawSavedStep(Canvas canvas) {
        Iterator<IStep> i = mStepMemory.getSaveStepIterator();
        while (i.hasNext()) {
            IStep step = i.next();
            step.draw(canvas);
        }
    }

    public void savePath(IStep step){
        if (step == null){
            return;
        }
        mStepMemory.saveStep(step);

        mStepMemory.clearDeleteStep();
    }

    public void restore(){
        createFgBitmap();
        mStepMemory.restore();
        mView.postInvalidate();
    }

    public void unRestore(){
        createFgBitmap();
        mStepMemory.unRestore();
        mView.postInvalidate();
    }

    public void setStatusColor(PaintFeatures.PaintColor color){
        mPaintWrapper.setColor(color);
    }

    public void setStatusWidth(PaintFeatures.PaintWidth width){
        mPaintWrapper.setWidth(width);
    }

    public void setStatusEraserSize(PaintFeatures.PaintEraserSize eraserSize){
        mPaintWrapper.setEraserSize(eraserSize);
    }

    public void setStatusFont(PaintFeatures.PaintFont font){
        mPaintWrapper.setFont(font);
    }

    public Type getType(){
        return mType;
    }

    public void setType(Type type) {
        mType = type;
        mPaintWrapper.updatePaint(mType);
    }

    private void createFgBitmap(){
        mBitmap = null;
        mCanvas = null;
        int width = mView.getMeasuredWidth();
        int height = mView.getMeasuredHeight();
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    private float posX, posY;
    public void handleEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mType == Type.DRAW){
                    posX = x;
                    posY = y;
                    DrawStep step = new DrawStep(new Path(), mPaintWrapper.getPaint());
                    step.moveTo(posX, posY);
                    mCurStep = step;
                }
                else if (mType == Type.ERASER){
                    posX = x;
                    posY = y;
                    EraserStep step = new EraserStep(new Path(), mPaintWrapper.getPaint());
                    step.moveTo(posX, posY);
                    mCurStep = step;
                }
                else if(mType == Type.TEXT){
                    posX = x;
                    posY = y;
                    TextStep step = new TextStep("戴益波", posX, posY, mPaintWrapper.getPaint());
                    mCurStep = step;
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if (mType == Type.DRAW){
                    if (Math.abs(x - posX) > TOUCH_TOLERANCE
                            || Math.abs(y - posY) > TOUCH_TOLERANCE) {
                        ((DrawStep) mCurStep).quadTo(posX, posY, (x + posX) / 2, (y + posY) / 2);
                        posX = x;
                        posY = y;
                    }
                }
                else if (mType == Type.ERASER){
                    if (Math.abs(x - posX) > TOUCH_TOLERANCE
                            || Math.abs(y - posY) > TOUCH_TOLERANCE) {
                        ((EraserStep) mCurStep).lineTo(x, y);
                        posX = x;
                        posY = y;
                    }
                }
                else if(mType == Type.TEXT) {
                    if (Math.abs(x - posX) > TOUCH_TOLERANCE
                            || Math.abs(y - posY) > TOUCH_TOLERANCE) {
                        ((TextStep) mCurStep).setPoint(x, y);
                        posX = x;
                        posY = y;
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                if (mType == Type.DRAW){
                    savePath(mCurStep);
                    mCurStep = null;
                }
                else if (mType == Type.ERASER){
                    savePath(mCurStep);
                    mCurStep = null;
                }
                else if(mType == Type.TEXT){
                    savePath(mCurStep);
                    mCurStep = null;
                }
                break;
        }
        mView.postInvalidate();
    }
}
