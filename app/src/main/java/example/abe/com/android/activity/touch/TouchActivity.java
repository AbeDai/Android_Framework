package example.abe.com.android.activity.touch;

import android.content.Intent;
import android.view.View;

import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.android.activity.touch.scalegesture.ScaleGestureActivity;
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

    @OnClick({R.id.act_draw_btn_scale_gesture})
    public void toNextActivity(View view){
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.act_draw_btn_scale_gesture:
                intent.setClass(this, ScaleGestureActivity.class);
                break;
        }
        startActivity(intent);
    }
}
