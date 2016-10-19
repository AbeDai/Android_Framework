package example.abe.com.android.activity.socket.image;

import android.graphics.Bitmap;

/**
 * Created by abe on 16/8/12.
 */
public class BitmapEvent {
    Bitmap bitmap;

    public BitmapEvent(Bitmap bitmap){
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
