package example.abe.com.android_framework.activity.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import example.abe.com.android_framework.R;
import example.abe.com.framework.imageloader.ImageLoader;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.viewinject.ContentView;
import example.abe.com.framework.viewinject.ViewInject;

@ContentView(id = R.layout.activity_image_loader)
public class ImageLoaderActivity extends BaseActivity {

    @ViewInject(id = R.id.act_image_loader_grid_view)
    private GridView mGridView;
    private String[] mUrlStrs = Images.imageThumbUrls;

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
        mGridView.setAdapter(new ListImgItemAdaper(this, 0, mUrlStrs));
    }

    private class ListImgItemAdaper extends ArrayAdapter<String> {

        public ListImgItemAdaper(Context context, int resource, String[] datas) {
            super(ImageLoaderActivity.this, 0, datas);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = new ImageView(ImageLoaderActivity.this);
            }

            final ImageView imageview = (ImageView) convertView;
            imageview.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 120));
            imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageview.setImageResource(R.drawable.ic_no_picture);

            ImageLoader.getInstance().getBitmap(getItem(position), new ImageLoader.BitmapListener() {
                @Override
                public void onHandle(Bitmap bitmap) {
                    imageview.setImageBitmap(bitmap);
                }
            });
            return convertView;
        }

    }

}
