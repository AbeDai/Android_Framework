package example.abe.com.framework.recycleview.wrapper;

import android.content.Context;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import example.abe.com.framework.recycleview.base.ItemHeadFootDelegate;
import example.abe.com.framework.recycleview.base.ViewHolder;

import static example.abe.com.framework.recycleview.wrapper.WrapperHelper.ITEM_TYPE_FOOTER;
import static example.abe.com.framework.recycleview.wrapper.WrapperHelper.ITEM_TYPE_HEADER;


/**
 * Created by abe on 16/10/11.
 */
public class HeaderAndFooterWrapper<T> extends RecyclerView.Adapter<ViewHolder> {
    //HeaderView数据
    private SparseArrayCompat<ItemHeadFootDelegate> mHeaderDelegates = new SparseArrayCompat<>();
    //FootView数据
    private SparseArrayCompat<ItemHeadFootDelegate> mFooterDelegates = new SparseArrayCompat<>();
    //内容实现Adapter
    private RecyclerView.Adapter<ViewHolder> mInnerAdapter;
    //上下文环境
    protected Context mContext;

    /**
     * 构造方法
     *
     * @param adapter 修饰Adapter
     */
    public HeaderAndFooterWrapper(Context context, RecyclerView.Adapter<ViewHolder> adapter) {
        mInnerAdapter = adapter;
        mContext = context;
    }

    /**
     * 添加HeaderView
     *
     * @param delegate 表头代理
     */
    public void addHeaderDelegate(ItemHeadFootDelegate<T> delegate) {
        mHeaderDelegates.put(mHeaderDelegates.size() + ITEM_TYPE_HEADER, delegate);
    }

    /**
     * 添加FootView
     *
     * @param delegate 表尾代理
     */
    public void addFootDelegate(ItemHeadFootDelegate<T> delegate) {
        mFooterDelegates.put(mFooterDelegates.size() + ITEM_TYPE_FOOTER, delegate);
    }

    /**
     * HeaderView数量
     *
     * @return 数量
     */
    public int getHeadersCount() {
        return mHeaderDelegates.size();
    }

    /**
     * FootView数量
     *
     * @return 数量
     */
    public int getFootersCount() {
        return mFooterDelegates.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderViewPos(position)) {
            //设置HeaderView视图类型
            return mHeaderDelegates.keyAt(position);
        } else if (isFooterViewPos(position)) {
            //设置FootView视图类型
            return mFooterDelegates.keyAt(position - getHeadersCount() - getRealItemCount());
        }
        return mInnerAdapter.getItemViewType(position - getHeadersCount());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderDelegates.get(viewType) != null) {
            //设置HeaderView视图ViewHolder
            ItemHeadFootDelegate delegate = mHeaderDelegates.get(viewType);
            View view = delegate.getItemView(mContext, parent);
            ViewHolder holder = new ViewHolder(parent.getContext(), view);
            return holder;
        } else if (mFooterDelegates.get(viewType) != null) {
            //设置FootView视图ViewHolder
            ItemHeadFootDelegate delegate = mFooterDelegates.get(viewType);
            View view = delegate.getItemView(mContext, parent);
            ViewHolder holder = new ViewHolder(parent.getContext(), view);
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
