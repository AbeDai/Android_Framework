package example.abe.com.android_framework.activity.recycleview;

import android.content.Intent;
import android.view.View;

import com.example.OnClick;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.activity.recycleview.example.baseadapter.BaseAdapterActivity;
import example.abe.com.android_framework.activity.recycleview.example.loadadapter.LoadAndRefreshActivity;
import example.abe.com.android_framework.activity.recycleview.example.wrapperadapter.WrapperAdapterActivity;
import example.abe.com.android_framework.activity.recycleview.template.RecycleListActivity;
import example.abe.com.framework.main.BaseActivity;

public class RecycleViewActivity extends BaseActivity {

    @Override
    public int getLayoutID(){
        return R.layout.activity_recycle_view;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView(){
    }

    @OnClick({R.id.act_recycle_view_example, R.id.act_recycle_view_base_adapter,
            R.id.act_recycle_view_wrapper_adapter, R.id.act_recycle_view_load_and_refresh})
    public void onBtnClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.act_recycle_view_example:
                intent.setClass(this, RecycleListActivity.class);
                break;
            case R.id.act_recycle_view_base_adapter:
                intent.setClass(this, BaseAdapterActivity.class);
                break;
            case R.id.act_recycle_view_wrapper_adapter:
                intent.setClass(this, WrapperAdapterActivity.class);
                break;
            case R.id.act_recycle_view_load_and_refresh:
                intent.setClass(this, LoadAndRefreshActivity.class);
                break;
        }
        startActivity(intent);
    }
}

