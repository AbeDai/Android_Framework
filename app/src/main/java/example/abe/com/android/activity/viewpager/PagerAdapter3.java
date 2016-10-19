package example.abe.com.android.activity.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by abe on 16/8/3.
 * 动态添加删除,不需要用到Fragment的时候，会销毁Fragment对象
 * 再在getItem中创建对象
 */
public class PagerAdapter3 extends FragmentStatePagerAdapter {

    private List<Fragment> mData;

    public PagerAdapter3(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        mData = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter3.POSITION_NONE;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    public void notifyDataSetChanged(List<Fragment> data){
        mData = data;
        notifyDataSetChanged();
    }
}
