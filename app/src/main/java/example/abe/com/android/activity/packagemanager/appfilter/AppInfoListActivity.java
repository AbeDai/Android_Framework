package example.abe.com.android.activity.packagemanager.appfilter;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.BindView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import example.abe.com.android.R;
import example.abe.com.android.activity.packagemanager.commom.AppInfoModel;
import example.abe.com.android.activity.packagemanager.commom.AppItemDelegate;
import example.abe.com.android.activity.packagemanager.commom.NothingModel;
import example.abe.com.framework.eventcenter.EventCenter;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.recycleview.adapter.BaseAdapter;

public class AppInfoListActivity extends BaseActivity {

    public static final int FILTER_ALL_APP = 0;//所有应用程序
    public static final int FILTER_SYSTEM_APP = 1;//系统程序
    public static final int FILTER_THIRD_APP = 2;//第三方应用程序
    public static final int FILTER_SDCARD_APP = 3;//安装在SDCard的应用程序
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

    //更新App数据
    public void onEventUI(NothingModel event) {
        mAdapter.notifyDataSetChanged();
    }

    //后台加载应用
    private void loadAppInfoBg(final int filter) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<AppInfoModel> list = queryFilterAppInfo(filter);
                if (list != null || list.isEmpty()){
                    mDataList.clear();
                    mDataList.addAll(list);
                    EventCenter.getDefault().post(new NothingModel());
                }
            }
        }).start();
    }

    /**
     * 根据查询条件，查询特定的ApplicationInfo
     *
     * @param filter 查询条件
     * @return 查询结果
     */
    private List<AppInfoModel> queryFilterAppInfo(int filter) {
        // 获得PackageManager对象
        pm = getPackageManager();

        // 查询所有已经安装的应用程序
        List<ApplicationInfo> listApplications = pm.getInstalledApplications(
                PackageManager.GET_UNINSTALLED_PACKAGES);

        // 调用系统排序，根据name排序
        // 该排序很重要，否则只能显示系统应用，而不能列出第三方应用程序
        Collections.sort(listApplications, new ApplicationInfo.DisplayNameComparator(pm));

        // 保存过滤查到的AppInfo
        List<AppInfoModel> appInfoList = new ArrayList<>();

        // 根据条件来过滤
        switch (filter) {
            case FILTER_ALL_APP: // 所有应用程序
                for (ApplicationInfo app : listApplications) {
                    appInfoList.add(getAppInfo(app));
                }
                break;
            case FILTER_SYSTEM_APP: // 系统程序
                for (ApplicationInfo app : listApplications) {
                    if ((app.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                        appInfoList.add(getAppInfo(app));
                    }
                }
                break;
            case FILTER_THIRD_APP: // 第三方应用程序
                for (ApplicationInfo app : listApplications) {
                    //非系统程序
                    if ((app.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
                        appInfoList.add(getAppInfo(app));
                    }
                    //本来是系统程序，被用户手动更新后，该系统程序也成为第三方应用程序了
                    else if ((app.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
                        appInfoList.add(getAppInfo(app));
                    }
                }
                break;
            case FILTER_SDCARD_APP: // 安装在SDCard的应用程序
                for (ApplicationInfo app : listApplications) {
                    if ((app.flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) != 0) {
                        appInfoList.add(getAppInfo(app));
                    }
                }
                break;
        }
        return appInfoList;
    }

    /**
     * 构造AppInfo对象
     */
    private AppInfoModel getAppInfo(ApplicationInfo app) {
        AppInfoModel appInfo = new AppInfoModel();
        appInfo.setAppLabel((String) app.loadLabel(pm));
        appInfo.setAppIcon(app.loadIcon(pm));
        appInfo.setPkgName(app.packageName);
        return appInfo;
    }
}
