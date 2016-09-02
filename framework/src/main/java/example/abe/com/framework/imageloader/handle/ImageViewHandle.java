package example.abe.com.framework.imageloader.handle;

import android.graphics.Bitmap;
import android.widget.ImageView;

import example.abe.com.framework.R;
import example.abe.com.framework.util.ResourceUtil;

/**
 * Created by abe on 16/9/1.
 */
public class ImageViewHandle implements IImageHandle{

    private String mUrl;
    private ImageView mIv;

    public ImageViewHandle(ImageView imageView, String url){
        mIv = imageView;
        mUrl = url;
        setDefalutImage();
    }

    private void setDefalutImage() {
        if (mIv != null){
            mIv.setImageResource(R.drawable.ic_no_picture);
        }
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
        if (mIv != null){
            mIv.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onImageLoadFailed(String url){
        if (mIv != null){
            mIv.setImageResource(R.drawable.ic_no_picture);
        }
    }
}
