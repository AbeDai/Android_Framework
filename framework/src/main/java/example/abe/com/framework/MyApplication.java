package example.abe.com.framework;

import android.app.Application;

/**
 * Created by abe on 16/7/3.
 */
public class MyApplication extends Application {

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        if (instance == null){
            instance = this;
        }
    }

    public static MyApplication getInstance(){
        return instance;
    }
}