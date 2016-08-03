package example.abe.com.android_framework.activity.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by abe on 16/8/3.
 * 创建了Fragment之后，就保存在FragmentManager中了。
 * 之后，不需要在getItem创建Fragment了
 */
public class PagerAdapter2 extends FragmentPagerAdapter {

    private List<Fragment> mData;

    public PagerAdapter2(FragmentManager fm, List<Fragment> data) {
        super(fm);
        mData = data;
    }

    @Override
    public Fragment getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

}
