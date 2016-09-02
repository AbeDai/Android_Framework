package example.abe.com.framework.imageloader.handle;

import android.graphics.Bitmap;

import example.abe.com.framework.R;
import example.abe.com.framework.util.ResourceUtil;

/**
 * Created by abe on 16/9/1.
 */
public class BitmapHandle implements IImageHandle {

    public interface BitmapHandleListener {
        void onHandle(Bitmap bitmap);
    }

    private BitmapHandleListener mListener;
    private String mUrl;

    public BitmapHandle(String url, BitmapHandleListener listener){
        mListener = listener;
        mUrl = url;
    }

    @Override
    public String getImageUrl(){
        return mUrl;
    }

    @Override
    public Bitmap onPreHandle(Bitmap origin){
        //TODO:图片压缩需要考虑一下
        return origin;
    }

    @Override
    public void onImageLoadSuccess(Bitmap bitmap, String url){
        if (mListener != null){
            mListener.onHandle(bitmap);
        }
    }

    @Override
    public void onImageLoadFailed(String url){
        if (mListener != null){
            mListener.onHandle(ResourceUtil.getBitmap(R.drawable.ic_no_picture));
        }
    }
}
