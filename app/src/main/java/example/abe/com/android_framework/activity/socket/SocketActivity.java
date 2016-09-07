package example.abe.com.android_framework.activity.socket;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.BindView;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.activity.socket.image.ImageSocketActivity;
import example.abe.com.android_framework.activity.socket.reader.ReaderSocketActivity;
import example.abe.com.framework.main.BaseActivity;

public class SocketActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.act_socket_btn_send_word_socket)
    protected Button mBtnSendWord;
    @BindView(R.id.act_socket_btn_send_image_socket)
    protected Button mBtnSendImage;

    @Override
    public int getLayoutID(){
        return R.layout.activity_socket;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        mBtnSendWord.setOnClickListener(this);
        mBtnSendImage.setOnClickListener(this);
    }

    @Override
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
