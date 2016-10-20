package example.abe.com.android.activity.materialdesign.drawerlayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;

public class DrawerLayoutActivity extends BaseActivity {

    @Override
    public int getLayoutID() {
        return R.layout.activity_drawer_layout;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
        replaceFragment(R.id.act_drawer_fl_start_drawer, DrawerFragment.instance());
    }

    private void replaceFragment(int id, Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(id, fragment);
        transaction.commit();
    }

//    @OnClick({R.id.act_material_design_btn_drawer_layout})
//    public void onBtnClick() {
//        Intent intent = new Intent();
//        intent.setClass(this, MessengerActivity.class);
//        startActivity(intent);
//    }
}
