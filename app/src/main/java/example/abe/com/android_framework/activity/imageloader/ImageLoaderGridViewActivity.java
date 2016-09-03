package example.abe.com.android_framework.activity.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;

import example.abe.com.android_framework.R;
import example.abe.com.framework.imageloader.ImageLoader;
import example.abe.com.framework.imageloader.handle.BitmapHandle;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.viewinject.ContentView;
import example.abe.com.framework.viewinject.ViewInject;

@ContentView(id = R.layout.activity_image_loader_grid_view)
public class ImageLoaderGridViewActivity extends BaseActivity {

    @ViewInject(id = R.id.act_image_loader_grid_view_root)
    private GridView mGridView;

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
        mGridView.setAdapter(new ListImgItemAdapter(this, 0, ImageModel.listImageUrl));
    }

    private class ListImgItemAdapter extends ArrayAdapter<String> {

        public ListImgItemAdapter(Context context, int resource, List<String> datas) {
            super(context, resource, datas);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                ImageView imageView= new ImageView(ImageLoaderGridViewActivity.this);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 120));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                convertView = imageView;
            }

            final ImageView imageView = (ImageView) convertView;
            ImageLoader.getInstance().getImageFIFO(imageView, getItem(position));

            return convertView;
        }
    }

}
