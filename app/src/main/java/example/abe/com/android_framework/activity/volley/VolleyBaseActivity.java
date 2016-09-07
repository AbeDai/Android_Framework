package example.abe.com.android_framework.activity.volley;

import android.view.View;
import android.widget.Button;
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

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import example.abe.com.android_framework.R;
import example.abe.com.framework.main.BaseActivity;

public class VolleyBaseActivity extends BaseActivity implements View.OnClickListener {

    private String mGetUrl;
    private String mPostUrl;
    private Map<String, String> mPostParamMap;
    private RequestQueue mQueue;
    @BindView(R.id.act_volley_base_btn_get_string)
    protected Button mBtnGetStr;
    @BindView(R.id.act_volley_base_btn_post_string)
    protected Button mBtnPostStr;
    @BindView(R.id.act_volley_base_btn_get_json)
    protected Button mBtnGetJson;
    @BindView(R.id.act_volley_base_btn_post_json)
    protected Button mBtnPostJson;
    @BindView(R.id.act_volley_base_et_show)
    protected EditText mEtShow;

    @Override
    public int getLayoutID(){
        return R.layout.activity_volley_base;
    }

    @Override
    public void initData(){
        mGetUrl = "http://ditu.amap.com/service/pl/pl.json?rand=635840524184357321";
        mPostUrl = "http://121.196.244.159:8081/mobileAppServlet";
        mQueue = Volley.newRequestQueue(this);
        mPostParamMap = new HashMap<>();
        mPostParamMap.put("secretId", "jui5dhmmimhggmidheodkdhlkndo5g7");
        mPostParamMap.put("m", "getConfigData");
        mPostParamMap.put("ver", "2.0");
        mPostParamMap.put("cla", "system");
    }

    @Override
    public void initView(){
        mBtnGetStr.setOnClickListener(this);
        mBtnPostStr.setOnClickListener(this);
        mBtnGetJson.setOnClickListener(this);
        mBtnPostJson.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Request request;
        switch (v.getId()) {
            case R.id.act_volley_base_btn_get_string:
                request = new StringRequest(
                        Request.Method.GET,
                        mGetUrl,
                        mStringListener,
                        mErrorListener);
                mQueue.add(request);
                break;

            case R.id.act_volley_base_btn_post_string:
                request = new StringRequest(
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
                break;

            case R.id.act_volley_base_btn_get_json:
                request = new JsonObjectRequest(
                        Request.Method.GET,
                        mGetUrl,
                        null,
                        mJsonListener,
                        mErrorListener);
                mQueue.add(request);
                break;

            //此方法传入的参数，为json格式。需要获得满足条件的接口，才可以测试。
            case R.id.act_volley_base_btn_post_json:
                JSONObject param = new JSONObject(mPostParamMap);
                request = new JsonObjectRequest(
                        Request.Method.POST,
                        mPostUrl,
                        param,
                        mJsonListener,
                        mErrorListener);
                mQueue.add(request);
                break;
        }
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

