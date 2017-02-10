package example.abe.com.android.activity.retrofit;


import com.google.gson.JsonElement;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by abe on 16/5/22.
 */
public interface Service {
        @POST("/login")
        Call<JsonElement> getLogin(@QueryMap HashMap<String, String> options);

        @POST("/logout")
        Call<JsonElement> getLogout(@QueryMap HashMap<String, String> options);
}