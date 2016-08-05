package example.abe.com.framework.viewinject;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/7/29.
 */
public class ViewInjectUtil {
    private static final String METHOD_SET_CONTENT_VIEW = "setContentView";
    private static final String METHOD_FIND_VIEW_BY_ID = "findViewById";

    /**
     * 注入操作初始化
     * @param activity 当前活动
     */
    public static void inject(Activity activity) {
        injectContentView(activity);
        injectViews(activity);
    }

    /**
     * 注入界面操作
     * @param activity 当前活动
     */
    private static void injectContentView(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        ContentView contentViewAnnotation = clazz.getAnnotation(ContentView.class);
        if (contentViewAnnotation != null) {
            int id = contentViewAnnotation.id();
            if (id != -1) {
                try {
                    Method method = clazz.getMethod(METHOD_SET_CONTENT_VIEW, int.class);
                    method.setAccessible(true);
                    method.invoke(activity, id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 注入视图属性
     * @param activity 当前活动
     */
    private static void injectViews(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields){
            ViewInject viewInjectAnnotation = field.getAnnotation(ViewInject.class);
            if (viewInjectAnnotation != null){
                int id = viewInjectAnnotation.id();
                if (id != -1){
                    try {
                        Method method = clazz.getMethod(METHOD_FIND_VIEW_BY_ID, Integer.TYPE);
                        View resView = (View)method.invoke(activity, id);
                        field.setAccessible(true);
                        field.set(activity, resView);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}