package example.abe.com.android.activity.packagemanager.appfilter;

import android.content.Intent;
import android.view.View;

import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;

import static example.abe.com.android.activity.packagemanager.commom.AppInfoHelper.FILTER_ALL_APP;
import static example.abe.com.android.activity.packagemanager.commom.AppInfoHelper.FILTER_SDCARD_APP;
import static example.abe.com.android.activity.packagemanager.commom.AppInfoHelper.FILTER_SYSTEM_APP;
import static example.abe.com.android.activity.packagemanager.commom.AppInfoHelper.FILTER_THIRD_APP;

public class AppFilterActivity extends BaseActivity {

    @Override
    public int getLayoutID(){
        return R.layout.activity_app_filter;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView(){
    }

    @OnClick({R.id.act_filter_btn_all_app, R.id.act_filter_btn_third_app,
            R.id.act_filter_btn_system_app, R.id.act_package_btn_sdcard_app})
    public void onBtnClick(View v){
        int filter = FILTER_ALL_APP;
        switch (v.getId()) {
            case R.id.act_filter_btn_all_app:
                filter = FILTER_ALL_APP;
                break;
            case R.id.act_filter_btn_system_app:
                filter = FILTER_SYSTEM_APP;
                break;
            case R.id.act_filter_btn_third_app:
                filter = FILTER_THIRD_APP;
                break;
            case R.id.act_package_btn_sdcard_app:
                filter = FILTER_SDCARD_APP;
                break;
        }
        Intent intent = new Intent(getBaseContext(), AppInfoListActivity.class);
        intent.putExtra(AppInfoListActivity.INTENT_NAME_FILTER, filter);
        startActivity(intent);
    }
}
