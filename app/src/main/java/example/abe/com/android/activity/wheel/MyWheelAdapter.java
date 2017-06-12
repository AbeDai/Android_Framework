package example.abe.com.android.activity.wheel;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import example.abe.com.android.R;
import example.abe.com.android.activity.wheel.wheelview.AbsWheelAdapter;

public class MyWheelAdapter extends AbsWheelAdapter<String> {

    private Context mContext;

    public MyWheelAdapter(Context context) {
        mContext = context;
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_wheel_activity_text, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.tv = (TextView)convertView.findViewById(R.id.item_wheel_act_tv);
            convertView.setTag(viewHolder);
        }
        ViewHolder viewHolder = (ViewHolder)convertView.getTag();
        viewHolder.tv.setText(getItem(position));
        return convertView;
    }

    @Override
    public void refreshView(int curPosition, int position, View convertView) {
        ViewHolder viewHolder = (ViewHolder)convertView.getTag();
        viewHolder.tv.setText(getItem(position));

        if (curPosition == position){
            viewHolder.tv.setTextColor(Color.RED);
            viewHolder.tv.setTextSize(18);
            convertView.setAlpha(1);
        }else {
            viewHolder.tv.setTextColor(Color.BLACK);
            viewHolder.tv.setTextSize(15);
            int delta = Math.abs(position - curPosition);
            float alpha = (float) Math.pow(0.5f, delta);
            convertView.setAlpha(alpha);
        }
    }

    public static class ViewHolder{
        private TextView tv;
    }

}
