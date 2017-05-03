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

public class AllDispalyActivity extends BaseActivity {

    @BindView(R.id.grid_view)
    protected AllDisplayGridView gridview;
    @BindView(R.id.list_view)
    protected AllDisplayListView listView;

    @Override
    public int getLayoutID(){
        return R.layout.activity_all_dispaly;
    }

    @Override
    public void initData(){
    }

    @Override
    public void initView(){
        gridview.setAdapter(new ImageAdapter(this));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.makeText("位置: " + position);
            }
        });

        listView.setAdapter(new ImageAdapter(this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.makeText("位置: " + position);
            }
        });
    }

   private class ImageAdapter extends BaseAdapter {

       public Context mContext;

       public ImageAdapter(Context context){
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
