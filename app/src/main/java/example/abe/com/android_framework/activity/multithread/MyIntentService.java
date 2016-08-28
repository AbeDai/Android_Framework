package example.abe.com.android_framework.activity.multithread;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;

import example.abe.com.android_framework.activity.eventcenter.MessageEvent;
import example.abe.com.framework.eventcenter.EventCenter;
import example.abe.com.framework.util.LogUtil;
import example.abe.com.framework.viewinject.ViewInjectUtil;

/**
 * Created by abe on 16/8/28.
 */
public class MyIntentService extends IntentService {

    public static final String TASK_KEY_ID = "task_id";
    public static final int TASK_ID_ONE = 1;
    public static final int TASK_ID_TWO = 2;
    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //根据Intent处理不同的事务
        int taskId = intent.getIntExtra(TASK_KEY_ID, -1);
        switch (taskId) {
            case TASK_ID_ONE:
                EventCenter.getDefault().post(new MessageEvent("do task1"));
                break;
            case TASK_ID_TWO:
                EventCenter.getDefault().post(new MessageEvent("do task2"));
                break;
            default:
                EventCenter.getDefault().post(new MessageEvent("error"));
                break;
        }
    }

    /** 生命周期 **/
    @Override
    public void onCreate() {
        LogUtil.d("onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.d("onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        LogUtil.d("onDestroy");
        super.onDestroy();
    }
}