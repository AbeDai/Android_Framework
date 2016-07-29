package example.abe.com.framework.util;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import example.abe.com.framework.Annotation.ContentView;
import example.abe.com.framework.Annotation.ViewInject;

/**
 * Created by Administrator on 2016/7/29.
 */
public class ABViewInjectUtils {
    private static final String METHOD_SET_CONTENT_VIEW = "setContentView";
    private static final String METHOD_FIND_VIEW_BY_ID = "findViewById";

    public static void inject(Activity activity) {
        injectContentView(activity);
        injectViews(activity);
    }

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
                    method.invoke(activity, id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

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