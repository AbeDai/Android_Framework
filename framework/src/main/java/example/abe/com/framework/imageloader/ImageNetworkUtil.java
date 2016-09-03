package example.abe.com.framework.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import example.abe.com.framework.util.LogUtil;

/**
 * Created by abe on 16/8/31.
 */
public class ImageNetworkUtil {

    /**
     * 网络加载位图
     * @param path 图片地址
     * @return 位图
     */
    public static Bitmap loadBitmapByUrl(String path) {
        InputStream is = null;
        Bitmap bitmap = null;
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            is = new BufferedInputStream(conn.getInputStream());
            bitmap = BitmapFactory.decodeStream(is);
            return bitmap;
        } catch (MalformedURLException e) {
            LogUtil.e("图片解析错误");
            e.printStackTrace();
        }catch (IOException e) {
            LogUtil.e("网络连接错误");
            e.printStackTrace();
        }finally {
            try {
                if (is != null){
                    is.close();
                }
            }catch (IOException e){
                LogUtil.e("输入流关闭错误");
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    /**
     * 网络加载图片字节码（ByteArray）
     * @param path 图片地址
     * @return 位图
     */
    public static byte[] loadByteArrayByUrl(String path) {
        byte[] bytes = new byte[0];
        InputStream is = null;
        ByteArrayOutputStream os = null;
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            is = conn.getInputStream();
            os = new ByteArrayOutputStream();

            byte[] buffer = new byte[512];
            int len = 0;
            while ((len = is.read(buffer)) != -1){
                os.write(buffer, 0, len);
            }

            bytes = os.toByteArray();
            return bytes;
        } catch (MalformedURLException e) {
            LogUtil.e("图片解析错误");
            e.printStackTrace();
        }catch (IOException e) {
            LogUtil.e("网络连接错误");
            e.printStackTrace();
        }finally {
            try {
                if (is != null){
                    is.close();
                }
            }catch (IOException e){
                LogUtil.e("输入流关闭错误");
                e.printStackTrace();
            }
            try {
                if (os != null){
                    os.close();
                }
            }catch (IOException e){
                LogUtil.e("输出流关闭错误");
                e.printStackTrace();
            }
        }
        return bytes;
    }
}
