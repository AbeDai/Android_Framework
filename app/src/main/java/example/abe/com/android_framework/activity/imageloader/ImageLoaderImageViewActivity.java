package example.abe.com.android_framework.activity.imageloader;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.BindView;

import example.abe.com.android_framework.R;
import example.abe.com.framework.imageloader.ImageLoader;
import example.abe.com.framework.imageloader.handle.BitmapHandle;
import example.abe.com.framework.main.BaseActivity;

public class ImageLoaderImageViewActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.act_image_loader_btn_change_image)
    protected Button mBtnChangeImage;
    @BindView(R.id.act_image_loader_image_view_root)
    protected ImageView mIvShow;

    @Override
    public int getLayoutID(){
        return R.layout.activity_image_loader_image_view;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
        mBtnChangeImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ImageLoader.getInstance().getBitmap(ImageModel.getRandomImageUrl(),
                new BitmapHandle.BitmapHandleListener() {
                    @Override
                    public void onHandle(Bitmap bitmap) {
                        mIvShow.setImageBitmap(bitmap);
                    }
                });
    }
}
