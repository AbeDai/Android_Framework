package example.abe.com.framework.viewinject.provider;

import android.content.Context;
import android.view.View;

/**
 * View中查找目标View的实现策略
 */
public class ViewProvider implements Provider {

    @Override
    public Context getContext(Object source) {
        return ((View) source).getContext();
    }

    @Override
    public View findView(Object source, int id) {
        return ((View) source).findViewById(id);
    }
}
