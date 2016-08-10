package example.abe.com.framework.util;

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
        String strings = BaseApplication.getInstance().getResources().getString(id);
        return strings;
    }
}
