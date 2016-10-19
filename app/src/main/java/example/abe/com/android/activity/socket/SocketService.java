package example.abe.com.android.activity.socket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import example.abe.com.framework.util.LogUtil;

/**
 * Created by abe on 16/8/12.
 */
public abstract class SocketService extends Service {

    private boolean exit;
    private ServerSocket server;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    public void init() {
        exit = false;
        new TCPServerThread().start();
    }

    @Override
    //当Service不在使用时调用
    public void onDestroy() {
        exit = true;

        if (server != null && !server.isClosed()){
            try {
                server.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class TCPServerThread extends Thread {
        @Override
        public void run() {
            Socket client = null;
            try {
                server = new ServerSocket(getPort());
            } catch (IOException e) {
                LogUtil.e("ServerSocket 创建失败");
                e.printStackTrace();
                return;
            }

            while (!exit) {
                try {
                    //server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的
                    client = server.accept();
                } catch (IOException e) {
                    LogUtil.e("ServerSocket 获取客户端连接失败");
                    e.printStackTrace();
                    break;
                }

                final Socket fClient = client;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        response(fClient);
                    }
                }).start();
            }
        }
    }

    abstract public void response(Socket socket);
    abstract public int getPort();

}
