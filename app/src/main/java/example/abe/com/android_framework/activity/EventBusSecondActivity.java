package example.abe.com.android_framework.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import example.abe.com.android_framework.Event.FirstEvent;
import example.abe.com.android_framework.Event.SecondEvent;
import example.abe.com.android_framework.Event.ThirdEvent;
import example.abe.com.android_framework.R;

public class EventBusSecondActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_second);

        findViewById(R.id.act_third_tv_event_1).setOnClickListener(this);
        findViewById(R.id.act_third_tv_event_2).setOnClickListener(this);
        findViewById(R.id.act_third_tv_event_3).setOnClickListener(this);
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
}
