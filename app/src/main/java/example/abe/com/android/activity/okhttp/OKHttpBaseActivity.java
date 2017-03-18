package example.abe.com.android.activity.okhttp;

import android.view.View;
import android.widget.EditText;

import com.example.BindView;
import com.example.OnClick;

import java.io.IOException;

import example.abe.com.android.R;
import example.abe.com.android.utils.ApiUtil;
import example.abe.com.framework.main.BaseActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OKHttpBaseActivity extends BaseActivity {

    @BindView(R.id.act_okhttp_base_et_show)
    protected EditText mEtShow;

    private OkHttpClient mClient = new OkHttpClient();

    private String mGetUrl;
    private String mPostUrl;

    @Override
    public int getLayoutID() {
        return R.layout.activity_okhttp_base;
    }

    @Override
    public void initData() {
        mGetUrl = ApiUtil.BASE_URL + ApiUtil.PATTERN_LOGIN + "?username=daiyibo&password=123";
        mPostUrl = ApiUtil.BASE_URL + ApiUtil.PATTERN_LOGOUT;
    }

    @Override
    public void initView() {
    }

    @OnClick({R.id.act_okhttp_base_btn_sync_get_json})
    public void onSyncGet(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Request.Builder builder = new Request.Builder();
                    builder = builder.url(mGetUrl);
                    Request request = builder.build();
                    Call call = mClient.newCall(request);
                    Response response = call.execute();
                    if (response.isSuccessful()) {
                        setShowText(response.body().string());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @OnClick(R.id.act_okhttp_base_btn_sync_post_json)
    public void onSyncPost() {
        new Thread(new Runnable() {
            @Override
            public void run(){
                try {
                    RequestBody body = new FormBody.Builder()
                            .add("username", "daiyibo")
                            .build();
                    Request request = new Request.Builder()
                            .url(mPostUrl)
                            .post(body)
                            .build();
                    Response response = mClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        setShowText(response.body().string());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @OnClick(R.id.act_okhttp_base_btn_async_get_json)
    public void onAsyncGet() {
        Request request = new Request.Builder()
                .url(mGetUrl)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                setShowText(response.body().string());
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
        });
    }

    @OnClick(R.id.act_okhttp_base_btn_async_post_json)
    public void onAsyncPost() {
        RequestBody body = new FormBody.Builder()
                .add("username", "daiyibo")
                .build();
        Request request = new Request.Builder()
                .url(mPostUrl)
                .post(body)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                setShowText(response.body().string());
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void setShowText(final String str){
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                mEtShow.setText(str);
            }
        });
    }
}