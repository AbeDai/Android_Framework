package example.abe.com.android.activity.alldisplay;

import android.content.Intent;
import android.view.View;

import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;

public class AllDispalyActivity extends BaseActivity {

    @Override
    public int getLayoutID(){
        return R.layout.activity_all_dispaly;
    }

    @Override
    public void initData(){
    }

    @Override
    public void initView(){
    }

    @OnClick({R.id.act_all_display_btn_grid_view, R.id.act_all_display_btn_list_view})
    public void onViewClick(View view){
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.act_all_display_btn_grid_view:
                intent.setClass(this, AllDisplayGridViewActivity.class);
                break;
            case R.id.act_all_display_btn_list_view:
                intent.setClass(this, AllDisplayListViewActivity.class);
                break;
        }
        startActivity(intent);
    }
}
