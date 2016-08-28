package example.abe.com.android_framework.activity.multithread;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import example.abe.com.android_framework.R;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.viewinject.ContentView;
import example.abe.com.framework.viewinject.ViewInject;

@ContentView(id = R.layout.activity_thread)
public class MultiThreadActivity extends BaseActivity implements View.OnClickListener{

    @ViewInject(id = R.id.act_thread_btn_async_task)
    public Button mBtnAsyncTask;
    @ViewInject(id = R.id.act_thread_btn_handler_thread)
    public Button mBtnHandlerThread;
    @ViewInject(id = R.id.act_thread_btn_thread_pool)
    public Button mBtnThreadPool;
    @ViewInject(id = R.id.act_thread_btn_intent_service)
    public Button mBtnIntentService;

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        mBtnAsyncTask.setOnClickListener(this);
        mBtnHandlerThread.setOnClickListener(this);
        mBtnThreadPool.setOnClickListener(this);
        mBtnIntentService.setOnClickListener(this);
    }

    @Override
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
