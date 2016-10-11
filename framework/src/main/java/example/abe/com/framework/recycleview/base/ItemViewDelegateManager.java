package example.abe.com.framework.recycleview.base;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Created by zhy on 16/6/22.
 */
public class ItemViewDelegateManager<T> {

    //代理Map
    HashMap<Integer, ItemViewDelegate<T>> delegateMap = new HashMap();

    /**
     * 获取表单元代理数量
     */
    public int getItemViewDelegateCount() {
        return delegateMap.size();
    }

    /**
     * 添加表单元代理
     * @param delegate 表单元代理
     * @return  代理中心
     */
    public ItemViewDelegateManager<T> addDelegate(ItemViewDelegate<T> delegate) {
        int viewType = delegateMap.size();
        if (delegate != null) {
            delegateMap.put(viewType, delegate);
        }
        return this;
    }

    /**
     * 添加表单元代理
     * @param viewType 表单元类型
     * @param delegate 表单元代理
     * @return 代理中心
     */
    public ItemViewDelegateManager<T> addDelegate(int viewType, ItemViewDelegate<T> delegate) {
        if (delegateMap.get(viewType) != null) {
            throw new IllegalArgumentException(
                    "已经存在("+ viewType + ")类型的表单元代理(" + delegateMap.get(viewType) + ")");
        }
        delegateMap.put(viewType, delegate);
        return this;
    }

    /**
     * 移除表单元代理
     * @param delegate 表单元代理
     * @return 代理中心
     */
    public ItemViewDelegateManager<T> removeDelegate(ItemViewDelegate<T> delegate) {
        if (delegate == null) {
            throw new NullPointerException("表单元代理为空");
        }
        Iterator<Map.Entry<Integer, ItemViewDelegate<T>>> iterator = delegateMap.entrySet().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getValue() == delegate) {
                iterator.remove();
                break;
            }
        }
        return this;
    }

    /**
     * 移除表单元代理
     * @param itemType 表单元类型
     * @return 代理中心
     */
    public ItemViewDelegateManager<T> removeDelegate(int itemType) {
        Iterator<Map.Entry<Integer, ItemViewDelegate<T>>> iterator = delegateMap.entrySet().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getKey() == itemType) {
                iterator.remove();
            }
        }
        return this;
    }

    /**
     * 获取表单元代理
     * @param viewType 表单元类型
     * @return 代理中心
     */
    public ItemViewDelegate getItemViewDelegate(int viewType) {
        ItemViewDelegate delegate = delegateMap.get(viewType);
        if (delegate == null){
            throw new IllegalArgumentException("不存在此类型的表单元代理");
        }
        return delegate;
    }

    /**
     * 获取表单元代理类型
     * @param itemViewDelegate 表单元代理
     * @return 代理类型
     */
    public int getItemViewType(ItemViewDelegate itemViewDelegate) {
        for (Map.Entry<Integer, ItemViewDelegate<T>> entry : delegateMap.entrySet()) {
            if (entry.getValue() == itemViewDelegate) {
                return entry.getKey();
            }
        }
        throw new IllegalArgumentException(
                "No ItemViewDelegate added that matches position=" + " in data source");
    }

    /**
     * 获取代理类型(根据数据内容和表单元位置)
     * @param item 数据
     * @param position 位置
     * @return 代理类型
     */
    public int getItemViewType(T item, int position) {
        for (Map.Entry<Integer, ItemViewDelegate<T>> entry : delegateMap.entrySet()) {
            if (entry.getValue().isForViewType(item, position)) {
                return entry.getKey();
            }
        }
        throw new IllegalArgumentException("找不到匹配的代理");
    }

    /**
     * 绑定ViewHolder内容
     * @param holder ViewHolder
     * @param item 数据
     * @param position 位置
     */
    public void bindViewHolder(ViewHolder holder, T item, int position) {
        for (Map.Entry<Integer, ItemViewDelegate<T>> entry : delegateMap.entrySet()) {
            if (entry.getValue().isForViewType(item, position)) {
                entry.getValue().bindViewHolder(holder, item, position);
                return;
            }
        }
        throw new IllegalArgumentException("找不到匹配的代理");
    }

}
