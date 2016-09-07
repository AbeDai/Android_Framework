package example.abe.com.android_framework.activity.drawing;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.BindView;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.activity.drawing.custom.CustomActivity;
import example.abe.com.android_framework.activity.drawing.whiteboard.WhiteboardActivity;
import example.abe.com.framework.main.BaseActivity;

public class DrawActivity extends BaseActivity implements View.OnClickListener{

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
