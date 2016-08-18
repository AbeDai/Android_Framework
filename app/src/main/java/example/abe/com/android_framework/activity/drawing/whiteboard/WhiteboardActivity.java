package example.abe.com.android_framework.activity.drawing.whiteboard;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.activity.drawing.whiteboard.tool.DrawManager;
import example.abe.com.android_framework.activity.drawing.whiteboard.tool.WhiteboardStatus;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.viewinject.ContentView;
import example.abe.com.framework.viewinject.ViewInject;

@ContentView(id = R.layout.activity_whiteboard)
public class WhiteboardActivity extends BaseActivity implements View.OnClickListener{

    @ViewInject(id = R.id.act_whiteboard_pen_paint_view)
    private PenPaintView mPenPaintView;

    @ViewInject(id = R.id.act_whiteboard_ll_detail_tool_bar)
    private LinearLayout mLlDetailToolBar;
    @ViewInject(id = R.id.act_whiteboard_btn_color)
    private Button mBtnColor;
    @ViewInject(id = R.id.act_whiteboard_btn_color_red)
    private Button mBtnRed;
    @ViewInject(id = R.id.act_whiteboard_btn_color_blue)
    private Button mBtnBlue;
    @ViewInject(id = R.id.act_whiteboard_btn_color_green)
    private Button mBtnGreen;
    @ViewInject(id = R.id.act_whiteboard_btn_width)
    private Button mBtnWidth;
    @ViewInject(id = R.id.act_whiteboard_btn_width_bold)
    private Button mBtnWidthBold;
    @ViewInject(id = R.id.act_whiteboard_btn_width_normal)
    private Button mBtnWidthNormal;
    @ViewInject(id = R.id.act_whiteboard_btn_width_light)
    private Button mBtnWidthLight;
    @ViewInject(id = R.id.act_whiteboard_btn_eraser)
    private Button mBtnEraser;
    @ViewInject(id = R.id.act_whiteboard_btn_eraser_big)
    private Button mBtnEraserBig;
    @ViewInject(id = R.id.act_whiteboard_btn_eraser_normal)
    private Button mBtnEraserNormal;
    @ViewInject(id = R.id.act_whiteboard_btn_eraser_small)
    private Button mBtnEraserSmall;


    @ViewInject(id = R.id.act_whiteboard_btn_text)
    private Button mBtnText;
    @ViewInject(id = R.id.act_whiteboard_btn_rect)
    private Button mBtnRect;
    @ViewInject(id = R.id.act_whiteboard_btn_restore)
    private Button mBtnRestore;
    @ViewInject(id = R.id.act_whiteboard_btn_un_restore)
    private Button mBtnUnRestore;

    private DrawManager mPenPaintManager;

    @Override
    public void initData(){
        mPenPaintManager = mPenPaintView.getManager();
    }

    @Override
    public void initView(){

        mBtnColor.setOnClickListener(mColorListener);
        mBtnRed.setOnClickListener(mColorListener);
        mBtnBlue.setOnClickListener(mColorListener);
        mBtnGreen.setOnClickListener(mColorListener);

        mBtnWidth.setOnClickListener(mWidthListener);
        mBtnWidthBold.setOnClickListener(mWidthListener);
        mBtnWidthNormal.setOnClickListener(mWidthListener);
        mBtnWidthLight.setOnClickListener(mWidthListener);

        mBtnEraser.setOnClickListener(mEraserListener);
        mBtnEraserBig.setOnClickListener(mEraserListener);
        mBtnEraserNormal.setOnClickListener(mEraserListener);
        mBtnEraserNormal.setOnClickListener(mEraserListener);

//        mBtnText.setOnClickListener(this);
//        mBtnRect.setOnClickListener(this);

        mBtnRestore.setOnClickListener(mRestoreListener);
        mBtnUnRestore.setOnClickListener(mRestoreListener);

    }

    private View.OnClickListener mColorListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.act_whiteboard_btn_color:
                    break;
                case R.id.act_whiteboard_btn_color_red:
                    mPenPaintManager.setStatusColor(WhiteboardStatus.Color.RED);
                    break;
                case R.id.act_whiteboard_btn_color_blue:
                    mPenPaintManager.setStatusColor(WhiteboardStatus.Color.BLUE);
                    break;
                case R.id.act_whiteboard_btn_color_green:
                    mPenPaintManager.setStatusColor(WhiteboardStatus.Color.GREEN);
                    break;
            }
            invertVisibilityStatus(mLlDetailToolBar);
        }
    };

    private View.OnClickListener mWidthListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.act_whiteboard_ll_detail_tool_bar_width:
                    break;
                case R.id.act_whiteboard_btn_width_bold:
                    mPenPaintManager.setStatusWidth(WhiteboardStatus.Width.BOLD);
                    break;
                case R.id.act_whiteboard_btn_width_normal:
                    mPenPaintManager.setStatusWidth(WhiteboardStatus.Width.NORMAL);
                    break;
                case R.id.act_whiteboard_btn_width_light:
                    mPenPaintManager.setStatusWidth(WhiteboardStatus.Width.LIGHT);
                    break;
            }
            invertVisibilityStatus(mLlDetailToolBar);
        }
    };

    private View.OnClickListener mEraserListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.act_whiteboard_btn_eraser:
                    break;
                case R.id.act_whiteboard_btn_eraser_big:
                    mPenPaintManager.setStatusEraserSize(WhiteboardStatus.EraserSize.BIG);
                    break;
                case R.id.act_whiteboard_btn_eraser_normal:
                    mPenPaintManager.setStatusEraserSize(WhiteboardStatus.EraserSize.NORMAL);
                    break;
                case R.id.act_whiteboard_btn_eraser_small:
                    mPenPaintManager.setStatusEraserSize(WhiteboardStatus.EraserSize.SMALL);
                    break;
            }
            invertVisibilityStatus(mLlDetailToolBar);
        }
    };

    private View.OnClickListener mRestoreListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.act_whiteboard_btn_restore:
                    mPenPaintManager.restore();
                    break;
                case R.id.act_whiteboard_btn_un_restore:
                    mPenPaintManager.unRestore();
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {

    }

    private void invertVisibilityStatus(View view) {
        if (view.getVisibility() != View.VISIBLE){
            view.setVisibility(View.VISIBLE);
        }else {
            view.setVisibility(View.GONE);
        }
    }
}
