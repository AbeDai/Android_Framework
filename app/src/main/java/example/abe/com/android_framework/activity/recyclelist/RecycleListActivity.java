package example.abe.com.android_framework.activity.recyclelist;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.main.BaseActivity;
import example.abe.com.framework.annotation.ContentView;
import example.abe.com.framework.annotation.ViewInject;
import example.abe.com.framework.util.LogUtil;

@ContentView(id = R.layout.activity_recycle_list)
public class RecycleListActivity extends BaseActivity {

    @ViewInject(id = R.id.act_abrecycle_list_rv)
    private RecyclerView mRecyclerView;
    private List<String> mData;
    private ListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //数据初始化
        mData = new ArrayList<>();
        for (int i = 'a'; i <= 'z'; i++) {
            mData.add(String.valueOf((char) i));
        }

        //RecycleView视图
        mAdapter = new ListAdapter(this, mData);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClickItem(View view, int position) {
                LogUtil.d("onClickItem:\nview--" + view + "\nposition--" + position);
            }

            @Override
            public boolean onLongClickItem(View view, int position) {
                LogUtil.d("onLongClickItem:\nview--" + view + "\nposition--" + position);
                return false;
            }
        });
        mRecyclerView.setAdapter(mAdapter);//设置adapter
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//设置布局管理器
        mRecyclerView.addItemDecoration(new DivideDecoration());//添加分割线
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//设置Item增加、移除动画

        //增加删除按钮
        findViewById(R.id.act_abrecycle_list_btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.addData(1);
            }
        });
        findViewById(R.id.act_abrecycle_list_btn_del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.removeData(1);
            }
        });

    }

    interface OnItemClickListener {

        void onClickItem(View view, int position);

        boolean onLongClickItem(View view, int position);
    }

    class ListAdapter extends RecyclerView.Adapter<ViewHolder> {

        private OnItemClickListener l;
        private List<String> mData;
        private Context mContext;

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
            l = listener;
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
                    if (l != null) {
                        l.onClickItem(v, position);
                    }
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (l != null) {
                        return l.onLongClickItem(v, position);
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

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView ivIcon;

        public ViewHolder(View view) {
            super(view);
            tvTitle = (TextView) view.findViewById(R.id.item_abrecycle_list_tv_title);
            ivIcon = (ImageView) view.findViewById(R.id.item_abrecycle_list_img_icon);
        }
    }

    class DivideDecoration extends RecyclerView.ItemDecoration {

        private Drawable mDivider;

        {
            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(Color.LTGRAY);
            drawable.setSize(0, 1);
            mDivider = drawable;
        }

        /*
         * 绘制分割线,根据ChildView的位置,在RecycleView的相应位置绘制分割线
         * 每次ChildView的位置改变(即,滑动ChildView),就需要重新绘制
         */
        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            LogUtil.d("onDraw()");

            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();
            int childCount = parent.getChildCount();

            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }

        /*
         * 位置偏移
         */
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        }
    }

}

