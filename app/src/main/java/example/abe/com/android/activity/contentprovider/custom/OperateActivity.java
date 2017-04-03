package example.abe.com.android.activity.contentprovider.custom;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.widget.TextView;

import com.example.BindView;
import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.util.SharedPreferencesUtil;

public class OperateActivity extends BaseActivity {

    @BindView(R.id.act_operate_edit_text_show)
    protected TextView mTvShow;

    private static int COUNT = 0;

    @Override
    public int getLayoutID() {
        return R.layout.activity_operate;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
    }

    @OnClick(R.id.act_operate_btn_add_name)
    public void addName() {
        ContentResolver resolver = getContentResolver();
        ContentValues values = new ContentValues();
        values.put("name", "戴益波" + COUNT++);
        Uri uri = Uri.parse("content://example.abe.com.android.activity.contentprovider.custom.contentprovider");
        resolver.insert(uri, values);
        mTvShow.setText("数据插入成功");
    }

    @OnClick(R.id.act_operate_btn_show_name)
    public void showName() {
        String str = SharedPreferencesUtil.get("name", "无数据");
        mTvShow.setText(str);
    }

    @OnClick(R.id.act_operate_btn_delete_name)
    public void deleteName() {
        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse("content://example.abe.com.android.activity.contentprovider.custom.contentprovider");
        resolver.delete(uri, null, null);
        mTvShow.setText("数据删除成功");
    }
}
