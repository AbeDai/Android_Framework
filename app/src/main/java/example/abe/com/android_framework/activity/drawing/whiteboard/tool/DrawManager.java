package example.abe.com.android_framework.activity.drawing.whiteboard.tool;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.MotionEvent;
import android.view.View;

import java.util.Iterator;

import example.abe.com.android_framework.activity.drawing.whiteboard.tool.memory.StepMemory;
import example.abe.com.android_framework.activity.drawing.whiteboard.tool.paint.PaintFeatures;
import example.abe.com.android_framework.activity.drawing.whiteboard.tool.paint.PaintWrapper;
import example.abe.com.android_framework.activity.drawing.whiteboard.tool.step.DrawStep;
import example.abe.com.android_framework.activity.drawing.whiteboard.tool.step.EraserStep;
import example.abe.com.android_framework.activity.drawing.whiteboard.tool.step.IStep;
import example.abe.com.android_framework.activity.drawing.whiteboard.tool.step.RectStep;
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

    public void drawAllStep(Canvas canvas) {
        if (mCanvas == null || mBitmap == null) {
            createFgBitmap();
        } else {
            clearBitmap(mCanvas);
        }

        drawSavedStep(mCanvas);
        if (mCurStep != null) {
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

    public void savePath(IStep step) {
        if (step == null) {
            return;
        }
        mStepMemory.saveStep(step);

        mStepMemory.clearDeleteStep();
    }

    public void restore() {
        clearBitmap(mCanvas);
        mStepMemory.restore();
        mView.postInvalidate();
    }

    public void unRestore() {
        clearBitmap(mCanvas);
        mStepMemory.unRestore();
        mView.postInvalidate();
    }

    public void setStatusColor(PaintFeatures.PaintColor color) {
        mPaintWrapper.setColor(color);
    }

    public void setStatusWidth(PaintFeatures.PaintWidth width) {
        mPaintWrapper.setWidth(width);
    }

    public void setStatusEraserSize(PaintFeatures.PaintEraserSize eraserSize) {
        mPaintWrapper.setEraserSize(eraserSize);
    }

    public void setStatusFont(PaintFeatures.PaintFont font) {
        mPaintWrapper.setFont(font);
    }

    public Type getType() {
        return mType;
    }

    public void setType(Type type) {
        mType = type;
        mPaintWrapper.updatePaint(mType);
    }

    private float posX, posY;

    public void handleEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                posX = x;
                posY = y;
                if (mType == Type.DRAW) {
                    mCurStep = new DrawStep(new Path(), mPaintWrapper.getPaint());
                    ((DrawStep) mCurStep).moveTo(posX, posY);
                } else if (mType == Type.ERASER) {
                    mCurStep = new EraserStep(new Path(), mPaintWrapper.getPaint());
                    ((EraserStep) mCurStep).moveTo(posX, posY);
                } else if (mType == Type.TEXT) {
                    mCurStep = new TextStep("戴益波", mPaintWrapper.getPaint());
                    ((TextStep) mCurStep).setPoint(posX, posY);
                } else if (mType == Type.RECT) {
                    mCurStep = new RectStep(posX, posY, mPaintWrapper.getPaint());
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if (Math.abs(x - posX) > TOUCH_TOLERANCE
                        || Math.abs(y - posY) > TOUCH_TOLERANCE) {
                    if (mType == Type.DRAW) {
                            ((DrawStep) mCurStep).quadTo(posX, posY, (x + posX) / 2, (y + posY) / 2);
                    } else if (mType == Type.ERASER) {
                            ((EraserStep) mCurStep).lineTo(x, y);
                    } else if (mType == Type.TEXT) {
                            ((TextStep) mCurStep).setPoint(x, y);
                    } else if (mType == Type.RECT) {
                            ((RectStep) mCurStep).setPoint(x, y);
                    }
                    posX = x;
                    posY = y;
                }
                break;

            case MotionEvent.ACTION_UP:
                if (mType == Type.DRAW || mType == Type.ERASER
                        || mType == Type.TEXT || mType == Type.RECT) {
                    savePath(mCurStep);
                    mCurStep = null;
                }
                break;
        }
        mView.postInvalidate();
    }

    private void createFgBitmap() {
        mBitmap = null;
        mCanvas = null;
        int width = mView.getMeasuredWidth();
        int height = mView.getMeasuredHeight();
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    private void clearBitmap(Canvas canvas) {
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        canvas.drawPaint(paint);
    }
}
