package example.abe.com.android_framework.activity.imageloader;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import example.abe.com.android_framework.R;
import example.abe.com.framework.imageloader.ImageLoader;
import example.abe.com.framework.imageloader.handle.BitmapHandle;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.viewinject.ContentView;
import example.abe.com.framework.viewinject.ViewInject;

@ContentView(id = R.layout.activity_image_loader_image_view)
public class ImageLoaderImageViewActivity extends BaseActivity implements View.OnClickListener {

    @ViewInject(id = R.id.act_image_loader_btn_change_image)
    private Button mBtnChangeImage;
    @ViewInject(id = R.id.act_image_loader_image_view_root)
    private ImageView mIvShow;
    private int position;

    @Override
    public void initData() {
        position = 0;
    }

    @Override
    public void initView() {
        mBtnChangeImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        position = ((position + 1) % Images.listImageUrl.size());
        ImageLoader.getInstance().getImageFIFO(Images.listImageUrl.get(position),
                new BitmapHandle.BitmapHandleListener() {
                    @Override
                    public void onHandle(Bitmap bitmap) {
                        mIvShow.setImageBitmap(bitmap);
                    }
                });
    }
}
