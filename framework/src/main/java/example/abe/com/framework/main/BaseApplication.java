package example.abe.com.framework.main;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

/**
 * Created by abe on 16/7/3.
 */
public class BaseApplication extends MultiDexApplication {

    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        if (instance == null){
            instance = this;
        }
    }

    public static BaseApplication getInstance(){
        return instance;
    }
}