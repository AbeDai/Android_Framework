package example.abe.com.android.activity.okhttp;

import android.content.Intent;
import android.view.View;

import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;

public class OKHttpActivity extends BaseActivity {

    @Override
    public int getLayoutID() {
        return R.layout.activity_okhttp;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
    }

    @OnClick({R.id.act_okhttp_btn_base, R.id.act_okhttp_btn_upload, R.id.act_okhttp_btn_ssl})
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.act_okhttp_btn_base: {
                intent = new Intent(this, OKHttpBaseActivity.class);
            }
            break;

            case R.id.act_okhttp_btn_upload:{
                intent = new Intent(this, OkHttpUploadActivity.class);
            }
            break;

            case R.id.act_okhttp_btn_ssl:{
                intent = new Intent(this, SSLOKHttpActivity.class);
            }
            break;
        }
        startActivity(intent);
    }
}