package example.abe.com.android.activity.retrofit;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import example.abe.com.android.model.BaseModel;
import example.abe.com.android.utils.ApiUtil;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by abe on 16/5/22.
 */
public class NetworkPresent {

    public interface ABCallback {
        void onSuccess(BaseModel data);
        void onFailure(BaseModel data);
    }

    public static final int WRITE_TIME_OUT_SEC = 60;
    public static final int READ_TIME_OUT_SEC = 30;
    private static Retrofit sRetrofit;

    public static Retrofit getRetrofit() {
        if (sRetrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);//开启log日志记录
            OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                    .writeTimeout(WRITE_TIME_OUT_SEC, TimeUnit.SECONDS)
                    .readTimeout(READ_TIME_OUT_SEC, TimeUnit.SECONDS)
                    .addInterceptor(logging)
                    .build();//用于拦截

            sRetrofit = new Retrofit.Builder()
                    .baseUrl(ApiUtil.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(mOkHttpClient)
                    .build();
        }
        return sRetrofit;
    }

    public static void getLogin(String username, String password, final ABCallback abCallback) {
        HashMap<String, String> args = new HashMap<>();
        args.put("username", username);
        args.put("password", password);
        handleCall(getRetrofit().create(Service.class).getLogin(args), abCallback);
    }

    public static void getLogout(String username, final ABCallback abCallback) {
        HashMap<String, String> args = new HashMap<>();
        args.put("username", username);
        handleCall(getRetrofit().create(Service.class).getLogout(args), abCallback);
    }

    private static void handleCall(Call<JsonElement> call, final ABCallback abCallback) {
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (abCallback == null) return;

                if (response.isSuccessful() && response.body() != null) {
                    BaseModel data = new Gson().fromJson(response.body(), BaseModel.class);
                    abCallback.onSuccess(data);
                } else {
                    abCallback.onSuccess(NetworkPresent.getFailedBaseModel());
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                abCallback.onFailure(NetworkPresent.getFailedBaseModel());
            }
        });
    }

    private static BaseModel getFailedBaseModel() {
        BaseModel baseModel = new BaseModel();
        baseModel.setData("");
        baseModel.setError(-1);
        baseModel.setMsg("服务端操作数据库出现异常");
        return baseModel;
    }
}


