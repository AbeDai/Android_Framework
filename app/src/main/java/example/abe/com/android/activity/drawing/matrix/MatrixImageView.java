package example.abe.com.android.activity.drawing.matrix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.widget.ImageView;

import example.abe.com.android.R;

/**
 * Created by abe on 16/11/24.
 */

public class MatrixImageView extends ImageView {
    private Bitmap bitmap;
    private Matrix matrix;

    public MatrixImageView(Context context) {
        super(context);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        matrix = new Matrix();
    }

    public MatrixImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        matrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 画出原图像
        canvas.drawBitmap(bitmap, 0, 0, null);
        // 画出变换后的图像
        canvas.drawBitmap(bitmap, matrix, null);
        super.onDraw(canvas);
    }

    @Override
    public void setImageMatrix(Matrix matrix) {
        this.matrix.set(matrix);
        super.setImageMatrix(matrix);
    }

    public Bitmap getImageBitmap() {
        return bitmap;
    }
}
