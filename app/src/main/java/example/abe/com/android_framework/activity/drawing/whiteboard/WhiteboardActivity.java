package example.abe.com.android_framework.activity.drawing.whiteboard;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.BindView;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.activity.drawing.whiteboard.tool.DrawManager;
import example.abe.com.android_framework.activity.drawing.whiteboard.tool.Type;
import example.abe.com.android_framework.activity.drawing.whiteboard.tool.paint.PaintFeatures;
import example.abe.com.android_framework.activity.drawing.whiteboard.tool.view.AbPaintView;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.util.FileUtil;
import example.abe.com.framework.util.TimeUtil;

public class WhiteboardActivity extends BaseActivity {

    @BindView(R.id.act_whiteboard_pen_paint_view)
    protected AbPaintView mPenPaintView;
    @BindView(R.id.act_whiteboard_ll_detail_tool_bar)
    protected LinearLayout mLlDetailToolBar;
    @BindView(R.id.act_whiteboard_btn_color)
    protected Button mBtnColor;
    @BindView(R.id.act_whiteboard_btn_color_red)
    protected Button mBtnRed;
    @BindView(R.id.act_whiteboard_btn_color_blue)
    protected Button mBtnBlue;
    @BindView(R.id.act_whiteboard_btn_color_green)
    protected Button mBtnGreen;
    @BindView(R.id.act_whiteboard_btn_width)
    protected Button mBtnWidth;
    @BindView(R.id.act_whiteboard_btn_width_bold)
    protected Button mBtnWidthBold;
    @BindView(R.id.act_whiteboard_btn_width_normal)
    protected Button mBtnWidthNormal;
    @BindView(R.id.act_whiteboard_btn_width_light)
    protected Button mBtnWidthLight;
    @BindView(R.id.act_whiteboard_btn_eraser)
    protected Button mBtnEraser;
    @BindView(R.id.act_whiteboard_btn_eraser_big)
    protected Button mBtnEraserBig;
    @BindView(R.id.act_whiteboard_btn_eraser_normal)
    protected Button mBtnEraserNormal;
    @BindView(R.id.act_whiteboard_btn_eraser_small)
    protected Button mBtnEraserSmall;
    @BindView(R.id.act_whiteboard_btn_text)
    protected Button mBtnText;
    @BindView(R.id.act_whiteboard_btn_text_big)
    protected Button mBtnTextBig;
    @BindView(R.id.act_whiteboard_btn_text_normal)
    protected Button mBtnTextNormal;
    @BindView(R.id.act_whiteboard_btn_text_small)
    protected Button mBtnTextSmall;
    @BindView(R.id.act_whiteboard_btn_rect)
    protected Button mBtnRect;
    @BindView(R.id.act_whiteboard_btn_restore)
    protected Button mBtnRestore;
    @BindView(R.id.act_whiteboard_btn_un_restore)
    protected Button mBtnUnRestore;
    @BindView(R.id.act_whiteboard_btn_save)
    protected Button mBtnSave;

    private DrawManager mPenPaintManager;

    @Override
    public int getLayoutID(){
        return R.layout.activity_whiteboard;
    }

    @Override
    public void initData() {
        mPenPaintManager = mPenPaintView.getDrawManager();
    }

    @Override
    public void initView() {

        //颜色
        mBtnColor.setOnClickListener(mColorListener);
        mBtnRed.setOnClickListener(mColorListener);
        mBtnBlue.setOnClickListener(mColorListener);
        mBtnGreen.setOnClickListener(mColorListener);

        //线条粗细
        mBtnWidth.setOnClickListener(mWidthListener);
        mBtnWidthBold.setOnClickListener(mWidthListener);
        mBtnWidthNormal.setOnClickListener(mWidthListener);
        mBtnWidthLight.setOnClickListener(mWidthListener);

        //橡皮
        mBtnEraser.setOnClickListener(mEraserListener);
        mBtnEraserBig.setOnClickListener(mEraserListener);
        mBtnEraserNormal.setOnClickListener(mEraserListener);
        mBtnEraserSmall.setOnClickListener(mEraserListener);

        //文字
        mBtnText.setOnClickListener(mTextListener);
        mBtnTextBig.setOnClickListener(mTextListener);
        mBtnTextNormal.setOnClickListener(mTextListener);
        mBtnTextSmall.setOnClickListener(mTextListener);

        //矩形
        mBtnRect.setOnClickListener(mRectListener);

        //恢复
        mBtnRestore.setOnClickListener(mRestoreListener);

        //重做
        mBtnUnRestore.setOnClickListener(mRestoreListener);

        //保存
        mBtnSave.setOnClickListener(mSaveListener);

    }

    private View.OnClickListener mColorListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
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
            switch (v.getId()) {
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
            switch (v.getId()) {
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
            switch (v.getId()) {
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

    private View.OnClickListener mRectListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.act_whiteboard_btn_rect:
                    mPenPaintManager.setType(Type.RECT);
                    break;
            }
        }
    };

    private View.OnClickListener mSaveListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //截屏
            mPenPaintView.setDrawingCacheEnabled(true);
            mPenPaintView.buildDrawingCache();
            Bitmap bitmap= mPenPaintView.getDrawingCache();
            String name = "whiteboard_"
                    + TimeUtil.getStrCurrentCH(TimeUtil.FORMAT_YYYYMMDDHHMMSS)
                    + ".png";
            //保存
            FileUtil.saveBitmap(name, bitmap);
        }
    };

    private View.OnClickListener mRestoreListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.act_whiteboard_btn_restore:
                    mPenPaintManager.restore();
                    break;
                case R.id.act_whiteboard_btn_un_restore:
                    mPenPaintManager.unRestore();
                    break;
            }
        }
    };

    private void invertVisibilityStatus(View view) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }
}
