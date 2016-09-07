package viewinject.compiler;

import com.squareup.javapoet.ClassName;

public class TypeUtil {
    public static final ClassName ANDROID_VIEW = ClassName.get("android.view", "View");
    public static final ClassName ANDROID_ON_CLICK_LISTENER = ClassName.get("android.view", "View", "OnClickListener");
    public static final ClassName I_VIEW_INJECT = ClassName.get("example.abe.com.framework.viewinject", "IViewInject");
    public static final ClassName PROVIDER = ClassName.get("example.abe.com.framework.viewinject.provider", "Provider");
}
