package example.abe.com.framework.util;

import android.util.DisplayMetrics;

import example.abe.com.framework.main.BaseApplication;

/**
 * Created by abe on 16/5/19.
 */
public class DensityUtil {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        final float scale = BaseApplication.getInstance().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        final float scale = BaseApplication.getInstance().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取屏幕的宽高（单位:px）
     * @return int[width, height]
     */
    public static int[] getScreenSize(){
        DisplayMetrics dm = BaseApplication.getInstance().getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        int h_screen = dm.heightPixels;

        return new int[]{w_screen, h_screen};
    }
}