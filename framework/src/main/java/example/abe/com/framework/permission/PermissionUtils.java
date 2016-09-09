package example.abe.com.framework.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.Fragment;

import com.example.PermissionFail;
import com.example.PermissionSuccess;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by abe on 16/9/9.
 */
public class PermissionUtils {
    private String[] mPermissions;
    private int mRequestCode;
    private Object object;

    /**
     * 添加对象
     *
     * @param activity 活动页
     * @return
     */
    public static PermissionUtils with(Activity activity) {
        return new PermissionUtils(activity);
    }

    /**
     * 添加对象
     *
     * @param fragment 片段
     * @return
     */
    public static PermissionUtils with(Fragment fragment) {
        return new PermissionUtils(fragment);
    }

    /**
     * 添加权限
     *
     * @param permissions 权限数组
     * @return
     */
    public PermissionUtils permissions(String... permissions) {
        this.mPermissions = permissions;
        return this;
    }

    /**
     * 添加需求码
     *
     * @param requestCode 需求码
     * @return
     */
    public PermissionUtils addRequestCode(int requestCode) {
        this.mRequestCode = requestCode;
        return this;
    }

    /**
     * 开始请求
     */
    public void request() {
        if (object != null && mRequestCode != -1 && mPermissions != null) {
            requestPermissions(object, mRequestCode, mPermissions);
        }
    }

    /**
     * 请求权限申请结果
     *
     * @param activity     当前视图
     * @param requestCode  请求码
     * @param permissions  权限数组
     * @param grantResults 请求结果
     */
    public static void onRequestPermissionsResult(Activity activity, int requestCode, String[] permissions,
                                                  int[] grantResults) {
        requestResult(activity, requestCode, permissions, grantResults);
    }

    /**
     * 请求权限申请结果
     *
     * @param fragment     当前片段
     * @param requestCode  请求码
     * @param permissions  权限数组
     * @param grantResults 请求结果
     */
    public static void onRequestPermissionsResult(Fragment fragment, int requestCode, String[] permissions,
                                                  int[] grantResults) {
        requestResult(fragment, requestCode, permissions, grantResults);
    }

    /**
     * 构造函数
     *
     * @param object 对象
     */
    private PermissionUtils(Object object) {
        initData();

        this.object = object;
    }

    /**
     * 数据初始化
     */
    private void initData() {
        this.mRequestCode = -1;
        this.mPermissions = null;
        this.object = null;
    }

    /**
     * 申请权限
     *
     * @param object      当前环境（Activity或Fragment）
     * @param requestCode 请求码
     * @param permissions 权限组
     */
    @TargetApi(value = Build.VERSION_CODES.M)
    private static void requestPermissions(Object object, int requestCode, String[] permissions) {
        if (!PermissionsHelper.isOverMarshmallow()) {
            doExecuteSuccess(object, requestCode);
            return;
        }

        //过滤权限
        List<String> deniedPermissions = PermissionsHelper.filterDeniedPermissions(getActivity(object), permissions);

        //申请权限
        if (deniedPermissions.size() > 0) {
            if (object instanceof Activity) {
                ((Activity) object).requestPermissions(deniedPermissions.toArray(new String[deniedPermissions.size()]), requestCode);
            } else if (object instanceof Fragment) {
                ((Fragment) object).requestPermissions(deniedPermissions.toArray(new String[deniedPermissions.size()]), requestCode);
            } else {
                throw new IllegalArgumentException(object.getClass().getName() + " is not supported");
            }
        } else {
            doExecuteSuccess(object, requestCode);
        }
    }

    /**
     * 执行请求成功回调
     *
     * @param object      当前环境对象（Activity或Fragment）
     * @param requestCode 请求码
     */
    private static void doExecuteSuccess(Object object, int requestCode) {
        Method executeMethod = PermissionsHelper.findMethodWithRequestCode(object.getClass(),
                PermissionSuccess.class, requestCode);

        executeMethod(object, executeMethod);
    }

    /**
     * 执行请求失败回调
     *
     * @param object      当前环境对象（Activity或Fragment）
     * @param requestCode 请求码
     */
    private static void doExecuteFail(Object object, int requestCode) {
        Method executeMethod = PermissionsHelper.findMethodWithRequestCode(object.getClass(),
                PermissionFail.class, requestCode);

        executeMethod(object, executeMethod);
    }

    /**
     * 反射执行函数
     *
     * @param object 调用函数对象
     * @param method 函数
     */
    private static void executeMethod(Object object, Method method) {
        if (method != null) {
            try {
                method.setAccessible(true);
                method.invoke(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 请求结果回调
     *
     * @param obj          当前环境（Activity或Fragment）
     * @param requestCode  请求码
     * @param permissions  权限组
     * @param grantResults 请求结果
     */
    private static void requestResult(Object obj, int requestCode, String[] permissions,
                                      int[] grantResults) {
        List<String> deniedPermissions = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                deniedPermissions.add(permissions[i]);
            }
        }

        if (deniedPermissions.size() > 0) {
            doExecuteFail(obj, requestCode);
        } else {
            doExecuteSuccess(obj, requestCode);
        }
    }

    /**
     * 获取当前视图
     *
     * @param object 对象（Activity或Fragment）
     * @return 视图
     */
    private static Activity getActivity(Object object) {
        if (object instanceof Fragment) {
            return ((Fragment) object).getActivity();
        } else if (object instanceof Activity) {
            return (Activity) object;
        }
        return null;
    }
}

