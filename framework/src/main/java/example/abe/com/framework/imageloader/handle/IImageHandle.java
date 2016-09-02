package example.abe.com.framework.imageloader.handle;

import android.graphics.Bitmap;

/**
 * Created by abe on 16/9/1.
 */
public interface IImageHandle {

    /**
     * 获取图片地址
     * @return 图片地址
     */
    String getImageUrl();

    /**
     * 处理图片
     * @param origin 原始图片
     * @return 处理后图片
     */
    Bitmap onPreHandle(Bitmap origin);

    /**
     * 图片加载成功回调
     * @param bitmap 最终图片
     * @param url 图片地址
     */
    void onImageLoadSuccess(Bitmap bitmap, String url);

    /**
     * 图片加载成功回调
     * @param url 图片地址
     */
    void onImageLoadFailed(String url);
}
