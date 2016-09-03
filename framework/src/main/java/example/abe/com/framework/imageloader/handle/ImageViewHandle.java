package example.abe.com.framework.imageloader.handle;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;

import example.abe.com.framework.R;
import example.abe.com.framework.main.BaseApplication;
import example.abe.com.framework.util.EncryptionUtil;

/**
 * Created by abe on 16/9/1.
 */
public class ImageViewHandle implements IImageHandle {

    private String mUrl;
    private ImageView mIv;
    int[] size;

    public ImageViewHandle(ImageView imageView, String url) {
        mIv = imageView;
        mUrl = url;
        init();
    }


    private void init() {
        if (mIv != null){
            //获取图片大小
            size = getImageViewSize(mIv);
            //设置默认图片
            mIv.setImageResource(R.drawable.ic_no_picture);
            //将ImageView与Url绑定
            mIv.setTag(R.id.image_view_handle_image_url, mUrl);
        }
    }

    @Override
    public String getUniqueId() {
        return EncryptionUtil.getMd5("ImageViewHandle" +
                size[0] + size[1] + mUrl);
    }

    @Override
    public String getImageUrl() {
        return mUrl;
    }

    @Override
    public Bitmap onPreHandle(byte[] bytes) {
        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);

        //根据不同的图片格式，选择不同的像素格式，减小内存占用。
        if (options.outMimeType != null && options.outMimeType.equals("image/jpeg")) {
            options.inPreferredConfig = Bitmap.Config.RGB_565;
        } else if (options.outMimeType != null && options.outMimeType.equals("image/png")) {
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        }
        //根据ImageView大小，设置图片采样率
        int sampleSize = calculateInSampleSize(options, size[0], size[1]);
        options.inSampleSize = sampleSize;

        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
        return bitmap;
    }

    @Override
    public void onImageLoadSuccess(Bitmap bitmap, String url) {
        String oldUrl = (String) mIv.getTag(R.id.image_view_handle_image_url);
        if (mIv != null && TextUtils.equals(url, oldUrl)) {
            //url相等，image url没有改变，进行图片加载
            mIv.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onImageLoadFailed(String url) {
        if (mIv != null) {
            mIv.setImageResource(R.drawable.ic_no_picture);
        }
    }

    /**
     * 根据ImageView获适当的压缩的宽和高
     *
     * @param imageView 图片
     * @return int[0]宽 int[1]高
     */
    private static int[] getImageViewSize(ImageView imageView) {
        int[] size = new int[]{0, 0};

        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        DisplayMetrics displayMetrics = BaseApplication.getInstance().getResources().getDisplayMetrics();

        // 获取ImageView的实际宽度
        int width = imageView.getWidth();
        if (width <= 0) {
            if (lp != null) {
                // 获取ImageView在layout中声明的宽度
                width = lp.width;
            }
        }
        if (width <= 0) {
            // 检查最大值宽度
            width = imageView.getMaxWidth();
        }
        if (width <= 0) {
            // 使用屏幕宽度
            width = displayMetrics.widthPixels;
        }

        // 获取ImageView的实际高度
        int height = imageView.getHeight();
        if (height <= 0) {
            if (lp != null) {
                // 获取ImageView在layout中声明的高度
                height = lp.height;
            }
        }
        if (height <= 0) {
            // 检查最大值高度
            height = imageView.getMaxHeight();
        }
        if (height <= 0) {
            // 使用屏幕高度
            height = displayMetrics.heightPixels;
        }

        size[0] = width;
        size[1] = height;
        return size;
    }

    /**
     * 根据需要的宽和高以及图片实际的宽和高计算SampleSize
     *
     * @param options   图片配置信息
     * @param reqWidth  所需宽度
     * @param reqHeight 所需高度
     * @return 合适SampleSize
     */
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth,
                                             int reqHeight) {
        final int width = options.outWidth;
        final int height = options.outHeight;
        int inSampleSize = 1;

        //计算出合适的SampleSize
        if (width > reqWidth || height > reqHeight) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
