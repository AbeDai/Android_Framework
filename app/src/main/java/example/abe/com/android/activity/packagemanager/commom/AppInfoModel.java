package example.abe.com.android.activity.packagemanager.commom;

import android.content.Intent;
import android.graphics.drawable.Drawable;

public class AppInfoModel {

    private String appLabel;//应用名
    private Drawable appIcon;//应用icon
    private Intent intent;//应用启动页
    private String pkgName;//包名

    public AppInfoModel() {
    }

    public String getAppLabel() {
        return appLabel;
    }

    public void setAppLabel(String appName) {
        this.appLabel = appName;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }
}
