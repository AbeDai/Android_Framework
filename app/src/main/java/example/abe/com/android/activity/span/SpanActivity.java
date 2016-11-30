package example.abe.com.android.activity.span;

import android.content.Intent;
import android.view.View;

import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;

public class SpanActivity extends BaseActivity {

    @Override
    public int getLayoutID(){
        return R.layout.activity_span;
    }

    @Override
    public void initData(){
    }

    @Override
    public void initView() {
    }

    @OnClick({R.id.act_span_btn_spannable_html, R.id.act_span_btn_spannable_string})
    public void onClick(View v){
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.act_span_btn_spannable_string:
                intent.setClass(this, SpannableStringActivity.class);
                break;
            case R.id.act_span_btn_spannable_html:
                intent.setClass(this, SpannableHtmlActivity.class);
                break;
        }
        startActivity(intent);
    }
}
