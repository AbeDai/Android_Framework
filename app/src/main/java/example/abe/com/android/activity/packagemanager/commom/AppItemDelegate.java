package example.abe.com.android.activity.packagemanager.commom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import example.abe.com.android.R;
import example.abe.com.framework.recycleview.base.ItemViewDelegate;
import example.abe.com.framework.recycleview.base.ViewHolder;

/**
 * Created by abe on 16/10/26.
 */
public class AppItemDelegate implements ItemViewDelegate<AppInfoModel> {

    @Override
    public View getItemView(Context context, ViewGroup parent) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_app_info, parent, false);
        return view;
    }

    @Override
    public boolean isForViewType(AppInfoModel item, int position) {
        return true;
    }

    @Override
    public void bindViewHolder(ViewHolder holder, AppInfoModel model, int position) {
        holder.setImageDrawable(R.id.item_app_info_iv_icon, model.getAppIcon());
        holder.setText(R.id.item_app_info_tv_app_label, model.getAppLabel());
        holder.setText(R.id.item_app_info_tv_pkg_name, model.getPkgName());
    }
}
