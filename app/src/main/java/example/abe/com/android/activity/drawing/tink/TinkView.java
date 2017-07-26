package example.abe.com.android.activity.drawing.tink;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by abe on 17-7-19.
 */

public class TinkView extends View {

    private boolean isDrawFirst = true;
    private boolean isDrawSecond = true;
    private float lineProgress = 0;
    private float lineSpeed = 0.1f;
    private Handler handler;
    private Paint barPaint = new Paint();
    private int tickSize = 20;
    private int barWidth = 4;

    public TinkView(Context context) {
        super(context);
        init();
    }

    public TinkView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init(){
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {
                    invalidate();
                }
            }
        };

        barPaint.setColor(Color.RED);
        barPaint.setAntiAlias(true);
        barPaint.setStyle(Paint.Style.STROKE);
        barPaint.setStrokeWidth(barWidth);
        //设置直线的圆角，因为画勾的画笔和圆圈的画笔是同一个，为了让勾的两条直线的焦点处更平滑，为其设置圆角
        barPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    private RectF circleBounds = null;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (circleBounds == null){
            initCircle();
        }

        //变量记录做完这次onDraw之后是否需要继续invalidate。
        boolean mustInvalidate = false;

        /*
         * My implementation
         * to draw tick
         */
        if (isDrawFirst && !isDrawSecond) {
            //开始画第一条线，获取圆心坐标
            float centerX = circleBounds.centerX();
            float centerY = circleBounds.centerY();
            //绘制外面的圆圈
            canvas.drawArc(circleBounds, 360, 360, false, barPaint);
            //根据速度来绘制直线
            canvas.drawLine(centerX - tickSize / 2, centerY, centerX - tickSize / 2 + tickSize / 2 * lineProgress,
                    centerY + tickSize / 2 * lineProgress, barPaint);
            //当绘制完第一条直线，开始绘制第二条
            if (lineProgress >= 1.0f) {
                lineProgress = 0f;
                isDrawSecond = true;
            } else {
                lineProgress += lineSpeed;
            }
            mustInvalidate = true;
        } else if (isDrawFirst && isDrawSecond) {
            //开始画第二条线，获取圆心
            float centerX = circleBounds.centerX();
            float centerY = circleBounds.centerY();
            //首先要画出第一条直线和外面的圆圈
            canvas.drawArc(circleBounds, 360, 360, false, barPaint);
            canvas.drawLine(centerX - tickSize / 2, centerY, centerX, centerY + tickSize / 2, barPaint);

            canvas.drawLine(centerX, centerY + tickSize / 2, centerX + tickSize / 2 * lineProgress, centerY +
                    tickSize / 2 - tickSize * lineProgress, barPaint);
            //当第二条直线绘制完毕，停止invalidate
            if (lineProgress >= 1.0f) {
                return;
            } else {
                lineProgress += lineSpeed;
                if (lineProgress > 1)
                    lineProgress = 1f;
                mustInvalidate = true;
            }
        }


        /*
         * 原来该代码的作者是在onDraw函数中直接调用invalidate函数
         * 如果onDraw函数中代码执行的时间很短的话，会导致频繁的去掉用invalidate，界面频繁刷新，占用cpu，有可能还会导致大量的GC
         * 于是我的老板和我说可以使用handler来延缓invalidate，每次做完onDraw，延迟几毫秒去invalidate
         * 但是要控制好延迟的时间，如果太大的话，会导致画面卡顿，如果大于16ms一般会造成卡顿
         * 因为在画勾的时候，测试过，当延迟的时间为20ms的时候，并不是很卡顿，所以就选择了20ms，所以具体延迟时间还是看需求吧
         */
        if (mustInvalidate) {
            Message message = handler.obtainMessage();
            message.what = 1;
            int delayMills = 25;
            if (!isDrawFirst)
                delayMills = 15;
            handler.sendMessageDelayed(message, delayMills);
        }
    }

    private void initCircle() {
            circleBounds = new RectF(100,
                    100,
                    200,
                    200);
    }

    /**
     * My implementation
     * 提供给外界的接口，开始画勾，这个时候要停止圆圈的转动，直接绘制完整的圆圈
     */
    public void beginDrawTick() {
        isDrawFirst = true;
        isDrawSecond = false;
        lineProgress = 0.0f;
        invalidate();
    }
}
