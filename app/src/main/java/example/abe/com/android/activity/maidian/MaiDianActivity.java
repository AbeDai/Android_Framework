package example.abe.com.android.activity.maidian;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.util.ToastUtil;

public class MaiDianActivity extends BaseActivity {

    //所有视图
    private ArrayList<View> mListViewAll;
    private ArrayList<String> mViewPath;

    @Override
    public int getLayoutID() {
        return R.layout.activity_mai_dian;
    }

    @Override
    public void initData() {
        //设置埋点
        mViewPath = new ArrayList<>();
        mViewPath.add("MaiDianActivity.act_mai_dian_et_user");
        mViewPath.add("MaiDianActivity.act_mai_dian_et_pwd");
        mViewPath.add("MaiDianActivity.act_mai_dian_tv_login");
    }

    @Override
    public void initView() {
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            /*获取当前点击位置，遍历布局，获取当前点击位置对应的view，根据view映射路径，与json文件中的对比*/
            double x = ev.getRawX();
            double y = ev.getRawY() - getBarHeight();
            /*解析根视图，会严重影响性能。作为简单埋点示例模板，这里只解析视图树一次。*/
            if (mListViewAll == null)
                mListViewAll = getView((ViewGroup) findViewById(android.R.id.content));
            for (int i = 0; i < mListViewAll.size(); i++) {
                /*获取点击位置的view*/
                int left = mListViewAll.get(i).getLeft();
                int right = mListViewAll.get(i).getRight();
                int top = mListViewAll.get(i).getTop();
                int bottom = mListViewAll.get(i).getBottom();
                if (x > left && x < right &&
                        y > top && y < bottom) {
                    /*判断这个view是否是我们要埋点的*/
                    String s = this.getClass().getSimpleName() + "." +
                            getResources().getResourceEntryName(mListViewAll.get(i).getId());
                    if (mViewPath.contains(s)) {
                        ToastUtil.makeText("这是我们的埋点:" + s);
                    }
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 递归调用是保存视图
     */
    private ArrayList<View> views;

    /**
     * 解析视图树，获取所有View和没有子View的ViewGroup
     *
     * @param target 解析目标
     * @return 所有View和没有子View的ViewGroup
     */
    public ArrayList<View> getView(ViewGroup target) {
        if (views == null)
            views = new ArrayList<View>();
        if (target == null) return null;
        //views.add(viewGroup);
        int count = target.getChildCount();
        for (int i = 0; i < count; i++) {
            if (target.getChildAt(i) instanceof ViewGroup) {
                this.getView((ViewGroup) target.getChildAt(i));
            } else {
                views.add(target.getChildAt(i));
            }
        }
        return views;
    }

    /**
     * 返回标题高度
     *
     * @return 高度
     */
    private int getBarHeight() {
        Rect outRect = new Rect();
        this.getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);
        return outRect.top;
    }
}
