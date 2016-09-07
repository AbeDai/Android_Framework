package example.abe.com.android_framework.activity.volley;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.BindView;

import example.abe.com.android_framework.R;
import example.abe.com.framework.main.BaseActivity;

public class VolleyActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.act_volley_btn_base)
    protected Button mBtnBase;
    @BindView(R.id.act_volley_btn_image)
    protected Button mBtnImage;
    @BindView(R.id.act_volley_btn_custom)
    protected Button mBtnCustom;

    @Override
    public int getLayoutID(){
        return R.layout.activity_volley;
    }

    @Override
    public void initData(){

    }

    @Override
    public void initView(){
        mBtnBase.setOnClickListener(this);
        mBtnImage.setOnClickListener(this);
        mBtnCustom.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.act_volley_btn_base: {
                Intent intent = new Intent(this, VolleyBaseActivity.class);
                startActivity(intent);
            }
            break;

            case R.id.act_volley_btn_image: {
                Intent intent = new Intent(this, VolleyImageActivity.class);
                startActivity(intent);
            }
            break;

            case R.id.act_volley_btn_custom: {
                Intent intent = new Intent(this, VolleyCustomActivity.class);
                startActivity(intent);
            }
            break;
        }
    }
}
