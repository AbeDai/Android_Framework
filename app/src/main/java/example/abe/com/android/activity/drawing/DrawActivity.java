package example.abe.com.android.activity.drawing;

import android.content.Intent;
import android.view.View;

import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.android.activity.drawing.custom.CustomActivity;
import example.abe.com.android.activity.drawing.matrix.MatrixActivity;
import example.abe.com.android.activity.drawing.surfaceview.SurfaceViewActivity;
import example.abe.com.android.activity.drawing.text.TextActivity;
import example.abe.com.android.activity.drawing.whiteboard.WhiteboardActivity;
import example.abe.com.framework.main.BaseActivity;

public class DrawActivity extends BaseActivity{

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

    @OnClick({R.id.act_draw_btn_whiteboard, R.id.act_draw_btn_text, R.id.act_draw_btn_custom_view,
            R.id.act_draw_btn_matrix, R.id.act_draw_btn_surface_view})
    public void toNextActivity(View view){
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.act_draw_btn_whiteboard:
                intent.setClass(this, WhiteboardActivity.class);
                break;
            case R.id.act_draw_btn_text:
                intent.setClass(this, TextActivity.class);
                break;
            case R.id.act_draw_btn_custom_view:
                intent.setClass(this, CustomActivity.class);
                break;
            case R.id.act_draw_btn_matrix:
                intent.setClass(this, MatrixActivity.class);
                break;
            case R.id.act_draw_btn_surface_view:
                intent.setClass(this, SurfaceViewActivity.class);
                break;
        }
        startActivity(intent);
    }
}
