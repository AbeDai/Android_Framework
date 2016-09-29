package example.abe.com.android_framework.activity.suspend;

import android.content.Intent;
import android.view.View;

import com.example.OnClick;

import example.abe.com.android_framework.R;
import example.abe.com.framework.main.BaseActivity;

public class SuspendWindowActivity extends BaseActivity {


    @Override
    public int getLayoutID() {
        return R.layout.activity_suspend_window;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
    }

    @OnClick(R.id.act_suspend_window_show_memory_window)
    public void OnSimpleSuspendWindowClick() {
        Intent intent = new Intent(this, MemoryService.class);
        startService(intent);
    }

    @OnClick(R.id.act_suspend_window_change_simple_suspend_window)
    public void OnChangeSimpleSuspendWindowClick() {

    }

    @OnClick(R.id.act_suspend_window_remove_simple_suspend_window)
    public void OnRemoveSimpleSuspendWindowClick() {

    }
}

