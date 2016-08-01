package example.abe.com.android_framework.activity.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by abe on 16/8/1.
 */
public class GsonRequest<T> extends Request<T> {

    private final Response.Listener<T> mListener;
    private Class<T> mClazz;
    private final int mMethod;
    private Map<String, String> mParams;

    public GsonRequest(int method, String url, Class<T> clazz, Response.Listener<T> listener,
                      Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener = listener;
        mClazz = clazz;
        mMethod = method;
        mParams = new HashMap<>();
    }

    public GsonRequest(String url, Class<T>clazz, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        this(Method.GET, url, clazz, listener, errorListener);
    }

    /**
     * 为Post添加参数
     * @param key 键
     * @param value 值
     */
    public GsonRequest addParam(String key, String value){
        mParams.put(key, value);
        return this;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonStr = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            Gson gson = new Gson();
            T model = gson.fromJson(jsonStr, mClazz);
            return Response.success(model, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        if (mMethod == Method.POST){
            return mParams;
        }
        return null;
    }
}
