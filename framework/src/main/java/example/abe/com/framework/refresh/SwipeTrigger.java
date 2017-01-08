package example.abe.com.framework.refresh;

/**
 * Created by abe on 17/1/4.
 * 滑动基础触发器（视图处理相关）
 */
public interface SwipeTrigger {

    /**
     * 准备操作 <br>
     *     STATUS_DEFAULT->STATUS_SWIPING_TO_REFRESH 或者 STATUS_DEFAULT->STATUS_SWIPING_TO_LOAD_MORE
     */
    void onPrepare();

    /**
     * 移动操作
     * @param y 主视图Y轴偏移点
     * @param isComplete 是否完成
     * @param automatic 是否为自动滚动
     */
    void onMove(int y, boolean isComplete, boolean automatic);

    /**
     * 释放操作 <br>
     *     STATUS_RELEASE_TO_LOAD_MORE->STATUS_LOADING_MORE 或者 STATUS_RELEASE_TO_REFRESH->STATUS_REFRESHING
     */
    void onRelease();

    /**
     * 完成操作 <br>
     *     STATUS_REFRESHING->STATUS_DEFAULT 或者 STATUS_LOADING_MORE->STATUS_DEFAULT
     */
    void onComplete();

    /**
     * 重置操作 <br>
     *     STATUS_DEFAULT
     */
    void onReset();
}
