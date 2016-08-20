package example.abe.com.android_framework.activity.banner;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/8/4.
 */
public class BannerAdapter extends PagerAdapter {
    private List<View> mData;

    public BannerAdapter(List<View> data) {
        super();
        mData = data;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (mData.get(position).getParent() == null){
            container.addView(mData.get(position));
        }
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
