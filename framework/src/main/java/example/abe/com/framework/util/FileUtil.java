package example.abe.com.framework.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by abe on 16/7/31.
 */

public class FileUtil {

    /**
     * 保存位图到本地
     * @param name 图片名
     * @param bytes 位图字节码
     */
    public static void saveBitmapBytes(String name, byte[] bytes){
        //保存图片
        File imgFile = FileUtil.getImageFile(name);
        try {
            OutputStream out = new FileOutputStream(imgFile);
            out.write(bytes);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 保存位图到本地
     * @param name 图片名
     * @param bitmap 图片位图
     */
    public static void saveBitmap(String name, Bitmap bitmap){
        //添加图片格式后缀
        name += ".png";
        //保存图片
        File imgFile = FileUtil.getImageFile(name);
        try {
            OutputStream out = new FileOutputStream(imgFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 本地获取位图
     * @param name 图片名
     * @return 位图
     */
    public static Bitmap getBitmap(String name) {
        //添加图片格式后缀
        name += ".png";
        //保存图片
        File imgFile = FileUtil.getImageFile(name);
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

    /**
     * 本地获取位图
     * @param name 图片名
     * @return 位图
     */
    public static byte[] getBitmapBytes(String name) {
        File imgFile = FileUtil.getImageFile(name);

        byte[] bytes = new byte[0];
        ByteArrayOutputStream os = null;
        FileInputStream in = null;
        try {
            in = new FileInputStream(imgFile);
            os = new ByteArrayOutputStream();

            byte[] buffer = new byte[512];
            while (in.read(buffer) != -1){
                os.write(buffer);
            }

            bytes = os.toByteArray();
            os.close();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytes;
    }

    /**
     * 获取图片文件（不存在则创建）
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
     * 获取图片目录（不存在则创建）
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
     * 获取缓存目录（不存在则创建）
     * @return 缓存目录
     */
    public static File getCacheDir(){
        File cache = new File(Environment.getExternalStorageDirectory(), "Abe");
        if (!cache.exists()) {
            cache.mkdirs();
        }

        return cache;
    }

    /**
     * 判断图片文件是否存在
     * @param name 图片名
     * @return 是否存在
     */
    public static boolean isImageFileExists(String name){
        File image = new File(getImagesDir(), name);
        return image.exists();
    }
}
