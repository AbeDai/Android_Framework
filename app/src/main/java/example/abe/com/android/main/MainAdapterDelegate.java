package example.abe.com.android.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import example.abe.com.android.R;
import example.abe.com.android.model.FunctionModel;
import example.abe.com.framework.recycleview.base.ItemViewDelegate;
import example.abe.com.framework.recycleview.base.ViewHolder;

/**
 * Created by abe on 16/10/13.
 */

public class MainAdapterDelegate implements ItemViewDelegate<FunctionModel> {

    @Override
    public View getItemView(Context context, ViewGroup parent) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_main_activity_list, parent, false);
        return view;
    }

    @Override
    public boolean isForViewType(FunctionModel item, int position) {
        return true;
    }

    @Override
    public void bindViewHolder(ViewHolder holder, FunctionModel imageTextModel, int position) {
        holder.setText(R.id.item_main_act_list_tv_title, imageTextModel.getTitle());
        holder.setText(R.id.item_main_act_list_tv_dec, imageTextModel.getContent());
    }
}
