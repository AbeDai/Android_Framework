package example.abe.com.android.activity.webview.jsnative;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.BindView;
import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.util.ToastUtil;

public class WebViewActivity extends BaseActivity {

    private static final String URL = "file:///android_asset/html/test.html";
    @BindView(R.id.act_web_view_webview)
    protected WebView mWebView;
    private int mBackCount = 3;

    @Override
    public int getLayoutID() {
        return R.layout.activity_web_view;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
        WebSettings webSettings = mWebView.getSettings();
        //支持Js方法调用
        webSettings.setJavaScriptEnabled(true);
        //设置Encoding编码
        webSettings.setDefaultTextEncodingName("utf-8");
        //加载网页
        mWebView.loadUrl(URL);
        //添加JS调用接口
        mWebView.addJavascriptInterface(new JsAction(this), "action");
        //设置网页监听客户端
        mWebView.setWebViewClient(webViewClient);
    }

    //WebView操作监听
    private WebViewClient webViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //拦截URL跳转
            if (url != null && url.equals("http://www.baidu.com/")) {
                ToastUtil.makeText("跳转网页：http://www.baidu.com");
                return true;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }
    };

    @OnClick(R.id.act_web_view_btn_change_color)
    public void onChangeBgColorAction() {
        //带参数调用JS方法
        String color = "#cccccc";
        mWebView.loadUrl("javascript: changeColor('" + color + "')");
    }

    @OnClick(R.id.act_web_view_btn_get_title)
    public void onChangeWebViewTitle() {
        //带参数与返回值调用JS方法
        mWebView.evaluateJavascript("sum(1,2)", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                ToastUtil.makeText("sum(1,2) = " + value);
            }
        });
    }

    /**
     * JS调用Android原生方法接口实现类
     */
    public class JsAction {

        private Activity activity;

        private JsAction(Activity activity) {
            this.activity = activity;
        }

        @JavascriptInterface
        public String back() {
            if (mBackCount > 0) {
                mBackCount--;
                return "是否确认返回(" + mBackCount + ")";
            } else {
                if (activity != null) {
                    activity.finish();
                }
                return "返回成功";
            }
        }

        @JavascriptInterface
        public void showDialog(int title, String content) {
            new AlertDialog.Builder(activity)
                    .setTitle(String.valueOf(title))
                    .setMessage(content)
                    .setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create()
                    .show();
        }
    }
}
