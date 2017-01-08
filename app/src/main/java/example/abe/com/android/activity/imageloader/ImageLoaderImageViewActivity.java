package example.abe.com.android.activity.imageloader;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.example.BindView;
import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.android.utils.ImageURLUtil;
import example.abe.com.framework.imageloader.ImageLoader;
import example.abe.com.framework.imageloader.handle.BitmapHandle;
import example.abe.com.framework.main.BaseActivity;

public class ImageLoaderImageViewActivity extends BaseActivity {

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
    }

    @OnClick({R.id.act_image_loader_btn_change_image})
    public void onChangeImage() {
        ImageLoader.getInstance().getBitmap(ImageURLUtil.getRandomImageUrl(),
                new BitmapHandle.BitmapHandleListener() {
                    @Override
                    public void onHandle(Bitmap bitmap) {
                        mIvShow.setImageBitmap(bitmap);
                    }
                });
    }
}
