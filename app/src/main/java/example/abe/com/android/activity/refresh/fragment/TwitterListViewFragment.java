package example.abe.com.android.activity.refresh.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.BindView;

import java.util.ArrayList;
import java.util.List;

import example.abe.com.android.R;
import example.abe.com.android.activity.refresh.ui.TwitterHeaderView;
import example.abe.com.framework.main.BaseFragment;
import example.abe.com.framework.refresh.OnLoadMoreListener;
import example.abe.com.framework.refresh.OnRefreshListener;
import example.abe.com.framework.refresh.SwipeToLoadLayout;
import example.abe.com.framework.util.ResourceUtil;

public class TwitterListViewFragment extends BaseFragment implements OnRefreshListener, OnLoadMoreListener {

    @BindView(R.id.frag_twitter_swipe_to_load)
    protected SwipeToLoadLayout mSwipeToLoadLayout;

    @BindView(R.id.swipe_refresh_header)
    protected TwitterHeaderView mRefreshHeader;

    @BindView(R.id.swipe_target)
    protected ListView mLv;

    private MyAdapter mAdapter;

    private List<String> mListData;

    public TwitterListViewFragment() {
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_twitter_list_view;
    }

    @Override
    public void initData() {
        mListData = new ArrayList<>();
    }

    @Override
    public void initView() {
        mAdapter = new MyAdapter();
        mLv.setAdapter(mAdapter);

        mSwipeToLoadLayout.setOnRefreshListener(this);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);
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

    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mListData.size();
        }

        @Override
        public Object getItem(int position) {
            return mListData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = new TextView(getContext());
                convertView.setLayoutParams(new AbsListView.LayoutParams(
                        AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT));
                convertView.setPadding(0, 20, 0, 20);
                convertView.setBackgroundColor(ResourceUtil.getColor(R.color.cardview_light_background));
            }
            ((TextView)convertView).setText(mListData.get(position));
            return convertView;
        }
    }
}
