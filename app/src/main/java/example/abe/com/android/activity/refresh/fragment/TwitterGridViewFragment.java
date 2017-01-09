package example.abe.com.android.activity.refresh.fragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.BindView;

import java.util.ArrayList;
import java.util.List;

import example.abe.com.android.R;
import example.abe.com.android.activity.refresh.ui.TwitterFooterView;
import example.abe.com.android.activity.refresh.ui.TwitterHeaderView;
import example.abe.com.framework.imageloader.ImageLoader;
import example.abe.com.framework.main.BaseFragment;
import example.abe.com.framework.refresh.OnLoadMoreListener;
import example.abe.com.framework.refresh.OnRefreshListener;
import example.abe.com.framework.refresh.SwipeToLoadLayout;
import example.abe.com.framework.util.DensityUtil;
import example.abe.com.framework.util.ToastUtil;

public class TwitterGridViewFragment extends BaseFragment implements OnRefreshListener,
        OnLoadMoreListener, AdapterView.OnItemClickListener {

    @BindView(R.id.frag_twitter_grid_view_swipe_to_load)
    protected SwipeToLoadLayout mSwipeToLoadLayout;

    @BindView(R.id.swipe_refresh_header)
    protected TwitterHeaderView mRefreshHeader;

    @BindView(R.id.swipe_load_more_footer)
    protected TwitterFooterView mFooterHeader;

    @BindView(R.id.swipe_target)
    protected GridView mGridView;

    private MyAdapter mAdapter;

    private List<String> mListData;

    public static TwitterGridViewFragment newInstance(){
        return new TwitterGridViewFragment();
    }

    public TwitterGridViewFragment() {
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_twitter_grid_view;
    }

    @Override
    public void initData() {
        mListData = new ArrayList<>();
    }

    @Override
    public void initView() {
        mAdapter = new MyAdapter(getContext(), 0, mListData);
        mGridView.setAdapter(mAdapter);

        mGridView.setOnItemClickListener(this);

        mSwipeToLoadLayout.setOnRefreshListener(this);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);

        mSwipeToLoadLayout.setRefreshing(true);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ToastUtil.makeText("图片地址：" + mAdapter.getItem(position));
    }

    @Override
    public void onRefresh() {
        mSwipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                addListItem();
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
                addListItem();
                mAdapter.notifyDataSetChanged();
                mSwipeToLoadLayout.setLoadingMore(false);
            }
        }, 1000);
    }

    private void addListItem(){
        for (int i = 0; i < 5; i++){
            mListData.add("http://img.my.csdn.net/uploads/201407/26/1406383291_6518.jpg");
        }
    }

    public class MyAdapter extends ArrayAdapter<String> {

        public MyAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                ImageView imageView= new ImageView(getContext());
                imageView.setLayoutParams(new AbsListView.LayoutParams(
                        AbsListView.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(getContext(), 300)));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                convertView = imageView;
            }

            final ImageView imageView = (ImageView) convertView;
            ImageLoader.getInstance().getImageFIFO(imageView, getItem(position));

            return convertView;
        }
    }
}
