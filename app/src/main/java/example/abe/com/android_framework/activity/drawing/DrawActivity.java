package example.abe.com.android_framework.activity.drawing;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.BindView;
import com.example.OnClick;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.activity.drawing.custom.CustomActivity;
import example.abe.com.android_framework.activity.drawing.whiteboard.WhiteboardActivity;
import example.abe.com.framework.main.BaseActivity;

public class DrawActivity extends BaseActivity{

    @BindView(R.id.act_draw_btn_custom_view)
    protected Button mBtnCustom;
    @BindView(R.id.act_draw_btn_whiteboard)
    protected Button mBtnWhiteBoard;

    @Override
    public int getLayoutID(){
        return R.layout.activity_draw;
    }

    @Override
    public void initData(){
    }

    @Override
    public void initView(){
    }

    @OnClick(R.id.act_draw_btn_custom_view)
    public void onEnterCustomView(){
        Intent intent = new Intent(this, CustomActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.act_draw_btn_whiteboard)
    public void onEnterWhiteboard(View view){
        Intent intent = new Intent(this, WhiteboardActivity.class);
        startActivity(intent);
    }
}
