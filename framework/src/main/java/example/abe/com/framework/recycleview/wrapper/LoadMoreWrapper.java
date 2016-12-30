package example.abe.com.framework.recycleview.wrapper;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import example.abe.com.framework.recycleview.base.ViewHolder;

import static example.abe.com.framework.recycleview.wrapper.WrapperHelper.ITEM_TYPE_LOAD_MORE;

/**
 * Created by abe on 16/10/11.
 * @deprecated <br>
 *     销毁原因：<br>
 *     1.此功能通用性不强，只能用在RecycleView中<br>
 *     2.上拉加载功能太弱
 */
@Deprecated
public class LoadMoreWrapper<T> extends RecyclerView.Adapter<ViewHolder> {

    /**
     * 加载更加监听接口
     */
    public interface OnLoadMoreListener {
        void onLoadMoreRequested(ViewHolder holder);
    }

    //加载更多视图
    private View mLoadMoreView;
    //内容实现Adapter
    private RecyclerView.Adapter<ViewHolder> mInnerAdapter;
    //LoadMore监听
    private OnLoadMoreListener mOnLoadMoreListener;

    /**
     * 构造函数
     * @param adapter 修饰Adapter
     */
    public LoadMoreWrapper(RecyclerView.Adapter<ViewHolder> adapter) {
        mInnerAdapter = adapter;
    }

    /**
     * 设置加载更多监听
     * @param loadMoreListener 加载更多监听
     */
    public void setOnLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        mOnLoadMoreListener = loadMoreListener;
    }

    /**
     * 设置加载更多视图
     * @param loadMoreView 视图
     */
    public void setLoadMoreView(View loadMoreView) {
        mLoadMoreView = loadMoreView;
    }

    @Override
    public int getItemViewType(int position) {
        //设置LoadMore视图类型
        if (isShowLoadMore(position)) {
            return ITEM_TYPE_LOAD_MORE;
        }
        return mInnerAdapter.getItemViewType(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //设置LoadMore视图ViewHolder
        if (viewType == ITEM_TYPE_LOAD_MORE) {
            ViewHolder holder = new ViewHolder(parent.getContext(), mLoadMoreView);
            return holder;
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (isShowLoadMore(position)) {
            //调用LoadMore监听
            if (mOnLoadMoreListener != null) {
                mOnLoadMoreListener.onLoadMoreRequested(holder);
            }
            return;
        }
        mInnerAdapter.onBindViewHolder(holder, position);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        mInnerAdapter.onAttachedToRecyclerView(recyclerView);
        //设置GridLayoutManager布局Item宽度
        WrapperHelper.onAttachedToRecyclerView(recyclerView, new WrapperHelper.SpanSizeCallback() {
            @Override
            public int getSpanSize(GridLayoutManager layoutManager,
                                   GridLayoutManager.SpanSizeLookup oldLookup, int position) {
                if (isShowLoadMore(position)) {
                    return layoutManager.getSpanCount();
                }
                if (oldLookup != null) {
                    return oldLookup.getSpanSize(position);
                }
                return 1;
            }
        });
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        mInnerAdapter.onViewAttachedToWindow(holder);
        //设置StaggeredGridLayoutManager布局Item宽度
        if (isShowLoadMore(holder.getLayoutPosition())) {
            WrapperHelper.setFullSpan(holder);
        }
    }

    @Override
    public int getItemCount() {
        return mInnerAdapter.getItemCount() + (hasLoadMore() ? 1 : 0);
    }

    /**
     * 是否存在加载更多的视图
     * @return 是否存在
     */
    private boolean hasLoadMore() {
        return mLoadMoreView != null;
    }

    /**
     * 是否显示加载更多视图
     * @param position 位置
     * @return 是否显示
     */
    private boolean isShowLoadMore(int position) {
        return hasLoadMore() && (position >= mInnerAdapter.getItemCount());
    }
}
