package example.abe.com.android.activity.touch;

import android.content.Intent;
import android.view.View;

import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.android.activity.touch.gesture.GestureActivity;
import example.abe.com.android.activity.touch.scalegesture.ScaleGestureActivity;
import example.abe.com.android.activity.touch.velocitytrack.VelocityTrackerActivity;
import example.abe.com.framework.main.BaseActivity;

public class TouchActivity extends BaseActivity {

    @Override
    public int getLayoutID(){
        return R.layout.activity_touch;
    }

    @Override
    public void initData(){
    }

    @Override
    public void initView(){
    }

    @OnClick({R.id.act_draw_btn_scale_gesture, R.id.act_btn_gesture, R.id.act_btn_velocity_tracker})
    public void toNextActivity(View view){
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.act_draw_btn_scale_gesture:
                intent.setClass(this, ScaleGestureActivity.class);
                break;
            case R.id.act_btn_gesture:
                intent.setClass(this, GestureActivity.class);
                break;
            case R.id.act_btn_velocity_tracker:
                intent.setClass(this, VelocityTrackerActivity.class);
                break;

        }
        startActivity(intent);
    }
}
