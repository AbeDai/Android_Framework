package example.abe.com.android_framework.activity.drawing.whiteboard.tool.paint;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

import example.abe.com.android_framework.activity.drawing.whiteboard.tool.WhiteboardStatus;

/**
 * Created by abe on 16/8/17.
 */
public class PaintFactory {

    public static Paint getDefaultPaint(){
        Paint paint = new Paint();
        paint.setAntiAlias(true);//抗锯齿功能,会消耗资源
        paint.setDither(true);//图像抖动处理
        paint.setStyle(Paint.Style.STROKE);//画笔的样式
        paint.setStrokeJoin(Paint.Join.ROUND);//绘制时各图形的结合方式
        paint.setStrokeCap(Paint.Cap.ROUND);//绘制时笔尖处理方式
        paint.setStrokeWidth(12);//画笔粗细
        return paint;
    }

    public static Paint getPaint(WhiteboardStatus status){
        Paint paint = getDefaultPaint();

        if (status.type == WhiteboardStatus.Type.DRAW){
            paint.setColor(status.color.getColor());
            paint.setStrokeWidth(status.width.getWidth());
        }
        else if (status.type == WhiteboardStatus.Type.ERASER){
            paint.setColor(Color.TRANSPARENT);
            paint.setStrokeWidth(status.eraserSize.getSize());
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        }

        return paint;
    }
}
