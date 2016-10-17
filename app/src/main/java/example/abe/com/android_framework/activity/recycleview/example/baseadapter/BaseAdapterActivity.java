package example.abe.com.android_framework.activity.recycleview.example.baseadapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.BindView;

import java.util.ArrayList;
import java.util.List;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.activity.imageloader.ImageModel;
import example.abe.com.framework.imageloader.ImageLoader;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.recycleview.adapter.BaseAdapter;
import example.abe.com.framework.recycleview.base.ItemViewDelegate;
import example.abe.com.framework.recycleview.base.ViewHolder;

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