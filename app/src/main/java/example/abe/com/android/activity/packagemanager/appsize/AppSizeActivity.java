package example.abe.com.android.activity.packagemanager.appsize;

import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageStats;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.Formatter;
import android.view.View;

import com.example.BindView;

import java.util.ArrayList;
import java.util.List;

import example.abe.com.android.R;
import example.abe.com.android.activity.packagemanager.commom.AppInfoHelper;
import example.abe.com.android.activity.packagemanager.commom.AppInfoModel;
import example.abe.com.android.activity.packagemanager.commom.AppItemDelegate;
import example.abe.com.android.main.MyApplication;
import example.abe.com.framework.eventcenter.EventCenter;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.recycleview.adapter.BaseAdapter;
import example.abe.com.framework.util.ToastUtil;

import static example.abe.com.android.activity.packagemanager.commom.AppInfoHelper.FILTER_ALL_APP;

public class AppSizeActivity extends BaseActivity implements BaseAdapter.OnItemClickListener<AppInfoModel> {

    @BindView(R.id.act_app_size_rv)
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
        return R.layout.activity_app_size;
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
        //更新显示当前包得大小信息
        AppInfoHelper.getInstance().queryPackageSize(mDataList.get(position).getPkgName(), new PkgSizeObserver());
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
                List<AppInfoModel> list = AppInfoHelper.getInstance().queryFilterAppInfo(FILTER_ALL_APP);
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

    //aidl文件形成的Bindler机制服务类
    public class PkgSizeObserver extends IPackageStatsObserver.Stub {
        /***
         * 回调函数，
         *
         * @param pStats    返回数据封装在PackageStats对象中
         * @param succeeded 代表回调成功
         */
        @Override
        public void onGetStatsCompleted(final PackageStats pStats, boolean succeeded)
                throws RemoteException {
            uiHandler.post(new Runnable() {
                @Override
                public void run() {
                    ToastUtil.makeTextLong("缓存大小:" + formatFileSize(pStats.cacheSize)
                            + "\n数据大小:" + formatFileSize(pStats.dataSize)
                            + "\n程序大小:" + formatFileSize(pStats.codeSize)
                            + "\n应用大小:" + formatFileSize(pStats.codeSize + pStats.dataSize + pStats.cacheSize));
                }
            });
        }
    }

    /**
     * 文件大小单位转化
     */
    private String formatFileSize(long size) {
        return Formatter.formatFileSize(MyApplication.getInstance(), size);
    }
}
