package example.abe.com.android_framework.activity.drawing.whiteboard;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;

import example.abe.com.android_framework.activity.drawing.whiteboard.tool.DrawManager;
import example.abe.com.android_framework.activity.drawing.whiteboard.tool.Type;
import example.abe.com.android_framework.activity.drawing.whiteboard.tool.paint.PaintFeatures;
import example.abe.com.android_framework.activity.drawing.whiteboard.tool.view.AbPaintView;

public class PenPaintView extends AbPaintView {

    private DrawManager mDrawManager;

    public PenPaintView(Context context) {
        super(context);
        init();
    }

    public PenPaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PenPaintView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mDrawManager = new DrawManager(this, Type.DRAW);
        mDrawManager.setStatusColor(PaintFeatures.PaintColor.BLUE);
        mDrawManager.setStatusWidth(PaintFeatures.PaintWidth.NORMAL);
        mDrawManager.setStatusEraserSize(PaintFeatures.PaintEraserSize.NORMAL);
        mDrawManager.setType(Type.DRAW);
    }

    public DrawManager getDrawManager() {
        return mDrawManager;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
