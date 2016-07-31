package example.abe.com.framework.util;

import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import example.abe.com.framework.main.BaseApplication;

/**
 * Created by abe on 16/7/3.
 */

public class ToastUtil {
    private static Toast mToast;
    private static Toast mToastEdit;

    /**
     * Toast的LENGTH_SHORT显示
     * @param msg 消息
     */
    public static void makeText(CharSequence msg) {
        showToast(msg, Toast.LENGTH_SHORT);
    }

    /**
     * Toast的LENGTH_SHORT显示
     * @param resId 消息资源ID
     */
    public static void makeText(int resId) {
        showToast(resId, Toast.LENGTH_SHORT);
    }

    /**
     * Toast的LENGTH_LONG显示
     * @param msg 消息
     */
    public static void makeTextLong(CharSequence msg) {
        showToast(msg, Toast.LENGTH_LONG);
    }

    /**
     * Toast的LENGTH_LONG显示
     * @param resId 消息资源ID
     */
    public static void makeTextLong(int resId) {
        showToast(resId, Toast.LENGTH_LONG);
    }

    private static void showToast(CharSequence msg, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(BaseApplication.getInstance(), msg, duration);
        } else {
            mToast.setText(msg);
            mToast.setDuration(duration);
        }
        mToast.show();
    }

    private static void showToast(int resId, int duration) {
        if (resId == 0) {
            return;
        }

        if (mToast == null) {
            mToast = Toast.makeText(BaseApplication.getInstance(), resId, duration);
        } else {
            mToast.setText(resId);
            mToast.setDuration(duration);
        }
        mToast.show();
    }
}
