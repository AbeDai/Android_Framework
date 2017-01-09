package example.abe.com.framework.imageloader;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import example.abe.com.framework.imageloader.handle.IImageHandle;
import example.abe.com.framework.util.EncryptionUtil;

/**
 * Created by abe on 16/8/31.
 */
public class LoadCenter {

    //队列排列形式
    private QueueType mQueueType;
    public enum  QueueType{
        FIFO, LIFO,
    }

    //线程池
    private ExecutorService mThreadPool;
    private static final int CPU_COUNT =  Runtime.getRuntime().availableProcessors();
    private static final int DEFAULT_THREAD_COUNT = CPU_COUNT + 1;

    //后台线程
    private Thread mPoolThread;
    private Handler mPoolThreadHandler;

    //UI操作
    private Handler mUIHandler;

    //图片缓存
    private ImageCache mImageCache;

    //任务栈
    private LinkedList<Runnable> mTaskQueue;
    private byte[] mLockTaskQueue;

    //信号量
    private Semaphore mSemaphorePoolThreadHandler;
    private Semaphore mSemaphoreThreadPool;

    /**
     * 创建图片加载中心
     * @param queueType 任务排队模式
     */
    public LoadCenter(QueueType queueType) {
        mQueueType = queueType;
        init();
    }

    /**
     * 提交图片处理任务
     * @param imageHandle 图像处理策略
     */
    public void submitTask(IImageHandle imageHandle) {
        addTask(createTask(imageHandle));
    }

    /**
     * 初始化
     */
    private void init() {
        mUIHandler = new Handler();
        mImageCache = new ImageCache();
        mTaskQueue = new LinkedList<>();
        mLockTaskQueue = new byte[0];
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
                mSemaphorePoolThreadHandler.release();//mPoolThreadHandler已创建通知
                Looper.loop();
            }
        };
        mPoolThread.start();
    }

    /**
     * 加载Bitmap任务
     *
     * @param imageHandle 图片处理策略
     * @return 任务
     */
    private Runnable createTask(final IImageHandle imageHandle) {
        return new Runnable() {
            @Override
            public void run() {
                byte[] bytes;
                final Bitmap bitmap;
                final String url = imageHandle.getImageUrl();
                String name = EncryptionUtil.getMd5(url);
                String uid = imageHandle.getUniqueId();

                //保存在内存
                if (mImageCache.isExistsCache(uid)){
                    bitmap = mImageCache.getBitmapCache(uid);
                }
                //保存在本地
                else if (mImageCache.isExistsDisk(name)
                        && (bytes = mImageCache.getBitmapDisk(name)).length > 0){
                    bitmap = imageHandle.onPreHandle(bytes);
                    mImageCache.saveBitmapCache(uid, bitmap);
                }
                //网络加载
                else{
                    bytes = ImageNetworkUtil.loadByteArrayByUrl(url);
                    mImageCache.saveBitmapDisk(name, bytes);
                    bitmap = imageHandle.onPreHandle(bytes);
                    mImageCache.saveBitmapCache(uid, bitmap);
                }

                mUIHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (bitmap == null){
                            imageHandle.onImageLoadFailed(url);
                        }else {
                            imageHandle.onImageLoadSuccess(bitmap, url);
                        }
                    }
                });

                //使最新任务先执行
                mSemaphoreThreadPool.release();
            }
        };
    }

    /**
     * 添加任务
     *
     * @param task 任务
     */
    private void addTask(Runnable task) {
        synchronized (mLockTaskQueue){
            if (mQueueType == QueueType.LIFO){
                mTaskQueue.push(task);
            }else {
                mTaskQueue.add(task);
            }
        }

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
     * 获取任务
     *
     * @return 任务
     */
    private Runnable getTask() {
        synchronized (mLockTaskQueue){
            if (mQueueType == QueueType.LIFO){
                return mTaskQueue.pop();
            }else {
                return mTaskQueue.poll();
            }
        }
    }
}
