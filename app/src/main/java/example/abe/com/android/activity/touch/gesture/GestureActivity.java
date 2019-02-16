package example.abe.com.android.activity.touch.gesture;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.util.LogUtil;

public class GestureActivity extends BaseActivity {

    @Override
    public int getLayoutID() {
        return R.layout.activity_gesture;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @SuppressLint("AppCompatCustomView")
    public static class MyTextView extends TextView {

        private GestureDetector mGestureDetector;

        public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init();
        }

        public MyTextView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        private void init() {
            mGestureDetector = new GestureDetector(getContext(), new GestureDetector.OnGestureListener() {
                @Override
                public boolean onDown(MotionEvent e) {
                    LogUtil.e("onDown:" + e.toString());
                    return true;
                }

                @Override
                public void onShowPress(MotionEvent e) {
                    LogUtil.e("onShowPress:" + e.toString());
                }

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    LogUtil.e("onSingleTapUp:" + e.toString());
                    return false;
                }

                @Override
                public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                    LogUtil.e("onScroll:" + e1.toString() + ", " + e2.toString());
                    return false;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    LogUtil.e("onLongPress:" + e.toString());

                }

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    LogUtil.e("onFling:" + e1.toString() + ", " + e2.toString());
                    return false;
                }
            });
        }

        @Override
        @SuppressLint("ClickableViewAccessibility")
        public boolean onTouchEvent(MotionEvent event) {
            return mGestureDetector.onTouchEvent(event);
        }
    }
}
