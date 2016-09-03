package example.abe.com.framework.imageloader.handle;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import example.abe.com.framework.R;
import example.abe.com.framework.util.EncryptionUtil;
import example.abe.com.framework.util.LogUtil;
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
    public String getUniqueId(){
        return EncryptionUtil.getMd5("BitmapHandle" + mUrl);
    }

    @Override
    public String getImageUrl(){
        return mUrl;
    }

    @Override
    public Bitmap onPreHandle(byte[] bytes){
        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);

        //根据不同的图片格式，选择不同的像素格式，减小内存占用。
        if (options.outMimeType != null && options.outMimeType.equals("image/jpeg")){
            options.inPreferredConfig = Bitmap.Config.RGB_565;
        }else if (options.outMimeType != null && options.outMimeType.equals("image/png")){
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        }

        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
        return bitmap;
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
