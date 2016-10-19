package example.abe.com.android.activity.recycleview.example.baseadapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.BindView;

import java.util.ArrayList;
import java.util.List;

import example.abe.com.android.R;
import example.abe.com.android.activity.imageloader.ImageModel;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.recycleview.adapter.BaseAdapter;

public class BaseAdapterActivity extends BaseActivity {

    @BindView(R.id.act_base_adapter_recycle_view)
    protected RecyclerView mRv;
    private BaseAdapter<ImageTextModel> mAdapter;
    private List<ImageTextModel> mListData;

    @Override
    public int getLayoutID() {
        return R.layout.activity_base_adapter;
    }

    @Override
    public void initData() {
        mListData = new ArrayList<>();
        for (int i = 0; i < ImageModel.LIST_IMAGE_URL.size(); i++) {
            String imgUrl = ImageModel.LIST_IMAGE_URL.get(i);
            mListData.add(new ImageTextModel(imgUrl, "图片位置:" + i));
        }
    }

    @Override
    public void initView() {
        mAdapter = new BaseAdapter<>(this, mListData);
        mAdapter.addItemViewDelegate(new ImageTextDelegate());
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(mAdapter);
    }
}