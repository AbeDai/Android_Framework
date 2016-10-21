package example.abe.com.android.activity.materialdesign.drawerlayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.TextView;

import com.example.BindView;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.util.LogUtil;

public class DrawerLayoutActivity extends BaseActivity {

    @BindView(R.id.act_drawer_tv_content)
    protected TextView mTvContent;
    @BindView(R.id.activity_drawer_layout_dl)
    protected DrawerLayout mDl;

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
        mDl.addDrawerListener(mDrawerListener);
    }

    DrawerLayout.DrawerListener mDrawerListener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
            LogUtil.e("onDrawerSlide");
        }

        @Override
        public void onDrawerOpened(View drawerView) {
            LogUtil.e("onDrawerOpened");
        }

        @Override
        public void onDrawerClosed(View drawerView) {
            LogUtil.e("onDrawerClosed");
        }

        @Override
        public void onDrawerStateChanged(int newState) {
            LogUtil.e("onDrawerStateChanged");
        }
    };

    public void setContentText(String text){
        mTvContent.setText(text);
    }

    public void closeDrawer(){
        mDl.closeDrawers();
    }

    private void replaceFragment(int id, Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(id, fragment);
        transaction.commit();
    }
}
