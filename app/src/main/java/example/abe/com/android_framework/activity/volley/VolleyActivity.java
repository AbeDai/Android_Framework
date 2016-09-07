package example.abe.com.android_framework.activity.volley;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import example.abe.com.android_framework.R;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.viewinject.annotation.ContentView;
import example.abe.com.framework.viewinject.ViewInject;

@ContentView(id = R.layout.activity_volley)
public class VolleyActivity extends BaseActivity implements View.OnClickListener {

    @ViewInject(id = R.id.act_volley_btn_base)
    private Button mBtnBase;
    @ViewInject(id = R.id.act_volley_btn_image)
    private Button mBtnImage;
    @ViewInject(id = R.id.act_volley_btn_custom)
    private Button mBtnCustom;

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
