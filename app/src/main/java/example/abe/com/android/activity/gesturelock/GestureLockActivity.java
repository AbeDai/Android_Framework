package example.abe.com.android.activity.gesturelock;

import com.example.BindView;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.util.ToastUtil;

public class GestureLockActivity extends BaseActivity {

    @BindView(R.id.act_gesture_lock_view_1)
    protected GestureLockView mGestureLockView1;

    @BindView(R.id.act_gesture_lock_view_2)
    protected GestureLockView mGestureLockView2;

    @Override
    public int getLayoutID(){
        return R.layout.activity_gesture_lock;
    }

    @Override
    public void initView() {

        mGestureLockView1.setCallBack(new GestureLockView.CallBack() {

            @Override
            public void onFinish(String password) {
                ToastUtil.makeText("GestureLockView1: " + password);
            }
        });

        mGestureLockView2.setCallBack(new GestureLockView.CallBack() {

            @Override
            public void onFinish(String password) {
                ToastUtil.makeText("GestureLockView2: " + password);
            }
        });
    }

    @Override
    public void initData() {
    }

}
