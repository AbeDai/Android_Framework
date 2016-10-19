package example.abe.com.android.activity.multithread;

import android.content.Intent;
import android.view.View;

import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;

public class MultiThreadActivity extends BaseActivity{

    @Override
    public int getLayoutID(){
        return R.layout.activity_thread;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
    }

    @OnClick({R.id.act_thread_btn_async_task, R.id.act_thread_btn_handler_thread, R.id.act_thread_btn_thread_pool, R.id.act_thread_btn_intent_service})
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.act_thread_btn_async_task:
                intent = new Intent(MultiThreadActivity.this, AsyncTaskActivity.class);
                break;
            case R.id.act_thread_btn_handler_thread:
                intent = new Intent(MultiThreadActivity.this, HandlerThreadActivity.class);
                break;
            case R.id.act_thread_btn_thread_pool:
                intent = new Intent(MultiThreadActivity.this, ThreadPoolActivity.class);
                break;
            case R.id.act_thread_btn_intent_service:
                intent = new Intent(MultiThreadActivity.this, IntentServiceActivity.class);
                break;
        }
        startActivity(intent);
    }
}
