package example.abe.com.android_framework.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import example.abe.com.framework.util.ABViewInjectUtils;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ABViewInjectUtils.inject(this);
    }
}
