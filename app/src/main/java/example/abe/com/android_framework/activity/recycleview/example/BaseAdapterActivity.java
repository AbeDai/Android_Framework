package example.abe.com.android_framework.activity.recycleview.example;

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
            mListData.add(new ImageTextModel(imgUrl, String.valueOf(i)));
        }
    }

    @Override
    public void initView() {
        mAdapter = new BaseAdapter<>(this, mListData);
        mAdapter.addItemViewDelegate(new ItemViewDelegate<ImageTextModel>() {
            @Override
            public View getItemView(Context context, ViewGroup parent) {
                View view = LayoutInflater.from(context)
                        .inflate(R.layout.item_image_text_recycle_view_example, parent, false);
                return view;
            }

            @Override
            public boolean isForViewType(ImageTextModel item, int position) {
                return true;
            }

            @Override
            public void bindViewHolder(ViewHolder holder, ImageTextModel imageTextModel, int position) {
                holder.setText(R.id.item_image_text_recycle_view_example_tv, imageTextModel.getText());
                ImageView imageView = holder.getView(R.id.item_image_text_recycle_view_example_iv);
                ImageLoader.getInstance().getImageFIFO(imageView, imageTextModel.getImgUrl());
            }
        });
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(mAdapter);
    }
}