package example.abe.com.android.activity.album.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import example.abe.com.android.R;
import example.abe.com.android.activity.album.model.PhotoItemModel;
import example.abe.com.android.main.MyApplication;
import example.abe.com.framework.recycleview.base.ItemViewDelegate;
import example.abe.com.framework.recycleview.base.ViewHolder;
import example.abe.com.framework.util.DensityUtil;

import static android.R.attr.tag;

/**
 * Created by abe on 16/10/24.
 */
public class PhotoImageDelegate implements ItemViewDelegate<PhotoItemModel> {

    @Override
    public View getItemView(Context context, ViewGroup parent) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_photo_image, parent, false);
        return view;
    }

    @Override
    public boolean isForViewType(PhotoItemModel item, int position) {
        return true;
    }

    @Override
    public void bindViewHolder(final ViewHolder holder, PhotoItemModel photoItemModel, int position) {
        String path = "file://" + photoItemModel.getImagePath();
        Picasso.with(MyApplication.getInstance())
                .load(path)
                .resize(DensityUtil.dip2px(100),DensityUtil.dip2px(150))
                .centerCrop()
                .into((ImageView)holder.getView(R.id.item_photo_image_iv));
    }
}
