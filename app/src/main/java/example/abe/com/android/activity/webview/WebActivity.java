package example.abe.com.android.activity.webview;

import android.content.Intent;
import android.view.View;

import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.android.activity.webview.cookie.CookieActivity;
import example.abe.com.android.activity.webview.jsnative.WebViewActivity;
import example.abe.com.framework.main.BaseActivity;

public class WebActivity extends BaseActivity {

    @Override
    public int getLayoutID() {
        return R.layout.activity_web;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
    }

    @OnClick({R.id.act_web_btn_h5_native, R.id.act_web_btn_web_cookie})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.act_web_btn_h5_native:
                intent.setClass(this, WebViewActivity.class);
                break;
            case R.id.act_web_btn_web_cookie:
                intent.setClass(this, CookieActivity.class);
                break;
        }
        startActivity(intent);
    }

}
