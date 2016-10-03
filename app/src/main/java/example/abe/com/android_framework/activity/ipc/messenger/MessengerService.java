package example.abe.com.android_framework.activity.ipc.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import example.abe.com.framework.util.LogUtil;

public class MessengerService extends Service {
    public static final int MSG_FROM_CLIENT = 1000;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_FROM_CLIENT:
                    LogUtil.i("服务端收到的信息:" + msg.getData().get("msg"));

                    //得到客户端传来的Messenger对象
                    Messenger mMessenger = msg.replyTo;

                    //创建传输给客户端的消息
                    Message mMessage = Message.obtain(null, MessengerService.MSG_FROM_CLIENT);
                    Bundle mBundle = new Bundle();
                    mBundle.putString("rep", "这里是服务端，我们收到信息了");
                    mMessage.setData(mBundle);

                    //发送消息到客户端
                    try {
                        mMessenger.send(mMessage);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        //创建服务端Messenger对象与Handler相关联
        Messenger mMessenger = new Messenger(mHandler);
        //通过IBinder与Messenger相关联
        return mMessenger.getBinder();
    }
}

