package example.abe.com.android.activity.suspend;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import java.util.Timer;
import java.util.TimerTask;

public class MemoryService extends Service {

    private Timer mTimer;//计时器
    private static final int TIME_PERIOD = 2000;//循环时间
    private static final int WHAT_SHOW_MEMORY_WINDOW = 0;//显示内存悬浮窗
    private static final int WHAT_UPDATE_MEMORY_DATA = 1;//更新内存数据
    private static final int WHAT_REMOVE_MEMORY_WINDOW = 2;//移除内存悬浮窗

    public MemoryService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mTimer = new Timer(true);
        mTimer.schedule(mTimerTask, 0, TIME_PERIOD);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    /**
     * 计时任务
     */
    private TimerTask mTimerTask = new TimerTask() {
        @Override
        public void run() {
            if (!MemoryWindowManager.getInstance().isShowMemoryWindow()) {
                //显示内存悬浮窗
                mUIHandle.sendEmptyMessage(WHAT_SHOW_MEMORY_WINDOW);
            }
            //更新内存数据
            mUIHandle.sendEmptyMessage(WHAT_UPDATE_MEMORY_DATA);
        }
    };

    /**
     * UI操作
     */
    private Handler mUIHandle = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case WHAT_SHOW_MEMORY_WINDOW:
                    //显示内存悬浮窗
                    MemoryWindowManager.getInstance().showMemoryWindow();
                    break;
                case WHAT_UPDATE_MEMORY_DATA:
                    //更新内存数据
                    MemoryWindowManager.getInstance().updateMemoryData();
                    break;
                case WHAT_REMOVE_MEMORY_WINDOW:
                    //移除内存悬浮窗
                    MemoryWindowManager.getInstance().removeMemoryWindow();
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //停止计时
        if (mTimer != null) {
            mTimer.purge();
            mTimer.cancel();
            mTimer = null;
        }
        //移除内存悬浮窗
        mUIHandle.sendEmptyMessage(WHAT_REMOVE_MEMORY_WINDOW);
    }
}
