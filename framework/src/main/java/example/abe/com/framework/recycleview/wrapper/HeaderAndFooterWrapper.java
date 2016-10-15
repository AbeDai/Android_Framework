package example.abe.com.framework.recycleview.wrapper;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import example.abe.com.framework.recycleview.adapter.BaseAdapter;
import example.abe.com.framework.recycleview.base.ViewHolder;

import static example.abe.com.framework.recycleview.wrapper.WrapperHelper.ITEM_TYPE_FOOTER;
import static example.abe.com.framework.recycleview.wrapper.WrapperHelper.ITEM_TYPE_HEADER;


/**
 * Created by abe on 16/10/11.
 */
public class HeaderAndFooterWrapper<T> extends RecyclerView.Adapter<ViewHolder> {
    //HeaderView数据
    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    //FootView数据
    private SparseArrayCompat<View> mFootViews = new SparseArrayCompat<>();
    //内容实现Adapter
    private RecyclerView.Adapter<ViewHolder> mInnerAdapter;

    /**
     * 构造方法
     *
     * @param adapter 修饰Adapter
     */
    public HeaderAndFooterWrapper(RecyclerView.Adapter<ViewHolder> adapter) {
        mInnerAdapter = adapter;
    }

    /**
     * 添加HeaderView
     *
     * @param view 视图
     */
    public void addHeaderView(View view) {
        mHeaderViews.put(mHeaderViews.size() + ITEM_TYPE_HEADER, view);
    }

    /**
     * 添加FootView
     *
     * @param view 视图
     */
    public void addFootView(View view) {
        mFootViews.put(mFootViews.size() + ITEM_TYPE_FOOTER, view);
    }

    /**
     * HeaderView数量
     *
     * @return 数量
     */
    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    /**
     * FootView数量
     *
     * @return 数量
     */
    public int getFootersCount() {
        return mFootViews.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderViewPos(position)) {
            //设置HeaderView视图类型
            return mHeaderViews.keyAt(position);
        } else if (isFooterViewPos(position)) {
            //设置FootView视图类型
            return mFootViews.keyAt(position - getHeadersCount() - getRealItemCount());
        }
        return mInnerAdapter.getItemViewType(position - getHeadersCount());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderViews.get(viewType) != null) {
            //设置HeaderView视图ViewHolder
            ViewHolder holder = new ViewHolder(parent.getContext(), mHeaderViews.get(viewType));
            return holder;
        } else if (mFootViews.get(viewType) != null) {
            //设置FootView视图ViewHolder
            ViewHolder holder = new ViewHolder(parent.getContext(), mFootViews.get(viewType));
            return holder;
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //头部尾部视图无操作
        if (isHeaderViewPos(position) || isFooterViewPos(position)) {
            return;
        }
        mInnerAdapter.onBindViewHolder(holder, position - getHeadersCount());
    }

    @Override
    public int getItemCount() {
        return getHeadersCount() + getFootersCount() + getRealItemCount();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        mInnerAdapter.onAttachedToRecyclerView(recyclerView);

        //设置GridLayoutManager布局Item宽度
        WrapperHelper.onAttachedToRecyclerView(recyclerView, new WrapperHelper.SpanSizeCallback() {
            @Override
            public int getSpanSize(GridLayoutManager layoutManager, GridLayoutManager.SpanSizeLookup oldLookup, int position) {
                if (isHeaderViewPos(position) || isFooterViewPos(position)) {
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
        int position = holder.getLayoutPosition();
        if (isHeaderViewPos(position) || isFooterViewPos(position)) {
            WrapperHelper.setFullSpan(holder);
        }
    }

    /**
     * 获取ItemCount数量
     *
     * @return 内部Adapter数量
     */
    private int getRealItemCount() {
        return mInnerAdapter.getItemCount();
    }

    /**
     * 判断是否为HeaderView
     *
     * @param position 位置
     * @return 是否为HeaderView
     */
    private boolean isHeaderViewPos(int position) {
        return position < getHeadersCount();
    }

    /**
     * 判断是否为FooterView
     *
     * @param position 位置
     * @return 是否为FootView
     */
    private boolean isFooterViewPos(int position) {
        return position >= getHeadersCount() + getRealItemCount();
    }
}
