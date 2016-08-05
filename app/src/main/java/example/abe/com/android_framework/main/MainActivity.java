package example.abe.com.android_framework.main;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.activity.banner.BannerActivity;
import example.abe.com.android_framework.activity.assets.AssetsActivity;
import example.abe.com.android_framework.activity.cardview.CardViewActivity;
import example.abe.com.android_framework.activity.drawing.DrawActivity;
import example.abe.com.android_framework.activity.eventbus.EventBusActivity;
import example.abe.com.android_framework.activity.eventcenter.EventCenterActivity;
import example.abe.com.android_framework.activity.gridview.GridViewActivity;
import example.abe.com.android_framework.activity.recyclelist.RecycleListActivity;
import example.abe.com.android_framework.activity.retrofit.RetrofitActivity;
import example.abe.com.android_framework.activity.tablayout.TabLayoutActivity;
import example.abe.com.android_framework.activity.viewpager.ViewPagerActivity;
import example.abe.com.android_framework.activity.volley.VolleyActivity;
import example.abe.com.framework.viewinject.ContentView;
import example.abe.com.framework.viewinject.ViewInject;
import example.abe.com.framework.util.ResourceUtil;

@ContentView(id = R.layout.activity_main)
public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @ViewInject(id = R.id.act_main_list)
    private ListView mLv;
    private List<String> mListTitle;
    private List<String> mListDec;

    @Override
    public void initData(){
        mListTitle = ResourceUtil.getStringList(R.array.main_act_btn_title);
        mListDec = ResourceUtil.getStringList(R.array.main_act_btn_dec);
    }

    @Override
    public void initView(){
        mLv.setAdapter(new MainAdapter(this, mListTitle, mListDec));
        mLv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = null;
        switch (position) {
            case 0://RecycleView使用范例
                intent = new Intent(MainActivity.this, RecycleListActivity.class);
                break;

            case 1://网络框架
                intent = new Intent(MainActivity.this, RetrofitActivity.class);
                break;

            case 2: //ViewPager使用范例
                intent = new Intent(MainActivity.this, ViewPagerActivity.class);
                break;

            case 3://Assets使用范例
                intent = new Intent(MainActivity.this, AssetsActivity.class);
                break;

            case 4://TabLayout使用范例
                intent = new Intent(MainActivity.this, TabLayoutActivity.class);
                break;

            case 5: //EventBus使用模板
                intent = new Intent(MainActivity.this, EventBusActivity.class);
                break;

            case 6: //CardView用法介绍
                intent = new Intent(MainActivity.this, CardViewActivity.class);
                break;

            case 7: //GridView使用介绍
                intent = new Intent(MainActivity.this, GridViewActivity.class);
                break;

            case 8://Volley使用介绍
                intent = new Intent(MainActivity.this, VolleyActivity.class);
                break;

            case 9://绘图详解
                intent = new Intent(MainActivity.this, DrawActivity.class);
                break;

            case 10://广告栏详解
                intent = new Intent(MainActivity.this, BannerActivity.class);
                break;

            case 11://自定义EventBus
                intent = new Intent(MainActivity.this, EventCenterActivity.class);
                break;
        }
        startActivity(intent);
    }
}
