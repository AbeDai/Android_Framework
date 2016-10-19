package example.abe.com.android.activity.multithread;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.widget.TextView;

import com.example.BindView;

import example.abe.com.android.R;
import example.abe.com.framework.eventcenter.EventCenter;
import example.abe.com.framework.main.BaseActivity;

public class HandlerThreadActivity extends BaseActivity {

    @BindView(R.id.act_handler_thread_tv)
    protected TextView mTv;
    private HandlerThread mHandlerThread;
    private Handler handler;
    private static final int MSG_UPDATE_INFO = 0x110;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EventCenter.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventCenter.getDefault().register(this);
        mHandlerThread.quit();//释放资源
    }

    @Override
    public int getLayoutID(){
        return R.layout.activity_handler_thread;
    }

    @Override
    public void initData() {
        mHandlerThread = new HandlerThread("");
        mHandlerThread.start();
        handler = new Handler(mHandlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == MSG_UPDATE_INFO){
                    SystemClock.sleep(1000);
                    EventCenter.getDefault().post(new HandlerThreadEvent());
                    handler.sendEmptyMessageDelayed(MSG_UPDATE_INFO, 1000);
                }
            }
        };
    }

    @Override
    public void initView() {
        mTv.setText("正在加载大盘指数...");
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.sendEmptyMessage(MSG_UPDATE_INFO);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeMessages(MSG_UPDATE_INFO);
    }

    public void onEventUI(HandlerThreadEvent event) {
        mTv.setText("实时更新中，当前大盘指数：" + (Math.random() * 3000 + 1000));
    }

    private class HandlerThreadEvent{}
}
