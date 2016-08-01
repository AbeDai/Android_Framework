package example.abe.com.android_framework.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import example.abe.com.android_framework.R;

public class MainAdapter extends BaseAdapter {

    private List<String> mListTitle;
    private List<String> mListDec;
    private Context mContext;

    public MainAdapter(Context context, List<String> listTitle, List<String> listDec) {
        mListTitle = listTitle;
        mListDec = listDec;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mListTitle.size();
    }

    @Override
    public Object getItem(int position) {
        return mListTitle.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_main_activity_list, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.setTitle(mListTitle.get(position));
        viewHolder.setDec(mListDec.get(position));

        return convertView;
    }

    private class ViewHolder {
        TextView mTvTitle;
        TextView mTvDec;

        private ViewHolder(View content) {
            mTvTitle = (TextView) content.findViewById(R.id.item_main_act_list_title);
            mTvDec = (TextView) content.findViewById(R.id.item_main_act_list_dec);
        }

        private void setTitle(String title){
            mTvTitle.setText(title);
        }

        private void setDec(String dec){
            mTvDec.setText(dec);
        }
    }
}

