package example.abe.com.framework.viewinject;

import android.app.Activity;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

import example.abe.com.framework.viewinject.provider.ActivityProvider;
import example.abe.com.framework.viewinject.provider.Provider;
import example.abe.com.framework.viewinject.provider.ViewProvider;

public class ViewInjectUtil {

    //Activity实现策略
    private static final ActivityProvider PROVIDER_ACTIVITY = new ActivityProvider();
    //View实现策略
    private static final ViewProvider PROVIDER_VIEW = new ViewProvider();
    //注解方法缓存
    private static final Map<String, IViewInject> VIEW_INJECT_MAP = new HashMap<>();

    public static void inject(Activity activity) {
        inject(activity, activity, PROVIDER_ACTIVITY);
    }

    public static void inject(View view) {
        inject(view, view);
    }

    public static void inject(Object host, View view) {
        inject(host, view, PROVIDER_VIEW);
    }

    public static void inject(Object host, Object source, Provider provider) {
        String className = host.getClass().getName();
        try {
            IViewInject viewInject = VIEW_INJECT_MAP.get(className);
            if (viewInject == null) {
                Class<?> finderClass = Class.forName(className + "$$ViewInject");
                viewInject = (IViewInject) finderClass.newInstance();
                VIEW_INJECT_MAP.put(className, viewInject);
            }
            viewInject.inject(host, source, provider);
        } catch (Exception e) {
//            throw new RuntimeException("Unable to inject for " + className, e);
        }
    }
}
