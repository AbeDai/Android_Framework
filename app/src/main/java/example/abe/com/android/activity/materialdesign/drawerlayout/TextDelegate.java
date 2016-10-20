package example.abe.com.android.activity.materialdesign.drawerlayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import example.abe.com.android.R;
import example.abe.com.framework.recycleview.base.ItemViewDelegate;
import example.abe.com.framework.recycleview.base.ViewHolder;

/**
 * Created by abe on 16/10/20.
 */

public class TextDelegate implements ItemViewDelegate<TextModel> {

    @Override
    public View getItemView(Context context, ViewGroup parent) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_text_recycle_view_example, parent, false);
        return view;
    }

    @Override
    public boolean isForViewType(TextModel item, int position) {
        return true;
    }

    @Override
    public void bindViewHolder(ViewHolder holder, TextModel textModel, int position) {
        holder.setText(R.id.item_image_text_recycle_view_example_tv, textModel.getText());
    }
}
