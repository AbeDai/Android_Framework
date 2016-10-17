package example.abe.com.framework.recycleview.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by abe on 16/10/17.
 */

public interface ItemHeadFootDelegate<T> {
    /**
     * 获取内容视图
     * @param context 上下文环境
     * @param parent 父视图
     * @return 内容视图
     */
    View getItemView(Context context, ViewGroup parent);

    /**
     * 绑定数据
     * @param holder ViewHolder
     * @param t 数据
     * @param position 位置
     */
    void bindViewHolder(ViewHolder holder, T t, int position);
}
