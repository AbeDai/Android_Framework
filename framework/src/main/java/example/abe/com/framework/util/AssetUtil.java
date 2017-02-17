package example.abe.com.framework.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import example.abe.com.framework.main.BaseApplication;

/**
 * Created by Administrator on 2016/8/4.
 */
public class AssetUtil {
    /**
     * 从Asset中获取图片资源
     * @param fileName 文件名
     * @return 位图资源
     */
    public static Bitmap getAssetBitmap(String fileName) {

        Bitmap bitmap = null;
        try {
            //打开文件
            InputStream inputStream = BaseApplication.getInstance().getAssets().open(fileName);

            //图片操作
            bitmap = BitmapFactory.decodeStream(inputStream);

            //关闭文件
            try {
                inputStream.close();
            } catch (IOException e) {
                LogUtil.e("关闭输入流失败!");
            }
        } catch (IOException e) {
            LogUtil.e("asset获取文件: " + fileName + " 失败!");
        }

        return bitmap;
    }

    /**
     * 从Asset中字符串资源
     * @param fileName 文件名
     * @return 字符串资源
     */
    public static String getAssetInputStream(String fileName) {

        String str = null;
        try {
            //打开文件
            InputStream inputStream = BaseApplication.getInstance().getAssets().open(fileName);

            //图片操作
            str = IOUtils.stream2String(inputStream);

            //关闭文件
            try {
                inputStream.close();
            } catch (IOException e) {
                LogUtil.e("关闭输入流失败!");
            }
        } catch (IOException e) {
            LogUtil.e("asset获取文件: " + fileName + " 失败!");
        }

        return str;
    }

    /**
     * 获取Asset中的根目录列表
     * @return 列表
     */
    public static List<String> getAssetRootList() {
        List<String> list = null;
        try {
            String[] array = BaseApplication.getInstance().getAssets().list("");
            list = Arrays.asList(array);
        } catch (IOException e) {
            LogUtil.e("asset获取根目录失败!");
        }
        return list;
    }
}
