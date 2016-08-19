package example.abe.com.android_framework.activity.drawing.whiteboard.tool.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import example.abe.com.android_framework.activity.drawing.whiteboard.tool.DrawManager;

/**
 * Created by abe on 16/8/18.
 */
public abstract class AbPaintView extends View{

    public abstract DrawManager getDrawManager();

    public AbPaintView(Context context) {
        super(context);
    }

    public AbPaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AbPaintView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onDraw(Canvas canvas) {
        getDrawManager().drawAllStep(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        getDrawManager().handleEvent(event);
        return true;
    }
}
