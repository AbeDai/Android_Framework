package example.abe.com.android.activity.packagemanager.appfilter;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

import static example.abe.com.android.activity.packagemanager.commom.AppInfoHelper.FILTER_ALL_APP;

public class AppInfoListActivity extends BaseActivity {

    public static final String INTENT_NAME_FILTER = "intent_name_filter";//过滤类型

    @BindView(R.id.act_app_info_list_rv)
    protected RecyclerView mRv;
    private BaseAdapter<AppInfoModel> mAdapter;
    private List<AppInfoModel> mDataList;
    private PackageManager pm;
    private int filter;

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
        return R.layout.activity_app_info_list;
    }

    @Override
    public void initData() {
        if (getIntent() != null) {
            filter = getIntent().getIntExtra(INTENT_NAME_FILTER, FILTER_ALL_APP);
        }
        mDataList = new ArrayList<>();
    }

    @Override
    public void initView() {
        mAdapter = new BaseAdapter<>(this, mDataList);
        mAdapter.addItemViewDelegate(new AppItemDelegate());
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(mAdapter);

        loadAppInfoBg(filter);
    }

    //后台加载应用
    private void loadAppInfoBg(final int filter) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<AppInfoModel> list = AppInfoHelper.getInstance().queryFilterAppInfo(filter);
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
