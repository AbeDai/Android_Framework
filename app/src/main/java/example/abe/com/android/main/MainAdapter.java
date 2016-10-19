package example.abe.com.android.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import example.abe.com.android.R;
import example.abe.com.android.main.ActivityFactory.Flags;

public class MainAdapter extends BaseAdapter {

    private List<Flags> mListFlag;
    private List<Flags> mListFlagFilter;
    private Context mContext;

    public MainAdapter(Context context, List<Flags> listFlag) {
        mListFlag = new ArrayList<>(listFlag);
        mListFlagFilter = new ArrayList<>(mListFlag);
        mContext = context;
    }

    @Override
    public int getCount() {
        return mListFlagFilter.size();
    }

    @Override
    public Object getItem(int position) {
        return mListFlagFilter.get(position);
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
        Flags flag = mListFlagFilter.get(position);
        viewHolder.setTitle(ActivityFactory.getTitle(flag));
        viewHolder.setDec(ActivityFactory.getContent(flag));

        return convertView;
    }

    public void filter(String key) {
        mListFlagFilter.clear();
        mListFlagFilter.addAll(fuzzyMatchList(mListFlag, key));
        notifyDataSetChanged();
    }

    public List<Flags> getListFlagFilter(){
        return mListFlagFilter;
    }

    private List<Flags> fuzzyMatchList(List<Flags> list, String key) {
        key = key.toLowerCase();//转小写

        List<Flags> temp = new ArrayList<>();
        for (Flags flag : list) {
            //内容转小写
            if (ActivityFactory.getTitle(flag).toLowerCase().contains(key)
                    || ActivityFactory.getContent(flag).toLowerCase().contains(key)) {
                temp.add(flag);
            }
        }

        return temp;
    }

    private class ViewHolder {
        TextView mTvTitle;
        TextView mTvDec;

        private ViewHolder(View content) {
            mTvTitle = (TextView) content.findViewById(R.id.item_main_act_list_title);
            mTvDec = (TextView) content.findViewById(R.id.item_main_act_list_dec);
        }

        private void setTitle(String title) {
            mTvTitle.setText(title);
        }

        private void setDec(String dec) {
            mTvDec.setText(dec);
        }
    }
}

