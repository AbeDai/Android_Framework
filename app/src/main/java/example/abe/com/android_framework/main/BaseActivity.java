package example.abe.com.android_framework.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import example.abe.com.framework.viewinject.ViewInjectUtil;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtil.inject(this);

        initData();
        initView();
    }

    abstract public void initData();

    abstract public void initView();
}
