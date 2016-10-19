package example.abe.com.android.activity.recycleview.template;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import example.abe.com.android.R;

/**
 * Created by abe on 16/8/3.
 */
public class ViewHolder extends RecyclerView.ViewHolder {
    TextView tvTitle;
    ImageView ivIcon;

    public ViewHolder(View view) {
        super(view);
        tvTitle = (TextView) view.findViewById(R.id.item_abrecycle_list_tv_title);
        ivIcon = (ImageView) view.findViewById(R.id.item_abrecycle_list_img_icon);
    }
}
