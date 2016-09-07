package example.abe.com.android_framework.activity.drawing;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.activity.drawing.custom.CustomActivity;
import example.abe.com.android_framework.activity.drawing.whiteboard.WhiteboardActivity;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.viewinject.annotation.ContentView;
import example.abe.com.framework.viewinject.ViewInject;

@ContentView(id = R.layout.activity_draw)
public class DrawActivity extends BaseActivity implements View.OnClickListener{

    @ViewInject(id = R.id.act_draw_btn_custom_view)
    private Button mBtnCustom;
    @ViewInject(id = R.id.act_draw_btn_whiteboard)
    private Button mBtnWhiteBoard;

    @Override
    public void initData(){

    }

    @Override
    public void initView(){
        mBtnCustom.setOnClickListener(this);
        mBtnWhiteBoard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()){
            case R.id.act_draw_btn_custom_view:
                intent = new Intent(this, CustomActivity.class);
                break;

            case R.id.act_draw_btn_whiteboard:
                intent = new Intent(this, WhiteboardActivity.class);
                break;
        }

        startActivity(intent);
    }


}
