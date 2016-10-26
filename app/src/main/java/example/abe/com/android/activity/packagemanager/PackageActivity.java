package example.abe.com.android.activity.packagemanager;

import android.content.Intent;
import android.view.View;

import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.android.activity.packagemanager.appfilter.AppFilterActivity;
import example.abe.com.android.activity.packagemanager.launcher.LauncherActivity;
import example.abe.com.framework.main.BaseActivity;

public class PackageActivity extends BaseActivity {

    @Override
    public int getLayoutID(){
        return R.layout.activity_package;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView(){

    }

    @OnClick({R.id.act_package_btn_app_search, R.id.act_package_btn_app_launcher})
    public void onBtnClick(View v){
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.act_package_btn_app_search:
                intent.setClass(this, AppFilterActivity.class);
                break;

            case R.id.act_package_btn_app_launcher:
                intent.setClass(this, LauncherActivity.class);
                break;
        }
        startActivity(intent);
    }
}
