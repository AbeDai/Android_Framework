package example.abe.com.framework.eventcenter;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/8/4.
 */
public class EventMethod {
    public enum Type {
        UI,
        BG,
    }

    private Type mType;
    private Method mMethod;
    private Object mReveiver;

    public EventMethod(Type type, Method method, Object reveiver) {
        mType = type;
        mMethod = method;
        mReveiver = reveiver;
    }

    public Type getmType() {
        return mType;
    }

    public void setmType(Type mType) {
        this.mType = mType;
    }

    public Method getmMethod() {
        return mMethod;
    }

    public void setmMethod(Method mMethod) {
        this.mMethod = mMethod;
    }

    public Object getmReveiver() {
        return mReveiver;
    }

    public void setmReveiver(Object mReveiver) {
        this.mReveiver = mReveiver;
    }
}
