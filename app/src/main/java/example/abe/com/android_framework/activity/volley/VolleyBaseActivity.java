package example.abe.com.android_framework.activity.volley;

import android.os.Bundle;
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

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.main.BaseActivity;
import example.abe.com.framework.annotation.ContentView;
import example.abe.com.framework.annotation.ViewInject;

@ContentView(id = R.layout.activity_volley_base)
public class VolleyBaseActivity extends BaseActivity implements View.OnClickListener {

    private String mGetUrl;
    private String mPostUrl;
    private Map<String, String> mPostParamMap;
    private RequestQueue mQueue;
    @ViewInject(id = R.id.act_volley_base_btn_get_string)
    private Button mBtnGetStr;
    @ViewInject(id = R.id.act_volley_base_btn_post_string)
    private Button mBtnPostStr;
    @ViewInject(id = R.id.act_volley_base_btn_get_json)
    private Button mBtnGetJson;
    @ViewInject(id = R.id.act_volley_base_btn_post_json)
    private Button mBtnPostJson;
    @ViewInject(id = R.id.act_volley_et_show)
    private EditText mEtShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    private void initData() {
        mQueue = Volley.newRequestQueue(this);

        mGetUrl = "http://ditu.amap.com/service/pl/pl.json?rand=635840524184357321";
        mPostUrl = "http://121.196.244.159:8081/mobileAppServlet";

        mPostParamMap = new HashMap<>();
        mPostParamMap.put("secretId", "jui5dhmmimhggmidheodkdhlkndo5g7");
        mPostParamMap.put("m", "getConfigData");
        mPostParamMap.put("ver", "2.0");
        mPostParamMap.put("cla", "system");
    }

    private void initView() {
        mBtnGetStr.setOnClickListener(this);
        mBtnPostStr.setOnClickListener(this);
        mBtnGetJson.setOnClickListener(this);
        mBtnPostJson.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.act_volley_base_btn_get_string: {
                StringRequest stringRequest = new StringRequest(
                        Request.Method.GET,
                        mGetUrl,
                        mStringListener,
                        mErrorListener);
                mQueue.add(stringRequest);
            }
            break;

            case R.id.act_volley_base_btn_post_string: {
                StringRequest stringRequest = new StringRequest(
                        Request.Method.POST,
                        mPostUrl,
                        mStringListener,
                        mErrorListener) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        return mPostParamMap;
                    }
                };
                mQueue.add(stringRequest);
            }
            break;

            case R.id.act_volley_base_btn_get_json: {
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.GET,
                        mGetUrl,
                        null,
                        mJsonListener, mErrorListener);
                mQueue.add(jsonObjectRequest);
            }
            break;

            //TODO:无法使用
            case R.id.act_volley_base_btn_post_json: {
                JSONObject param = new JSONObject(mPostParamMap);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.POST,
                        mPostUrl,
                        param,
                        mJsonListener, mErrorListener);
                mQueue.add(jsonObjectRequest);
            }
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
            mEtShow.setText(error.getMessage());
        }
    };
}
