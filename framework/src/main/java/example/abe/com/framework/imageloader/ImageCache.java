package example.abe.com.framework.imageloader;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import example.abe.com.framework.util.FileUtil;
import example.abe.com.framework.util.LogUtil;

/**
 * Created by abe on 16/8/31.
 */
public class ImageCache {

    /**
     * 图片缓存的核心对象
     */
    private LruCache<String, Bitmap> mLruCache;

    public ImageCache() {
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheMemory = maxMemory / 8;
        mLruCache = new LruCache<String, Bitmap>(cacheMemory) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //获取位图大小
                return value.getByteCount();
            }
        };
    }

    /**
     * 保存图片到缓存
     *
     * @param uid   名字
     * @param bitmap 位图
     */
    public synchronized void saveBitmapCache(String uid, Bitmap bitmap) {
        if (mLruCache.get(uid) == null) {
            mLruCache.put(uid, bitmap);
        }
    }

    /**
     * 保存图片到本地
     *
     * @param name   名字
     * @param bytes 位图字节码
     */
    public synchronized void saveBitmapDisk(String name, byte[] bytes) {
        if (!FileUtil.isImageFileExists(name)) {
            FileUtil.saveBitmapBytes(name, bytes);
        }
    }

    /**
     * 获取缓存中保存的图片
     *
     * @param uid 名字
     * @return 位图
     */
    public synchronized Bitmap getBitmapCache(String uid) {
        Bitmap bitmap;
        if ((bitmap = mLruCache.get(uid)) != null) {
            return bitmap;
        }
        return bitmap;
    }

    /**
     * 获取本地中保存的图片
     *
     * @param name 名字
     * @return 位图
     */
    public synchronized byte[] getBitmapDisk(String name) {
        byte[] bytes = new byte[0];
        if (FileUtil.isImageFileExists(name)) {
            bytes = FileUtil.getBitmapBytes(name);
        }
        return bytes;
    }

    /**
     * 判断缓存中存在图片
     *
     * @param uid 名字
     * @return 是否存在
     */
    public synchronized boolean isExistsCache(String uid) {
        if (mLruCache.get(uid) != null)
            return true;

        return false;
    }

    /**
     * 判断本地中存在图片
     *
     * @param name 名字
     * @return 是否存在
     */
    public synchronized boolean isExistsDisk(String name) {
        if (FileUtil.isImageFileExists(name))
            return true;

        return false;
    }
}