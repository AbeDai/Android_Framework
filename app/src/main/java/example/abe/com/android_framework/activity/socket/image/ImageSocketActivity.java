package example.abe.com.android_framework.activity.socket.image;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import example.abe.com.android_framework.R;
import example.abe.com.framework.eventcenter.EventCenter;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.util.LogUtil;
import example.abe.com.framework.viewinject.ContentView;
import example.abe.com.framework.viewinject.ViewInject;

@ContentView(id = R.layout.activity_image_socket)
public class ImageSocketActivity extends BaseActivity implements View.OnClickListener {

    @ViewInject(id = R.id.act_image_socket_btn_start)
    private Button mBtnStart;
    @ViewInject(id = R.id.act_image_socket_btn_send)
    private Button mBtnSend;
    @ViewInject(id = R.id.act_image_socket_btn_stop)
    private Button mBtnStop;
    @ViewInject(id = R.id.act_image_socket_iv_display)
    private ImageView mIvDisplay;

    private Socket mSocket;
    private Intent mIntent;
    private static int COUNT;
    private List<Integer> listRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventCenter.getInstance().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventCenter.getInstance().unRigister(this);
    }

    @Override
    public void initData() {
        COUNT = 0;
        listRes = new ArrayList<>(Arrays.asList(
                R.drawable.icon_card_view,
                R.drawable.icon_custom_dialog));
    }

    @Override
    public void initView() {
        mBtnStart.setOnClickListener(this);
        mBtnSend.setOnClickListener(this);
        mBtnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.act_image_socket_btn_start:
                startServices();
                break;
            case R.id.act_image_socket_btn_send:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        connectServer();
                        sendImage(COUNT++);
                        disConnectServer();
                    }
                }).start();
                break;
            case R.id.act_image_socket_btn_stop:
                stopServices();
                break;
        }
    }

    private void startServices() {
        //Socket发送文字到Socket Service
        mIntent = new Intent(this, ImageSocketService.class);
        startService(mIntent);
    }

    private void connectServer() {
        try {
            mSocket = new Socket("localhost", ImageSocketService.PORT);
        } catch (IOException e) {
            LogUtil.e("连接TCP服务失败, 请重试...");
        }
    }

    private void sendImage(int count) {
        if (mSocket == null) {
            LogUtil.e("Socket为空, 请重试...");
            return;
        }

        try {
            //图片解码
            int id = listRes.get(count % listRes.size());
            Bitmap bm = BitmapFactory.decodeResource(getResources(), id);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG, 100, os);
            byte[] bytes = os.toByteArray();

            //传输
            OutputStream out = new BufferedOutputStream(mSocket.getOutputStream());
            out.write(bytes);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.e("发送数据失败，请重试...");
        }
    }

    private void disConnectServer() {
        if (mSocket == null) {
            LogUtil.e("Socket为空, 请重试...");
            return;
        }

        try {
            mSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.e("断开Socket失败");
        }
    }

    private void stopServices() {
        //Socket发送文字到Socket Service
        stopService(mIntent);
    }

    public void onEventUI(BitmapEvent event) {
        mIvDisplay.setImageBitmap(event.getBitmap());
    }

}
