package example.abe.com.android_framework.activity.eventcenter;

import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.BindView;

import example.abe.com.android_framework.R;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.eventcenter.EventCenter;

public class EventCenterActivity extends BaseActivity {

    @BindView(R.id.act_event_center_tv_content)
    protected TextView tv;
    @BindView(R.id.act_event_center_btn_post_event)
    protected Button mBtnenter;
    @BindView(R.id.act_event_center_btn_register)
    protected Button mBtnRegister;
    @BindView(R.id.act_event_center_btn_unregister)
    protected Button mBtnUnRegister;

    @Override
    public int getLayoutID(){
        return R.layout.activity_event_center;
    }

    @Override
    public void initView() {
        mBtnenter.setOnClickListener(mBtnListener);
        mBtnRegister.setOnClickListener(mBtnListener);
        mBtnUnRegister.setOnClickListener(mBtnListener);
    }

    @Override
    public void initData() {

    }

    View.OnClickListener mBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.act_event_center_btn_post_event:
                    MessageEvent event = new MessageEvent("发送消息：我是戴益波");
                    EventCenter.getDefault().post(event);
                    break;

                case R.id.act_event_center_btn_register:
                    EventCenter.getDefault().register(EventCenterActivity.this);
                    break;

                case R.id.act_event_center_btn_unregister:
                    EventCenter.getDefault().unRigister(EventCenterActivity.this);
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onEventUI(MessageEvent event) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            Log.e("ABE onEventUI", "当前线程是UI线程");
        } else {
            Log.e("ABE onEventUI", "当前线程是BG线程");
        }

        tv.setText(event.getMessage());
    }

    public void onEventBg(MessageEvent event) {
        if (Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId()) {
            Log.e("ABE onEventBg", "当前线程是UI线程");
            return;
        } else {
            Log.e("ABE onEventBg", "当前线程是BG线程");
        }

        //报错
        // tv.setText(event.getmMessage());
    }
}
