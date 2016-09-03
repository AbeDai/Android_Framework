package example.abe.com.framework.imageloader.handle;

import android.graphics.Bitmap;

/**
 * Created by abe on 16/9/1.
 */
public interface IImageHandle {

    /**
     * 获取处理后得到的图片的唯一id
     * （将与图片处理有关的所有因素都结合组成一个MD5码）
     *
     * @return 唯一id
     */
    String getUniqueId();

    /**
     * 获取图片地址
     * @return 图片地址
     */
    String getImageUrl();

    /**
     * 处理图片
     * @param bytes 原始图片字节码
     * @return 处理后图片
     */
    Bitmap onPreHandle(byte[] bytes);

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
