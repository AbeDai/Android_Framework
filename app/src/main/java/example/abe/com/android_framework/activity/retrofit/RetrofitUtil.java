package example.abe.com.android_framework.activity.retrofit;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

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

public class RetrofitUtil {

    public static final String BASE_URL = "http://121.196.244.159:8081/";
    public static final int WRITE_TIME_OUT_SEC = 60;
    public static final int READ_TIME_OUT_SEC = 30;
    private static Retrofit sRetrofit;
    private static Service sService;

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
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(mOkHttpClient)
                    .build();
        }
        return sRetrofit;
    }

    public static Service getService() {
        if (sService == null) {
            sService = getRetrofit().create(Service.class);
        }
        return sService;
    }

    public static <T> void handleCall(final ABCallback abCallback, HashMap<String, String> args, final Class<T> classOfT) {
        Call<JsonElement> call = getService().getData(args);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (abCallback == null) return;

                if (response.isSuccessful() && response.body() != null) {
                    //json解析
                    JsonElement body = response.body();
                    JsonArray jsonObjs = body.getAsJsonArray();
                    JsonObject jsonObj = (JsonObject) jsonObjs.get(0);
                    JsonArray dataJsonArray = jsonObj.getAsJsonArray("data");

                    //获取数据
                    int retVal = jsonObj.get("retVal").getAsInt();
                    T data = new Gson().fromJson(dataJsonArray.get(0), classOfT);

                    //调用接口
                    if (retVal != 1) {
                        abCallback.onFailure("接口返回失败");
                    } else {
                        abCallback.onSuccess(data);
                    }

                } else {//response 返回为报错 如 404 or 500 错误
                    String message = "";
                    try {
                        message = response.errorBody().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    abCallback.onFailure(message);
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                if (abCallback == null) return;

                if (t instanceof IOException) {
                    abCallback.onFailure("网络不给力，请稍后再试");
                } else {
                    abCallback.onFailure("服务器异常，请稍后再试");
                }
            }
        });
    }

    public interface ABCallback {
        <T> void onSuccess(T data);
        void onFailure(String message);
    }
}