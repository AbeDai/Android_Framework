package example.abe.com.framework.viewinject.provider;

import android.content.Context;
import android.view.View;

/**
 * 查找目标View的策略接口
 */
public interface Provider {

    /**
     * 获取当前上下文环境
     * @param source 视图所在的源对象
     * @return 上下文环境
     */
    Context getContext(Object source);

    /**
     * 包装获取视图的方法
     * @param source 视图所在的源对象
     * @param id 视图id
     * @return 视图
     */
    View findView(Object source, int id);
}
