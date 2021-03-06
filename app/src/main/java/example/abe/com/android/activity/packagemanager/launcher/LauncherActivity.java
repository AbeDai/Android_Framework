package example.abe.com.android.activity.packagemanager.launcher;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.BindView;

import java.util.ArrayList;
import java.util.List;

import example.abe.com.android.R;
import example.abe.com.android.activity.packagemanager.commom.AppInfoHelper;
import example.abe.com.android.activity.packagemanager.commom.AppInfoModel;
import example.abe.com.android.activity.packagemanager.commom.AppItemDelegate;
import example.abe.com.framework.eventcenter.EventCenter;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.recycleview.adapter.BaseAdapter;

public class LauncherActivity extends BaseActivity implements BaseAdapter.OnItemClickListener<AppInfoModel> {

    @BindView(R.id.act_launcher_rv)
    protected RecyclerView mRv;
    private BaseAdapter<AppInfoModel> mAdapter;
    private List<AppInfoModel> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventCenter.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventCenter.getDefault().unRigister(this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_launcher;
    }

    @Override
    public void initData() {
        mDataList = new ArrayList<>();
    }

    @Override
    public void initView() {
        mAdapter = new BaseAdapter<>(this, mDataList);
        mAdapter.addItemViewDelegate(new AppItemDelegate());
        mAdapter.setOnItemClickListener(this);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(mAdapter);

        //获取所有信息
        loadAppInfoBg();
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, AppInfoModel data, int position) {
        startActivity(data.getIntent());
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, AppInfoModel data, int position) {
        return false;
    }

    //后台加载应用
    private void loadAppInfoBg() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<AppInfoModel> list = AppInfoHelper.getInstance().queryAllAppInfo();
                if (list != null || list.isEmpty()) {
                    mDataList.clear();
                    mDataList.addAll(list);

                    //UI操作
                    uiHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        }).start();
    }
}
