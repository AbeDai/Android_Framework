package example.abe.com.android_framework.activity.recycleview.template;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import example.abe.com.framework.util.LogUtil;

/**
 * Created by abe on 16/8/3.
 */
public class DivideDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDivider;
    {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.LTGRAY);
        drawable.setSize(0, 1);
        mDivider = drawable;
    }

    /*
     * 绘制分割线,根据ChildView的位置,在RecycleView的相应位置绘制分割线
     * 每次ChildView的位置改变(即,滑动ChildView),就需要重新绘制
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        LogUtil.d("onDraw()");

        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    /*
     * 位置偏移
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
    }
}
