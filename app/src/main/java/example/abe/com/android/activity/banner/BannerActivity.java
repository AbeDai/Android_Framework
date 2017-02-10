package example.abe.com.android.activity.banner;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import example.abe.com.android.R;
import example.abe.com.android.utils.ImageURLUtil;
import example.abe.com.framework.main.BaseActivity;

public class BannerActivity extends BaseActivity {

    private BannerFragment mBannerFrag1;
    private BannerFragment mBannerFrag2;
    private List<String> mData1;
    private List<String> mData2;

    @Override
    public int getLayoutID(){
        return R.layout.activity_banner;
    }

    @Override
    public void initData() {
        mData1 = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5"));
        mData2 = new ArrayList<>(Arrays.asList(
                ImageURLUtil.getRandomImageUrl(),
                ImageURLUtil.getRandomImageUrl(),
                ImageURLUtil.getRandomImageUrl(),
                ImageURLUtil.getRandomImageUrl(),
                ImageURLUtil.getRandomImageUrl()));
    }

    @Override
    public void initView() {
        mBannerFrag1 = BannerFragment.newInstanceWithTitle(mData1);
        replaceFragment(R.id.act_banner_frame_layout1, mBannerFrag1);
        mBannerFrag2 = BannerFragment.newInstanceWithURL(mData2);
        replaceFragment(R.id.act_banner_frame_layout2, mBannerFrag2);
    }

    private void replaceFragment(int id, Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(id, fragment);
        transaction.commit();
    }
}
