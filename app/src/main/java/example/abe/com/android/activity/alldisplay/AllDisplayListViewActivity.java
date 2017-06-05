package example.abe.com.android.activity.alldisplay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.BindView;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.util.ToastUtil;

public class AllDisplayListViewActivity extends BaseActivity {

    @BindView(R.id.act_all_display_list_view)
    protected ListView mListView;

    @Override
    public int getLayoutID(){
        return R.layout.activity_all_display_list_view;
    }

    @Override
    public void initData(){
    }

    @Override
    public void initView(){
        mListView.setAdapter(new MyAdapter(this));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.makeText("位置: " + position);
            }
        });
    }

    private class MyAdapter extends BaseAdapter {

        public Context mContext;

        public MyAdapter(Context context){
            mContext = context;
        }

        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public Object getItem(int position) {
            return R.drawable.ic_launcher;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(AllDisplayListViewActivity.this)
                        .inflate(R.layout.item_all_display_list_view, parent, false);
            }
            return convertView;
        }
    }
}
