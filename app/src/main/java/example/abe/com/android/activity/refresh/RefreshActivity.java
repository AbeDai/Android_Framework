package example.abe.com.android.activity.refresh;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.BindView;

import java.util.ArrayList;
import java.util.List;

import example.abe.com.android.R;
import example.abe.com.android.activity.refresh.fragment.TwitterFrameLayoutFragment;
import example.abe.com.android.activity.refresh.fragment.TwitterGridViewFragment;
import example.abe.com.android.activity.refresh.fragment.TwitterListViewFragment;
import example.abe.com.android.activity.refresh.fragment.TwitterRecyclerFragment;
import example.abe.com.android.activity.refresh.fragment.TwitterScrollViewFragment;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.util.ResourceUtil;

public class RefreshActivity extends BaseActivity {

    @BindView(R.id.act_refresh_view_pager)
    protected ViewPager mPager;

    @BindView(R.id.act_refresh_tab_layout)
    protected TabLayout mTabLayout;

    private ViewPagerAdapter mPagerAdapter;

    @Override
    public int getLayoutID(){
        return R.layout.activity_refresh;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView(){
        //ViewPager和TabLayout建立关联
        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mPager);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<String> mTitleList = new ArrayList<>();

        private ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            initData();
        }

        private void initData() {
            mTitleList = ResourceUtil.getStringList(R.array.refresh_view_pager_title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            String title = mTitleList.get(position);
            Fragment fragment = null;
            switch (title){
                case "ListView":
                    fragment = TwitterListViewFragment.newInstance();
                    break;
                case "GridView":
                    fragment = TwitterGridViewFragment.newInstance();
                    break;
                case "RecyclerView":
                    fragment = TwitterRecyclerFragment.newInstance();
                    break;
                case "ScrollView":
                    fragment = TwitterScrollViewFragment.newInstance();
                    break;
                case "FrameLayout":
                    fragment = TwitterFrameLayoutFragment.newInstance();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return mTitleList.size();
        }
    }
}
