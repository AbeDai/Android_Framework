package example.abe.com.framework.viewinject.provider;

import android.app.Activity;
import android.content.Context;
import android.view.View;

/**
 * Activity中查找目标View的实现策略
 */
public class ActivityProvider implements Provider {

    @Override
    public Context getContext(Object source) {
        return ((Activity) source);
    }

    @Override
    public View findView(Object source, int id) {
        return ((Activity) source).findViewById(id);
    }
}
