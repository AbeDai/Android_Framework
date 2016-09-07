package example.abe.com.android_framework.activity.multithread;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.activity.eventcenter.MessageEvent;
import example.abe.com.framework.eventcenter.EventCenter;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.util.LogUtil;
import example.abe.com.framework.viewinject.annotation.ContentView;
import example.abe.com.framework.viewinject.ViewInject;

@ContentView(id = R.layout.activity_intent_service)
public class IntentServiceActivity extends BaseActivity {

    @ViewInject(id = R.id.act_intent_service_btn_intent1_start)
    private Button mBtnStartIntent1;
    @ViewInject(id = R.id.act_intent_service_btn_intent2_start)
    private Button mBtnStartIntent2;
    @ViewInject(id = R.id.act_intent_service_tv)
    private TextView mTv;
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
