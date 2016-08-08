package example.abe.com.android_framework.activity.viewpager;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import example.abe.com.android_framework.R;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.viewinject.ContentView;
import example.abe.com.framework.viewinject.ViewInject;
import example.abe.com.framework.util.ResourceUtil;

@ContentView(id = R.layout.activity_view_pager)
public class ViewPagerActivity extends BaseActivity implements View.OnClickListener{

    @ViewInject(id = R.id.act_view_pager_pager1)
    private ViewPager mViewPager1;
    @ViewInject(id = R.id.act_view_pager_pager2)
    private ViewPager mViewPager2;
    @ViewInject(id = R.id.act_view_pager_pager3)
    private ViewPager mViewPager3;
    @ViewInject(id = R.id.act_view_pager_btn_add)
    private View mBtnAdd;
    @ViewInject(id = R.id.act_view_pager_btn_del)
    private View mBtnDel;
    private PagerAdapter1 mAdapter1;
    private PagerAdapter2 mAdapter2;
    private PagerAdapter3 mAdapter3;
    private List<View> mData1;
    private List<Fragment> mData2;
    private LinkedList<Fragment> mData3;

    @Override
    public void initData() {

        //范例1
        mData1 = new ArrayList<>();
        mData1.add(getViewForColor(Color.RED));
        mData1.add(getViewForColor(Color.YELLOW));
        mData1.add(getViewForColor(Color.BLUE));
        mData1.add(getViewForColor(Color.GREEN));

        //范例2
        List<String> listTitle = ResourceUtil.getStringList(R.array.view_pager_title);
        List<String> listContent = ResourceUtil.getStringList(R.array.view_pager_content);
        mData2 = new ArrayList<>();
        for (int i = 0; i < listContent.size(); i++) {
            Fragment fragment = ViewPagerFragment.newInstance(listTitle.get(i), listContent.get(i));
            mData2.add(fragment);
        }

        //范例3
        mData3 = new LinkedList<>();
        for (int i = 0; i < listContent.size(); i++) {
            Fragment fragment = ViewPagerFragment.newInstance(listTitle.get(i), listContent.get(i));
            mData3.add(fragment);
        }
    }

    @Override
    public void initView() {
        //范例1
        //使用最原始的适配器,创建ViewPager
        mAdapter1 = new PagerAdapter1(mData1);
        mViewPager1.setAdapter(mAdapter1);
        mViewPager1.setOffscreenPageLimit(2);

        //范例2
        //简单使用模版
        mAdapter2 = new PagerAdapter2(getSupportFragmentManager(), mData2);
        mViewPager2.setAdapter(mAdapter2);

        //范例3
        //动态增加删减Fragment模版
        mAdapter3 = new PagerAdapter3(getSupportFragmentManager(), mData3);
        mViewPager3.setAdapter(mAdapter3);
        mBtnAdd.setOnClickListener(this);//添加Fragment
        mBtnDel.setOnClickListener(this);//删除Fragment
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.act_view_pager_btn_del:
                mData3.removeLast();
                mAdapter3.notifyDataSetChanged(mData3);
                break;

            case R.id.act_view_pager_btn_add:
                String title = "new_title";
                String content = "new_content:" + mData3.size();
                Fragment fragment = ViewPagerFragment.newInstance(title, content);
                mData3.add(fragment);
                mAdapter3.notifyDataSetChanged(mData3);
                break;
        }
    }

    private View getViewForColor(int color) {
        View view = new View(this);
        view.setBackgroundColor(color);
        return view;
    }


}

