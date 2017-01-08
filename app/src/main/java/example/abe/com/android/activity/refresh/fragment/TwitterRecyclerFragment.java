package example.abe.com.android.activity.refresh.fragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.BindView;

import java.util.ArrayList;
import java.util.List;

import example.abe.com.android.R;
import example.abe.com.android.activity.refresh.ui.TwitterFooterView;
import example.abe.com.android.activity.refresh.ui.TwitterHeaderView;
import example.abe.com.framework.main.BaseFragment;
import example.abe.com.framework.recycleview.adapter.BaseAdapter;
import example.abe.com.framework.recycleview.base.ItemViewDelegate;
import example.abe.com.framework.recycleview.base.ViewHolder;
import example.abe.com.framework.refresh.OnLoadMoreListener;
import example.abe.com.framework.refresh.OnRefreshListener;
import example.abe.com.framework.refresh.SwipeToLoadLayout;
import example.abe.com.framework.util.ResourceUtil;

/**
 * Created by abe on 17/1/8.
 */
public class TwitterRecyclerFragment extends BaseFragment implements OnRefreshListener, OnLoadMoreListener {

    @BindView(R.id.frag_twitter_recycler_swipe_to_load)
    protected SwipeToLoadLayout mSwipeToLoadLayout;

    @BindView(R.id.swipe_refresh_header)
    protected TwitterHeaderView mRefreshHeader;

    @BindView(R.id.swipe_load_more_footer)
    protected TwitterFooterView mFooterHeader;

    @BindView(R.id.swipe_target)
    protected RecyclerView mRv;

    private BaseAdapter<String> mAdapter;

    private List<String> mListData;

    public static TwitterRecyclerFragment newInstance() {
        return new TwitterRecyclerFragment();
    }

    public TwitterRecyclerFragment() {
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_twitter_recycler_view;
    }

    @Override
    public void initData() {
        mListData = new ArrayList<>();
    }

    @Override
    public void initView() {
        mAdapter = new BaseAdapter<>(getContext(), mListData);
        mAdapter.addItemViewDelegate(new TextDelegate());
        mRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mRv.setAdapter(mAdapter);

        mSwipeToLoadLayout.setOnRefreshListener(this);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);

        mSwipeToLoadLayout.setRefreshing(true);
    }

    @Override
    public void onRefresh() {
        mSwipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                addListItem("refresh");
                mAdapter.notifyDataSetChanged();
                mSwipeToLoadLayout.setRefreshing(false);
            }
        }, 1000);
    }

    @Override
    public void onLoadMore() {
        mSwipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                addListItem("loadmore");
                mAdapter.notifyDataSetChanged();
                mSwipeToLoadLayout.setLoadingMore(false);
            }
        }, 1000);
    }

    private void addListItem(String preStr){
        String string;
        for (int i = 0; i < 5; i++){
            string = preStr + ": " + mListData.size();
            mListData.add(string);
        }
    }
    public class TextDelegate implements ItemViewDelegate<String> {

        @Override
        public View getItemView(Context context, ViewGroup parent) {
            TextView tv = new TextView(context);
            tv.setPadding(100, 50, 0, 50);
            tv.setId(R.id.text);
            tv.setBackgroundColor(ResourceUtil.getColor(R.color.theme_accent));
            tv.setTextColor(ResourceUtil.getColor(R.color.text_white));
            tv.setLayoutParams(new RecyclerView.LayoutParams(
                    RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
            return tv;
        }

        @Override
        public boolean isForViewType(String item, int position) {
            return true;
        }

        @Override
        public void bindViewHolder(ViewHolder holder, String text, int position) {
            holder.setText(R.id.text, "位置：" + text);
        }
    }
}
