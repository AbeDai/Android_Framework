package example.abe.com.android.activity.eventcenter;

/**
 * Created by Administrator on 2016/8/5.
 */
public class MessageEvent {
    private String mMessage;

    public MessageEvent(String message){
        mMessage = message;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String mMessage) {
        this.mMessage = mMessage;
    }
}
