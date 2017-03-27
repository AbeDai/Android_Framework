package example.abe.com.android.activity.okhttp;

import android.widget.TextView;

import com.example.BindView;
import com.example.OnClick;

import org.apache.commons.io.output.StringBuilderWriter;

import java.io.IOException;
import java.io.PrintWriter;

import example.abe.com.android.R;
import example.abe.com.android.utils.ApiUtil;
import example.abe.com.framework.main.BaseActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpUploadActivity extends BaseActivity {

    private OkHttpClient mClient = new OkHttpClient();

    @BindView(R.id.act_ok_http_load_tv_show)
    protected TextView mTvShow;

    @Override
    public int getLayoutID() {
        return R.layout.activity_okhttp_upload;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
    }

    @OnClick(R.id.act_ok_http_load_btn_upload)
    protected void onUploadFileClick() {
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), "aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa__aaaaaaaa");
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addPart(fileBody)
                .build();
        Request request = new Request.Builder()
                .url(ApiUtil.BASE_URL + ApiUtil.PATTERN_UPLOAD)
                .post(requestBody)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                StringBuilderWriter writer = new StringBuilderWriter();
                PrintWriter printWriter = new PrintWriter(writer);
                e.printStackTrace(printWriter);
                setShowText(printWriter.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                setShowText(response.body().string());
            }
        });
    }

    public void setShowText(final String str){
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                mTvShow.setText(str);
            }
        });
    }
}
