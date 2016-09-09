package example.abe.com.framework.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by abe on 16/9/1.
 */
public class EncryptionUtil {

    /**
     * MD5加密
     * @param str 原始内容
     * @return 加密内容
     */
    public static String getMd5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();

            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
