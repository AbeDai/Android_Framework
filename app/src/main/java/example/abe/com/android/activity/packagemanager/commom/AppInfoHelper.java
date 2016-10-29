package example.abe.com.android.activity.packagemanager.commom;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Process;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import example.abe.com.android.main.MyApplication;

import static android.os.Build.VERSION_CODES.JELLY_BEAN_MR1;

/**
 * Created by abe on 16/10/29.
 */

public class AppInfoHelper {

    public static final int FILTER_ALL_APP = 0;//所有应用程序
    public static final int FILTER_SYSTEM_APP = 1;//系统程序
    public static final int FILTER_THIRD_APP = 2;//第三方应用程序
    public static final int FILTER_SDCARD_APP = 3;//安装在SDCard的应用程序
    private static AppInfoHelper instance;//单例

    private PackageManager mPm;

    private AppInfoHelper(){
        //获得PackageManager对象
        mPm = MyApplication.getInstance().getPackageManager();
    }

    public static AppInfoHelper getInstance(){
        if (instance == null){
            synchronized (AppInfoHelper.class){
                if (instance == null){
                    instance = new AppInfoHelper();
                }
            }
        }
        return instance;
    }

    /**
     * 获得所有启动Activity的信息
     */
    public List<AppInfoModel> queryAllAppInfo() {
        //设置查询条件
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        //通过查询，获得所有ResolveInfo对象.
        List<ResolveInfo> resolveInfos = mPm.queryIntentActivities(mainIntent,
                PackageManager.MATCH_DEFAULT_ONLY);

        //调用系统排序，根据name排序
        //该排序很重要，否则只能显示系统应用，而不能列出第三方应用程序
        Collections.sort(resolveInfos, new ResolveInfo.DisplayNameComparator(mPm));

        //数据添加
        List<AppInfoModel> list = new ArrayList<>();
        for (ResolveInfo reInfo : resolveInfos) {
            //添加至列表中
            list.add(getAppInfo(reInfo));
        }

        return list;
    }

    /**
     * 根据查询条件，查询特定的ApplicationInfo
     *
     * @param filter 查询条件
     * @return 查询结果
     */
    public List<AppInfoModel> queryFilterAppInfo(int filter) {
        // 查询所有已经安装的应用程序
        List<ApplicationInfo> listApplications = mPm.getInstalledApplications(
                PackageManager.GET_UNINSTALLED_PACKAGES);

        // 调用系统排序，根据name排序
        // 该排序很重要，否则只能显示系统应用，而不能列出第三方应用程序
        Collections.sort(listApplications, new ApplicationInfo.DisplayNameComparator(mPm));

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
     * 获取应用大小
     *
     * @param pkgName
     */
    public void queryPackageSize(String pkgName, IPackageStatsObserver.Stub observer) {
        if (pkgName != null) {
            //使用放射机制得到PackageManager类的隐藏函数getPackageSizeInfo
            try {
                if (Build.VERSION.SDK_INT < JELLY_BEAN_MR1){//系统版本小于4.2
                    //通过反射机制获得该隐藏函数
                    Method getPackageSizeInfo = mPm.getClass().getDeclaredMethod("getPackageSizeInfo",
                            String.class, IPackageStatsObserver.class);
                    //调用该函数，并且给其分配参数 ，待调用流程完成后会回调PkgSizeObserver类的函数
                    getPackageSizeInfo.invoke(mPm, pkgName, observer);
                }else {//系统版本大于等于4.2
                    //通过反射机制获得该隐藏函数
                    Method getPackageSizeInfo = mPm.getClass().getDeclaredMethod("getPackageSizeInfo",
                            String.class, int.class, IPackageStatsObserver.class);
                    //调用该函数，并且给其分配参数 ，待调用流程完成后会回调PkgSizeObserver类的函数
                    getPackageSizeInfo.invoke(mPm, pkgName, Process.myUid() / 100000, observer);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 构造AppInfo对象
     */
    private AppInfoModel getAppInfo(ApplicationInfo app) {
        AppInfoModel appInfo = new AppInfoModel();
        appInfo.setAppLabel((String) app.loadLabel(mPm));
        appInfo.setAppIcon(app.loadIcon(mPm));
        appInfo.setPkgName(app.packageName);
        return appInfo;
    }

    /**
     * 构造AppInfo对象
     */
    private AppInfoModel getAppInfo(ResolveInfo reInfo) {
        //获得该应用程序启动Activity的Name
        String activityName = reInfo.activityInfo.name;
        //获得应用程序的包名
        String pkgName = reInfo.activityInfo.packageName;
        //获得应用程序的Label
        String appLabel = (String) reInfo.loadLabel(mPm);
        //获得应用程序图标
        Drawable icon = reInfo.loadIcon(mPm);
        //为应用程序的启动Activity 准备Intent
        Intent launchIntent = new Intent();
        launchIntent.setComponent(new ComponentName(pkgName, activityName));
        //创建一个AppInfo对象，并赋值
        AppInfoModel appInfo = new AppInfoModel();
        appInfo.setAppLabel(appLabel);
        appInfo.setPkgName(pkgName);
        appInfo.setAppIcon(icon);
        appInfo.setIntent(launchIntent);
        return appInfo;
    }


}
