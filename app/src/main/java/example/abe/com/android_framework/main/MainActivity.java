package example.abe.com.android_framework.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.activity.assets.AssetsActivity;
import example.abe.com.android_framework.activity.cardview.CardViewActivity;
import example.abe.com.android_framework.activity.eventbus.EventBusActivity;
import example.abe.com.android_framework.activity.gridview.GridViewActivity;
import example.abe.com.android_framework.activity.recyclelist.RecycleListActivity;
import example.abe.com.android_framework.activity.retrofit.RetrofitActivity;
import example.abe.com.android_framework.activity.tablayout.TabLayoutActivity;
import example.abe.com.android_framework.activity.viewpager.ViewPagerActivity;
import example.abe.com.android_framework.activity.volley.VolleyActivity;
import example.abe.com.framework.annotation.ContentView;
import example.abe.com.framework.annotation.ViewInject;

@ContentView(id = R.layout.activity_main)
public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @ViewInject(id = R.id.act_main_list)
    private ListView mLv;
    private List<String> mListTitle;
    private List<String> mListDec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
        initView();
    }

    private void initData() {
        String[] titles = getResources().getStringArray(R.array.main_act_btn_title);
        mListTitle = Arrays.asList(titles);
        String[] decs = getResources().getStringArray(R.array.main_act_btn_dec);
        mListDec = Arrays.asList(decs);
    }

    private void initView() {
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
        }
        startActivity(intent);
    }
}
