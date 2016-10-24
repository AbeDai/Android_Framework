package example.abe.com.android.activity.album.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import example.abe.com.android.R;
import example.abe.com.android.activity.album.model.PhotoBucketModel;
import example.abe.com.framework.recycleview.base.ItemViewDelegate;
import example.abe.com.framework.recycleview.base.ViewHolder;

/**
 * Created by abe on 16/10/24.
 */

public class PhotoBucketDelegate implements ItemViewDelegate<PhotoBucketModel> {

    @Override
    public View getItemView(Context context, ViewGroup parent) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_photo_bucket, parent, false);
        return view;
    }

    @Override
    public boolean isForViewType(PhotoBucketModel item, int position) {
        return true;
    }

    @Override
    public void bindViewHolder(ViewHolder holder, PhotoBucketModel photoBucketModel, int position) {
        holder.setText(R.id.item_photo_bucket_name, photoBucketModel.getBucketName());
        holder.setText(R.id.item_photo_bucket_count, String.valueOf(photoBucketModel.getCount()));
    }
}
