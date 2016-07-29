package example.abe.com.android_framework.activity.gridview;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.main.BaseActivity;
import example.abe.com.framework.annotation.ContentView;
import example.abe.com.framework.annotation.ViewInject;
import example.abe.com.framework.util.ToastUtil;

@ContentView(id = R.layout.activity_grid_view)
public class GridViewActivity extends BaseActivity {

    private static final String ITEM_IMAGE = "item_image";
    private static final String ITEM_TEXT = "item_text";

    @ViewInject(id = R.id.grid_view_1)
    private GridView gridview1;
    @ViewInject(id = R.id.grid_view_2)
    private GridView gridview2;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gridview1.setAdapter(getSimpleAdapter());
        gridview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, Object> item = (HashMap<String, Object>) parent.getItemAtPosition(position);
                ToastUtil.makeText((String)item.get(ITEM_TEXT));
            }
        });

        gridview2.setAdapter(new ImageAdapter());
        gridview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.makeText("位置: " + position);
            }
        });
    }

    /**
     * 获取系统封装好的Adapter
     */
    public SimpleAdapter getSimpleAdapter() {
        List<HashMap<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put(ITEM_IMAGE, R.mipmap.ic_launcher);
            map.put(ITEM_TEXT, "NO." + String.valueOf(i));
            data.add(map);
        }

        return new SimpleAdapter(
                this,
                data,
                R.layout.item_grid_view_image_text,
                new String[]{ITEM_IMAGE, ITEM_TEXT},
                new int[]{R.id.grid_view_item_image, R.id.grid_view_item_text});
    }

    /**
     * 自定义Adapter
     */
    public class ImageAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 100;
        }

        @Override
        public Object getItem(int position) {
            return R.mipmap.ic_launcher;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(GridViewActivity.this);
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(R.mipmap.ic_launcher);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setLayoutParams(new GridView.LayoutParams(
                    GridView.LayoutParams.WRAP_CONTENT,
                    GridView.LayoutParams.WRAP_CONTENT));
            return imageView;
        }
    }
}

