package example.abe.com.android.activity.projectmode.mvvm.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import example.abe.com.android.R;
import example.abe.com.android.activity.projectmode.mvvm.viewmodel.MVVMViewModel;
import example.abe.com.android.databinding.ActivityMvvmBinding;

/**
 * App项目结构与MVVM设计不兼容，使用系统Activity
 */
public class MVVMActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMvvmBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm);
        binding.setViewModel(new MVVMViewModel());
    }
}

