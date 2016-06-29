package example.abe.com.android_framework;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import example.abe.com.android_framework.activity.AssetsActivity;
import example.abe.com.android_framework.activity.EventBusFristActivity;
import example.abe.com.android_framework.activity.LogActivity;
import example.abe.com.android_framework.activity.RecycleListActivity;
import example.abe.com.android_framework.activity.RetrofitActivity;
import example.abe.com.android_framework.activity.SegmentViewActivity;
import example.abe.com.android_framework.activity.TabLayoutActivity;
import example.abe.com.android_framework.activity.TimeUtilActivity;
import example.abe.com.android_framework.activity.ViewPagerActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<Integer> mData;
    private ListView mLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initData() {
        mData = new ArrayList();
        //自定义Log
        mData.add(R.string.btn_title_log);
        //分段选择项
        mData.add(R.string.btn_title_segment_view);
        //时间类
        mData.add(R.string.btn_title_time_util);
        //网络框架
        mData.add(R.string.btn_title_retrofit);
        //RecycleView使用范例
        mData.add(R.string.btn_title_recycle_view);
        //ViewPager界面
        mData.add(R.string.btn_title_view_pager);
        //Assets界面
        mData.add(R.string.btn_title_assets);
        //TabLayout界面
        mData.add(R.string.btn_title_tab_layout);
        //EventBus使用模板
        mData.add(R.string.btn_title_event_bus);
    }

    private void initView() {
        mLv = (ListView) findViewById(R.id.act_main_list);
        mLv.setAdapter(new MainAdapter());
        mLv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch ((int) id) {
            //自定义Log
            case R.string.btn_title_log: {
                Intent intent = new Intent(MainActivity.this, LogActivity.class);
                startActivity(intent);
            }
            break;

            //分段选择项
            case R.string.btn_title_segment_view: {
                Intent intent = new Intent(MainActivity.this, SegmentViewActivity.class);
                startActivity(intent);
            }
            break;

            //时间类
            case R.string.btn_title_time_util: {
                Intent intent = new Intent(MainActivity.this, TimeUtilActivity.class);
                startActivity(intent);
            }
            break;

            //网络框架
            case R.string.btn_title_retrofit: {
                Intent intent = new Intent(MainActivity.this, RetrofitActivity.class);
                startActivity(intent);
            }
            break;

            //RecycleView使用范例
            case R.string.btn_title_recycle_view: {
                Intent intent = new Intent(MainActivity.this, RecycleListActivity.class);
                startActivity(intent);
            }
            break;

            //ViewPager使用范例
            case R.string.btn_title_view_pager: {
                Intent intent = new Intent(MainActivity.this, ViewPagerActivity.class);
                startActivity(intent);
            }
            break;

            //Assets使用范例
            case R.string.btn_title_assets: {
                Intent intent = new Intent(MainActivity.this, AssetsActivity.class);
                startActivity(intent);
            }
            break;

            //TabLayout使用范例
            case R.string.btn_title_tab_layout: {
                Intent intent = new Intent(MainActivity.this, TabLayoutActivity.class);
                startActivity(intent);
            }
            break;

            //EventBus使用模板
            case R.string.btn_title_event_bus: {
                Intent intent = new Intent(MainActivity.this, EventBusFristActivity.class);
                startActivity(intent);
            }
            break;

            default:
                break;
        }
    }


    class MainAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return mData.get(position);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = LayoutInflater.from(MainActivity.this)
                        .inflate(R.layout.item_main_activity_list, parent, false);
            }

            TextView tv = (TextView) convertView;
            tv.setText(getResources().getString(mData.get(position)));

            return convertView;
        }

    }
}
