package example.abe.com.android_framework.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.zip.Inflater;

import example.abe.com.android_framework.R;
import example.abe.com.framework.util.ABDensityUtil;
import example.abe.com.framework.util.ABLog;
import example.abe.com.framework.util.ABToast;

public class ToastActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);

        findViewById(R.id.act_toast_show_msg_long).setOnClickListener(this);
        findViewById(R.id.act_toast_show_msg_short).setOnClickListener(this);
        findViewById(R.id.act_toast_show_id_long).setOnClickListener(this);
        findViewById(R.id.act_toast_show_id_short).setOnClickListener(this);
        findViewById(R.id.act_toast_show_id_view).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.act_toast_show_msg_long:
                ABToast.makeTextLong("LONG-msg文字内容");
                break;

            case R.id.act_toast_show_msg_short:
                ABToast.makeText("SHORT-msg文字内容");
                break;

            case R.id.act_toast_show_id_long:
                ABToast.makeTextLong(R.string.btn_title_log);
                break;

            case R.id.act_toast_show_id_short:
                ABToast.makeText(R.string.btn_title_log);
                break;

            case R.id.act_toast_show_id_view:
                View content = LayoutInflater.from(ToastActivity.this)
                        .inflate(R.layout.item_custom_view, null);
                ABToast.showEditToast(content);
                break;
        }

    }
}
