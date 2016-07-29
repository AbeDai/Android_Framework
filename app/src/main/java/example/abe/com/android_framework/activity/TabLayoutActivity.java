package example.abe.com.android_framework.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import example.abe.com.android_framework.R;
import example.abe.com.framework.Annotation.ContentView;
import example.abe.com.framework.Annotation.ViewInject;
import example.abe.com.framework.util.ABLog;

@ContentView(id = R.layout.activity_tab_layout)
public class TabLayoutActivity extends BaseActivity {

    @ViewInject(id = R.id.act_main_pager)
    private ViewPager mPager;
    @ViewInject(id = R.id.act_main_tab_layout_1)
    private TabLayout mTabLayout1;
    @ViewInject(id = R.id.act_main_tab_layout_2)
    private TabLayout mTabLayout2;
    private android.support.v4.view.PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ViewPager和TabLayout建立关联
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mTabLayout1.setupWithViewPager(mPager);


        //单独TabLayout
        TabLayout.Tab tab1 = mTabLayout2.newTab().setIcon(R.mipmap.ic_launcher).setText("自定义_1");
        TabLayout.Tab tab2 = mTabLayout2.newTab().setIcon(android.R.drawable.sym_def_app_icon).setText("自定义_2");
        TabLayout.Tab tab3 = mTabLayout2.newTab().setIcon(android.R.drawable.star_big_on).setText("自定义_3");
        TabLayout.Tab tab4 = mTabLayout2.newTab().setIcon(android.R.drawable.ic_menu_week).setText("自定义_4");
        TabLayout.Tab tab5 = mTabLayout2.newTab().setIcon(android.R.drawable.ic_dialog_email).setText("自定义_5");
        mTabLayout2.addTab(tab1);
        mTabLayout2.addTab(tab2);
        mTabLayout2.addTab(tab3);
        mTabLayout2.addTab(tab4);
        mTabLayout2.addTab(tab5);
        mTabLayout2.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ABLog.d("onTabUnselected -- tab_position:" + tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                ABLog.d("onTabReselected -- tab_position:" + tab.getPosition());
            }

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ABLog.d("onTabSelected -- tab_position:" + tab.getPosition());
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    private class PagerAdapter extends FragmentPagerAdapter {

        private static final int NUM_PAGES = 5;
        private ArrayList<Fragment> mFragmentList = new ArrayList<>();
        private List<String> mTitleList = new ArrayList<>();
        private List<String> mContentList = new ArrayList<>();
        {
            mTitleList = Arrays.asList(getResources().getStringArray(R.array.view_pager_title));
            mContentList = Arrays.asList(getResources().getStringArray(R.array.view_pager_content));
        }

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return ViewPagerActivity.ScreenSlidePageFragment
                    .instance(mTitleList.get(position), mContentList.get(position));
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
