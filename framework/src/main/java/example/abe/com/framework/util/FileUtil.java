package example.abe.com.framework.util;

import android.nfc.tech.NfcA;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.util.jar.Attributes;

/**
 * Created by abe on 16/7/31.
 */

public class FileUtil {

    /**
     * 获取图片文件
     * @param name 图片名
     * @return 图片文件
     */
    public static File getImageFile(String name){
        File image = new File(getImagesDir(), name);
        if (!image.exists()){
            try {
                image.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        return image;
    }

    /**
     * 获取图片目录
     * @return 图片目录
     */
    public static File getImagesDir(){
        File images = new File(getCacheDir(), "images");
        if (!images.exists()) {
            images.mkdirs();
        }

        return images;
    }

    /**
     * 获取缓存目录
     * @return 缓存目录
     */
    public static File getCacheDir(){
        File cache = new File(Environment.getExternalStorageDirectory(), "Abe");
        if (!cache.exists()) {
            cache.mkdirs();
        }

        return cache;
    }

}
