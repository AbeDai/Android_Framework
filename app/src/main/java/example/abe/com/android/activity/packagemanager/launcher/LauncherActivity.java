package example.abe.com.android.activity.packagemanager.launcher;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

    //更新App数据
    public void onEventUI(NothingModel event) {
        mAdapter.notifyDataSetChanged();
    }

    //后台加载应用
    private void loadAppInfoBg() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<AppInfoModel> list = queryAppInfo();
                if (list != null || list.isEmpty()) {
                    mDataList.clear();
                    mDataList.addAll(list);
                    EventCenter.getDefault().post(new NothingModel());
                }
            }
        }).start();
    }

    /**
     * 获得所有启动Activity的信息，类似于Launch界面
     */
    private List<AppInfoModel> queryAppInfo() {
        //获得PackageManager对象
        PackageManager pm = this.getPackageManager();

        //设置查询条件
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        //通过查询，获得所有ResolveInfo对象.
        List<ResolveInfo> resolveInfos = pm.queryIntentActivities(mainIntent,
                PackageManager.MATCH_DEFAULT_ONLY);

        //调用系统排序，根据name排序
        //该排序很重要，否则只能显示系统应用，而不能列出第三方应用程序
        Collections.sort(resolveInfos, new ResolveInfo.DisplayNameComparator(pm));

        //数据添加
        List<AppInfoModel> list = new ArrayList<>();
        for (ResolveInfo reInfo : resolveInfos) {
            //获得该应用程序启动Activity的Name
            String activityName = reInfo.activityInfo.name;
            //获得应用程序的包名
            String pkgName = reInfo.activityInfo.packageName;
            //获得应用程序的Label
            String appLabel = (String) reInfo.loadLabel(pm);
            //获得应用程序图标
            Drawable icon = reInfo.loadIcon(pm);
            //为应用程序的启动Activity 准备Intent
            Intent launchIntent = new Intent();
            launchIntent.setComponent(new ComponentName(pkgName, activityName));
            //创建一个AppInfo对象，并赋值
            AppInfoModel appInfo = new AppInfoModel();
            appInfo.setAppLabel(appLabel);
            appInfo.setPkgName(pkgName);
            appInfo.setAppIcon(icon);
            appInfo.setIntent(launchIntent);
            //添加至列表中
            list.add(appInfo);
        }

        return list;
    }
}
