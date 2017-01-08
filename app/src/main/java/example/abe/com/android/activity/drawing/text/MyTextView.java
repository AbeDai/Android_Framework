package example.abe.com.android.activity.drawing.text;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.util.AttributeSet;
import android.view.View;

import example.abe.com.framework.util.DensityUtil;

/**
 * Created by abe on 16/11/1.
 */
public class MyTextView extends View {

    /**
     * 要显示的文字
     */
    private String text;
    /**
     * 文字的颜色
     */
    private int textColor;
    /**
     * 文字的大小
     */
    private int textSize;
    /**
     * 文字的方位
     */
    private int textAlign;
    public static final int TEXT_ALIGN_LEFT = 0x00000001;
    public static final int TEXT_ALIGN_RIGHT = 0x00000010;
    public static final int TEXT_ALIGN_CENTER_VERTICAL = 0x00000100;
    public static final int TEXT_ALIGN_CENTER_HORIZONTAL = 0x00001000;
    public static final int TEXT_ALIGN_TOP = 0x00010000;
    public static final int TEXT_ALIGN_BOTTOM = 0x00100000;

    /**
     * 文本中轴线X坐标
     */
    private float textCenterX;
    /**
     * 文本baseline线Y坐标
     */
    private float textBaselineY;

    /**
     * 控件的宽度
     */
    private int viewWidth;
    /**
     * 控件的高度
     */
    private int viewHeight;
    /**
     * 控件画笔
     */
    private Paint paint;

    private FontMetrics fm;

    public MyTextView(Context context) {
        super(context);
        init();
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 变量初始化
     */
    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextAlign(Align.CENTER);
        //默认情况下文字居中显示
        textAlign = TEXT_ALIGN_CENTER_HORIZONTAL | TEXT_ALIGN_CENTER_VERTICAL;
        //默认的文本颜色是黑色
        this.textColor = Color.BLACK;
        //文字内容
        this.text = "";
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        viewWidth = getWidth();
        viewHeight = getHeight();
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制控件内容
        setTextLocation();
        canvas.drawText(text, textCenterX, textBaselineY, paint);
        super.onDraw(canvas);
    }

    /**
     * 定位文本绘制的位置
     */
    private void setTextLocation() {
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        fm = paint.getFontMetrics();
        //文本的宽度
        float textWidth = paint.measureText(text);
        float textCenterVerticalBaselineY = viewHeight / 2 - fm.descent + (fm.descent - fm.ascent) / 2;
        switch (textAlign) {
            case TEXT_ALIGN_CENTER_HORIZONTAL | TEXT_ALIGN_CENTER_VERTICAL:
                textCenterX = (float) viewWidth / 2;
                textBaselineY = textCenterVerticalBaselineY;
                break;
            case TEXT_ALIGN_LEFT | TEXT_ALIGN_CENTER_VERTICAL:
                textCenterX = textWidth / 2;
                textBaselineY = textCenterVerticalBaselineY;
                break;
            case TEXT_ALIGN_RIGHT | TEXT_ALIGN_CENTER_VERTICAL:
                textCenterX = viewWidth - textWidth / 2;
                textBaselineY = textCenterVerticalBaselineY;
                break;
            case TEXT_ALIGN_BOTTOM | TEXT_ALIGN_CENTER_HORIZONTAL:
                textCenterX = viewWidth / 2;
                textBaselineY = viewHeight - fm.bottom;
                break;
            case TEXT_ALIGN_TOP | TEXT_ALIGN_CENTER_HORIZONTAL:
                textCenterX = viewWidth / 2;
                textBaselineY = -fm.ascent;
                break;
            case TEXT_ALIGN_TOP | TEXT_ALIGN_LEFT:
                textCenterX = textWidth / 2;
                textBaselineY = -fm.ascent;
                break;
            case TEXT_ALIGN_BOTTOM | TEXT_ALIGN_LEFT:
                textCenterX = textWidth / 2;
                textBaselineY = viewHeight - fm.bottom;
                break;
            case TEXT_ALIGN_TOP | TEXT_ALIGN_RIGHT:
                textCenterX = viewWidth - textWidth / 2;
                textBaselineY = -fm.ascent;
                break;
            case TEXT_ALIGN_BOTTOM | TEXT_ALIGN_RIGHT:
                textCenterX = viewWidth - textWidth / 2;
                textBaselineY = viewHeight - fm.bottom;
                break;
        }
    }

    /**
     * 设置文本内容
     *
     * @param text
     */
    public void setText(String text) {
        this.text = text;
        invalidate();
    }

    /**
     * 设置文本大小
     *
     * @param textSizeSp 文本大小，单位是sp
     */
    public void setTextSize(int textSizeSp) {
        this.textSize = DensityUtil.sp2px(getContext(), textSizeSp);
        invalidate();
    }

    /**
     * 设置文本的方位
     */
    public void setTextAlign(int textAlign) {
        this.textAlign = textAlign;
        invalidate();
    }

    /**
     * 设置文本的颜色
     *
     * @param textColor
     */
    public void setTextColor(int textColor) {
        this.textColor = textColor;
        invalidate();
    }
}
