package example.abe.com.android_framework.activity.imageloader;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.BindView;

import java.util.List;

import example.abe.com.android_framework.R;
import example.abe.com.framework.imageloader.ImageLoader;
import example.abe.com.framework.main.BaseActivity;

public class ImageLoaderGridViewActivity extends BaseActivity {

    @BindView(R.id.act_image_loader_grid_view_root)
    protected GridView mGridView;

    @Override
    public int getLayoutID(){
        return R.layout.activity_image_loader_grid_view;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
        mGridView.setAdapter(new ListImgItemAdapter(this, 0, ImageModel.LIST_IMAGE_URL));
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
