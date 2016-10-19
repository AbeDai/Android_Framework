package example.abe.com.android.activity.gridview;

/**
 * Created by abe on 16/8/3.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import example.abe.com.android.R;

public class ImageAdapter extends BaseAdapter {

    public Context mContext;

    public ImageAdapter(Context context){
        mContext = context;
    }

    @Override
    public int getCount() {
        return 100;
    }

    @Override
    public Object getItem(int position) {
        return R.drawable.ic_launcher;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            convertView = new ImageView(mContext);
        }
        imageView = (ImageView) convertView;
        imageView.setImageResource(R.drawable.ic_launcher);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setLayoutParams(new GridView.LayoutParams(
                GridView.LayoutParams.WRAP_CONTENT,
                GridView.LayoutParams.WRAP_CONTENT));

        return imageView;
    }
}
