package example.abe.com.framework.recycleview.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by abe on 16/10/11.
 */
public class ViewHolder extends RecyclerView.ViewHolder {
    private HashMap<Integer, View> mViewMap;
    private View mItemView;
    private Context mContext;

    public ViewHolder(Context context, View itemView) {
        super(itemView);
        mContext = context;
        mItemView = itemView;
        mViewMap = new HashMap<>();
    }

    /**
     * 获取视图
     */
    public <T extends View> T getView(int viewId) {
        View view = mViewMap.get(viewId);
        if (view == null) {
            view = mItemView.findViewById(viewId);
            mViewMap.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 获取内容视图
     */
    public View getItemView() {
        return mItemView;
    }

    /**********************以下为辅助方法**********************/
    /**
     * 设置Text内容
     * @param viewId 视图id
     * @param text 内容
     */
    public void setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
    }

    /**
     * 设置图片资源
     * @param viewId 视图id
     * @param resId 资源id
     */
    public void setImageResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
    }

    /**
     * 设置位图
     * @param viewId 视图id
     * @param bitmap 位图资源
     */
    public void setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
    }

    /**
     * 设置绘制内容
     * @param viewId 视图id
     * @param drawable 绘图内容
     */
    public void setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
    }

    /**
     * 设置背景颜色
     * @param viewId 视图id
     * @param color 颜色
     */
    public void setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
    }

    /**
     * 设置背景颜色
     * @param viewId 视图id
     * @param backgroundRes 背景资源
     */
    public void setBackgroundRes(int viewId, int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
    }

    /**
     * 设置文字颜色
     * @param viewId 视图id
     * @param textColor 文字颜色
     */
    public void setTextColor(int viewId, int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
    }

    /**
     * 设置文字颜色
     * @param viewId 视图id
     * @param textColorRes 文字颜色资源
     */
    public void setTextColorRes(int viewId, int textColorRes) {
        TextView view = getView(viewId);
        view.setTextColor(mContext.getResources().getColor(textColorRes));
    }

    /**
     * 设置透明度
     * @param viewId 视图id
     * @param value 透明度
     */
    public void setAlpha(int viewId, float value) {
        getView(viewId).setAlpha(value);
    }

    /**
     * 设置是否可见
     * @param viewId 视图id
     * @param visible 是否可见
     */
    public void setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    /**
     * 设置是否选中
     */
    public void setChecked(int viewId, boolean checked) {
        Checkable view = (Checkable) getView(viewId);
        view.setChecked(checked);
    }

    /**
     * 设置点击监听
     */
    public void setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
    }

    /**
     * 设置长按监听
     */
    public void setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
    }

    /**
     * 设置触摸监听
     */
    public void setOnTouchListener(int viewId, View.OnTouchListener listener) {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
    }

    /**
     * 设置Tag
     */
    public void setTag(int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
    }

    /**
     * 设置Tag
     */
    public void setTag(int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
    }
}
