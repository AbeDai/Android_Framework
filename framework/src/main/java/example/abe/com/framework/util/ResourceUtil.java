package example.abe.com.framework.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import example.abe.com.framework.main.BaseApplication;

/**
 * Created by abe on 16/8/3.
 */

public class ResourceUtil {

    /**
     * 资源文件中的String Array
     * @param id 资源id
     * @return list列表
     */
    public static List<String> getStringList(int id) {
        String[] strings = BaseApplication.getInstance().getResources().getStringArray(id);
        return Arrays.asList(strings);
    }

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
