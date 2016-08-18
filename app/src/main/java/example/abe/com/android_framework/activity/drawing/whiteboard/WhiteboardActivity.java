package example.abe.com.android_framework.activity.drawing.whiteboard;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.activity.drawing.whiteboard.tool.DrawManager;
import example.abe.com.android_framework.activity.drawing.whiteboard.tool.Type;
import example.abe.com.android_framework.activity.drawing.whiteboard.tool.paint.PaintFeatures;
import example.abe.com.android_framework.activity.drawing.whiteboard.tool.view.AbPaintView;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.viewinject.ContentView;
import example.abe.com.framework.viewinject.ViewInject;

@ContentView(id = R.layout.activity_whiteboard)
public class WhiteboardActivity extends BaseActivity implements View.OnClickListener{

    @ViewInject(id = R.id.act_whiteboard_pen_paint_view)
    private AbPaintView mPenPaintView;
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
    @ViewInject(id = R.id.act_whiteboard_btn_text_big)
    private Button mBtnTextBig;
    @ViewInject(id = R.id.act_whiteboard_btn_text_normal)
    private Button mBtnTextNormal;
    @ViewInject(id = R.id.act_whiteboard_btn_text_small)
    private Button mBtnTextSmall;

    @ViewInject(id = R.id.act_whiteboard_btn_rect)
    private Button mBtnRect;
    @ViewInject(id = R.id.act_whiteboard_btn_restore)



    private Button mBtnRestore;
    @ViewInject(id = R.id.act_whiteboard_btn_un_restore)
    private Button mBtnUnRestore;

    private DrawManager mPenPaintManager;

    @Override
    public void initData(){
        mPenPaintManager = mPenPaintView.getDrawManager();
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
        mBtnEraserSmall.setOnClickListener(mEraserListener);

        mBtnText.setOnClickListener(mTextListener);
        mBtnTextBig.setOnClickListener(mTextListener);
        mBtnTextNormal.setOnClickListener(mTextListener);
        mBtnTextSmall.setOnClickListener(mTextListener);

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
                    mPenPaintManager.setStatusColor(PaintFeatures.PaintColor.RED);
                    mPenPaintManager.setType(Type.DRAW);
                    break;
                case R.id.act_whiteboard_btn_color_blue:
                    mPenPaintManager.setStatusColor(PaintFeatures.PaintColor.BLUE);
                    mPenPaintManager.setType(Type.DRAW);
                    break;
                case R.id.act_whiteboard_btn_color_green:
                    mPenPaintManager.setStatusColor(PaintFeatures.PaintColor.GREEN);
                    mPenPaintManager.setType(Type.DRAW);
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
                    mPenPaintManager.setStatusWidth(PaintFeatures.PaintWidth.BOLD);
                    mPenPaintManager.setType(Type.DRAW);
                    break;
                case R.id.act_whiteboard_btn_width_normal:
                    mPenPaintManager.setStatusWidth(PaintFeatures.PaintWidth.NORMAL);
                    mPenPaintManager.setType(Type.DRAW);
                    break;
                case R.id.act_whiteboard_btn_width_light:
                    mPenPaintManager.setStatusWidth(PaintFeatures.PaintWidth.LIGHT);
                    mPenPaintManager.setType(Type.DRAW);
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
                    mPenPaintManager.setStatusEraserSize(PaintFeatures.PaintEraserSize.BIG);
                    mPenPaintManager.setType(Type.ERASER);
                    break;
                case R.id.act_whiteboard_btn_eraser_normal:
                    mPenPaintManager.setStatusEraserSize(PaintFeatures.PaintEraserSize.NORMAL);
                    mPenPaintManager.setType(Type.ERASER);
                    break;
                case R.id.act_whiteboard_btn_eraser_small:
                    mPenPaintManager.setStatusEraserSize(PaintFeatures.PaintEraserSize.SMALL);
                    mPenPaintManager.setType(Type.ERASER);
                    break;
            }
            invertVisibilityStatus(mLlDetailToolBar);
        }
    };

    private View.OnClickListener mTextListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.act_whiteboard_btn_text:
                    break;
                case R.id.act_whiteboard_btn_text_big:
                    mPenPaintManager.setStatusFont(PaintFeatures.PaintFont.BIG);
                    mPenPaintManager.setType(Type.TEXT);
                    break;
                case R.id.act_whiteboard_btn_text_normal:
                    mPenPaintManager.setStatusFont(PaintFeatures.PaintFont.NORMAL);
                    mPenPaintManager.setType(Type.TEXT);
                    break;
                case R.id.act_whiteboard_btn_text_small:
                    mPenPaintManager.setStatusFont(PaintFeatures.PaintFont.SMALL);
                    mPenPaintManager.setType(Type.TEXT);
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
