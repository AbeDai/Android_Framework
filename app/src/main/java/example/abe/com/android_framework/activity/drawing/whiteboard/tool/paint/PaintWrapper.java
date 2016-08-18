package example.abe.com.android_framework.activity.drawing.whiteboard.tool.paint;

import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

import example.abe.com.android_framework.activity.drawing.whiteboard.tool.Type;


/**
 * Created by abe on 16/8/17.
 */
public class PaintWrapper {

    private PaintFeatures.PaintWidth width;
    private PaintFeatures.PaintColor color;
    private PaintFeatures.PaintEraserSize eraserSize;
    private PaintFeatures.PaintFont font;
    private Paint paint;

    public PaintWrapper(){
        width = PaintFeatures.PaintWidth.NORMAL;
        color = PaintFeatures.PaintColor.BLUE;
        eraserSize = PaintFeatures.PaintEraserSize.NORMAL;
        paint = new Paint();
    }

    public Paint updatePaint(Type type){
        paint.reset();
        switch (type){
            case DRAW:{
                //抗锯齿功能,会消耗资源
                paint.setAntiAlias(true);

                //图像抖动处理
                paint.setDither(true);

                //画笔
                paint.setStyle(Paint.Style.STROKE);

                //各图形的结合方式
                paint.setStrokeJoin(Paint.Join.ROUND);

                //笔尖圆形样式
                paint.setStrokeCap(Paint.Cap.ROUND);

                //颜色
                paint.setColor(color.value);

                //宽度
                paint.setStrokeWidth(width.value);
            }break;

            case ERASER:{
                //画笔
                paint.setStyle(Paint.Style.STROKE);

                //笔尖方形样式
                paint.setStrokeCap(Paint.Cap.SQUARE);

                //颜色
                paint.setColor(android.graphics.Color.TRANSPARENT);

                //宽度
                paint.setStrokeWidth(eraserSize.value);

                //复合模式
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            }break;

            case TEXT:{
                //抗锯齿功能,会消耗资源
                paint.setAntiAlias(true);

                //图像抖动处理
                paint.setDither(true);

                //颜色
                paint.setColor(color.value);

                //字体
                paint.setTextSize(font.value);
            }break;

            case RECT:{

            }break;
        }

        return paint;
    }

    public void setWidth(PaintFeatures.PaintWidth width) {
        this.width = width;
    }

    public void setColor(PaintFeatures.PaintColor color) {
        this.color = color;
    }

    public void setEraserSize(PaintFeatures.PaintEraserSize eraserSize) {
        this.eraserSize = eraserSize;
    }

    public void setFont(PaintFeatures.PaintFont font) {
        this.font = font;
    }

    public Paint getPaint() {
        return paint;
    }
}
