package example.abe.com.android_framework.activity.multithread;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.BindView;

import example.abe.com.android_framework.R;
import example.abe.com.framework.main.BaseActivity;

public class MultiThreadActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.act_thread_btn_async_task)
    protected Button mBtnAsyncTask;
    @BindView(R.id.act_thread_btn_handler_thread)
    protected Button mBtnHandlerThread;
    @BindView(R.id.act_thread_btn_thread_pool)
    protected Button mBtnThreadPool;
    @BindView(R.id.act_thread_btn_intent_service)
    protected Button mBtnIntentService;

    @Override
    public int getLayoutID(){
        return R.layout.activity_thread;
    }

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
