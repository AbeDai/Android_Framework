package example.abe.com.android.activity.touch.velocitytrack;

import android.view.MotionEvent;
import android.view.VelocityTracker;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.util.LogUtil;

public class VelocityTrackerActivity extends BaseActivity {

    private VelocityTracker mTracker;
    private Runnable mVelocityRunnable = new Runnable() {
        @Override
        public void run() {
            mTracker.computeCurrentVelocity(1000);
            LogUtil.e("(x, y) = ( " + mTracker.getXVelocity() + ", " + mTracker.getYVelocity() + ")");
            uiHandler.postDelayed(this, 1000);
        }
    };

    @Override
    public int getLayoutID() {
        return R.layout.activity_velocity_tracker;
    }

    @Override
    public void initData() {
        mTracker = VelocityTracker.obtain();
        uiHandler.postDelayed(mVelocityRunnable, 1000);
    }

    @Override
    public void initView() {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mTracker.addMovement(event);
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        uiHandler.removeCallbacks(mVelocityRunnable);
    }
}
