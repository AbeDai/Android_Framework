package example.abe.com.android.activity.wheel.wheelview;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by abe on 17/6/11.
 */
public interface IWheelAdapter<T> {

    /**
     * 设置内容视图
     * @param position 当前位置
     * @param convertView 重用视图
     * @param parent 父视图
     * @return 内容视图
     */
    View bindView(int position, View convertView, ViewGroup parent);

    /**
     * 根据位置刷新数据
     * @param curPosition 选中位置
     * @param position 当前项位置
     * @param view 视图
     */
    void refreshView(int curPosition, int position, View view);

    /**
     * 获取数据列表数量
     * @return 列表数量
     */
    int getDataCount();

    /**
     * 设置数据列表
     * @param list 数据列表
     */
    void setDataList(List<T> list);

    /**
     * 获取数据列表
     * @return 数据列表
     */
    List<T> getDataList();
}
