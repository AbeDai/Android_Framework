package example.abe.com.android.activity.socket;

import android.content.Intent;
import android.view.View;

import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.android.activity.socket.image.ImageSocketActivity;
import example.abe.com.android.activity.socket.reader.ReaderSocketActivity;
import example.abe.com.framework.main.BaseActivity;

public class SocketActivity extends BaseActivity{

    @Override
    public int getLayoutID(){
        return R.layout.activity_socket;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
    }

    @OnClick({R.id.act_socket_btn_send_word_socket, R.id.act_socket_btn_send_image_socket})
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.act_socket_btn_send_word_socket:
                intent = new Intent(this, ReaderSocketActivity.class);
                break;

            case R.id.act_socket_btn_send_image_socket:
                intent = new Intent(this, ImageSocketActivity.class);
                break;
        }
        startActivity(intent);
    }
}
