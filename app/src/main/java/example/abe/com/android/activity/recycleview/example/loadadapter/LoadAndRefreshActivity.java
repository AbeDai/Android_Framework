package example.abe.com.android.activity.recycleview.example.loadadapter;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.BindView;

import java.util.ArrayList;
import java.util.List;

import example.abe.com.android.R;
import example.abe.com.android.utils.ImageURLUtil;
import example.abe.com.android.activity.recycleview.example.baseadapter.ImageTextDelegate;
import example.abe.com.android.activity.recycleview.example.baseadapter.ImageTextModel;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.recycleview.adapter.BaseAdapter;
import example.abe.com.framework.recycleview.base.ViewHolder;
import example.abe.com.framework.recycleview.wrapper.LoadMoreWrapper;
import example.abe.com.framework.util.ToastUtil;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class LoadAndRefreshActivity extends BaseActivity {

    @BindView(R.id.act_load_and_refresh_recycle_view)
    protected RecyclerView mRv;
    @BindView(R.id.act_load_and_refresh_recycle_ptr)
    protected PtrClassicFrameLayout mPtr;
    private List<ImageTextModel> mListData;
    private RecyclerView.Adapter<ViewHolder> mAdapter;

    @Override
    public int getLayoutID() {
        return R.layout.activity_load_and_refresh;
    }

    @Override
    public void initData() {
        mListData = new ArrayList<>();
        for (int i = 0; i < ImageURLUtil.LIST_IMAGE_URL.size(); i++) {
            String imgUrl = ImageURLUtil.LIST_IMAGE_URL.get(i);
            mListData.add(new ImageTextModel(imgUrl, "图片位置:" + i));
        }
    }

    @Override
    public void initView() {
        mPtr.setLastUpdateTimeRelateObject(this);
        mPtr.setResistance(1.7f);
        mPtr.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtr.setDurationToClose(200);
        mPtr.setDurationToCloseHeader(500);
        mPtr.setKeepHeaderWhenRefresh(true);
        mPtr.setPtrHandler(mPtrHandler);
        mPtr.setHeaderView(getTextView("刷新"));

        BaseAdapter<ImageTextModel> adapterBase = new BaseAdapter<>(this, mListData);
        adapterBase.addItemViewDelegate(new ImageTextDelegate());
        LoadMoreWrapper<ImageTextModel> adapterLoadMore = new LoadMoreWrapper<>(adapterBase);
        adapterLoadMore.setLoadMoreView(getTextView("加载更多"));
        adapterLoadMore.setOnLoadMoreListener(listener);
        mAdapter = adapterLoadMore;
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(mAdapter);
    }

    LoadMoreWrapper.OnLoadMoreListener listener = new LoadMoreWrapper.OnLoadMoreListener() {
        @Override
        public void onLoadMoreRequested(ViewHolder holder) {
            ToastUtil.makeText("加载更多…");
        }
    };

    PtrHandler mPtrHandler = new PtrHandler() {
        @Override
        public void onRefreshBegin(final PtrFrameLayout frame) {
            frame.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ToastUtil.makeText("下拉刷新");
                    frame.refreshComplete();
                }
            }, 1000);
        }

        @Override
        public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
            return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
        }
    };

    private TextView getTextView(String title){
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
        TextView textView = new TextView(this);
        textView.setText(title);
        textView.setLayoutParams(lp);
        textView.setBackgroundColor(Color.RED);
        return textView;
    }
}
