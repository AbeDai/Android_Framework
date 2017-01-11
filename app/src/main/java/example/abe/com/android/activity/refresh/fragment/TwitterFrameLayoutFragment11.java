package example.abe.com.android.activity.refresh.fragment;


import android.widget.ScrollView;

import com.example.BindView;

import example.abe.com.android.R;
import example.abe.com.android.activity.refresh.ui.TwitterFooterView;
import example.abe.com.android.activity.refresh.ui.TwitterHeaderView;
import example.abe.com.framework.main.BaseFragment;
import example.abe.com.framework.refresh.OnLoadMoreListener;
import example.abe.com.framework.refresh.OnRefreshListener;
import example.abe.com.framework.refresh.SwipeToLoadLayout;

public class TwitterFrameLayoutFragment11 extends BaseFragment implements OnRefreshListener, OnLoadMoreListener {

    @BindView(R.id.frag_twitter_frame_layout_swipe_to_load)
    protected SwipeToLoadLayout mSwipeToLoadLayout;

    protected TwitterHeaderView mRefreshHeader;

    @BindView(R.id.swipe_load_more_footer)
    protected TwitterFooterView mFooterHeader;

    @BindView(R.id.swipe_target)
    protected ScrollView mScrollView;

    public static TwitterFrameLayoutFragment11 newInstance() {
        return new TwitterFrameLayoutFragment11();
    }

    public TwitterFrameLayoutFragment11() {
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_twitter_frame_layout;
    }

    @Override
    public void initData() {}

    @Override
    public void initView() {
        //头部视图
        mRefreshHeader = new TwitterHeaderView(getContext());
        mSwipeToLoadLayout.setRefreshHeaderView(mRefreshHeader);

        //回调监听
        mSwipeToLoadLayout.setOnRefreshListener(this);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);

        //自动刷新
        mSwipeToLoadLayout.setRefreshing(true);
    }

    @Override
    public void onRefresh() {
        mSwipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeToLoadLayout.setRefreshing(false);
            }
        }, 1000);
    }

    @Override
    public void onLoadMore() {
        mSwipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeToLoadLayout.setLoadingMore(false);
            }
        }, 1000);
    }
}