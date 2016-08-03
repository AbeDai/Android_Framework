package example.abe.com.android_framework.activity.tablayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.activity.viewpager.ViewPagerFragment;
import example.abe.com.framework.util.ResourceUtil;

/**
 * Created by abe on 16/8/3.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private static final int NUM_PAGES = 5;
    private ArrayList<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();
    private List<String> mContentList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        initData();
    }

    private void initData() {
        mTitleList = ResourceUtil.getStringList(R.array.view_pager_title);
        mContentList = ResourceUtil.getStringList(R.array.view_pager_content);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return ViewPagerFragment.instance(mTitleList.get(position), mContentList.get(position));
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
