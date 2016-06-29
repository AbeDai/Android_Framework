package example.abe.com.android_framework.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import example.abe.com.android_framework.R;

public class ViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        //使用最原始的适配器,创建ViewPager
        final ArrayList<View> views = new ArrayList<>();
        View view1 = new View(this);
        view1.setBackgroundColor(Color.RED);
        View view2 = new View(this);
        view2.setBackgroundColor(Color.YELLOW);
        View view3 = new View(this);
        view3.setBackgroundColor(Color.BLUE);
        View view4 = new View(this);
        view4.setBackgroundColor(Color.GREEN);
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);
        ViewPager mPager0 = (ViewPager) findViewById(R.id.act_view_pager_pager1);
        mPager0.setOffscreenPageLimit(2);
        PagerAdapter0 adapter0 = new PagerAdapter0(views);
        mPager0.setAdapter(adapter0);

        //简单使用模版
        final ArrayList<Fragment> fragments1 = new ArrayList<>();
        fragments1.add(ScreenSlidePageFragment
                .instance(getResources().getString(R.string.view_pager_title_1)
                        , getResources().getString(R.string.view_pager_content_1)));
        fragments1.add(ScreenSlidePageFragment
                .instance(getResources().getString(R.string.view_pager_title_2)
                        , getResources().getString(R.string.view_pager_content_2)));
        fragments1.add(ScreenSlidePageFragment
                .instance(getResources().getString(R.string.view_pager_title_3)
                        , getResources().getString(R.string.view_pager_content_3)));
        fragments1.add(ScreenSlidePageFragment
                .instance(getResources().getString(R.string.view_pager_title_4)
                        , getResources().getString(R.string.view_pager_content_4)));
        fragments1.add(ScreenSlidePageFragment
                .instance(getResources().getString(R.string.view_pager_title_5)
                        , getResources().getString(R.string.view_pager_content_5)));
        ViewPager mPager1 = (ViewPager) findViewById(R.id.act_view_pager_pager2);
        PagerAdapter1 adapter1 = new PagerAdapter1(getSupportFragmentManager(), fragments1);
        mPager1.setAdapter(adapter1);

        //动态增加删减Fragment模版
        final LinkedList<Fragment> fragments2 = new LinkedList<>();
        fragments2.add(ScreenSlidePageFragment
                .instance(getResources().getString(R.string.view_pager_title_1)
                        , getResources().getString(R.string.view_pager_content_1)));
        fragments2.add(ScreenSlidePageFragment
                .instance(getResources().getString(R.string.view_pager_title_2)
                        , getResources().getString(R.string.view_pager_content_2)));
        fragments2.add(ScreenSlidePageFragment
                .instance(getResources().getString(R.string.view_pager_title_3)
                        , getResources().getString(R.string.view_pager_content_3)));
        fragments2.add(ScreenSlidePageFragment
                .instance(getResources().getString(R.string.view_pager_title_4)
                        , getResources().getString(R.string.view_pager_content_4)));
        fragments2.add(ScreenSlidePageFragment
                .instance(getResources().getString(R.string.view_pager_title_5)
                        , getResources().getString(R.string.view_pager_content_5)));
        ViewPager mPager2 = (ViewPager) findViewById(R.id.act_view_pager_pager3);
        final PagerAdapter2 adapter2 = new PagerAdapter2(getSupportFragmentManager(), fragments2);
        mPager2.setAdapter(adapter2);
        findViewById(R.id.act_view_pager_btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragments2.add(ScreenSlidePageFragment.instance("new_title", "new_content:" + fragments2.size()));
                adapter2.notifyDataSetChanged();
            }
        });
        findViewById(R.id.act_view_pager_btn_del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragments2.removeLast();
                adapter2.notifyDataSetChanged();
            }
        });
    }

    //基类重写,实现一个包含View的ViewPager适配器
    private class PagerAdapter0 extends PagerAdapter {
        private List<View> views;

        public PagerAdapter0(List<View> fragments) {
            super();
            this.views = fragments;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object)   {
            container.removeView(views.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {  //这个方法用来实例化页卡
            container.addView(views.get(position), 0);
            return views.get(position);
        }

        @Override
        public int getCount() {
            return  views.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0==arg1;
        }
    }

    //创建了Fragment之后，就保存在FragmentManager中了。
    //之后，不需要在getItem创建Fragment了
    private class PagerAdapter1 extends FragmentPagerAdapter {

        private List<Fragment> fragments;

        public PagerAdapter1(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    //动态添加删除,不需要用到Fragment的时候，会销毁Fragment对象
    //再在getItem中创建对象
    private class PagerAdapter2 extends FragmentStatePagerAdapter {

        private List<Fragment> fragments;

        public PagerAdapter2(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter2.POSITION_NONE;
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    public static class ScreenSlidePageFragment extends Fragment {

        private ViewGroup rootView;
        private TextView tvTitle;
        private TextView tvContent;

        public static Fragment instance(String title, String content){
            ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
            Bundle args = new Bundle();
            args.putString("title", title);
            args.putString("content", content);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            //数据属性
            Bundle args = getArguments();
            String title = args.getString("title");
            String content = args.getString("content");

            //设置界面
            rootView = (ViewGroup) inflater
                    .inflate(R.layout.fragment_view_pager, container, false);
            tvTitle = (TextView)rootView
                    .findViewById(R.id.fragment_screen_slide_page_tv_title);
            tvContent = (TextView)rootView
                    .findViewById(R.id.fragment_screen_slide_page_tv_content);

            //设置UI
            tvTitle.setText(title);
            tvContent.setText(content);

            return rootView;
        }
    }

}

