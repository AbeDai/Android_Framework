package example.abe.com.android_framework.activity.eventbus;

import android.os.Bundle;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.main.BaseActivity;
import example.abe.com.framework.annotation.ContentView;
import example.abe.com.framework.util.ToastUtil;

@ContentView(id = R.layout.activity_event_bus_frist)
public class EventBusActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);//注册

        initView();
    }

    private void initView() {
        findViewById(R.id.act_third_tv_event_1).setOnClickListener(this);
        findViewById(R.id.act_third_tv_event_2).setOnClickListener(this);
        findViewById(R.id.act_third_tv_event_3).setOnClickListener(this);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        EventBus.getDefault().unregister(this);//解除注册
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.act_third_tv_event_1:
                EventBus.getDefault().post(new FirstEvent("Event_1_btn clicked"));
                break;

            case R.id.act_third_tv_event_2:
                EventBus.getDefault().post(new SecondEvent("Event_2_btn clicked"));
                break;

            case R.id.act_third_tv_event_3:
                EventBus.getDefault().post(new ThirdEvent("Event_3_btn clicked"));
                break;
        }
    }


    //FirstEvent接收函数-1个
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEvent event) {
        ToastUtil.makeText("onEventMainThread收到了消息：" + "FirstEvent" + event.getMsg()
                + "\n" + "当前线程: " + Thread.currentThread());
    }

    //SecondEvent接收函数-3个
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCustomEventMainThread(SecondEvent event) {
        ToastUtil.makeText("onEventMainThread收到了消息：" + event.getMsg()
                + "\n" + "当前线程: " + Thread.currentThread());
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onCustomEventBackgroundThread(SecondEvent event){
        ToastUtil.makeText("onEventBackground收到了消息：" + event.getMsg()
                + "\n" + "当前线程: " + Thread.currentThread());
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onCustomEventAsync(SecondEvent event){
        ToastUtil.makeText("onEventAsync收到了消息：" + event.getMsg()
                + "\n" + "当前线程: " + Thread.currentThread());
    }

    //ThirdEvent接收函数-1个
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onCustomEvent(ThirdEvent event) {
        ToastUtil.makeText("onEvent收到了消息：" + event.getMsg()
                + "\n" + "当前线程: " + Thread.currentThread());
    }
}
