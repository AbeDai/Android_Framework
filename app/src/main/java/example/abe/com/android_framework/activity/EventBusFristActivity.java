package example.abe.com.android_framework.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import example.abe.com.android_framework.Event.FirstEvent;
import example.abe.com.android_framework.Event.SecondEvent;
import example.abe.com.android_framework.Event.ThirdEvent;
import example.abe.com.android_framework.R;

public class EventBusFristActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_frist);
        EventBus.getDefault().register(this);//注册

        initView();
    }

    private void initView() {
        findViewById(R.id.act_frist_tv_enter).setOnClickListener(this);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        EventBus.getDefault().unregister(this);//解除注册
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(EventBusFristActivity.this, EventBusSecondActivity.class);
        startActivity(intent);
    }


    //FirstEvent接收函数-1个
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEvent event) {
        Log.d("harvic", "onEventMainThread收到了消息：" + "FirstEvent" + event.getMsg()
                + "\n" + "当前线程: " + Thread.currentThread());
    }

    //SecondEvent接收函数-3个
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCustomEventMainThread(SecondEvent event) {
        Log.d("harvic", "onEventMainThread收到了消息：" + event.getMsg()
                + "\n" + "当前线程: " + Thread.currentThread());
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onCustomEventBackgroundThread(SecondEvent event){
        Log.d("harvic", "onEventBackground收到了消息：" + event.getMsg()
                + "\n" + "当前线程: " + Thread.currentThread());
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onCustomEventAsync(SecondEvent event){
        Log.d("harvic", "onEventAsync收到了消息：" + event.getMsg()
                + "\n" + "当前线程: " + Thread.currentThread());
    }

    //ThirdEvent接收函数-1个
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onCustomEvent(ThirdEvent event) {
        Log.d("harvic", "onEvent收到了消息：" + event.getMsg()
                + "\n" + "当前线程: " + Thread.currentThread());
    }
}
