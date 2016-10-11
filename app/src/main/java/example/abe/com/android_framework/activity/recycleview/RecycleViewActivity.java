package example.abe.com.android_framework.activity.recycleview;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.BindView;
import com.example.OnClick;

import java.util.ArrayList;
import java.util.List;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.activity.recycleview.example.BaseAdapterActivity;
import example.abe.com.android_framework.activity.recycleview.example.ImageTextModel;
import example.abe.com.android_framework.activity.recycleview.template.RecycleListActivity;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.recycleview.adapter.BaseAdapter;

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

    @OnClick({R.id.act_recycle_view_example, R.id.act_recycle_view_base_adapter})
    public void onBtnClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.act_recycle_view_example:
                intent.setClass(this, RecycleListActivity.class);
                break;
            case R.id.act_recycle_view_base_adapter:
                intent.setClass(this, BaseAdapterActivity.class);
                break;
        }
        startActivity(intent);
    }
}

