package example.abe.com.android.activity.drawing.clock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

public class ClockView extends View {

    private Paint mPaint;
    private Path mPath;

    //背景颜色
    private int mBgColor;
    private RectF mValueRectF;

    //待扫过的刻度颜色
    private int mWaitScanScaleColor;

    //刚扫过的刻度颜色
    private int mIsScanningScaleColor;

    //时间
    private int mHour;
    private int mMinute;
    private int mSecond;
    private int mMilliSecond;

    //日历
    private Calendar mCalender;

    public ClockView(Context context) {
        this(context, null);
    }

    public ClockView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        mPath = new Path();

        mBgColor = Color.parseColor("#78c4ec");
        mWaitScanScaleColor = Color.parseColor("#dadada");
        mIsScanningScaleColor = Color.parseColor("#ffffff");

        mValueRectF = new RectF();

        mCalender = Calendar.getInstance();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(600, 600);
    }

    /**
     * 设置当前时间
     *
     * @param time 当前时间
     */
    public void setTime(long time) {
        mCalender.setTimeInMillis(time);
        mHour = mCalender.get(Calendar.HOUR_OF_DAY);
        mMinute = mCalender.get(Calendar.MINUTE);
        mSecond = mCalender.get(Calendar.SECOND);
        mMilliSecond = mCalender.get(Calendar.MILLISECOND);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(mBgColor);
        drawValueLine(canvas);
        drawValue(canvas);
        drawOutScale(canvas, mSecond, mMilliSecond);
        drawSecond(canvas, mSecond, mMilliSecond);
        drawInCircle(canvas);
        drawHour(canvas, mHour, mMinute, mSecond);
        drawMinute(canvas, mMinute, mSecond);
    }

    /**
     * 画最外围的数字圈的线
     *
     * @param canvas 画布
     */
    private void drawValueLine(Canvas canvas) {
        //平移坐标系原点
        canvas.translate(300, 300);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.STROKE);
        mValueRectF.union(-240, -240, 240, 240);
        for (int i = 1; i <= 4; i++) {
            canvas.drawArc(mValueRectF, 275, 80, false, mPaint);
            canvas.rotate(90);
        }
    }

    /**
     * 画最外圈的值
     *
     * @param canvas 画布
     */
    private void drawValue(Canvas canvas) {
        mPaint.setTextSize(24);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
        float topDis = Math.abs(fontMetrics.top);//为基线到字体上边框的距离
        float bottomDis = Math.abs(fontMetrics.bottom);//为基线到字体下边框的距离
        float d = (topDis + bottomDis) / 2 - bottomDis;//字体矩形框中点到基线的距离
        canvas.drawText("3", 240, d, mPaint);
        canvas.drawText("6", 0, 240 + d, mPaint);
        canvas.drawText("9", -240, d, mPaint);
        canvas.drawText("12", 0, -240 + d, mPaint);
    }

    /**
     * 画秒针
     *
     * @param canvas      画布
     * @param second      秒
     * @param milliSecond 毫秒
     */
    private void drawSecond(Canvas canvas, int second, int milliSecond) {
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.FILL);
        mPath.reset();
        mPath.moveTo(0, -198);
        mPath.lineTo(-10, -178);
        mPath.lineTo(10, -178);
        mPath.close();
        float angle = (second + milliSecond / 1000f) * 6;
        canvas.rotate(angle);
        canvas.drawPath(mPath, mPaint);
        canvas.rotate(-angle);
    }

    /**
     * 画最外围的刻度
     *
     * @param canvas      画布
     * @param second      秒数
     * @param milliSecond 毫秒
     */
    private void drawOutScale(Canvas canvas, int second, int milliSecond) {
        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.STROKE);
        mPath.reset();
        mPath.moveTo(0, -200);
        mPath.lineTo(0, -220);
        mPath.close();
        for (int i = 0; i < 300; i++) {
            if (isCurScanning(i, second, milliSecond)) {
                mPaint.setColor(mIsScanningScaleColor);
            } else {
                mPaint.setColor(mWaitScanScaleColor);
            }
            canvas.drawPath(mPath, mPaint);
            canvas.rotate(1.2f);
        }
    }

    /**
     * 当前绘制的刻度是否需要设置为扫描中的颜色
     *
     * @param n           刻度位置
     * @param second      秒
     * @param milliSecond 毫秒
     * @return 是否需要设置扫描色
     */
    private boolean isCurScanning(int n, int second, int milliSecond) {
        int count = second * 5 + milliSecond / 200;
        if (second <= 59 && second != 0 && n <= count+2 && n > count - 2) {
            return true;
        } else if (second < 1 && (n > 295 + milliSecond / 200 || n <= count)) {
            return true;
        }
        return false;
    }

    /**
     * 画中间的圆心
     *
     * @param canvas 画布
     */
    private void drawInCircle(Canvas canvas) {
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(0, 0, 8, mPaint);
    }

    /**
     * 画时针
     *
     * @param canvas 画布
     * @param hour   小时
     * @param minute 分钟
     * @param second 秒针
     */
    private void drawHour(Canvas canvas, int hour, int minute, int second) {
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.FILL);
        mPath.reset();
        mPath.moveTo(6, -3);
        mPath.lineTo(100, -2);
        mPath.quadTo(104, 0, 100, 2);
        mPath.lineTo(6, 3);
        mPath.close();
        float angle = (360 / 12 * (hour + minute / 60f + second / 3600f) - 90);
        canvas.rotate(angle);
        canvas.drawPath(mPath, mPaint);
        canvas.rotate(-angle);
    }

    /**
     * 画分针
     *
     * @param canvas 画布
     * @param minute 分钟
     * @param second 秒
     */
    private void drawMinute(Canvas canvas, int minute, int second) {
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.FILL);
        mPath.reset();
        mPath.moveTo(6, -2);
        mPath.lineTo(150, -1);
        mPath.lineTo(150, 1);
        mPath.lineTo(6, 2);
        mPath.close();
        float angle = 360 / 60 * (minute + second / 60f) - 90;
        canvas.rotate(angle);
        canvas.drawPath(mPath, mPaint);
        canvas.rotate(-angle);
    }

}
