package example.abe.com.android.activity.suspend;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.WindowManager;

import example.abe.com.android.main.MyApplication;
import example.abe.com.framework.util.DensityUtil;

/**
 * Created by abe on 16/9/28.
 */
public class MemoryWindowManager {

    private static MemoryWindowManager instance;
    private Context mContext;
    private WindowManager mWindowManager;
    private ActivityManager mActivityManager;
    private MemoryWindow mMemoryWindow;
    private WindowManager.LayoutParams mMemoryWindowParams;

    /**
     * 获取内存管理类单例
     *
     * @return 单例
     */
    public static MemoryWindowManager getInstance() {
        if (instance == null) {
            synchronized (MemoryWindowManager.class) {
                instance = new MemoryWindowManager();
            }
        }
        return instance;
    }

    /**
     * 构造函数
     */
    public MemoryWindowManager() {
        initData();
    }

    /**
     * 数据初始化
     */
    private void initData() {
        mContext = MyApplication.getInstance().getApplicationContext();
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        mActivityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
    }

    /**
     * 是否显示内存悬浮窗
     *
     * @return 是否显示
     */
    public boolean isShowMemoryWindow() {
        return mMemoryWindow != null;
    }

    /**
     * 显示内存悬浮窗
     */
    public void showMemoryWindow() {
        mMemoryWindow = new MemoryWindow(mContext);
        WindowManager.LayoutParams params = getMemoryWindowLayoutParams(0, 0);
        mWindowManager.addView(mMemoryWindow, params);
    }

    /**
     * 移除内存悬浮窗
     */
    public void removeMemoryWindow() {
        mWindowManager.removeView(mMemoryWindow);
        mMemoryWindow = null;
    }

    /**
     * 更新内存悬浮窗位置
     */
    public void setMemoryWindowPosition(float x, float y) {
        WindowManager.LayoutParams param = getMemoryWindowLayoutParams((int) x, (int) y);
        mWindowManager.updateViewLayout(mMemoryWindow, param);
    }

    /**
     * 获取基础LayoutParam属性
     *
     * @return 基础属性LayoutParam
     */
    private WindowManager.LayoutParams getMemoryWindowLayoutParams(int x, int y) {
        if (mMemoryWindowParams == null) {
            mMemoryWindowParams = new WindowManager.LayoutParams();
            mMemoryWindowParams.type = WindowManager.LayoutParams.TYPE_PHONE;
            mMemoryWindowParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            mMemoryWindowParams.format = PixelFormat.RGBA_8888;
            mMemoryWindowParams.gravity = Gravity.LEFT | Gravity.TOP;
            mMemoryWindowParams.width = DensityUtil.dip2px(mContext, 30);
            mMemoryWindowParams.height = DensityUtil.dip2px(mContext, 18);
        }
        mMemoryWindowParams.x = x;
        mMemoryWindowParams.y = y;
        return mMemoryWindowParams;
    }

    /**
     * 更新内存悬浮窗
     */
    public void updateMemoryData() {
        int percentAvail = (int) (100 - ((float) getAvailMemory() / getTotalMemory() * 100));
        mMemoryWindow.setTextContent(percentAvail + "%");
    }

    /**
     * 获取已用内存
     *
     * @return 已用内存(单位:byte)
     */
    private long getAvailMemory() {
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        mActivityManager.getMemoryInfo(mi);
        return mi.availMem;
    }

    /**
     * 获取总内存
     *
     * @return 总内存(单位:byte)
     */
    private long getTotalMemory() {
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        mActivityManager.getMemoryInfo(mi);
        return mi.totalMem;
    }
}
