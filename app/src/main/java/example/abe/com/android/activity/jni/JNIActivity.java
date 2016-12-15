package example.abe.com.android.activity.jni;

import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.util.ToastUtil;

public class JNIActivity extends BaseActivity {

    @Override
    public int getLayoutID() {
        return R.layout.activity_jni;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
    }

    @OnClick(R.id.act_jni_btn_call_native_method)
    public void callNativeMethod(){
        ToastUtil.makeText(getHelloContent());
    }

    @OnClick(R.id.act_jni_btn_native_call_android_method)
    public void nativeCallAndroidMethod(){
        showHelloContent();
    }

    // 固定写法，需要加载的资源文件为libhello.so
    // 省略掉lib与so关键字，只留下hello
    static {
        System.loadLibrary("hello");
    }

    //JNI调用该函数
    public static void methodCalledByJni(String jniHelloContent){
        ToastUtil.makeText(jniHelloContent);
    }

/*****************声明一个本地方法，用native关键字修饰*****************/
    //从Native中获取HelloContent
    public native String getHelloContent();

    //调用（内部调用Android方法）的Native方法
    public native void showHelloContent();
}
