package example.abe.com.android.activity.materialdesign;

import android.content.Intent;
import android.view.View;

import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.android.activity.materialdesign.cardview.CardViewActivity;
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

    @OnClick({R.id.act_material_design_btn_drawer_layout, R.id.act_material_design_btn_card_view})
    public void onBtnClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.act_material_design_btn_drawer_layout:
                intent.setClass(this, DrawerLayoutActivity.class);
                break;
            case R.id.act_material_design_btn_card_view:
                intent.setClass(this, CardViewActivity.class);
                break;
        }
        startActivity(intent);
    }
}
