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
import example.abe.com.android.model.ClazzModel;

public class MainAdapter extends BaseAdapter {

    private List<ClazzModel> mListClazz;
    private List<ClazzModel> mListClazzFilter;
    private Context mContext;

    public MainAdapter(Context context, List<ClazzModel> listClazz) {
        mContext = context;
        mListClazz = listClazz;
        mListClazzFilter = new ArrayList<>(listClazz);
    }

    @Override
    public int getCount() {
        return ActivityFactory.getInstance().getClazzSize();
    }

    @Override
    public Object getItem(int position) {
        return ActivityFactory.getInstance().getClazzModel(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        ClazzModel clazzModel = (ClazzModel)getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_main_activity_list, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.setTitle(clazzModel.getTitle());
        viewHolder.setDec(clazzModel.getContent());

        return convertView;
    }

    public void filter(String key) {
        mListClazzFilter.clear();
        mListClazzFilter.addAll(fuzzyMatchList(mListClazz, key));
        notifyDataSetChanged();
    }

    private List<ClazzModel> fuzzyMatchList(List<ClazzModel> list, String key) {
        key = key.toLowerCase();//转小写

        List<ClazzModel> temp = new ArrayList<>();
        for (ClazzModel clazzModel : list) {
            //内容转小写
            if (clazzModel.getTitle().toLowerCase().contains(key)
                    || clazzModel.getContent().toLowerCase().contains(key)) {
                temp.add(clazzModel);
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

