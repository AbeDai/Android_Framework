package example.abe.com.android_framework.activity.banner;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import example.abe.com.android_framework.R;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.viewinject.annotation.ContentView;

@ContentView(id = R.layout.activity_banner)
public class BannerActivity extends BaseActivity {

    private BannerFragment mBannerFrag1;
    private BannerFragment mBannerFrag2;
    private List<String> mData1;
    private List<String> mData2;

    @Override
    public void initData() {
        mData1 = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5"));
        mData2 = new ArrayList<>(Arrays.asList(
                "http://f.hiphotos.baidu.com/baike/c0%3Dbaike180%2C5%2C5%2C180%2C60/sign=cc7b8e436859252db71a155655f2685e/caef76094b36acaf3b5ffaa574d98d1001e99c2f.jpg",
                "http://ww2.sinaimg.cn/mw690/80621b10jw1etixphol7nj21nl1nltwj.jpg",
                "http://f.hiphotos.baidu.com/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=28e193859558d109d0eea1e0b031a7da/c83d70cf3bc79f3d0d6914cbbda1cd11738b2945.jpg",
                "http://b.hiphotos.baidu.com/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=ac5e4673a086c9171c0e5a6ba8541baa/0ff41bd5ad6eddc4684e16703edbb6fd5366338b.jpg",
                "http://c.hiphotos.baidu.com/baike/c0%3Dbaike220%2C5%2C5%2C220%2C73/sign=f8dc51723d2ac65c73086e219a9bd974/7aec54e736d12f2e7b63237247c2d562853568ba.jpg"));
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
