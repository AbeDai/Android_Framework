package example.abe.com.android_framework.activity.volley;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.BindView;
import com.example.OnClick;

import example.abe.com.android_framework.R;
import example.abe.com.framework.main.BaseActivity;

public class VolleyActivity extends BaseActivity{

    @Override
    public int getLayoutID(){
        return R.layout.activity_volley;
    }

    @Override
    public void initData(){
    }

    @Override
    public void initView(){
    }

    @OnClick({R.id.act_volley_btn_base, R.id.act_volley_btn_image, R.id.act_volley_btn_custom})
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.act_volley_btn_base: {
                intent = new Intent(this, VolleyBaseActivity.class);
            }
            break;

            case R.id.act_volley_btn_image: {
                intent = new Intent(this, VolleyImageActivity.class);
            }
            break;

            case R.id.act_volley_btn_custom: {
                intent = new Intent(this, VolleyCustomActivity.class);
            }
            break;
        }
        startActivity(intent);
    }
}
