package example.abe.com.android_framework.activity.volley;

import android.content.Intent;
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
import example.abe.com.framework.util.ResourceUtil;

@ContentView(id = R.layout.activity_volley)
public class VolleyActivity extends BaseActivity implements View.OnClickListener {

    @ViewInject(id = R.id.act_volley_btn_base)
    private Button mBtnBase;
    @ViewInject(id = R.id.act_volley_btn_image)
    private Button mBtnImage;
    @ViewInject(id = R.id.act_volley_btn_custom)
    private Button mBtnCustom;

    @Override
    public void initData(){

    }

    @Override
    public void initView(){
        mBtnBase.setOnClickListener(this);
        mBtnImage.setOnClickListener(this);
        mBtnCustom.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.act_volley_btn_base: {
                Intent intent = new Intent(this, VolleyBaseActivity.class);
                startActivity(intent);
            }
            break;

            case R.id.act_volley_btn_image: {
                Intent intent = new Intent(this, VolleyImageActivity.class);
                startActivity(intent);
            }
            break;

            case R.id.act_volley_btn_custom: {
                Intent intent = new Intent(this, VolleyCustomActivity.class);
                startActivity(intent);
            }
            break;
        }
    }
}
