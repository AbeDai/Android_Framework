package example.abe.com.framework.recycleview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import example.abe.com.framework.recycleview.base.ItemViewDelegate;
import example.abe.com.framework.recycleview.base.ItemViewDelegateManager;
import example.abe.com.framework.recycleview.base.ViewHolder;

/**
 * Created by abe on 16/10/11.
 */
public class BaseAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    //上下文环境
    protected Context mContext;
    //数据列表
    protected List<T> mListData;
    //代理管理者
    protected ItemViewDelegateManager mItemViewDelegateManager;
    //点击事件
    protected OnItemClickListener mOnItemClickListener;

    public BaseAdapter(Context context, List<T> listData) {
        mContext = context;
        mListData = listData;
        mItemViewDelegateManager = new ItemViewDelegateManager();
    }

    @Override
    public int getItemViewType(int position) {
        //设置代理类型
        return mItemViewDelegateManager.getItemViewType(mListData.get(position), position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //获取代理
        ItemViewDelegate itemViewDelegate = mItemViewDelegateManager.getItemViewDelegate(viewType);
        //获取内容视图
        View itemView = itemViewDelegate.getItemView(mContext, parent);
        //构造ViewHolder
        ViewHolder holder = new ViewHolder(mContext, itemView);
        //设置点击监听
        setListener(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //绑定ViewHolder内容
        mItemViewDelegateManager.bindViewHolder(holder, mListData.get(position), holder.getAdapterPosition());
    }

    @Override
    public int getItemCount() {
        int itemCount = mListData.size();
        return itemCount;
    }

    /**
     * 获取数据列表
     * @return 数据列表
     */
    public List<T> getListData() {
        return mListData;
    }

    /**
     * 获取代理中心
     * @return 代理中心
     */
    public ItemViewDelegateManager<T> getItemViewDelegateManager(){
        return mItemViewDelegateManager;
    }

    /**
     * 添加代理
     * @param itemViewDelegate 表单元代理
     */
    public void addItemViewDelegate(ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(itemViewDelegate);
    }

    /**
     * 添加代理
     * @param viewType 代理类型
     * @param itemViewDelegate 代理
     */
    public void addItemViewDelegate(int viewType, ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(viewType, itemViewDelegate);
    }

    /**
     * 设置点击监听
     * @param onItemClickListener 监听
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /**
     * 设置点击监听
     * @param viewHolder ViewHolder
     */
    protected void setListener(final ViewHolder viewHolder) {
        viewHolder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    mOnItemClickListener.onItemClick(v, viewHolder, mListData.get(position), position);
                }
            }
        });

        viewHolder.getItemView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    return mOnItemClickListener.onItemLongClick(v, viewHolder, mListData.get(position), position);
                }
                return false;
            }
        });
    }

    /**
     * 点击事件监听
     */
    public interface OnItemClickListener<T> {
        //点击事件
        void onItemClick(View view, RecyclerView.ViewHolder holder, T data, int position);
        //点击长按事件
        boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, T data, int position);
    }
}
