package example.abe.com.android.activity.keyboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;

import java.util.List;

import example.abe.com.android.R;

public class CustomKeyboardView extends KeyboardView {

    private static final int GRAY = 0xFF323232;

    private Context mContext;
    private Paint mPaint;
    private boolean isUpper;

    public CustomKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomKeyboardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public void init(Context context) {
        isUpper = false;
        mContext = context;
        mPaint = new Paint();
        mPaint.setTextSize(getResources().getDimension(R.dimen.dp_17));
        mPaint.setColor(GRAY);
        mPaint.setAntiAlias(true);
        mPaint.setTextAlign(Paint.Align.CENTER);
    }

    public void setIsUpper(boolean upper){
        isUpper = upper;
        postInvalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getKeyboard() != null) {
            List<Keyboard.Key> keys = getKeyboard().getKeys();
            for (Keyboard.Key key : keys) {
                if (key.label != null &&
                        (key.codes[0] == Keyboard.KEYCODE_MODE_CHANGE // 切换键盘按钮
                        || key.codes[0] == Keyboard.KEYCODE_DONE)) {// 确认按钮
                    drawBackground(key, canvas,
                            R.drawable.keyboard_special_key_selector);
                    drawText(key, canvas);
                }
                if (key.label != null &&
                        (key.codes[0] == 32)){// 空格
                    drawBackground(key, canvas,
                            R.drawable.keyboard_key_selector);
                    drawText(key, canvas);
                }
                if (key.codes[0] == Keyboard.KEYCODE_SHIFT){// 切换大小写
                    drawShiftIcon(key, canvas);
                }
            }
        }
    }

    private void drawBackground(Keyboard.Key key, Canvas canvas, int resId) {
        Drawable drawable = mContext.getResources().getDrawable(resId);
        int[] drawableState = key.getCurrentDrawableState();
        if (key.codes[0] != 0) {
            drawable.setState(drawableState);
        }
        drawable.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
        drawable.draw(canvas);
    }

    private void drawText(Keyboard.Key key, Canvas canvas) {
        Paint.FontMetrics fm = mPaint.getFontMetrics();
        float textY = key.y + key.height / 2 - fm.descent
                + (fm.descent - fm.ascent) / 2;
        float textX = key.x + (float) key.width / 2;
        canvas.drawText(key.label.toString(), textX, textY, mPaint);
    }

    private void drawShiftIcon(Keyboard.Key key, Canvas canvas) {
        Drawable drawable = null;
        if (isUpper){
            drawable = mContext.getResources().getDrawable(R.drawable.keyboard_shift_selected);
        }else {
            drawable = mContext.getResources().getDrawable(R.drawable.keyboard_shift_normal);
        }
        if (drawable != null){
            int drawWidth = drawable.getIntrinsicWidth();
            int drawHeight = drawable.getIntrinsicHeight();
            drawable.setBounds(key.x + (key.width - drawWidth)/2, key.y + (key.height - drawHeight)/2,
                    key.x + (key.width + drawWidth)/2,
                    key.y + (key.height + drawHeight)/2);
            drawable.draw(canvas);
        }
    }
}