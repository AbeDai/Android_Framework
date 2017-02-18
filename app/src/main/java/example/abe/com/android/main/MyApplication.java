package example.abe.com.android.main;

import example.abe.com.framework.main.BaseApplication;

/**
 * Created by abe on 16/7/29.
 */
public class MyApplication extends BaseApplication {

    private static final String ACTIVITY_CONFIG_XML_PATH = "config/activity_config.xml";

    @Override
    public void onCreate() {
        super.onCreate();

        //配置 首页Activity
        KnowledgeStructHelper.build(ACTIVITY_CONFIG_XML_PATH);
    }
}