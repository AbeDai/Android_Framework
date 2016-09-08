package example.abe.com.android_framework.activity.gridview;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.BindView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import example.abe.com.android_framework.R;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.util.ToastUtil;

public class GridViewActivity extends BaseActivity {

    private static final String ITEM_IMAGE = "item_image";
    private static final String ITEM_TEXT = "item_text";

    @BindView(R.id.grid_view_1)
    protected GridView gridview1;
    @BindView(R.id.grid_view_2)
    protected GridView gridview2;

    @Override
    public int getLayoutID(){
        return R.layout.activity_grid_view;
    }

    @Override
    public void initData(){
    }

    @Override
    public void initView(){
        gridview1.setAdapter(getSimpleAdapter());
        gridview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, Object> item = (HashMap<String, Object>) parent.getItemAtPosition(position);
                ToastUtil.makeText((String)item.get(ITEM_TEXT));
            }
        });

        gridview2.setAdapter(new ImageAdapter(this));
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
            map.put(ITEM_IMAGE, R.drawable.ic_launcher);
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
}

