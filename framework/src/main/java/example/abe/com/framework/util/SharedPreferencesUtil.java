package example.abe.com.framework.util;

import android.app.Activity;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.Set;

import example.abe.com.framework.main.BaseApplication;

/**
 * Created by abe on 16/9/26.
 */

public class SharedPreferencesUtil {

    public static final String MAIN_SHARE_PREFERENCES = "main_share_preferences";

    public static void put(String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void put(String key, Set<String> values) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putStringSet(key, values);
        editor.apply();
    }

    public static void put(String key, int value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void put(String key, long value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public static void put(String key, float value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public static void put(String key, boolean value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putBoolean(key, value);
        editor.apply();
    }


    public static String get(String key, String defaultValue) {
        try {
            return getSharedPreferences().getString(key, defaultValue);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static Set<String> get(String key, Set<String> defaultValue) {
        try {
            return getSharedPreferences().getStringSet(key, defaultValue);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static int get(String key, int defaultValue) {
        try {
            return getSharedPreferences().getInt(key, defaultValue);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static long get(String key, long defaultValue) {
        try {
            return getSharedPreferences().getLong(key, defaultValue);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static float get(String key, float defaultValue) {
        try {
            return getSharedPreferences().getFloat(key, defaultValue);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static boolean get(String key, boolean defaultValue) {
        try {
            return getSharedPreferences().getBoolean(key, defaultValue);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static void remove(String key){
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.remove(key);
        editor.apply();
    }

    public static void clear(){
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.clear();
        editor.apply();
    }

    private static SharedPreferences sp;
    public static SharedPreferences getSharedPreferences() {
        if (sp == null){
            synchronized (SharedPreferencesUtil.class) {
                //默认操作模式，代表该文件是私有数据，只能被应用本身访问，在该模式下，写入的内容会覆盖原文件的内容
                sp = BaseApplication.getInstance().getSharedPreferences(MAIN_SHARE_PREFERENCES, Activity.MODE_PRIVATE);
            }
        }
        return sp;
    }
}
