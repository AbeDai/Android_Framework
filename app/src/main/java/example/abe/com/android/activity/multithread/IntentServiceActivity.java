package example.abe.com.android.activity.multithread;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.BindView;
import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.android.activity.eventcenter.MessageEvent;
import example.abe.com.framework.eventcenter.EventCenter;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.util.LogUtil;

public class IntentServiceActivity extends BaseActivity {

    @BindView(R.id.act_intent_service_tv)
    protected TextView mTv;
    private Intent intent1;
    private Intent intent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventCenter.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventCenter.getDefault().unRigister(this);
    }

    @Override
    public int getLayoutID(){
        return R.layout.activity_intent_service;
    }

    @Override
    public void initData() {
        intent1 = new Intent(IntentServiceActivity.this, MyIntentService.class);
        intent1.putExtra(MyIntentService.TASK_KEY_ID, MyIntentService.TASK_ID_ONE);

        intent2 = new Intent(IntentServiceActivity.this, MyIntentService.class);
        intent2.putExtra(MyIntentService.TASK_KEY_ID, MyIntentService.TASK_ID_TWO);
    }

    @Override
    public void initView() {
    }

    @OnClick(R.id.act_intent_service_btn_intent1_start)
    public void startIntent1(){
        startService(intent1);
    }

    @OnClick(R.id.act_intent_service_btn_intent2_start)
    public void startIntent2(){
        startService(intent2);
    }

    public void onEventUI(MessageEvent event){
        String message = event.getMessage();
        LogUtil.d(message);
        mTv.setText(message);
    }
}
