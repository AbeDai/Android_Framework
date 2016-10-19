package example.abe.com.android.activity.socket.reader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Socket;

import example.abe.com.android.activity.eventcenter.MessageEvent;
import example.abe.com.android.activity.socket.SocketService;
import example.abe.com.framework.eventcenter.EventCenter;

public class ReaderSocketService extends SocketService {

    public static int PORT = 8089;

    @Override
    public void response(Socket socket) {
        try {
            Reader reader = new InputStreamReader(socket.getInputStream());
            char chars[] = new char[64];
            int len;
            while ((len = reader.read(chars)) != -1) {
                MessageEvent event = new MessageEvent(new String(chars, 0, len));
                EventCenter.getDefault().post(event);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getPort(){
        return PORT;
    }

}
