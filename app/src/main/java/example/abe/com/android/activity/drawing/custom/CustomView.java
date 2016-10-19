package example.abe.com.android.activity.drawing.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by abe on 16/7/30.
 */
public class CustomView extends View {

    private static int DRAW_SIDE_LENGTH = 200;
    private Paint paint;
    private RectF rectF;

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomView(Context context) {
        super(context);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(3);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //颜色填充
        canvas.drawColor(Color.BLACK);

        //圆形
        RectF rect = getNextRect();
        canvas.drawCircle(rect.centerX(), rect.centerY(), rect.width() / 2 - 10, paint);

        //弧线区域
        rect = getNextRect();
        canvas.drawArc(rect, //弧线所使用的矩形区域大小
                0,  //开始角度
                90, //扫过的角度
                false, //是否使用中心
                paint);

        //弧线区域
        rect = getNextRect();
        canvas.drawArc(rect, //弧线所使用的矩形区域大小
                0,  //开始角度
                90, //扫过的角度
                true, //是否使用中心
                paint);

        //线条
        rect = getNextRect();
        canvas.drawLine(rect.left, rect.top, rect.right, rect.bottom, paint);

        //椭圆
        rect = getNextRect();
        rect.left += 50;
        rect.right -= 50;
        canvas.drawOval(rect, paint);

        //矩形
        rect = getNextRect();
        rect.left += 20;
        rect.top += 20;
        rect.right -= 20;
        rect.bottom -= 20;
        canvas.drawRect(rect, paint);

        //圆角矩形
        rect = getNextRect();
        rect.left += 20;
        rect.top += 20;
        rect.right -= 20;
        rect.bottom -= 20;
        canvas.drawRoundRect(rect,
                20, //x轴的半径
                20, //y轴的半径
                paint);

        //绘制路径图像
        rect = getNextRect();
        Path path = new Path();
        path.moveTo(rect.left + 50, rect.top + 10);
        path.lineTo(rect.left + 100, rect.top + 100);
        path.lineTo(rect.left + 200, rect.top + 10);
        path.close();
        canvas.drawPath(path, paint);

        // 为Paint设置渐变器
        rect = getNextRect();
        Shader shader = new LinearGradient(rect.left, rect.top, rect.right, rect.bottom,
                new int[]{Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW},
                null, Shader.TileMode.REPEAT);
        paint.setShader(shader);
        paint.setShadowLayer(45, 10, 10, Color.GRAY);
        canvas.drawCircle(rect.centerX(), rect.centerY(), rect.width()/2 -10, paint);

        //绘制字符串
        rect = getNextRect();
        paint.setTextSize(22);
        paint.setShader(null);
        canvas.drawText("戴益波", rect.centerX(), rect.centerY(), paint);
    }

    private RectF getNextRect() {

        //初始化
        if (rectF == null) {
            rectF = new RectF(0, 0, DRAW_SIDE_LENGTH, DRAW_SIDE_LENGTH);
            return rectF;
        }

        //向下扩展
        if ((this.rectF.bottom + DRAW_SIDE_LENGTH) < getHeight()) {
            rectF.top += DRAW_SIDE_LENGTH;
            rectF.bottom += DRAW_SIDE_LENGTH;
        }
        //向右扩展
        else if ((this.rectF.right + DRAW_SIDE_LENGTH) < getWidth()) {
            rectF.top = 0;
            rectF.bottom = DRAW_SIDE_LENGTH;
            rectF.left = rectF.right;
            rectF.right += DRAW_SIDE_LENGTH;
        }
        //空间占满,从头开始
        else {
            this.rectF = null;
            return getNextRect();
        }

        return new RectF(rectF);
    }


}