package example.abe.com.android.activity.eventbus;

/**
 * Created by abe on 16/6/29.
 */

public class FirstEvent {
    private String mMsg;
    public FirstEvent(String msg) {
        mMsg = msg;
    }
    public String getMsg(){
        return mMsg;
    }
}