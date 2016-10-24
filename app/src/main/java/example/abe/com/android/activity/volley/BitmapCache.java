package example.abe.com.android.activity.volley;

import android.graphics.Bitmap;

import com.android.volley.toolbox.ImageLoader.ImageCache;

import example.abe.com.framework.util.FileUtil;

/**
 * Created by abe on 16/7/31.
 */
public class BitmapCache implements ImageCache {

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        String imgName = getFileFullNameByUrl(url);
        FileUtil.saveBitmap(imgName, bitmap);
    }

    @Override
    public Bitmap getBitmap(String url) {
        String imgName = getFileFullNameByUrl(url);
        return FileUtil.getBitmapByName(imgName);
    }

    private String getFileFullNameByUrl(String url) {
        String name = "";
        if (url != null) {
            name = url.replaceAll(":", "_");
            name = name.replaceAll("/", "_");
            name = name.replaceAll("\\.", "_");
            name = name + ".png";
        }
        return name;
    }
}