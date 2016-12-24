package example.abe.com.android.activity.drawing.shader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import example.abe.com.android.R;

/**
 * Created by abe on 16/11/24.
 */

public class ShaderView extends View {

    private Bitmap mBitmap;
    private Paint mPaint;

    public ShaderView(Context context) {
        super(context);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        mPaint = new Paint();
    }

    public ShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //BitmapShader，并将Shader.TileMode定义为CLAMP
        //CLAMP表示，当所画图形的尺寸大于Bitmap的尺寸的时候，会用Bitmap四边的颜色填充剩余空间。
        BitmapShader bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(bitmapShader);
        canvas.drawRect(0, 0, mBitmap.getWidth()*2, mBitmap.getHeight()*2, mPaint);

//        //BitmapShader，并将Shader.TileMode定义为REPEAT
//        //REPEAT表示，当我们绘制的图形尺寸大于Bitmap尺寸时，会用Bitmap重复平铺整个绘制的区域。
//        BitmapShader bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
//        mPaint.setShader(bitmapShader);
//        canvas.drawRect(0, 0, mBitmap.getWidth()*2, mBitmap.getHeight()*2, mPaint);

//        //BitmapShader，并将Shader.TileMode定义为MIRROR
//        //MIRROR表示，当我们绘制的图形尺寸大于Bitmap尺寸时，会用Bitmap重复平铺整个绘制的区域。
//        BitmapShader bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
//        mPaint.setShader(bitmapShader);
//        canvas.drawRect(0, 0, mBitmap.getWidth()*2, mBitmap.getHeight()*2, mPaint);

//        //LinearGradient创建线性渐变效果
//        //坐标(x0,y0)就是这条渐变直线的起点，坐标(x1,y1)就是这条渐变直线的终点。需要说明的是，坐标(x0,y0)和坐标(x1,y1)都是Canvas绘图坐标系中的坐标。color0和color1分别表示了渐变的起始颜色和终止颜色。
//        //CLAMP表示，当所画图形的尺寸大于Bitmap的尺寸的时候，会用Bitmap四边的颜色填充剩余空间。
//        LinearGradient linearGradient = new LinearGradient(0, 0, 500, 500, Color.GREEN, Color.BLUE, Shader.TileMode.CLAMP);
//        mPaint.setShader(linearGradient);
//        canvas.drawRect(0, 0, 500, 500, mPaint);

//        //RadialGradient是用来创建从中心向四周发散的辐射渐变效果
//        //坐标(centerX,centerY)是圆心，即起始的中心颜色的位置，radius确定了圆的半径，在圆的半径处的颜色是edgeColor，这样就确定了当位置从圆心移向圆的轮廓时，颜色逐渐从centerColor渐变到edgeColor。
//        //CLAMP表示，当所画图形的尺寸大于Bitmap的尺寸的时候，会用Bitmap四边的颜色填充剩余空间。
//        int canvasWidth = canvas.getWidth();
//        int canvasHeight = canvas.getHeight();
//        float centerX = canvasWidth / 2f;
//        float centerY = canvasHeight / 2f;
//        float radius = canvasWidth / 4f;
//        RadialGradient radialGradient = new RadialGradient(centerX, centerY, radius, Color.GREEN, Color.BLUE, Shader.TileMode.CLAMP);
//        mPaint.setShader(radialGradient);
//        canvas.drawRect(0, 0, canvasWidth, canvasHeight, mPaint);

//        //SweepGradient可以用来创建360度颜色旋转渐变效果，具体来说颜色是围绕中心点360度顺时针旋转的，起点就是3点钟位置。
//        //坐标(cx,cy)决定了中心点的位置，会绕着该中心点进行360度旋转。color0表示的是起点的颜色位置，color1表示的是终点的颜色位置。
//        int canvasWidth = canvas.getWidth();
//        int canvasHeight = canvas.getHeight();
//        float centerX = canvasWidth / 2f;
//        float centerY = canvasHeight / 2f;
//        float radius = canvasWidth / 4f;
//        SweepGradient sweepGradient = new SweepGradient(centerX, centerY, Color.GREEN, Color.BLUE);
//        mPaint.setShader(sweepGradient);
//        canvas.drawCircle(centerX, centerY, radius, mPaint);

//        //ComposeShader,就是混合Shader的意思，它可以将两个Shader按照一定的Xfermode组合起来。
//        //Xfermode可以用于实现新绘制的像素与Canvas上对应位置已有的像素按照混合规则进行颜色混合。
//        // Xfermode有三个子类：AvoidXfermode, PixelXorXfermode和PorterDuffXfermode，其中前两个类现在被Android废弃了，现在主要用的是PorterDuffXfermode。
//        int bitmapWidth = mBitmap.getWidth();
//        int bitmapHeight = mBitmap.getHeight();
//        BitmapShader bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//        LinearGradient linearGradient = new LinearGradient(0, 0, bitmapWidth, bitmapHeight, Color.GREEN, Color.BLUE, Shader.TileMode.CLAMP);
//        ComposeShader composeShader = new ComposeShader(bitmapShader, linearGradient, PorterDuff.Mode.MULTIPLY);
//        mPaint.setShader(composeShader);
//        canvas.drawRect(0, 0, bitmapWidth, bitmapHeight, mPaint);
    }
}
