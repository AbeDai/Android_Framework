package example.abe.com.android_framework.activity.drawing.whiteboard;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import example.abe.com.android_framework.activity.drawing.whiteboard.tool.DrawManager;

public class PenPaintView extends View {

    private DrawManager mManager;

    public PenPaintView(Context context) {
        super(context);
        init(null, 0);
    }

    public PenPaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public PenPaintView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        mManager = new DrawManager(this);
    }

    public DrawManager getManager(){
        return mManager;
    }

    @Override
    public void onDraw(Canvas canvas) {
        mManager.drawAllStep(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mManager.handleEvent(event);

        return true;
    }
}
