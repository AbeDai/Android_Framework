package example.abe.com.android_framework.activity.multithread;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.activity.eventcenter.MessageEvent;
import example.abe.com.framework.eventcenter.EventCenter;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.util.LogUtil;
import example.abe.com.framework.viewinject.ContentView;
import example.abe.com.framework.viewinject.ViewInject;

@ContentView(id = R.layout.activity_thread_pool)
public class ThreadPoolActivity extends BaseActivity implements View.OnClickListener {

    @ViewInject(id = R.id.act_thread_pool_tv)
    private TextView mTv;
    @ViewInject(id = R.id.act_thread_pool_btn_single_executor)
    private Button mBtnSingleExecutor;
    @ViewInject(id = R.id.act_thread_pool_btn_cache_thread_pool)
    private Button mBtnCacheThreadPool;
    @ViewInject(id = R.id.act_thread_pool_btn_fixed_thread_pool)
    private Button mBtnFixedThreadPool;
    @ViewInject(id = R.id.act_thread_pool_btn_scheduled_thread_pool)
    private Button mBtnScheduledThreadPool;
    private ExecutorService mSingleExecutor;
    private ExecutorService mCachedThreadPool;
    private ExecutorService mFixedThreadPool;
    private ScheduledExecutorService mScheduledThreadPool;
    private static int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventCenter.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventCenter.getDefault().register(this);
    }

    @Override
    public void initData() {
        count = 0;
        mSingleExecutor = Executors.newSingleThreadExecutor();
        mCachedThreadPool = Executors.newCachedThreadPool();
        mFixedThreadPool = Executors.newFixedThreadPool(5);
        mScheduledThreadPool = Executors.newScheduledThreadPool(3);
    }

    @Override
    public void initView() {
        mBtnSingleExecutor.setOnClickListener(this);
        mBtnCacheThreadPool.setOnClickListener(this);
        mBtnFixedThreadPool.setOnClickListener(this);
        mBtnScheduledThreadPool.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.act_thread_pool_btn_single_executor:
                mSingleExecutor.execute(getRunnable("single executor 执行结果"));
                break;
            case R.id.act_thread_pool_btn_cache_thread_pool:
                mCachedThreadPool.submit(getRunnable("cache thread pool 执行结果"));
                break;
            case R.id.act_thread_pool_btn_fixed_thread_pool:
                Future<String> future = mFixedThreadPool.submit(getCallable("fixed thread pool 执行结果"));
                MessageEvent event = null;
                try {
                    while (!future.isDone()) ;
                    event = new MessageEvent(future.get());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    EventCenter.getDefault().post(event);
                }
                break;
            case R.id.act_thread_pool_btn_scheduled_thread_pool:
                final ScheduledFuture<?> scheduledFuture = mScheduledThreadPool.scheduleAtFixedRate(getRunnable("scheduled thread pool 执行结果"), 1,
                        1, TimeUnit.SECONDS);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        scheduledFuture.cancel(true);
                    }
                }, 1000 * 10);
                break;
        }

    }

    public void onEventUI(MessageEvent event) {
        count++;
        LogUtil.d(count + "------" + event.getMessage());
        mTv.setText(count + "------" + event.getMessage());
    }

    private Runnable getRunnable(final String content) {
        return new Runnable() {
            @Override
            public void run() {
                EventCenter.getDefault().post(new MessageEvent(content));
            }
        };
    }

    private Callable<String> getCallable(final String result) {
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                return result;
            }
        };
    }
}