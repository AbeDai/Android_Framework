package example.abe.com.framework.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedInputStream;
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

    public static Bitmap downloadImgByUrl(String strUrl) {
        InputStream is = null;
        Bitmap bitmap = null;
        try {
            URL url = new URL(strUrl);
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
}
