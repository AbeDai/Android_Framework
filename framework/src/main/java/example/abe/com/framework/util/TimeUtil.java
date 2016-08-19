package example.abe.com.framework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by abe on 16/5/8.
 */
public class TimeUtil {

    public static final String FORMAT_YYYYMMDDHHMMSS = "yyyyMMdd_HHmmss";
    public static final String FORMAT_YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_YYYYMMDD = "yyyy-MM-dd";
    public static final long LENGHT_DAY_MSEC = 24 * 60 * 60 * 1000;
    public static final long LENGHT_HOUR_MSEC = 60 * 60 * 1000;
    public static final long LENGHT_MINUTE_MSEC = 60 * 1000;

    /**
     * 时间戳======>字符串（GMT）
     *
     * @param l      时间戳（毫秒）
     * @param format 格式
     * @return 字符串
     */
    public static String getStrTimeGMT(long l, String format) {
        TimeZone timeZone = TimeZone.getTimeZone("GMT+0");
        return getStrTime(l, format, timeZone);
    }

    /**
     * 字符串=======>时间戳（GMT）
     *
     * @param time   字符串
     * @param format 格式
     * @return 时间戳（毫秒）
     */
    public static long getTimeGMT(String time, String format) {
        TimeZone timeZone = TimeZone.getTimeZone("GMT+0");
        return getTime(time, format, timeZone);
    }

    /**
     * 字符串=======>日期（GMT）
     *
     * @param time   字符串
     * @param format 格式
     * @return 日期
     */
    public static Date getDateGMT(String time, String format) {
        TimeZone timeZone = TimeZone.getTimeZone("GMT+0");
        return getDate(time, format, timeZone);
    }

    /**
     * 日期=======>字符串（GMT）
     *
     * @param date   日期
     * @param format 格式
     * @return 字符串
     */
    public static String getStrTimeGMT(Date date, String format) {
        TimeZone timeZone = TimeZone.getTimeZone("GMT+0");
        return getStrTime(date, format, timeZone);
    }

    /**
     * 获取date中的详细内容（GMT）
     *
     * @param date  时间日期
     * @param field 内容域，例Calendar.HOUR
     * @return date详细内容
     */
    public static int getFieldGMT(Date date, int field) {
        TimeZone timeZone = TimeZone.getTimeZone("GMT+0");
        return getField(date, field, timeZone);
    }

    /**
     * 获取现在时间字符串 （GMT）
     *
     * @param format 格式
     * @return 时间字符串
     */
    public static String getStrCurrentGMT(String format) {
        TimeZone timeZone = TimeZone.getTimeZone("GMT+0");
        return getStrCurrent(format, timeZone);
    }

    /**
     * 时间戳======>字符串
     *
     * @param l      时间戳（毫秒）
     * @param format 格式
     * @return 字符串
     */
    public static String getStrTimeCH(long l, String format) {
        TimeZone timeZone = TimeZone.getTimeZone("GMT+8");
        return getStrTime(l, format, timeZone);
    }

    /**
     * 字符串=======>时间戳（China）
     *
     * @param time   字符串
     * @param format 格式
     * @return 时间戳（毫秒）
     */
    public static long getTimeCH(String time, String format) {
        TimeZone timeZone = TimeZone.getTimeZone("GMT+8");
        return getTime(time, format, timeZone);
    }

    /**
     * 字符串=======>日期（China）
     *
     * @param time   字符串
     * @param format 格式
     * @return 日期
     */
    public static Date getDateCH(String time, String format) {
        TimeZone timeZone = TimeZone.getTimeZone("GMT+8");
        return getDate(time, format, timeZone);
    }

    /**
     * 日期=======>字符串（China）
     *
     * @param date   日期
     * @param format 格式
     * @return 字符串
     */
    public static String getStrTimeCH(Date date, String format) {
        TimeZone timeZone = TimeZone.getTimeZone("GMT+8");
        return getStrTime(date, format, timeZone);
    }

    /**
     * 获取date中的详细内容（China）
     *
     * @param date  时间日期
     * @param field 内容域，例Calendar.HOUR
     * @return date详细内容
     */
    public static int getFieldCH(Date date, int field) {
        TimeZone timeZone = TimeZone.getTimeZone("GMT+8");
        return getField(date, field, timeZone);
    }

    //

    /**
     * 获取现在时间字符串 （China）
     *
     * @param format 格式
     * @return 时间字符串
     */
    public static String getStrCurrentCH(String format) {
        TimeZone timeZone = TimeZone.getTimeZone("GMT+8");
        return getStrCurrent(format, timeZone);
    }

    /**
     * 时间戳======>字符串
     *
     * @param l        时间戳（毫秒）
     * @param format   格式
     * @param timeZone 时区
     * @return 字符串
     */
    public static String getStrTime(long l, String format, TimeZone timeZone) {
        Date date = new Date(l);
//        Log.i("MainActivity" ,date.toString());
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(timeZone);
        return sdf.format(date);
    }

    /**
     * 时间戳======>日期
     *
     * @param l 时间戳（毫秒）
     * @return 日期
     */
    public static Date getDate(long l) {
        return new Date(l);
    }

    /**
     * 字符串=======>时间戳
     *
     * @param time     字符串
     * @param format   格式
     * @param timeZone 时区
     * @return 时间戳（毫秒）
     */
    public static long getTime(String time, String format, TimeZone timeZone) {
        Date d = getDate(time, format, timeZone);
        return d.getTime();
    }

    /**
     * 字符串=======>日期
     *
     * @param time     字符串
     * @param format   格式
     * @param timeZone 时区
     * @return 日期
     */
    public static Date getDate(String time, String format, TimeZone timeZone) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(timeZone);
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 日期=======>字符串
     *
     * @param date     日期
     * @param format   格式
     * @param timeZone 时区
     * @return 字符串
     */
    public static String getStrTime(Date date, String format, TimeZone timeZone) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(timeZone);
        return sdf.format(date);
    }

    /**
     * 获取时间
     *
     * @param date 时间日期
     * @return 从1970至此日期的毫秒数
     */
    public static long getTime(Date date) {
        return date.getTime();
    }

    /**
     * 获取date中的详细内容
     *
     * @param date     时间日期
     * @param field    内容域，例Calendar.HOUR
     * @param timeZone 时区
     * @return date详细内容
     */
    public static int getField(Date date, int field, TimeZone timeZone) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(timeZone);
        calendar.setTime(date);
        return calendar.get(field);
    }

    /**
     * 以字符串形式获取现在的时间
     *
     * @param format   时间格式
     * @param timeZone 时区
     * @return 时间字符串
     */
    public static String getStrCurrent(String format, TimeZone timeZone) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(timeZone);
        return sdf.format(new Date());
    }
}
