package example.abe.com.android.activity.webview.cookie;

import android.os.Build;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;

import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.util.ToastUtil;

public class CookieActivity extends BaseActivity {

    private static final String URL = "http://www.baidu.com/";

    @Override
    public int getLayoutID() {
        return R.layout.activity_cookie;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
    }

    //设置Cookie内容
    @OnClick(R.id.act_cookie_btn_save_cookie)
    public void saveCookie() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            CookieSyncManager.createInstance(this);
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            String content = "content——content";
            cookieManager.setCookie(URL, "content=" + content + ";domain=" + "baidu.com;" + "   path=" + "/");
            CookieSyncManager.getInstance().sync();
        } else {
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            String token = "content——content";
            cookieManager.setCookie(URL, "content=" + token + ";domain=" + "baidu.com;" + "   path=" + "/");
        }
    }

    //清空Cookie内容
    @OnClick(R.id.act_web_btn_remove_cookie)
    public void removeAllCookie() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            CookieSyncManager.createInstance(this);
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            cookieManager.removeAllCookie();
            CookieSyncManager.getInstance().sync();
        } else {
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            cookieManager.removeAllCookies(new ValueCallback<Boolean>() {
                @Override
                public void onReceiveValue(Boolean value) {
                }
            });
            CookieManager.getInstance().flush();
        }
    }

    //显示指定Cookie
    @OnClick(R.id.act_web_btn_show_cookie)
    public void showCookie(){
        String cookie;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            CookieSyncManager.createInstance(this);
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            cookie = cookieManager.getCookie(URL);
        } else {
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            cookie = cookieManager.getCookie(URL);
        }
        ToastUtil.makeText(cookie);
    }
}