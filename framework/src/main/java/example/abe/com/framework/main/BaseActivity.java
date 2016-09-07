package example.abe.com.framework.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import example.abe.com.framework.viewinject.ViewInjectUtil;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        ViewInjectUtil.inject(this);
        initData();
        initView();
    }

    abstract public int getLayoutID();

    abstract public void initData();

    abstract public void initView();
}
