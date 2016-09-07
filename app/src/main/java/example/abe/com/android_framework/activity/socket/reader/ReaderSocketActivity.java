package example.abe.com.android_framework.activity.socket.reader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.BindView;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.activity.eventcenter.MessageEvent;
import example.abe.com.framework.eventcenter.EventCenter;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.util.LogUtil;

public class ReaderSocketActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.act_reader_socket_btn_start)
    protected Button mBtnStart;
    @BindView(R.id.act_reader_socket_btn_connect)
    protected Button mBtnConnect;
    @BindView(R.id.act_reader_socket_btn_send)
    protected Button mBtnSend;
    @BindView(R.id.act_reader_socket_btn_disconnect)
    protected Button mBtnDisConnect;
    @BindView(R.id.act_reader_socket_btn_stop)
    protected Button mBtnStop;
    @BindView(R.id.act_reader_socket_tv_display)
    protected TextView mTvDisplay;

    private Socket mSocket;
    private Intent mIntent;
    private static int COUNT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventCenter.getDefault().register(this);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        EventCenter.getDefault().unRigister(this);
    }

    @Override
    public int getLayoutID(){
        return R.layout.activity_reader_socket;
    }

    @Override
    public void initData() {
        COUNT = 0;
    }

    @Override
    public void initView() {
        mBtnStart.setOnClickListener(this);
        mBtnConnect.setOnClickListener(this);
        mBtnSend.setOnClickListener(this);
        mBtnDisConnect.setOnClickListener(this);
        mBtnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.act_reader_socket_btn_start:
                startServices();
                break;
            case R.id.act_reader_socket_btn_connect:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        connectServer();
                    }
                }).start();
                break;
            case R.id.act_reader_socket_btn_send:
                sendMessage(COUNT++, "戴益波");
                break;
            case R.id.act_reader_socket_btn_disconnect:
                disConnectServer();
                break;
            case R.id.act_reader_socket_btn_stop:
                stopServices();
                break;
        }
    }

    private void startServices() {
        //Socket发送文字到Socket Service
        mIntent = new Intent(this, ReaderSocketService.class);
        startService(mIntent);
    }

    private void connectServer() {
        try {
            mSocket = new Socket("localhost", ReaderSocketService.PORT);
        } catch (IOException e) {
            LogUtil.e("连接TCP服务失败, 请重试...");
        }
    }

    private void sendMessage(int count, String message){
        if (mSocket == null){
            LogUtil.e("Socket为空, 请重试...");
            return;
        }

        try {
            Writer writer = new OutputStreamWriter(mSocket.getOutputStream());
            writer.write(count + "'th Connected: " + message);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.e("发送数据失败，请重试...");
        }
    }

    private void disConnectServer(){
        if (mSocket == null){
            LogUtil.e("Socket为空, 请重试...");
            return;
        }

        try {
            mSocket.close();
        }catch (IOException e){
            e.printStackTrace();
            LogUtil.e("断开Socket失败");
        }
    }

    private void stopServices(){
        //Socket发送文字到Socket Service
        stopService(mIntent);
    }

    public void onEventUI(MessageEvent event) {
        mTvDisplay.setText(event.getMessage());
    }


}
