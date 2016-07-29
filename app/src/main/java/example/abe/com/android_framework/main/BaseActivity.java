package example.abe.com.android_framework.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import example.abe.com.framework.util.ViewInjectUtil;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtil.inject(this);
    }
}
