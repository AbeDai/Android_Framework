package example.abe.com.framework.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Arrays;
import java.util.List;

import example.abe.com.framework.main.BaseApplication;

/**
 * Created by abe on 16/8/3.
 */

public class ResourceUtil {

    /**
     * 获取资源文件中的String Array
     * @param id 资源id
     * @return list列表
     */
    public static List<String> getStringList(int id) {
        String[] strings = BaseApplication.getInstance().getResources().getStringArray(id);
        return Arrays.asList(strings);
    }

    /**
     * 获取资源文件中的String
     * @param id 资源id
     * @return String内容
     */
    public static String getString(int id) {
        return BaseApplication.getInstance().getResources().getString(id);
    }

    /**
     * 获取资源文件中的图片
     * @param id 资源id
     * @return 位图
     */
    public static Bitmap getBitmap(int id){
        return BitmapFactory.decodeResource(BaseApplication.getInstance().getResources(), id);
    }
}
