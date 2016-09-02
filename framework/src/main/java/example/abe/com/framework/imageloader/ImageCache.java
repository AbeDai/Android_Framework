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
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    /**
     * 保存图片到缓存
     *
     * @param name   名字
     * @param bitmap 位图
     */
    public synchronized void saveBitmapCache(String name, Bitmap bitmap) {
        if (mLruCache.get(name) == null) {
            mLruCache.put(name, bitmap);
        }
    }

    /**
     * 保存图片到本地
     *
     * @param name   名字
     * @param bitmap 位图
     */
    public synchronized void saveBitmapDisk(String name, Bitmap bitmap) {
        if (!FileUtil.isImageFileExists(name)) {
            FileUtil.saveBitmap(name, bitmap);
        }
    }

    /**
     * 获取缓存中保存的图片
     *
     * @param name 名字
     * @return 位图
     */
    public synchronized Bitmap getBitmapCache(String name) {
        Bitmap bitmap;
        if ((bitmap = mLruCache.get(name)) != null) {
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
    public synchronized Bitmap getBitmapDisk(String name) {
        Bitmap bitmap = null;
        if (FileUtil.isImageFileExists(name)) {
            bitmap = FileUtil.getBitmap(name);
            mLruCache.put(name, bitmap);
            return bitmap;
        }
        return bitmap;
    }

    /**
     * 判断缓存中存在图片
     *
     * @param name 名字
     * @return 是否存在
     */
    public synchronized boolean isExistsCache(String name) {
        if (mLruCache.get(name) != null)
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