package example.abe.com.android_framework.activity.viewpager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by abe on 16/8/3.
 * 基类重写,实现一个包含View的ViewPager适配器
 */
public class PagerAdapter1 extends PagerAdapter {
    private List<View> mData;

    public PagerAdapter1(List<View> data) {
        super();
        mData = data;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mData.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {  //这个方法用来实例化页卡
        container.addView(mData.get(position), 0);
        return mData.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }
}
