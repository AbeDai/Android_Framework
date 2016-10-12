package example.abe.com.framework.recycleview.wrapper;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;

/**
 * Created by abe on 16/10/11.
 */
public class WrapperHelper {

    //HeaderAndFooterWrapper修饰者中使用
    public static final int ITEM_TYPE_HEADER = Integer.MAX_VALUE - 102;
    public static final int ITEM_TYPE_FOOTER = Integer.MAX_VALUE - 202;
    //LoadMoreWrapper修饰者中使用
    public static final int ITEM_TYPE_LOAD_MORE = Integer.MAX_VALUE - 2;

    /**
     * SpanSizeLookup回调接口
     */
    public interface SpanSizeCallback {
        int getSpanSize(GridLayoutManager layoutManager, GridLayoutManager.SpanSizeLookup oldLookup, int position);
    }

    /**
     * GridLayoutManager布局Item宽度设置满屏
     * @param recyclerView RecycleView对象
     * @param callback SpanSizeLookup回调接口
     */
    public static void onAttachedToRecyclerView(RecyclerView recyclerView, final SpanSizeCallback callback) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            final GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();

            //设置Item宽度
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return callback.getSpanSize(gridLayoutManager, spanSizeLookup, position);
                }
            });
            gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount());
        }
    }

    /**
     * StaggeredGridLayoutManager布局Item宽度设置满屏
     * @param holder ViewHolder
     */
    public static void setFullSpan(RecyclerView.ViewHolder holder) {
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(true);
        }
    }
}
