package example.abe.com.android_framework.activity.tablayout;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import java.util.List;
import java.util.Map;

import example.abe.com.android_framework.R;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.viewinject.ContentView;
import example.abe.com.framework.viewinject.ViewInject;
import example.abe.com.framework.util.LogUtil;

@ContentView(id = R.layout.activity_tab_layout)
public class TabLayoutActivity extends BaseActivity {

    @ViewInject(id = R.id.act_main_pager)
    private ViewPager mPager;
    @ViewInject(id = R.id.act_main_tab_layout_1)
    private TabLayout mTabLayout1;
    @ViewInject(id = R.id.act_main_tab_layout_2)
    private TabLayout mTabLayout2;
    private android.support.v4.view.PagerAdapter mPagerAdapter;
    private List<Map<String, Integer>> mData2;

    private static final String KEY_ICON = "KEY_ICON";
    private static final String KEY_TEXT = "KEY_TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView(){
        //ViewPager和TabLayout建立关联
        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mTabLayout1.setupWithViewPager(mPager);

        //单独TabLayout
        TabLayout.Tab tab1 = mTabLayout2.newTab().setIcon(R.drawable.ic_launcher).setText("自定义_1");
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
                LogUtil.d("onTabUnselected -- tab_position:" + tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                LogUtil.d("onTabReselected -- tab_position:" + tab.getPosition());
            }

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                LogUtil.d("onTabSelected -- tab_position:" + tab.getPosition());
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


}
