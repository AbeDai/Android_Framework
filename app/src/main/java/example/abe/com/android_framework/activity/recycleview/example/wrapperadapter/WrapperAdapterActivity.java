package example.abe.com.android_framework.activity.recycleview.example.wrapperadapter;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.BindView;
import com.example.OnClick;

import java.util.ArrayList;
import java.util.List;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.activity.imageloader.ImageModel;
import example.abe.com.android_framework.activity.recycleview.example.baseadapter.ImageTextDelegate;
import example.abe.com.android_framework.activity.recycleview.example.baseadapter.ImageTextModel;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.recycleview.adapter.BaseAdapter;
import example.abe.com.framework.recycleview.wrapper.HeaderAndFooterWrapper;
import example.abe.com.framework.recycleview.wrapper.LoadMoreWrapper;

public class WrapperAdapterActivity extends BaseActivity {

    @BindView(R.id.act_wrapper_adapter_recycle_view)
    protected RecyclerView mRv;
    private List<ImageTextModel> mListData;
    private BaseAdapter<ImageTextModel> mBaseAdapter;

    @Override
    public int getLayoutID() {
        return R.layout.activity_wrapper_adapter;
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
        mBaseAdapter = new BaseAdapter<>(this, mListData);
        mBaseAdapter.addItemViewDelegate(new ImageTextDelegate());
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(mBaseAdapter);
    }

    @OnClick({R.id.act_wrapper_adapter_btn_wrapper})
    public void onClickChangeWrapper() {
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 50);
        if (mRv.getAdapter() instanceof LoadMoreWrapper) {
            TextView header = new TextView(this);
            header.setText("Header内容");
            header.setLayoutParams(lp);
            header.setBackgroundColor(Color.RED);
            TextView footer = new TextView(this);
            footer.setText("Footer内容");
            footer.setLayoutParams(lp);
            footer.setBackgroundColor(Color.RED);
            HeaderAndFooterWrapper adapter = new HeaderAndFooterWrapper<>(mBaseAdapter);
            adapter.addHeaderView(header);
            adapter.addFootView(footer);
            mRv.setAdapter(adapter);
        } else if (mRv.getAdapter() instanceof HeaderAndFooterWrapper) {
            mRv.setAdapter(mBaseAdapter);
        } else if (mRv.getAdapter() instanceof BaseAdapter) {
            TextView textView = new TextView(this);
            textView.setText("加载更多内容");
            textView.setLayoutParams(lp);
            textView.setBackgroundColor(Color.RED);
            LoadMoreWrapper adapter = new LoadMoreWrapper<>(mBaseAdapter);
            adapter.setLoadMoreView(textView);
            mRv.setAdapter(adapter);
        }
    }

    @OnClick({R.id.act_wrapper_adapter_btn_layout_manager})
    public void onClickChangeLayoutManager() {
        if (mRv.getLayoutManager() instanceof GridLayoutManager) {
            mRv.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        } else if (mRv.getLayoutManager() instanceof LinearLayoutManager) {
            mRv.setLayoutManager(new GridLayoutManager(this, 2));
        } else if (mRv.getLayoutManager() instanceof StaggeredGridLayoutManager) {
            mRv.setLayoutManager(new LinearLayoutManager(this));
        }
    }
}
