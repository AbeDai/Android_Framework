package example.abe.com.android.activity.ipc.messenger;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.TextView;

import com.example.BindView;
import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.util.LogUtil;

public class MessengerActivity extends BaseActivity {

    @BindView(R.id.act_messenger_tv_show)
    protected TextView mTvShow;

    //客户端Messenger对象
    private Messenger mMessenger;

    private static int sCount;

    @Override
    public int getLayoutID(){
        return R.layout.activity_messenger;
    }

    @Override
    public void initData() {
        sCount = 0;
    }

    @Override
    public void initView() {

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        unbindService(mServiceConnection);
    }

    @OnClick({R.id.act_messenger_btn_server_remote, R.id.act_messenger_btn_send_messenger})
    public void onBtnClick(View v) {
        switch (v.getId()) {
            case R.id.act_messenger_btn_server_remote://开启后台服务
                Intent intent = new Intent(MessengerActivity.this, MessengerService.class);
                bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.act_messenger_btn_send_messenger://发送消息
                //创建传输给服务端的消息
                Message mMessage = Message.obtain(null, MessengerService.MSG_FROM_CLIENT);
                Bundle mBundle = new Bundle();
                mBundle.putString("msg", "这里是客户端，服务端收到了吗?");
                mMessage.setData(mBundle);

                //将Messenger传递给服务端
                mMessage.replyTo = new Messenger(mHandler);

                //消息发送到服务端
                try {
                    mMessenger.send(mMessage);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    //服务端发送过来的消息,在这里接收
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MessengerService.MSG_FROM_SERVICE:
                    LogUtil.i("客户端收到的信息:" + msg.getData().get("rep"));
                    mTvShow.setText("客户端收到的信息(" + sCount++ + "):" + msg.getData().get("rep"));
                    break;
            }
        }
    };

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //创建客户端Messenger对象
            mMessenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}