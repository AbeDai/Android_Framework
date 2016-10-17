package example.abe.com.android_framework.activity.recycleview.example.baseadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import example.abe.com.android_framework.R;
import example.abe.com.framework.imageloader.ImageLoader;
import example.abe.com.framework.recycleview.base.ItemViewDelegate;
import example.abe.com.framework.recycleview.base.ViewHolder;

/**
 * Created by abe on 16/10/13.
 */

public class ImageTextDelegate implements ItemViewDelegate<ImageTextModel> {

    @Override
    public View getItemView(Context context, ViewGroup parent) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_image_text_recycle_view_example, parent, false);
        return view;
    }

    @Override
    public boolean isForViewType(ImageTextModel item, int position) {
        return true;
    }

    @Override
    public void bindViewHolder(ViewHolder holder, ImageTextModel imageTextModel, int position) {
        holder.setText(R.id.item_image_text_recycle_view_example_tv, imageTextModel.getText());
        ImageView imageView = holder.getView(R.id.item_image_text_recycle_view_example_iv);
        ImageLoader.getInstance().getImageFIFO(imageView, imageTextModel.getImgUrl());
    }
}
