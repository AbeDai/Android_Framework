package example.abe.com.android_framework.activity.multithread;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.BindView;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.activity.eventcenter.MessageEvent;
import example.abe.com.framework.eventcenter.EventCenter;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.util.LogUtil;

public class IntentServiceActivity extends BaseActivity {

    @BindView(R.id.act_intent_service_btn_intent1_start)
    protected Button mBtnStartIntent1;
    @BindView(R.id.act_intent_service_btn_intent2_start)
    protected Button mBtnStartIntent2;
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
        mBtnStartIntent1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent1);
            }
        });

        mBtnStartIntent2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent2);
            }
        });
    }

    public void onEventUI(MessageEvent event){
        String message = event.getMessage();
        LogUtil.d(message);
        mTv.setText(message);
    }
}
