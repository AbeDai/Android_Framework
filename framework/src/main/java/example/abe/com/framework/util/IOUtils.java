package example.abe.com.framework.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by abe on 17/2/17.
 */

public class IOUtils {

    /**
     * 输入流 转 String
     * @param is 输入流
     * @return 对应字符串
     */
    public static String stream2String(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is), 16*1024); //强制缓存大小为16KB，一般Java类默认为8KB
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {  //处理换行符
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }
}
