package example.abe.com.android.activity.drawing.clock;

import com.example.BindView;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;

public class ClockActivity extends BaseActivity {

    @BindView(R.id.act_clock_clock_view)
    protected ClockView mClockView;

    private boolean isTimeTaskRunning = true;

    @Override
    public int getLayoutID(){
        return R.layout.activity_clock;
    }

    @Override
    public void initData(){
    }

    @Override
    public void initView(){
        startShowTime();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isTimeTaskRunning = false;
    }

    private void startShowTime() {
        uiHandler.post(mUpdateTimeTask);
    }

    /**
     * 更新时间的任务
     */
    private Runnable mUpdateTimeTask = new Runnable() {

        @Override
        public void run() {
            mClockView.setTime(System.currentTimeMillis());
            if (isTimeTaskRunning){
                uiHandler.postDelayed(mUpdateTimeTask, 16);
            }
        }
    };
}
