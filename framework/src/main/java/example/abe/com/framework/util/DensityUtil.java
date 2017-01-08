package example.abe.com.framework.util;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by abe on 16/5/19.
 */
public class DensityUtil {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取屏幕的宽高（单位:px）
     * @return int[width, height]
     */
    public static int[] getScreenSize(Context context){
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        int h_screen = dm.heightPixels;

        return new int[]{w_screen, h_screen};
    }

    /**
     * 获取屏幕的宽度（单位:px）
     * @return int[width, height]
     */
    public static int getScreenWidth(Context context){
        return getScreenSize(context)[0];
    }

    /**
     * 获取屏幕的高度（单位:px）
     * @return int[width, height]
     */
    public static int getScreenHeight(Context context){
        return getScreenSize(context)[1];
    }
}