package example.abe.com.android_framework.activity.recyclelist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import example.abe.com.android_framework.R;

/**
 * Created by abe on 16/8/3.
 */
public class ListAdapter extends RecyclerView.Adapter<ViewHolder> {

    private OnItemClickListener mListener;
    private List<String> mData;
    private Context mContext;

    interface OnItemClickListener {

        void onClickItem(View view, int position);

        boolean onLongClickItem(View view, int position);
    }

    ListAdapter(Context context, List<String> data) {
        mData = data;
        mContext = context;
    }

    /**
     * 设置Item点击监听
     *
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    /**
     * 构建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_recycle_list, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    /**
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (position % 2 == 0) {
            holder.itemView.setMinimumHeight(300);
        } else {
            holder.itemView.setMinimumHeight(100);
        }
        holder.tvTitle.setText(mData.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClickItem(v, position);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mListener != null) {
                    return mListener.onLongClickItem(v, position);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * 添加元素
     *
     * @param position
     */
    public void addData(int position) {
        mData.add(position, "Insert One");
        notifyItemInserted(position);
    }

    /**
     * 删除元素
     *
     * @param position
     */
    public void removeData(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }
}
