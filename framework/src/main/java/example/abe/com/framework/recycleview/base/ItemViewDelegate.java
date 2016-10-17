package example.abe.com.framework.recycleview.base;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by abe on 16/10/11.
 */
public interface ItemViewDelegate<T> {

    /**
     * 获取表单内容视图
     * @param context 上下文环境
     * @param parent 父视图
     * @return 内容视图
     */
    View getItemView(Context context, ViewGroup parent);

    /**
     * 根据数据和位置来判断是否为当前ViewType
     * @param item 数据内容
     * @param position 位置
     * @return 是否为当前ViewType
     */
    boolean isForViewType(T item, int position);

    /**
     * 为ViewHolder绑定数据
     * @param holder ViewHolder内容
     * @param t 数据
     * @param position 位置
     */
    void bindViewHolder(ViewHolder holder, T t, int position);
}
