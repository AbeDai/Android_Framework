package example.abe.com.android.activity.materialdesign;

import android.content.Intent;

import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.android.activity.materialdesign.drawerlayout.DrawerLayoutActivity;
import example.abe.com.framework.main.BaseActivity;

public class MaterialDesignActivity extends BaseActivity {

    @Override
    public int getLayoutID() {
        return R.layout.activity_material_design;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
    }

    @OnClick({R.id.act_material_design_btn_drawer_layout})
    public void onBtnClick() {
        Intent intent = new Intent();
        intent.setClass(this, DrawerLayoutActivity.class);
        startActivity(intent);
    }
}
