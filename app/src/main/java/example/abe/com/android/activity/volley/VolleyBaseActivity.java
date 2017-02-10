package example.abe.com.android.activity.volley;

import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.BindView;
import com.example.OnClick;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import example.abe.com.android.R;
import example.abe.com.android.utils.ApiUtil;
import example.abe.com.framework.main.BaseActivity;

public class VolleyBaseActivity extends BaseActivity {

    private String mGetUrl;
    private String mPostUrl;
    private Map<String, String> mPostParamMap;
    private RequestQueue mQueue;
    @BindView(R.id.act_volley_base_et_show)
    protected EditText mEtShow;

    @Override
    public int getLayoutID() {
        return R.layout.activity_volley_base;
    }

    @Override
    public void initData() {
        mGetUrl = ApiUtil.BASE_URL + ApiUtil.PATTERN_LOGIN + "?username=daiyibo&password=123";
        mPostUrl = ApiUtil.BASE_URL + ApiUtil.PATTERN_LOGIN;
        mQueue = Volley.newRequestQueue(this);
        mPostParamMap = new HashMap<>();
        mPostParamMap.put("username", "daiyibo");
        mPostParamMap.put("password", "123");
    }

    @Override
    public void initView() {
    }

    @OnClick(R.id.act_volley_base_btn_get_string)
    public void getString() {
        Request request = new StringRequest(
                Request.Method.GET,
                mGetUrl,
                mStringListener,
                mErrorListener);
        mQueue.add(request);
    }

    @OnClick(R.id.act_volley_base_btn_post_string)
    public void postString() {
        Request request = new StringRequest(
                Request.Method.POST,
                mPostUrl,
                mStringListener,
                mErrorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return mPostParamMap;
            }
        };
        mQueue.add(request);
    }

    @OnClick(R.id.act_volley_base_btn_get_json)
    public void getJson() {
        Request request = new JsonObjectRequest(
                Request.Method.GET,
                mGetUrl,
                null,
                mJsonListener,
                mErrorListener);
        mQueue.add(request);
    }

    @OnClick(R.id.act_volley_base_btn_post_json)
    public void postJson() {
        //此方法传入的参数，为json格式。需要获得满足条件的接口，才可以测试。
        JSONObject param = new JSONObject(mPostParamMap);
        Request request = new JsonObjectRequest(
                Request.Method.POST,
                mPostUrl,
                param,
                mJsonListener,
                mErrorListener);
        mQueue.add(request);
    }

    private Response.Listener mStringListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            mEtShow.setText(response);
        }
    };
    private Response.Listener mJsonListener = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            mEtShow.setText(response.toString());
        }
    };
    private Response.ErrorListener mErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            mEtShow.setText(error.toString());
        }
    };
}

