package example.abe.com.android_framework.activity.volley;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.android.volley.toolbox.ImageLoader.ImageCache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import example.abe.com.framework.util.FileUtil;

/**
 * Created by abe on 16/7/31.
 */
public class BitmapCache implements ImageCache {

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        String imgName = getFileFullNameByUrl(url);
        File imgFile = FileUtil.getImageFile(imgName);

        try {
            OutputStream out = new FileOutputStream(imgFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Bitmap getBitmap(String url) {
        String imgName = getFileFullNameByUrl(url);
        File imgFile = FileUtil.getImageFile(imgName);

        Bitmap bitmap = null;
        try {
            FileInputStream in = new FileInputStream(imgFile);
            bitmap = BitmapFactory.decodeStream(in);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    private String getFileFullNameByUrl(String url) {
        String name = "";
        if (url != null) {
            name = url.replaceAll(":", "_");
            name = name.replaceAll("/", "_");
            name = name.replaceAll("\\.", "_");
        }
        return name;
    }

}