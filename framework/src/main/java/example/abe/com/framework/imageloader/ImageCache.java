package example.abe.com.framework.imageloader;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import example.abe.com.framework.util.FileUtil;

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
     * 保存图片
     *
     * @param name   名字
     * @param bitmap 位图
     */
    public void saveBitmap(String name, Bitmap bitmap) {
        if (mLruCache.get(name) == null){
            mLruCache.put(name, bitmap);
        }
        if (!isExists(name)){
            FileUtil.saveBitmap(name, bitmap);
        }
    }

    /**
     * 获取已保存图片
     *
     * @param name 名字
     * @return 位图
     */
    public Bitmap getBitmap(String name) {
        Bitmap bitmap = null;
        if ((bitmap = mLruCache.get(name)) != null){
            return bitmap;
        }
        if (isExists(name)){
            bitmap = FileUtil.getBitmap(name);
            mLruCache.put(name, bitmap);
            return bitmap;
        }
        return bitmap;
    }

    /**
     * 图片是否已存在
     *
     * @param name 名字
     * @return 是否存在
     */
    public boolean isExists(String name) {
        return FileUtil.getImageFile(name).exists();
    }
}
