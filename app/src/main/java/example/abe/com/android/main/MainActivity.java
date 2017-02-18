package example.abe.com.android.main;

import android.Manifest;
import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;

import com.example.BindView;
import com.example.PermissionFail;
import com.example.PermissionSuccess;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.permission.PermissionUtils;

public class MainActivity extends BaseActivity {

    private static final int STORAGE_PERMISSIONS_REQUEST = 100;

    @BindView(R.id.act_main_view_pager)
    protected ViewPager mPager;
    @BindView(R.id.act_main_tab_layout)
    protected TabLayout mTabLayout;

    private ViewPagerAdapter mPagerAdapter;

    @Override
    public int getLayoutID(){
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mPager);

        requestPermissions();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],
                                           int[] grantResults) {
        PermissionUtils.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    private void requestPermissions(){
        PermissionUtils
                .with(this)
                .addRequestCode(STORAGE_PERMISSIONS_REQUEST)
                .permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .request();
    }

    @PermissionSuccess(requestCode = STORAGE_PERMISSIONS_REQUEST)
    public void onRequestStorageSuccess(){
    }

    @PermissionFail(requestCode = STORAGE_PERMISSIONS_REQUEST)
    public void onRequestStorageFail(){
        AlertDialog.Builder builder = new AlertDialog
                .Builder(this)
                .setTitle("存储读写权限")
                .setMessage("这项权限对我们非常重要，取消授权将对App的正常运行产生影响！")
                .setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return KnowledgeStructHelper.getListTab().get(position).getTitle();
        }

        @Override
        public Fragment getItem(int position) {
            return MainListFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return KnowledgeStructHelper.getListTab().size();
        }
    }

}
