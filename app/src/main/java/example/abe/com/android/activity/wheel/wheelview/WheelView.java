package example.abe.com.android.activity.wheel.wheelview;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * 滚轮控件
 *
 * @author daiyibo
 */
public class WheelView<T> extends ListView implements IWheelView<T> {

    /**
     * 滚轮滑动时展示选中项延迟时间
     */
    public static final int WHEEL_SCROLL_DELAY_DURATION = 300;

    /**
     * 单次平滑滚动事件持续时间
     */
    public static final int WHEEL_SMOOTH_SCROLL_DURATION = 50;

    /**
     * 标记：滚轮滑动时展示选中项
     */
    public static final int WHEEL_SCROLL_HANDLER_WHAT = 1001;

    /**
     * 可见项高度
     */
    private int mItemHeight = 0;

    /**
     * 滚轮个数
     */
    private int mWheelSize = 3;

    /**
     * 是否循环滚动
     */
    private boolean mLoop = false;

    /**
     * 记录滚轮当前刻度
     */
    private int mCurrentPosition = -1;

    /**
     * 滚轮适配器
     */
    private AbsWheelAdapter<T> mWheelAdapter;

    /**
     * 滚轮选中可见项监听
     */
    private OnWheelItemSelectedListener<T> mOnWheelItemSelectedListener;

    /**
     * 主线程事件分发器
     */
    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == WHEEL_SCROLL_HANDLER_WHAT) {
                if (mOnWheelItemSelectedListener != null) {
                    int position = getCurrentPosition() < 0 ? 0 : getCurrentPosition();
                    if (mWheelAdapter != null && mWheelAdapter.getDataCount() > position) {
                        mOnWheelItemSelectedListener.onItemSelected
                                (getCurrentPosition(), mWheelAdapter.getItem(position));
                    }
                }
            }
        }
    };

    /**
     * 事件触发拦截器
     */
    private OnTouchListener mTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            //内部拦截法，使父视图不接受此系列触发事件
            v.getParent().requestDisallowInterceptTouchEvent(true);
            return false;
        }
    };

    /**
     * 滚动事件监听
     */
    private OnScrollListener mOnScrollListener = new OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            // 滑动停止时，自动校准位置
            if (scrollState == SCROLL_STATE_IDLE) {
                View itemView = getChildAt(0);
                if (itemView != null) {
                    float deltaY = itemView.getY();
                    if (deltaY == 0 || mItemHeight == 0) {
                        return;
                    }
                    int smoothDistance;
                    if (Math.abs(deltaY) < mItemHeight / 2) {
                        smoothDistance = getSmoothDistance(deltaY);
                    } else {
                        smoothDistance = getSmoothDistance(mItemHeight + deltaY);
                    }
                    //WHEEL_SMOOTH_SCROLL_DURATION时间内，平滑滚动的smoothDistance距离
                    //递归调用此方法，直至 deltaY == 0
                    smoothScrollBy(smoothDistance, WHEEL_SMOOTH_SCROLL_DURATION);
                }
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int
                visibleItemCount, int totalItemCount) {
            if (visibleItemCount != 0) {
                //刷新位置
                refreshCurrentPosition();
            }
        }
    };

    public WheelView(Context context) {
        super(context);
        init();
    }

    public WheelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WheelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        setVerticalScrollBarEnabled(false);
        setScrollingCacheEnabled(false);
        setCacheColorHint(Color.TRANSPARENT);
        setFadingEdgeLength(0);
        setOverScrollMode(OVER_SCROLL_NEVER);
        setDividerHeight(0);
        setOnScrollListener(mOnScrollListener);
        setOnTouchListener(mTouchListener);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setNestedScrollingEnabled(true);
        }
        addOnGlobalLayoutListener();
    }

    private void addOnGlobalLayoutListener() {
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver
                .OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                if (getChildCount() > 0) {
                    mItemHeight = getChildAt(0).getHeight();
                    if (mItemHeight != 0) {
                        ViewGroup.LayoutParams params = getLayoutParams();
                        params.height = mItemHeight * mWheelSize;
                        setLayoutParams(params);
                        refreshVisibleItems(getFirstVisiblePosition(),
                                getFirstVisiblePosition() + mWheelSize / 2,
                                mWheelSize / 2);
                    }
                }
            }
        });
    }

    @Override
    public boolean getLoop(){
        return mLoop;
    }

    @Override
    public void setLoop(boolean loop) {
        if (loop != mLoop) {
            mLoop = loop;
            int curPosition = getCurrentPosition();
            if (mWheelAdapter != null) {
                mWheelAdapter.setLoop(loop);
            }
            setCurrentPosition(curPosition);
        }
    }

    @Override
    public int getWheelSize(){
        return mWheelSize;
    }

    @Override
    public void setWheelSize(int wheelSize) {
        if ((wheelSize & 1) == 0) {
            wheelSize ++;
        }
        mWheelSize = wheelSize;
        if (mWheelAdapter != null) {
            mWheelAdapter.setWheelSize(wheelSize);
        }
        // 更新控件大小
        addOnGlobalLayoutListener();
        requestLayout();
    }

    @Override
    public AbsWheelAdapter<T> getWheelAdapter() {
        return mWheelAdapter;
    }

    @Override
    public void setWheelAdapter(AbsWheelAdapter<T> adapter) {
        super.setAdapter(adapter);
        mWheelAdapter = adapter;
        mWheelAdapter.setWheelSize(mWheelSize);
        mWheelAdapter.setLoop(mLoop);
    }

    @Override
    public int getCurrentPosition() {
        return mCurrentPosition;
    }

    @Override
    public void setCurrentPosition(int position) {
        int dataSize;
        if (mWheelAdapter == null || (dataSize = mWheelAdapter.getDataCount()) == 0) {
            return;
        }
        int realSelection = position;
        if (mLoop) {
            int d = Integer.MAX_VALUE / 2 / dataSize;
            realSelection = position + d * dataSize - mWheelSize / 2;
        }
        super.setSelection(realSelection);
        refreshCurrentPosition();
    }

    @Override
    public OnWheelItemSelectedListener<T> getOnWheelItemSelectedListener() {
        return mOnWheelItemSelectedListener;
    }

    @Override
    public void setOnWheelItemSelectedListener(OnWheelItemSelectedListener<T> listener) {
        mOnWheelItemSelectedListener = listener;
    }

    /**
     * 设置滚轮选中位置，已弃用，具体使用{@link #setCurrentPosition(int)}
     *
     * @param selection 滚轮位置
     */
    @Override
    @Deprecated
    public void setSelection(int selection) {
        setCurrentPosition(selection);
    }

    /**
     * 设置滚轮数据适配器，已弃用，具体使用{@link #setWheelAdapter(AbsWheelAdapter)}
     *
     * @param adapter 适配器
     */
    @Override
    @Deprecated
    public void setAdapter(ListAdapter adapter) {
        if (adapter instanceof AbsWheelAdapter) {
            setWheelAdapter((AbsWheelAdapter) adapter);
        }
    }

    /**
     * 获取滚轮数据适配器，已弃用，具体使用{@link #getWheelAdapter()}
     */
    @Override
    @Deprecated
    public ListAdapter getAdapter() {
        return super.getAdapter();
    }

    /**
     * 平滑的滚动距离
     *
     * @param scrollDistance 当前距离
     * @return 平滑滚动距离
     */
    private int getSmoothDistance(float scrollDistance) {
        if (Math.abs(scrollDistance) < 2) {
            return (int) scrollDistance;
        } else if (Math.abs(scrollDistance) < 12) {
            return scrollDistance > 0 ? 2 : -2;
        } else {
            // 减缓平滑滑动速率
            return (int) (scrollDistance / 6);
        }
    }

    /**
     * 刷新当前位置
     */
    private void refreshCurrentPosition() {
        if (mWheelAdapter == null || mWheelAdapter.getDataCount() == 0){
            return;
        }
        if (getChildAt(0) == null || mItemHeight == 0) {
            return;
        }
        int firstPosition = getFirstVisiblePosition();
        if (mLoop && firstPosition == 0) {
            return;
        }
        int position;
        if (Math.abs(getChildAt(0).getY()) <= mItemHeight / 2) {
            position = firstPosition;
        } else {
            position = firstPosition + 1;
        }
        refreshVisibleItems(firstPosition, position + mWheelSize / 2,
                mWheelSize / 2);
        if (mLoop) {
            position = (position + mWheelSize / 2) % mWheelAdapter.getDataCount();
        }
        if (position == mCurrentPosition) {
            return;
        }
        mCurrentPosition = position;
        mHandler.removeMessages(WHEEL_SCROLL_HANDLER_WHAT);
        mHandler.sendEmptyMessageDelayed(WHEEL_SCROLL_HANDLER_WHAT, WHEEL_SCROLL_DELAY_DURATION);
    }

    /**
     * 刷新可见滚动列表
     *
     * @param firstPosition 首个可见项位置
     * @param curPosition 当前选中位置
     * @param offset 可见项偏移值
     */
    private void refreshVisibleItems(int firstPosition, int curPosition, int offset) {
        for (int position = curPosition - offset; position <= curPosition + offset; position++) {
            View itemView = getChildAt(position - firstPosition);
            if (itemView == null) {
                continue;
            }

            // 此时curPosition和i都包含了非循环滚动时隐藏Item，所以需要除去隐藏Item
            int argCurPosition = curPosition;
            int argPosition = position;
            if (!mLoop){
                argCurPosition = curPosition - offset < 0 ? 0: curPosition - offset;
                argPosition = position - offset < 0 ? 0: position - offset;
            }
            mWheelAdapter.refreshView(argCurPosition, argPosition, itemView);
        }
    }
}
