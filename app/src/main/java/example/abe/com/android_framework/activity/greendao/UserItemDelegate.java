package example.abe.com.android_framework.activity.greendao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.activity.greendao.dao.UserModel;
import example.abe.com.framework.recycleview.base.ItemViewDelegate;
import example.abe.com.framework.recycleview.base.ViewHolder;

/**
 * Created by abe on 16/10/13.
 */
public class UserItemDelegate extends ItemViewDelegate<UserModel> {

    @Override
    public View getItemView(Context context, ViewGroup parent) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_user_info_view, parent, false);
        return view;
    }

    @Override
    public boolean isForViewType(UserModel item, int position) {
        return true;
    }

    @Override
    public void bindViewHolder(ViewHolder holder, UserModel userModel, int position) {
        holder.setText(R.id.item_user_info_tv_id, String.valueOf(userModel.getId()));
        holder.setText(R.id.item_user_info_tv_name, userModel.getName());
        holder.setText(R.id.item_user_info_tv_age, String.valueOf(userModel.getAge()));
    }
}
