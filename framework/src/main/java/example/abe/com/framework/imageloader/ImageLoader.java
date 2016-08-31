package example.abe.com.framework.imageloader;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import example.abe.com.framework.util.LogUtil;

/**
 * Created by abe on 16/8/31.
 */
//TODO:考虑设计模式，然后可以处理多种形式的图片加载任务
//TODO:图片压缩需要考虑一下
public class ImageLoader {

    /**
     * bitmap监听
     */
    public interface BitmapListener {
        void onHandle(Bitmap bitmap);
    }

    //单例
    private static ImageLoader mInstance;

    //线程池
    private ExecutorService mThreadPool;
    private static final int DEFAULT_THREAD_COUNT = 3;

    //后台线程
    private Thread mPoolThread;
    private Handler mPoolThreadHandler;

    //UI操作
    private Handler mUIHandler;

    //图片缓存
    private ImageCache mImageCache;

    //任务栈
    private LinkedList<Runnable> mTaskStack;

    //信号量
    private Semaphore mSemaphorePoolThreadHandler;
    private Semaphore mSemaphoreThreadPool;

    /**
     * 获取单例
     *
     * @return 单例
     */
    public static ImageLoader getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                mInstance = new ImageLoader();
            }
        }
        return mInstance;
    }

    public void getBitmap(String url, BitmapListener listener) {
        addTask(createTask(url, listener));
    }

    /**
     * 内部构造函数
     */
    private ImageLoader() {
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        mUIHandler = new Handler();
        mImageCache = new ImageCache();
        mTaskStack = new LinkedList<>();
        mSemaphorePoolThreadHandler = new Semaphore(0);
        mSemaphoreThreadPool = new Semaphore(DEFAULT_THREAD_COUNT);
        mThreadPool = Executors.newFixedThreadPool(DEFAULT_THREAD_COUNT);

        initBackThread();
    }

    /**
     * 后台线程初始化
     */
    private void initBackThread() {
        mPoolThread = new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                mPoolThreadHandler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        //使最新任务先执行
                        try {
                            mSemaphoreThreadPool.acquire();
                        }catch (InterruptedException e) {
                        }

                        mThreadPool.execute(getTask());
                    }
                };
                mSemaphorePoolThreadHandler.release();//通知mPoolThreadHandler创建
                Looper.loop();
            }
        };
        mPoolThread.start();
    }

    //TODO:需要添加一个网络加载失败的回调。
    /**
     * 加载Bitmap任务
     *
     * @param url      地址
     * @param listener 回调
     * @return 任务
     */
    private Runnable createTask(final String url, final BitmapListener listener) {
        return new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap;
                String name = getFileFullNameByUrl(url);

                if (mImageCache.isExists(name)){
                    bitmap = mImageCache.getBitmap(name);
                }else{
                    bitmap = ImageNetworkUtil.downloadImgByUrl(url);
                    mImageCache.saveBitmap(name, bitmap);
                }

                mUIHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onHandle(bitmap);
                    }
                });

                //使最新任务先执行
                mSemaphoreThreadPool.release();
            }
        };
    }

    /**
     * 添加任务（LIFO）
     *
     * @param task 任务
     */
    private void addTask(Runnable task) {
        mTaskStack.push(task);

        //等待mPoolThreadHandler创建
        while (mPoolThreadHandler == null) {
            try {
                mSemaphorePoolThreadHandler.acquire();
            } catch (InterruptedException e) {
            }
        }
        mPoolThreadHandler.sendEmptyMessage(0);
    }

    /**
     * 获取任务（LIFO）
     *
     * @return 任务
     */
    private Runnable getTask() {
        return mTaskStack.pop();
    }

    //TODO: 改成MD5加密模式
    private String getFileFullNameByUrl(String url) {
        String name = "";
        if (url != null) {
            name = url.replaceAll(":", "_");
            name = name.replaceAll("/", "_");
            name = name.replaceAll("\\.", "_");
            name = name + ".png";
        }
        return name;
    }


}
