package example.abe.com.android_framework.activity.socket.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.net.Socket;

import example.abe.com.android_framework.activity.socket.SocketService;
import example.abe.com.framework.eventcenter.EventCenter;

public class ImageSocketService extends SocketService {

    public static int PORT = 8090;

    @Override
    public void response(Socket socket) {
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(socket.getInputStream());
            BitmapEvent event = new BitmapEvent(bitmap);
            EventCenter.getInstance().post(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getPort(){
        return PORT;
    }

}
