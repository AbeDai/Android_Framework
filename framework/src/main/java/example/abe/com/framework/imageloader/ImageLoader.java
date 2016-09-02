package example.abe.com.framework.imageloader;

import android.widget.ImageView;

import java.util.Queue;

import example.abe.com.framework.imageloader.handle.BitmapHandle;
import example.abe.com.framework.imageloader.handle.ImageViewHandle;

/**
 * Created by abe on 16/9/1.
 */
public class ImageLoader {

    //单例
    private static ImageLoader mInstance;

    //加载中心
    private LoadCenter mLoadCenterFIFO;
    private LoadCenter mLoadCenterLIFO;

    /**
     * 获取单例
     *
     * @return 单例
     */
    public static ImageLoader getInstance() {
        if (mInstance == null) {
            synchronized (LoadCenter.class) {
                mInstance = new ImageLoader();
            }
        }
        return mInstance;
    }

    private ImageLoader() {
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        mLoadCenterFIFO = new LoadCenter(LoadCenter.QueueType.FIFO);
        mLoadCenterLIFO = new LoadCenter(LoadCenter.QueueType.LIFO);
    }

    /***********************  位图处理策略  ***********************/
    public void getImage(String url, BitmapHandle.BitmapHandleListener listener) {
        getImageLIFO( url,listener);
    }

    public void getImageFIFO(String url, BitmapHandle.BitmapHandleListener listener) {
        mLoadCenterFIFO.submitTask(new BitmapHandle(url, listener));
    }

    public void getImageLIFO(String url, BitmapHandle.BitmapHandleListener listener) {
        mLoadCenterLIFO.submitTask(new BitmapHandle(url, listener));
    }

    /***********************  图片处理策略  ***********************/
    public void getImage(ImageView imageView, String url) {
        getImageLIFO(imageView, url);
    }

    public void getImageFIFO(ImageView imageView, String url) {
        mLoadCenterFIFO.submitTask(new ImageViewHandle(imageView, url));
    }

    public void getImageLIFO(ImageView imageView, String url) {
        mLoadCenterLIFO.submitTask(new ImageViewHandle(imageView, url));
    }
}
