package example.abe.com.framework.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

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

    /**
     * 获取资源文件中的Drawable
     * @param id 资源id
     * @return 位图
     */
    public static Drawable getDrawable(int id){
        return BaseApplication.getInstance().getResources().getDrawable(id);
    }

    /**
     * 获取资源文件中的颜色
     * @param id 资源id
     * @return 颜色
     */
    public static int getColor(int id){
        return BaseApplication.getInstance().getResources().getColor(id);
    }
}
