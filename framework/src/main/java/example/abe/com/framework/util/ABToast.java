package example.abe.com.framework.util;

import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import example.abe.com.framework.MyApplication;

/**
 * Created by abe on 16/7/3.
 */

public class ABToast {
    private static Toast mToast;
    private static Toast mToastEdit;

    public static void makeText(CharSequence msg) {
        showToast(msg, Toast.LENGTH_SHORT);
    }

    public static void makeText(int resId) {
        showToast(resId, Toast.LENGTH_SHORT);
    }

    public static void makeTextLong(CharSequence msg) {
        showToast(msg, Toast.LENGTH_LONG);
    }

    public static void makeTextLong(int resId) {
        showToast(resId, Toast.LENGTH_LONG);
    }

    private static void showToast(CharSequence msg, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(MyApplication.getInstance(), msg, duration);
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
            mToast = Toast.makeText(MyApplication.getInstance(), resId, duration);
        } else {
            mToast.setText(resId);
            mToast.setDuration(duration);
        }
        mToast.show();
    }

    public static void showEditToast(View view) {
        if (mToastEdit == null) {
            mToastEdit = new Toast(MyApplication.getInstance());
        }
        mToastEdit.setView(view);
        mToastEdit.setDuration(Toast.LENGTH_SHORT);
        mToastEdit.setGravity(Gravity.CENTER, 0, 0);
        mToastEdit.show();
    }
}
