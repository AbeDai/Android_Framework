package example.abe.com.framework.main;

import android.app.Application;

/**
 * Created by abe on 16/7/3.
 */
public class BaseApplication extends Application {

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