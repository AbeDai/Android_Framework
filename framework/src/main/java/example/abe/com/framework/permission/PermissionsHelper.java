package example.abe.com.framework.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import com.example.PermissionFail;
import com.example.PermissionSuccess;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by abe on 16/9/9.
 */
public class PermissionsHelper {

    /**
     * 判断系统版本是否大于等于6.0
     *
     * @return 是否大于6.0
     */
    public static boolean isOverMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    /**
     * 过滤被拒绝的权限
     *
     * @param activity   视图对象
     * @param permission 权限数组
     * @return 被拒绝的权限数组
     */
    @TargetApi(value = Build.VERSION_CODES.M)
    public static List<String> filterDeniedPermissions(Activity activity, String... permission) {
        List<String> denyPermissions = new ArrayList<>();
        if (isOverMarshmallow()) {
            for (String value : permission) {
                if (activity.checkSelfPermission(value) != PackageManager.PERMISSION_GRANTED) {
                    denyPermissions.add(value);
                }
            }
        }
        return denyPermissions;
    }

    /**
     * 过滤需要显示原理的权限
     *
     * @param activity    视图对象
     * @param permissions 权限数组
     * @return 需要显示原理的权限
     */
    @TargetApi(value = Build.VERSION_CODES.M)
    public static List<String> filterNeedShowRationalePermissions(Activity activity, String... permissions) {
        List<String> showRationalePermissions = new ArrayList<>();
        for (String value : permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, value)) {
                showRationalePermissions.add(value);
            }
        }
        return showRationalePermissions;
    }

    /**
     * 判断函数请求码与参数请求码是否相等
     *
     * @param m           函数
     * @param clazz       注解类
     * @param requestCode 请求码
     * @return
     */
    public static boolean isEqualRequestCodeFromAnnotation(Method m, Class clazz, int requestCode) {
        if (clazz.equals(PermissionFail.class)) {
            return requestCode == m.getAnnotation(PermissionFail.class).requestCode();
        } else if (clazz.equals(PermissionSuccess.class)) {
            return requestCode == m.getAnnotation(PermissionSuccess.class).requestCode();
        }else {
            return false;
        }
    }

    /**
     * 查找clazz中被annotation标注的且请求码为requestCode的函数
     *
     * @param clazz       类
     * @param annotation  注解类
     * @param requestCode 请求码
     * @return 函数
     */
    public static <A extends Annotation> Method findMethodWithRequestCode(Class clazz,
                                                                          Class<A> annotation, int requestCode) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotation)) {
                if (isEqualRequestCodeFromAnnotation(method, annotation, requestCode)) {
                    return method;
                }
            }
        }
        return null;
    }
}
