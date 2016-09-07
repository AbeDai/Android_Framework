package example.abe.com.framework.viewinject;

import example.abe.com.framework.viewinject.provider.Provider;

/**
 * 视图注入接口
 * @param <T> 注解类
 */
public interface IViewInject<T> {

    /**
     * 注入视图
     * @param host 注解类
     * @param source 查找 View 的源对象
     * @param provider
     */
    void inject(T host, Object source, Provider provider);
}

