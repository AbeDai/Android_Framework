package example.abe.com.android.activity.alldisplay;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.BindView;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.util.ToastUtil;

public class AllDisplayGridViewActivity extends BaseActivity {

    @BindView(R.id.act_all_display_grid_view)
    protected AllDisplayGridView mGridView;

    @Override
    public int getLayoutID(){
        return R.layout.activity_all_display_grid_view;
    }

    @Override
    public void initData(){
    }

    @Override
    public void initView(){
        mGridView.setAdapter(new MyAdapter(this));
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
            return 100;
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
            ImageView imageView;
            if (convertView == null) {
                convertView = new ImageView(mContext);
            }
            imageView = (ImageView) convertView;
            imageView.setImageResource(R.drawable.ic_launcher);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setLayoutParams(new GridView.LayoutParams(
                    GridView.LayoutParams.MATCH_PARENT,
                    GridView.LayoutParams.WRAP_CONTENT));

            return imageView;
        }
    }
}
