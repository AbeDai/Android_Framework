package example.abe.com.android.activity.recycleview.example.wrapperadapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import example.abe.com.framework.recycleview.base.ItemHeadFootDelegate;
import example.abe.com.framework.recycleview.base.ViewHolder;

/**
 * Created by abe on 16/10/17.
 */

public class ItemFooterDelegate implements ItemHeadFootDelegate{
    @Override
    public View getItemView(Context context, ViewGroup parent) {
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 50);
        TextView footer = new TextView(context);
        footer.setText("Footer内容");
        footer.setLayoutParams(lp);
        footer.setBackgroundColor(Color.RED);
        return footer;
    }

    @Override
    public void bindViewHolder(ViewHolder holder, Object o, int position) {

    }
}
