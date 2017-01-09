package example.abe.com.framework.refresh;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Scroller;

import example.abe.com.framework.R;

/**
 * Created by abe on 17/1/4.
 * 自定义加载框架
 */
public class SwipeToLoadLayout extends ViewGroup {

    /********************************  各种执行操作的默认时间  ********************************/

    private static final int DEFAULT_SWIPING_TO_REFRESH_TO_DEFAULT_SCROLLING_DURATION = 200;

    private static final int DEFAULT_RELEASE_TO_REFRESHING_SCROLLING_DURATION = 200;

    private static final int DEFAULT_REFRESH_COMPLETE_DELAY_DURATION = 300;

    private static final int DEFAULT_REFRESH_COMPLETE_TO_DEFAULT_SCROLLING_DURATION = 500;

    private static final int DEFAULT_DEFAULT_TO_REFRESHING_SCROLLING_DURATION = 500;

    private static final int DEFAULT_SWIPING_TO_LOAD_MORE_TO_DEFAULT_SCROLLING_DURATION = 200;

    private static final int DEFAULT_RELEASE_TO_LOADING_MORE_SCROLLING_DURATION = 200;

    private static final int DEFAULT_LOAD_MORE_COMPLETE_DELAY_DURATION = 300;

    private static final int DEFAULT_LOAD_MORE_COMPLETE_TO_DEFAULT_SCROLLING_DURATION = 300;

    private static final int DEFAULT_DEFAULT_TO_LOADING_MORE_SCROLLING_DURATION = 300;

    /**
     * 默认拖动缩放比例
     */
    private static final float DEFAULT_DRAG_RATIO = 0.5f;

    /**
     * 无用点ID标记值
     */
    private static final int INVALID_POINTER = -1;

    /**
     * 无用位置标记值
     */
    private static final int INVALID_COORDINATE = -1;

    /**
     * 自动滚动操作
     */
    private AutoScroller mAutoScroller;

    /**
     * 下拉刷新监听
     */
    private OnRefreshListener mRefreshListener;

    /**
     * 上拉加载监听
     */
    private OnLoadMoreListener mLoadMoreListener;

    /**
     * 刷新头部视图
     */
    private View mHeaderView;

    /**
     * 刷新内容视图
     */
    private View mTargetView;

    /**
     * 刷新尾部视图
     */
    private View mFooterView;

    /**
     * 头部视图高度
     */
    private int mHeaderHeight;

    /**
     * 尾部视图高度
     */
    private int mFooterHeight;

    /**
     * 是否存在头部视图
     */
    private boolean mHasHeaderView;

    /**
     * 是否存在尾部视图
     */
    private boolean mHasFooterView;

    /**
     * 拖动缩放比例值（realY = touchY * ratio）
     */
    private float mDragRatio = DEFAULT_DRAG_RATIO;

    /**
     * 是否为自动加载或自动刷新
     */
    private boolean mAutoLoading;

    /**
     * 手势触发最小间距
     */
    private final int mTouchSlop;

    /**
     * SwipeToLoadLayout当前状态<br>
     *     详细内容参考内部类 STATUS
     */
    private int mStatus = STATUS.STATUS_DEFAULT;

    /**
     * 头部视图相对位移（STATUS_DEFAULT状态下为0）
     */
    private int mHeaderOffset;

    /**
     * 内容视图相对位移（STATUS_DEFAULT状态下为0）
     */
    private int mTargetOffset;

    /**
     * 尾部视图相对位移（STATUS_DEFAULT状态下为0）
     */
    private int mFooterOffset;

    /**
     * 初始化点击操作Y位置（1.ACTION_DOWN:手指点击 2.ACTION_POINTER_DOWN:单指按下 3.ACTION_POINTER_UP:单指释放）
     */
    private float mInitDownY;

    /**
     * 初始化点击操作位置（1.ACTION_DOWN:手指点击 2.ACTION_POINTER_DOWN:单指按下 3.ACTION_POINTER_UP:单指释放）
     */
    private float mInitDownX;

    /**
     * 最近点击位置Y
     */
    private float mLastY;

    /**
     * 最近点击位置X
     */
    private float mLastX;

    /**
     * 有效触摸点ID
     */
    private int mActivePointerId;

    /**
     * <b>ATTRIBUTE:</b>
     * 开关：使能刷新功能
     */
    private boolean mRefreshEnabled = true;

    /**
     * <b>ATTRIBUTE:</b>
     * 开关：使能加载更多功能
     */
    private boolean mLoadMoreEnabled = true;

    /**
     * <b>ATTRIBUTE:</b>
     * 设置刷新框架布局类型
     */
    private int mStyle = STYLE.CLASSIC;

    /**
     * <b>ATTRIBUTE:</b>
     * 触发刷新的位移
     */
    private float mRefreshTriggerOffset;

    /**
     * <b>ATTRIBUTE:</b>
     * 触发加载的位移
     */
    private float mLoadMoreTriggerOffset;

    /**
     * <b>ATTRIBUTE:</b>
     * 头部视图最大位移
     */
    private float mRefreshFinalDragOffset;

    /**
     * <b>ATTRIBUTE:</b>
     * 尾部视图最大位移
     */
    private float mLoadMoreFinalDragOffset;

    /**
     * <b>ATTRIBUTE:</b>
     * 持续时间：STATUS_SWIPING_TO_REFRESH -> STATUS_DEFAULT
     */
    private int mSwipingToRefreshToDefaultScrollingDuration = DEFAULT_SWIPING_TO_REFRESH_TO_DEFAULT_SCROLLING_DURATION;

    /**
     * <b>ATTRIBUTE:</b>
     * 持续时间：STATUS_RELEASE_TO_REFRESH -> STATUS_REFRESHING
     */
    private int mReleaseToRefreshToRefreshingScrollingDuration = DEFAULT_RELEASE_TO_REFRESHING_SCROLLING_DURATION;

    /**
     * <b>ATTRIBUTE:</b>
     * 持续时间：刷新完成延时操作
     */
    private int mRefreshCompleteDelayDuration = DEFAULT_REFRESH_COMPLETE_DELAY_DURATION;

    /**
     * <b>ATTRIBUTE:</b>
     * 持续时间：STATUS_REFRESHING -> STATUS_DEFAULT
     */
    private int mRefreshCompleteToDefaultScrollingDuration = DEFAULT_REFRESH_COMPLETE_TO_DEFAULT_SCROLLING_DURATION;

    /**
     * <b>ATTRIBUTE:</b>
     * 持续时间：STATUS_DEFAULT -> STATUS_REFRESHING
     */
    private int mDefaultToRefreshingScrollingDuration = DEFAULT_DEFAULT_TO_REFRESHING_SCROLLING_DURATION;

    /**
     * <b>ATTRIBUTE:</b>
     * 持续时间：STATUS_RELEASE_TO_LOAD_MORE -> STATUS_LOADING_MORE
     */
    private int mReleaseToLoadMoreToLoadingMoreScrollingDuration = DEFAULT_RELEASE_TO_LOADING_MORE_SCROLLING_DURATION;

    /**
     * <b>ATTRIBUTE:</b>
     * 持续时间：加载更多完成延时操作
     */
    private int mLoadMoreCompleteDelayDuration = DEFAULT_LOAD_MORE_COMPLETE_DELAY_DURATION;

    /**
     * <b>ATTRIBUTE:</b>
     * 持续时间：STATUS_LOADING_MORE -> STATUS_DEFAULT
     */
    private int mLoadMoreCompleteToDefaultScrollingDuration = DEFAULT_LOAD_MORE_COMPLETE_TO_DEFAULT_SCROLLING_DURATION;

    /**
     * <b>ATTRIBUTE:</b>
     * 持续时间：STATUS_SWIPING_TO_LOAD_MORE -> STATUS_DEFAULT
     */
    private int mSwipingToLoadMoreToDefaultScrollingDuration = DEFAULT_SWIPING_TO_LOAD_MORE_TO_DEFAULT_SCROLLING_DURATION;

    /**
     * <b>ATTRIBUTE:</b>
     * 持续时间：STATUS_DEFAULT -> STATUS_LOADING_MORE
     */
    private int mDefaultToLoadingMoreScrollingDuration = DEFAULT_DEFAULT_TO_LOADING_MORE_SCROLLING_DURATION;

    /**
     * 刷新框架布局类型
     */
    public static final class STYLE {
        /**
         * 经典模式
         */
        public static final int CLASSIC = 0;
        /**
         * 内容视图之上模式（头部视图和尾部视图在内容视图的上方）
         */
        public static final int ABOVE = 1;
        /**
         * 内容视图之下模式（头部视图和尾部视图在内容视图的下方）
         */
        public static final int BLEW = 2;
        /**
         * 缩放模式
         */
        public static final int SCALE = 3;
    }

    public SwipeToLoadLayout(Context context) {
        this(context, null);
    }

    public SwipeToLoadLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeToLoadLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //XML自定义属性
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SwipeToLoadLayout, defStyleAttr, 0);
        try {
            final int N = a.getIndexCount();
            for (int i = 0; i < N; i++) {
                int attr = a.getIndex(i);
                if (attr == R.styleable.SwipeToLoadLayout_refresh_enabled) {
                    setRefreshEnabled(a.getBoolean(attr, true));

                } else if (attr == R.styleable.SwipeToLoadLayout_load_more_enabled) {
                    setLoadMoreEnabled(a.getBoolean(attr, true));

                } else if (attr == R.styleable.SwipeToLoadLayout_swipe_style) {
                    setSwipeStyle(a.getInt(attr, STYLE.CLASSIC));

                } else if (attr == R.styleable.SwipeToLoadLayout_drag_ratio) {
                    setDragRatio(a.getFloat(attr, DEFAULT_DRAG_RATIO));

                } else if (attr == R.styleable.SwipeToLoadLayout_refresh_final_drag_offset) {
                    setRefreshFinalDragOffset(a.getDimensionPixelOffset(attr, 0));

                } else if (attr == R.styleable.SwipeToLoadLayout_load_more_final_drag_offset) {
                    setLoadMoreFinalDragOffset(a.getDimensionPixelOffset(attr, 0));

                } else if (attr == R.styleable.SwipeToLoadLayout_refresh_trigger_offset) {
                    setRefreshTriggerOffset(a.getDimensionPixelOffset(attr, 0));

                } else if (attr == R.styleable.SwipeToLoadLayout_load_more_trigger_offset) {
                    setLoadMoreTriggerOffset(a.getDimensionPixelOffset(attr, 0));

                } else if (attr == R.styleable.SwipeToLoadLayout_swiping_to_refresh_to_default_scrolling_duration) {
                    setSwipingToRefreshToDefaultScrollingDuration(a.getInt(attr, DEFAULT_SWIPING_TO_REFRESH_TO_DEFAULT_SCROLLING_DURATION));

                } else if (attr == R.styleable.SwipeToLoadLayout_release_to_refreshing_scrolling_duration) {
                    setReleaseToRefreshingScrollingDuration(a.getInt(attr, DEFAULT_RELEASE_TO_REFRESHING_SCROLLING_DURATION));

                } else if (attr == R.styleable.SwipeToLoadLayout_refresh_complete_delay_duration) {
                    setRefreshCompleteDelayDuration(a.getInt(attr, DEFAULT_REFRESH_COMPLETE_DELAY_DURATION));

                } else if (attr == R.styleable.SwipeToLoadLayout_refresh_complete_to_default_scrolling_duration) {
                    setRefreshCompleteToDefaultScrollingDuration(a.getInt(attr, DEFAULT_REFRESH_COMPLETE_TO_DEFAULT_SCROLLING_DURATION));

                } else if (attr == R.styleable.SwipeToLoadLayout_default_to_refreshing_scrolling_duration) {
                    setDefaultToRefreshingScrollingDuration(a.getInt(attr, DEFAULT_DEFAULT_TO_REFRESHING_SCROLLING_DURATION));

                } else if (attr == R.styleable.SwipeToLoadLayout_swiping_to_load_more_to_default_scrolling_duration) {
                    setSwipingToLoadMoreToDefaultScrollingDuration(a.getInt(attr, DEFAULT_SWIPING_TO_LOAD_MORE_TO_DEFAULT_SCROLLING_DURATION));

                } else if (attr == R.styleable.SwipeToLoadLayout_release_to_loading_more_scrolling_duration) {
                    setReleaseToLoadingMoreScrollingDuration(a.getInt(attr, DEFAULT_RELEASE_TO_LOADING_MORE_SCROLLING_DURATION));

                } else if (attr == R.styleable.SwipeToLoadLayout_load_more_complete_delay_duration) {
                    setLoadMoreCompleteDelayDuration(a.getInt(attr, DEFAULT_LOAD_MORE_COMPLETE_DELAY_DURATION));

                } else if (attr == R.styleable.SwipeToLoadLayout_load_more_complete_to_default_scrolling_duration) {
                    setLoadMoreCompleteToDefaultScrollingDuration(a.getInt(attr, DEFAULT_LOAD_MORE_COMPLETE_TO_DEFAULT_SCROLLING_DURATION));

                } else if (attr == R.styleable.SwipeToLoadLayout_default_to_loading_more_scrolling_duration) {
                    setDefaultToLoadingMoreScrollingDuration(a.getInt(attr, DEFAULT_DEFAULT_TO_LOADING_MORE_SCROLLING_DURATION));

                }
            }
        } finally {
            a.recycle();
        }

        //手势触发最小间距
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

        //自动滑动执行单元
        mAutoScroller = new AutoScroller();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        //条件验证
        final int childNum = getChildCount();
        int childTempNum = 1;
        if (0 < childNum && childNum < 4) {
            mHeaderView = findViewById(R.id.swipe_refresh_header);
            mTargetView = findViewById(R.id.swipe_target);
            mFooterView = findViewById(R.id.swipe_load_more_footer);
            if (mTargetView == null){
                throw new IllegalStateException("缺少内容视图");
            }else {
                childTempNum++;
            }
            if (mHeaderView != null){
                if (mHeaderView instanceof SwipeRefreshTrigger){
                    childTempNum++;
                }else {
                    throw new IllegalStateException("头部视图没有实现SwipeRefreshTrigger接口");
                }
            }
            if (mFooterView != null){
                if (mFooterView instanceof SwipeLoadMoreTrigger){
                    childTempNum++;
                }else {
                    throw new IllegalStateException("尾部视图没有实现SwipeLoadMoreTrigger接口");
                }
            }
            if (childNum == childTempNum){
                throw new IllegalStateException("子视图不符合构建要求，只允许添加头部视图 内容视图 尾部视图 ");
            }
        } else {
            // 最多支持三个子视图
            throw new IllegalStateException("子视图不符合条件范围");
        }
        //隐藏头部视图和尾部视图
        if (mHeaderView != null && mHeaderView instanceof SwipeTrigger) {
            mHeaderView.setVisibility(GONE);
        }
        if (mFooterView != null && mFooterView instanceof SwipeTrigger) {
            mFooterView.setVisibility(GONE);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 头部视图
        if (mHeaderView != null) {
            final View headerView = mHeaderView;
            measureChildWithMargins(headerView, widthMeasureSpec, 0, heightMeasureSpec, 0);
            MarginLayoutParams lp = ((MarginLayoutParams) headerView.getLayoutParams());
            mHeaderHeight = headerView.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            if (mRefreshTriggerOffset < mHeaderHeight) {
                mRefreshTriggerOffset = mHeaderHeight;
            }
        }
        // 内容视图
        if (mTargetView != null) {
            final View targetView = mTargetView;
            measureChildWithMargins(targetView, widthMeasureSpec, 0, heightMeasureSpec, 0);
        }
        // 尾部视图
        if (mFooterView != null) {
            final View footerView = mFooterView;
            measureChildWithMargins(footerView, widthMeasureSpec, 0, heightMeasureSpec, 0);
            MarginLayoutParams lp = ((MarginLayoutParams) footerView.getLayoutParams());
            mFooterHeight = footerView.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            if (mLoadMoreTriggerOffset < mFooterHeight) {
                mLoadMoreTriggerOffset = mFooterHeight;
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        layoutChildren();

        mHasHeaderView = (mHeaderView != null);
        mHasFooterView = (mFooterView != null);
    }

    /**
     * 自定义LayoutParams
     */
    public static class LayoutParams extends MarginLayoutParams {

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
    }

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new SwipeToLoadLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new SwipeToLoadLayout.LayoutParams(p);
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new SwipeToLoadLayout.LayoutParams(getContext(), attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);
        switch (action) {
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                // swipeToRefresh -> finger up -> finger down if the status is still swipeToRefresh
                // in onInterceptTouchEvent ACTION_DOWN event will stop the scroller
                // if the event pass to the child view while ACTION_MOVE(condition is false)
                // in onInterceptTouchEvent ACTION_MOVE the ACTION_UP or ACTION_CANCEL will not be
                // passed to onInterceptTouchEvent and onTouchEvent. Instead It will be passed to
                // child view's onTouchEvent. So we must deal this situation in dispatchTouchEvent
                // 手指抬起时操作
                onActivePointerUp();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        final int action = MotionEventCompat.getActionMasked(event);
        switch (action) {
            case MotionEvent.ACTION_DOWN:

                mActivePointerId = MotionEventCompat.getPointerId(event, 0);
                mInitDownY = mLastY = getMotionEventY(event, mActivePointerId);
                mInitDownX = mLastX = getMotionEventX(event, mActivePointerId);

                // 非 刷新中状态，加载中状态和默认状态
                if (STATUS.isSwipingToRefresh(mStatus) || STATUS.isSwipingToLoadMore(mStatus) ||
                        STATUS.isReleaseToRefresh(mStatus) || STATUS.isReleaseToLoadMore(mStatus)) {
                    // 终止自动滑动操作
                    mAutoScroller.abortIfRunning();
                }

                if (STATUS.isSwipingToRefresh(mStatus) || STATUS.isReleaseToRefresh(mStatus)
                        || STATUS.isSwipingToLoadMore(mStatus) || STATUS.isReleaseToLoadMore(mStatus)) {
                    return true;
                }

                // 内容视图中子视图处理点击事件;

                // 1. 子视图处理点击事件:
                // 如果children#onTouchEvent() ACTION_DOWN时return true
                // 那么将不会传到SwipeToLoadLayout#onInterceptTouchEvent()
                // 但OTHER_ACTION还是会传到SwipeToLoadLayout#onInterceptTouchEvent()

                // 2. 子视图不处理点击事件:
                // 如果children#onTouchEvent() ACTION_DOWN时return false
                // 那么将会传回到SwipeToLoadLayout#onInterceptTouchEvent()
                // SwipeToLoadLayout#onTouchEvent() ACTION_DOWN时return true 来处理ACTION_DOWN事件
                break;
            case MotionEvent.ACTION_MOVE:
                if (mActivePointerId == INVALID_POINTER) {
                    return false;
                }
                float y = getMotionEventY(event, mActivePointerId);
                float x = getMotionEventX(event, mActivePointerId);
                final float yInitDiff = y - mInitDownY;
                final float xInitDiff = x - mInitDownX;
                mLastY = y;
                mLastX = x;
                boolean moved = Math.abs(yInitDiff) > Math.abs(xInitDiff)
                        && Math.abs(yInitDiff) > mTouchSlop;
                boolean triggerCondition =
                        // 刷新触发条件
                        (yInitDiff > 0 && moved && onCheckCanRefresh()) ||
                                // 加载更多触发条件
                                (yInitDiff < 0 && moved && onCheckCanLoadMore());
                if (triggerCondition) {
                    // 符合触发下拉刷新和上拉加载条件
                    // 拦截ACTION_MOVE事件，将事件传递到SwipeToLoadLayout#onTouchEvent()
                    return true;
                }
                break;
            case MotionEvent.ACTION_POINTER_UP: {
                onSecondaryPointerUp(event);
                mInitDownY = mLastY = getMotionEventY(event, mActivePointerId);
                mInitDownX = mLastX = getMotionEventX(event, mActivePointerId);
                break;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mActivePointerId = INVALID_POINTER;
                break;
        }
        return super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = MotionEventCompat.getActionMasked(event);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mActivePointerId = MotionEventCompat.getPointerId(event, 0);
                return true;

            case MotionEvent.ACTION_MOVE:
                // SwipeToLoadLayout#onInterceptTouchEvent() 已拦截 ACTION_MOVE事件
                final float y = getMotionEventY(event, mActivePointerId);
                final float x = getMotionEventX(event, mActivePointerId);

                final float yDiff = y - mLastY;
                final float xDiff = x - mLastX;
                mLastY = y;
                mLastX = x;

                if (Math.abs(xDiff) > Math.abs(yDiff) && Math.abs(xDiff) > mTouchSlop) {
                    return false;
                }

                if (STATUS.isStatusDefault(mStatus)) {
                    if (yDiff > 0 && onCheckCanRefresh()) {
                        mRefreshCallback.onPrepare();
                        setStatus(STATUS.STATUS_SWIPING_TO_REFRESH);
                    } else if (yDiff < 0 && onCheckCanLoadMore()) {
                        mLoadMoreCallback.onPrepare();
                        setStatus(STATUS.STATUS_SWIPING_TO_LOAD_MORE);
                    }
                } else if (STATUS.isRefreshStatus(mStatus)) {
                    if (mTargetOffset <= 0) {
                        setStatus(STATUS.STATUS_DEFAULT);
                        fixCurrentStatusLayout();
                        return false;
                    }
                } else if (STATUS.isLoadMoreStatus(mStatus)) {
                    if (mTargetOffset >= 0) {
                        setStatus(STATUS.STATUS_DEFAULT);
                        fixCurrentStatusLayout();
                        return false;
                    }
                }

                if (STATUS.isRefreshStatus(mStatus)) {
                    if (STATUS.isSwipingToRefresh(mStatus) || STATUS.isReleaseToRefresh(mStatus)) {
                        if (mTargetOffset >= mRefreshTriggerOffset) {
                            setStatus(STATUS.STATUS_RELEASE_TO_REFRESH);
                        } else {
                            setStatus(STATUS.STATUS_SWIPING_TO_REFRESH);
                        }
                        fingerScroll(yDiff);
                    }
                } else if (STATUS.isLoadMoreStatus(mStatus)) {
                    if (STATUS.isSwipingToLoadMore(mStatus) || STATUS.isReleaseToLoadMore(mStatus)) {
                        if (-mTargetOffset >= mLoadMoreTriggerOffset) {
                            setStatus(STATUS.STATUS_RELEASE_TO_LOAD_MORE);
                        } else {
                            setStatus(STATUS.STATUS_SWIPING_TO_LOAD_MORE);
                        }
                        fingerScroll(yDiff);
                    }
                }
                return true;

            case MotionEvent.ACTION_POINTER_DOWN: {
                final int pointerIndex = MotionEventCompat.getActionIndex(event);
                final int pointerId = MotionEventCompat.getPointerId(event, pointerIndex);
                if (pointerId != INVALID_POINTER) {
                    mActivePointerId = pointerId;
                }
                mInitDownY = mLastY = getMotionEventY(event, mActivePointerId);
                mInitDownX = mLastX = getMotionEventX(event, mActivePointerId);
                break;
            }
            case MotionEvent.ACTION_POINTER_UP: {
                onSecondaryPointerUp(event);
                mInitDownY = mLastY = getMotionEventY(event, mActivePointerId);
                mInitDownX = mLastX = getMotionEventX(event, mActivePointerId);
                break;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (mActivePointerId == INVALID_POINTER) {
                    return false;
                }
                mActivePointerId = INVALID_POINTER;
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 是否支持刷新功能
     *
     * @return 是否可以刷新
     */
    public boolean isRefreshEnabled() {
        return mRefreshEnabled;
    }

    /**
     * 使能刷新功能
     *
     * @param enable 是否可以刷新
     */
    public void setRefreshEnabled(boolean enable) {
        this.mRefreshEnabled = enable;
    }

    /**
     * 是否支持加载更多功能
     *
     * @return 是否可以加载更多
     */
    public boolean isLoadMoreEnabled() {
        return mLoadMoreEnabled;
    }

    /**
     * 使能加载更多功能
     *
     * @param enable 是否可以加载更多
     */
    public void setLoadMoreEnabled(boolean enable) {
        this.mLoadMoreEnabled = enable;
    }

    /**
     * 是否处于刷新状态
     *
     * @return 是否处于刷新状态
     */
    public boolean isRefreshing() {
        return STATUS.isRefreshing(mStatus);
    }

    /**
     * 是否处于加载更多状态
     *
     * @return 是否处于加载更多状态
     */
    public boolean isLoadingMore() {
        return STATUS.isLoadingMore(mStatus);
    }

    /**
     * 设置头部视图
     *
     * @param view 头部视图，必须实现SwipeRefreshTrigger，通过现实SwipeTrigger来扩展功能
     */
    public void setRefreshHeaderView(View view){
        if (view instanceof SwipeRefreshTrigger) {
            if (mHeaderView != null && mHeaderView != view) {
                removeView(mHeaderView);
            }
            if (mHeaderView != view) {
                this.mHeaderView = view;
                addView(view);
            }
        } else {
            throw new IllegalStateException("头部视图必须实现SwipeRefreshTrigger接口");
        }
    }

    /**
     * 设置尾部视图
     *
     * @param view 尾部视图，必须实现SwipeLoadTrigger，通过现实SwipeTrigger来扩展功能
     */
    public void setLoadMoreFooterView(View view) {
        if (view instanceof SwipeLoadMoreTrigger) {
            if (mFooterView != null && mFooterView != view) {
                removeView(mFooterView);
            }
            if (mFooterView != view) {
                this.mFooterView = view;
                addView(mFooterView);
            }
        } else {
            throw new IllegalStateException("尾部视图必须实现SwipeLoadTrigger接口");
        }
    }

    /**
     * 设置刷新框架布局类型
     *
     * @param style 刷新框架布局类型
     */
    public void setSwipeStyle(int style) {
        this.mStyle = style;
        requestLayout();
    }

    /**
     * 设置拖动缩放比例值（realY = touchY * ratio）<br>
     *
     * @param dragRatio 拖动缩放比例值，默认为{@link #DEFAULT_DRAG_RATIO}
     *
     */
    public void setDragRatio(float dragRatio) {
        if (dragRatio <= 0)
            return;

        this.mDragRatio = dragRatio;
    }

    /**
     * 设置触发刷新的位移{@link #mRefreshTriggerOffset}<br>
     * 默认值为头部视图高度 {@link #mHeaderHeight}<br>
     * 且必须要大于头部视图高度 {@link #mHeaderHeight}
     *
     * @param offset 触发刷新的位移
     */
    public void setRefreshTriggerOffset(int offset) {
        mRefreshTriggerOffset = offset;
    }

    /**
     * 设置触发加载更多的位移{@link #mLoadMoreTriggerOffset}<br>
     * 默认值为尾部视图高度 {@link #mFooterHeight}<br>
     * 且必须要大于尾部视图高度 {@link #mFooterHeight}
     *
     * @param offset 触发加载更多的位移
     */
    public void setLoadMoreTriggerOffset(int offset) {
        if (offset > 0)
            mLoadMoreTriggerOffset = offset;
    }

    /**
     * 设置下拉刷新最终位置<br/>
     * 如果位置设置为 0(default value) 或 小于触发刷新的位移 {@link #mRefreshTriggerOffset}
     * 则不存在下拉刷新最终位置
     *
     * @param offset 下拉刷新最终位置
     */
    public void setRefreshFinalDragOffset(int offset) {
        if (offset > 0)
            mRefreshFinalDragOffset = offset;
    }

    /**
     * 设置加载更多最终位置<br/>
     * 如果位置设置为 0(default value) 或 小于触发刷新的位移 {@link #mLoadMoreTriggerOffset}
     * 则不存在下拉刷新最终位置
     *
     * @param offset 加载更多最终位置
     */
    public void setLoadMoreFinalDragOffset(int offset) {
        mLoadMoreFinalDragOffset = offset;
    }

    /**
     * 设置 {@link #mSwipingToRefreshToDefaultScrollingDuration}（单位：毫秒）
     *
     * @param duration 持续时间
     */
    public void setSwipingToRefreshToDefaultScrollingDuration(int duration) {
        this.mSwipingToRefreshToDefaultScrollingDuration = duration;
    }

    /**
     * 设置{@link #mReleaseToRefreshToRefreshingScrollingDuration}（单位：毫秒）
     *
     * @param duration 持续时间
     */
    public void setReleaseToRefreshingScrollingDuration(int duration) {
        this.mReleaseToRefreshToRefreshingScrollingDuration = duration;
    }

    /**
     * 设置{@link #mRefreshCompleteDelayDuration}（单位：毫秒）
     *
     * @param duration 持续时间
     */
    public void setRefreshCompleteDelayDuration(int duration) {
        this.mRefreshCompleteDelayDuration = duration;
    }

    /**
     * 设置{@link #mRefreshCompleteToDefaultScrollingDuration}（单位：毫秒）
     *
     * @param duration 持续时间
     */
    public void setRefreshCompleteToDefaultScrollingDuration(int duration) {
        this.mRefreshCompleteToDefaultScrollingDuration = duration;
    }

    /**
     * 设置{@link #mDefaultToRefreshingScrollingDuration}（单位：毫秒）
     *
     * @param duration 持续时间
     */
    public void setDefaultToRefreshingScrollingDuration(int duration) {
        this.mDefaultToRefreshingScrollingDuration = duration;
    }

    /**
     * 设置{@link @mSwipingToLoadMoreToDefaultScrollingDuration}（单位：毫秒）
     *
     * @param duration 持续时间
     */
    public void setSwipingToLoadMoreToDefaultScrollingDuration(int duration) {
        this.mSwipingToLoadMoreToDefaultScrollingDuration = duration;
    }

    /**
     * 设置{@link #mReleaseToLoadMoreToLoadingMoreScrollingDuration}（单位：毫秒）
     *
     * @param duration 持续时间
     */
    public void setReleaseToLoadingMoreScrollingDuration(int duration) {
        this.mReleaseToLoadMoreToLoadingMoreScrollingDuration = duration;
    }

    /**
     * 设置{@link #mLoadMoreCompleteDelayDuration}（单位：毫秒）
     *
     * @param duration 持续时间
     */
    public void setLoadMoreCompleteDelayDuration(int duration) {
        this.mLoadMoreCompleteDelayDuration = duration;
    }

    /**
     * 设置{@link #mLoadMoreCompleteToDefaultScrollingDuration}（单位：毫秒）
     *
     * @param duration 持续时间
     */
    public void setLoadMoreCompleteToDefaultScrollingDuration(int duration) {
        this.mLoadMoreCompleteToDefaultScrollingDuration = duration;
    }

    /**
     * 设置{@link #mDefaultToLoadingMoreScrollingDuration}（单位：毫秒）
     *
     * @param duration 持续时间
     */
    public void setDefaultToLoadingMoreScrollingDuration(int duration) {
        this.mDefaultToLoadingMoreScrollingDuration = duration;
    }

    /**
     * 设置下拉刷新监听
     *
     * @param listener 监听
     */
    public void setOnRefreshListener(OnRefreshListener listener) {
        this.mRefreshListener = listener;
    }

    /**
     * 设置上拉加载监听
     *
     * @param listener 监听
     */
    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        this.mLoadMoreListener = listener;
    }

    /**
     * 设置自动刷新或取消刷新
     *
     * @param refreshing 是否刷新
     */
    public void setRefreshing(boolean refreshing) {
        if (!isRefreshEnabled() || mHeaderView == null) {
            return;
        }
        mAutoLoading = refreshing;
        if (refreshing) {
            if (STATUS.isStatusDefault(mStatus)) {
                setStatus(STATUS.STATUS_SWIPING_TO_REFRESH);
                scrollDefaultToRefreshing();
            }
        } else {
            if (STATUS.isRefreshing(mStatus)) {
                mRefreshCallback.onComplete();
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        scrollRefreshingToDefault();
                    }
                }, mRefreshCompleteDelayDuration);
            }
        }
    }

    /**
     * 设置自动加载或取消加载
     *
     * @param loadingMore 是否加载
     */
    public void setLoadingMore(boolean loadingMore) {
        if (!isLoadMoreEnabled() || mFooterView == null) {
            return;
        }
        mAutoLoading = loadingMore;
        if (loadingMore) {
            if (STATUS.isStatusDefault(mStatus)) {
                setStatus(STATUS.STATUS_SWIPING_TO_LOAD_MORE);
                scrollDefaultToLoadingMore();
            }
        } else {
            if (STATUS.isLoadingMore(mStatus)) {
                mLoadMoreCallback.onComplete();
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        scrollLoadingMoreToDefault();
                    }
                }, mLoadMoreCompleteDelayDuration);
            }
        }
    }

    /**
     * 内容视图中的子视图是否已经到顶了
     * @return 是否子视图已经到顶
     */
    protected boolean canChildScrollUp() {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (mTargetView instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) mTargetView;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                        .getTop() < absListView.getPaddingTop());
            } else {
                return ViewCompat.canScrollVertically(mTargetView, -1) || mTargetView.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(mTargetView, -1);
        }
    }

    /**
     * 内容视图中的子视图是否已经到底
     *
     * @return 是否子视图已经到底
     */
    protected boolean canChildScrollDown() {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (mTargetView instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) mTargetView;
                return absListView.getChildCount() > 0
                        && (absListView.getLastVisiblePosition() < absListView.getChildCount() - 1
                        || absListView.getChildAt(absListView.getChildCount() - 1).getBottom() > absListView.getPaddingBottom());
            } else {
                return ViewCompat.canScrollVertically(mTargetView, 1) || mTargetView.getScrollY() < 0;
            }
        } else {
            return ViewCompat.canScrollVertically(mTargetView, 1);
        }
    }

    /**
     * 视图布局<br>
     *     包括：1.头部视图 2.内容视图 3.尾部视图
     */
    private void layoutChildren() {
        final int width = getMeasuredWidth();
        final int height = getMeasuredHeight();

        final int paddingLeft = getPaddingLeft();
        final int paddingTop = getPaddingTop();
        final int paddingRight = getPaddingRight();
        final int paddingBottom = getPaddingBottom();

        if (mTargetView == null) {
            return;
        }

        //头部视图
        if (mHeaderView != null) {
            final View headerView = mHeaderView;
            MarginLayoutParams lp = (MarginLayoutParams) headerView.getLayoutParams();
            final int headerLeft = paddingLeft + lp.leftMargin;
            final int headerTop;
            switch (mStyle) {
                case STYLE.CLASSIC:
                    headerTop = paddingTop + lp.topMargin - mHeaderHeight + mHeaderOffset;
                    break;
                case STYLE.ABOVE:
                    headerTop = paddingTop + lp.topMargin - mHeaderHeight + mHeaderOffset;
                    break;
                case STYLE.BLEW:
                    headerTop = paddingTop + lp.topMargin;
                    break;
                case STYLE.SCALE:
                    headerTop = paddingTop + lp.topMargin - mHeaderHeight / 2 + mHeaderOffset / 2;
                    break;
                default:
                    //默认为CLASSIC模式
                    headerTop = paddingTop + lp.topMargin - mHeaderHeight + mHeaderOffset;
                    break;
            }
            final int headerRight = headerLeft + headerView.getMeasuredWidth();
            final int headerBottom = headerTop + headerView.getMeasuredHeight();
            headerView.layout(headerLeft, headerTop, headerRight, headerBottom);
        }

        //内容视图
        if (mTargetView != null) {
            final View targetView = mTargetView;
            MarginLayoutParams lp = (MarginLayoutParams) targetView.getLayoutParams();
            final int targetLeft = paddingLeft + lp.leftMargin;
            final int targetTop;

            switch (mStyle) {
                case STYLE.CLASSIC:
                    targetTop = paddingTop + lp.topMargin + mTargetOffset;
                    break;
                case STYLE.ABOVE:
                    targetTop = paddingTop + lp.topMargin;
                    break;
                case STYLE.BLEW:
                    targetTop = paddingTop + lp.topMargin + mTargetOffset;
                    break;
                case STYLE.SCALE:
                    targetTop = paddingTop + lp.topMargin + mTargetOffset;
                    break;
                default:
                    //默认为CLASSIC模式
                    targetTop = paddingTop + lp.topMargin + mTargetOffset;
                    break;
            }
            final int targetRight = targetLeft + targetView.getMeasuredWidth();
            final int targetBottom = targetTop + targetView.getMeasuredHeight();
            targetView.layout(targetLeft, targetTop, targetRight, targetBottom);
        }

        //尾部视图
        if (mFooterView != null) {
            final View footerView = mFooterView;
            MarginLayoutParams lp = (MarginLayoutParams) footerView.getLayoutParams();
            final int footerLeft = paddingLeft + lp.leftMargin;
            final int footerBottom;
            switch (mStyle) {
                case STYLE.CLASSIC:
                    footerBottom = height - paddingBottom - lp.bottomMargin + mFooterHeight + mFooterOffset;
                    break;
                case STYLE.ABOVE:
                    footerBottom = height - paddingBottom - lp.bottomMargin + mFooterHeight + mFooterOffset;
                    break;
                case STYLE.BLEW:
                    footerBottom = height - paddingBottom - lp.bottomMargin;
                    break;
                case STYLE.SCALE:
                    footerBottom = height - paddingBottom - lp.bottomMargin + mFooterHeight / 2 + mFooterOffset / 2;
                    break;
                default:
                    //默认为CLASSIC模式
                    footerBottom = height - paddingBottom - lp.bottomMargin + mFooterHeight + mFooterOffset;
                    break;
            }
            final int footerTop = footerBottom - footerView.getMeasuredHeight();
            final int footerRight = footerLeft + footerView.getMeasuredWidth();

            footerView.layout(footerLeft, footerTop, footerRight, footerBottom);
        }

        if (mStyle == STYLE.CLASSIC
                || mStyle == STYLE.ABOVE) {
            if (mHeaderView != null) {
                mHeaderView.bringToFront();
            }
            if (mFooterView != null) {
                mFooterView.bringToFront();
            }
        } else if (mStyle == STYLE.BLEW || mStyle == STYLE.SCALE) {
            if (mTargetView != null) {
                mTargetView.bringToFront();
            }
        }
    }

    private void fixCurrentStatusLayout() {
        if (STATUS.isRefreshing(mStatus)) {
            mTargetOffset = (int) (mRefreshTriggerOffset + 0.5f);
            mHeaderOffset = mTargetOffset;
            mFooterOffset = 0;
            layoutChildren();
            invalidate();
        } else if (STATUS.isStatusDefault(mStatus)) {
            mTargetOffset = 0;
            mHeaderOffset = 0;
            mFooterOffset = 0;
            layoutChildren();
            invalidate();
        } else if (STATUS.isLoadingMore(mStatus)) {
            mTargetOffset = -(int) (mLoadMoreTriggerOffset + 0.5f);
            mHeaderOffset = 0;
            mFooterOffset = mTargetOffset;
            layoutChildren();
            invalidate();
        }
    }

    /**
     * 手指滚动逻辑处理
     *
     * @param yDiff Y轴移动位置
     */
    private void fingerScroll(final float yDiff) {
        float ratio = mDragRatio;
        float yScrolled = yDiff * ratio;

        /**
         * 允许操作：
         * 1. (targetOffset>0 -> targetOffset=0 -> default status)
         * 2. (targetOffset<0 -> targetOffset=0 -> default status)
         * 禁止操作：
         * 1. (targetOffset>0 -> targetOffset=0 ->targetOffset<0 -> default status)
         * 2. (targetOffset<0 -> targetOffset=0 ->targetOffset>0 -> default status)
         **/
        float tmpTargetOffset = yScrolled + mTargetOffset;
        if ((tmpTargetOffset > 0 && mTargetOffset < 0)
                || (tmpTargetOffset < 0 && mTargetOffset > 0)) {
            yScrolled = -mTargetOffset;
        }

        if (mRefreshFinalDragOffset >= mRefreshTriggerOffset && tmpTargetOffset > mRefreshFinalDragOffset) {
            yScrolled = mRefreshFinalDragOffset - mTargetOffset;
        } else if (mLoadMoreFinalDragOffset >= mLoadMoreTriggerOffset && -tmpTargetOffset > mLoadMoreFinalDragOffset) {
            yScrolled = -mLoadMoreFinalDragOffset - mTargetOffset;
        }

        if (STATUS.isRefreshStatus(mStatus)) {
            mRefreshCallback.onMove(mTargetOffset, false, false);
        } else if (STATUS.isLoadMoreStatus(mStatus)) {
            mLoadMoreCallback.onMove(mTargetOffset, false, false);
        }

        updateScroll(yScrolled);
    }

    /**
     * 自动滚动处理逻辑
     * @param yScrolled Y轴移动位置
     */
    private void autoScroll(final float yScrolled) {

        if (STATUS.isSwipingToRefresh(mStatus)) {
            mRefreshCallback.onMove(mTargetOffset, false, true);
        } else if (STATUS.isReleaseToRefresh(mStatus)) {
            mRefreshCallback.onMove(mTargetOffset, false, true);
        } else if (STATUS.isRefreshing(mStatus)) {
            mRefreshCallback.onMove(mTargetOffset, true, true);
        } else if (STATUS.isSwipingToLoadMore(mStatus)) {
            mLoadMoreCallback.onMove(mTargetOffset, false, true);
        } else if (STATUS.isReleaseToLoadMore(mStatus)) {
            mLoadMoreCallback.onMove(mTargetOffset, false, true);
        } else if (STATUS.isLoadingMore(mStatus)) {
            mLoadMoreCallback.onMove(mTargetOffset, true, true);
        }
        updateScroll(yScrolled);
    }

    /**
     * 真正滚动操作实现方法（处理手指滚动和自动滚动）
     * 核心方法
     *
     * @param yScrolled Y轴移动位置
     */
    private void updateScroll(final float yScrolled) {
        if (yScrolled == 0) {
            return;
        }
        mTargetOffset += yScrolled;

        if (STATUS.isRefreshStatus(mStatus)) {
            mHeaderOffset = mTargetOffset;
            mFooterOffset = 0;
        } else if (STATUS.isLoadMoreStatus(mStatus)) {
            mFooterOffset = mTargetOffset;
            mHeaderOffset = 0;
        }

        layoutChildren();
        invalidate();
    }

    /**
     * 当有效触摸点手指抬起时操作
     */
    private void onActivePointerUp() {
        if (STATUS.isSwipingToRefresh(mStatus)) {
            scrollSwipingToRefreshToDefault();

        } else if (STATUS.isSwipingToLoadMore(mStatus)) {
            scrollSwipingToLoadMoreToDefault();

        } else if (STATUS.isReleaseToRefresh(mStatus)) {
            mRefreshCallback.onRelease();
            scrollReleaseToRefreshToRefreshing();

        } else if (STATUS.isReleaseToLoadMore(mStatus)) {
            mLoadMoreCallback.onRelease();
            scrollReleaseToLoadMoreToLoadingMore();

        }
    }

    /**
     * 多点触摸，一个触摸点抬起时的处理方法
     *
     * @param ev 当前触摸事件
     */
    private void onSecondaryPointerUp(MotionEvent ev) {
        final int pointerIndex = MotionEventCompat.getActionIndex(ev);
        final int pointerId = MotionEventCompat.getPointerId(ev, pointerIndex);
        if (pointerId == mActivePointerId) {
            // 当前有效触摸点抬起，重新选择新的有效触摸点
            final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
            mActivePointerId = MotionEventCompat.getPointerId(ev, newPointerIndex);
        }
    }

    private void scrollDefaultToRefreshing() {
        mAutoScroller.autoScroll((int) (mRefreshTriggerOffset + 0.5f), mDefaultToRefreshingScrollingDuration);
    }

    private void scrollDefaultToLoadingMore() {
        mAutoScroller.autoScroll(-(int) (mLoadMoreTriggerOffset + 0.5f), mDefaultToLoadingMoreScrollingDuration);
    }

    private void scrollSwipingToRefreshToDefault() {
        mAutoScroller.autoScroll(-mHeaderOffset, mSwipingToRefreshToDefaultScrollingDuration);
    }

    private void scrollSwipingToLoadMoreToDefault() {
        mAutoScroller.autoScroll(-mFooterOffset, mSwipingToLoadMoreToDefaultScrollingDuration);
    }

    private void scrollReleaseToRefreshToRefreshing() {
        mAutoScroller.autoScroll(mHeaderHeight - mHeaderOffset, mReleaseToRefreshToRefreshingScrollingDuration);
    }

    private void scrollReleaseToLoadMoreToLoadingMore() {
        mAutoScroller.autoScroll(-mFooterOffset - mFooterHeight, mReleaseToLoadMoreToLoadingMoreScrollingDuration);
    }

    private void scrollRefreshingToDefault() {
        mAutoScroller.autoScroll(-mHeaderOffset, mRefreshCompleteToDefaultScrollingDuration);
    }

    private void scrollLoadingMoreToDefault() {
        mAutoScroller.autoScroll(-mFooterOffset, mLoadMoreCompleteToDefaultScrollingDuration);
    }

    /**
     * 自动滚动完成时操作{@link AutoScroller#finish()}
     */
    private void autoScrollFinished() {
        int mLastStatus = mStatus;

        if (STATUS.isReleaseToRefresh(mLastStatus)) {
            setStatus(STATUS.STATUS_REFRESHING);
            fixCurrentStatusLayout();
            mRefreshCallback.onRefresh();

        } else if (STATUS.isRefreshing(mLastStatus)) {
            setStatus(STATUS.STATUS_DEFAULT);
            fixCurrentStatusLayout();
            mRefreshCallback.onReset();

        } else if (STATUS.isSwipingToRefresh(mLastStatus)) {
            if (mAutoLoading) {
                mAutoLoading = false;
                setStatus(STATUS.STATUS_REFRESHING);
                fixCurrentStatusLayout();
                mRefreshCallback.onRefresh();
            } else {
                setStatus(STATUS.STATUS_DEFAULT);
                fixCurrentStatusLayout();
                mRefreshCallback.onReset();
            }
        } else if (STATUS.isStatusDefault(mLastStatus)) {

        } else if (STATUS.isSwipingToLoadMore(mLastStatus)) {
            if (mAutoLoading) {
                mAutoLoading = false;
                setStatus(STATUS.STATUS_LOADING_MORE);
                fixCurrentStatusLayout();
                mLoadMoreCallback.onLoadMore();
            } else {
                setStatus(STATUS.STATUS_DEFAULT);
                fixCurrentStatusLayout();
                mLoadMoreCallback.onReset();
            }
        } else if (STATUS.isLoadingMore(mLastStatus)) {
            setStatus(STATUS.STATUS_DEFAULT);
            fixCurrentStatusLayout();
            mLoadMoreCallback.onReset();
        } else if (STATUS.isReleaseToLoadMore(mLastStatus)) {
            setStatus(STATUS.STATUS_LOADING_MORE);
            fixCurrentStatusLayout();
            mLoadMoreCallback.onLoadMore();
        } else {
            throw new IllegalStateException("illegal state: " + STATUS.getStatus(mLastStatus));
        }
    }

    /**
     * 验证是否可以下拉刷新
     *
     * @return 是否可以下拉刷新
     */
    private boolean onCheckCanRefresh() {
        return mRefreshEnabled && !canChildScrollUp() && mHasHeaderView && mRefreshTriggerOffset > 0;
    }

    /**
     * 验证是否可以加载更多
     *
     * @return 是否可以加载更多
     */
    private boolean onCheckCanLoadMore() {
        return mLoadMoreEnabled && !canChildScrollDown() && mHasFooterView && mLoadMoreTriggerOffset > 0;
    }

    private float getMotionEventY(MotionEvent event, int activePointerId) {
        final int index = MotionEventCompat.findPointerIndex(event, activePointerId);
        if (index < 0) {
            return INVALID_COORDINATE;
        }
        return MotionEventCompat.getY(event, index);
    }

    private float getMotionEventX(MotionEvent event, int activePointId) {
        final int index = MotionEventCompat.findPointerIndex(event, activePointId);
        if (index < 0) {
            return INVALID_COORDINATE;
        }
        return MotionEventCompat.getX(event, index);
    }

    /**
     * 刷新时间回调
     */
    abstract class RefreshCallback implements SwipeTrigger, SwipeRefreshTrigger {
    }

    /**
     * 加载更多回调
     */
    abstract class LoadMoreCallback implements SwipeTrigger, SwipeLoadMoreTrigger {
    }

    RefreshCallback mRefreshCallback = new RefreshCallback() {
        @Override
        public void onPrepare() {
            if (mHeaderView != null && mHeaderView instanceof SwipeTrigger && STATUS.isStatusDefault(mStatus)) {
                mHeaderView.setVisibility(VISIBLE);
                ((SwipeTrigger) mHeaderView).onPrepare();
            }
        }

        @Override
        public void onMove(int y, boolean isComplete, boolean automatic) {
            if (mHeaderView != null && mHeaderView instanceof SwipeTrigger && STATUS.isRefreshStatus(mStatus)) {
                if (mHeaderView.getVisibility() != VISIBLE) {
                    mHeaderView.setVisibility(VISIBLE);
                }
                ((SwipeTrigger) mHeaderView).onMove(y, isComplete, automatic);
            }
        }

        @Override
        public void onRelease() {
            if (mHeaderView != null && mHeaderView instanceof SwipeTrigger && STATUS.isReleaseToRefresh(mStatus)) {
                ((SwipeTrigger) mHeaderView).onRelease();
            }
        }

        @Override
        public void onRefresh() {
            if (mHeaderView != null && STATUS.isRefreshing(mStatus)) {
                if (mHeaderView instanceof SwipeRefreshTrigger) {
                    ((SwipeRefreshTrigger) mHeaderView).onRefresh();
                }
                if (mRefreshListener != null) {
                    mRefreshListener.onRefresh();
                }
            }
        }

        @Override
        public void onComplete() {
            if (mHeaderView != null && mHeaderView instanceof SwipeTrigger) {
                ((SwipeTrigger) mHeaderView).onComplete();
            }
        }

        @Override
        public void onReset() {
            if (mHeaderView != null && mHeaderView instanceof SwipeTrigger && STATUS.isStatusDefault(mStatus)) {
                ((SwipeTrigger) mHeaderView).onReset();
                mHeaderView.setVisibility(GONE);
            }
        }
    };

    LoadMoreCallback mLoadMoreCallback = new LoadMoreCallback() {

        @Override
        public void onPrepare() {
            if (mFooterView != null && mFooterView instanceof SwipeTrigger && STATUS.isStatusDefault(mStatus)) {
                mFooterView.setVisibility(VISIBLE);
                ((SwipeTrigger) mFooterView).onPrepare();
            }
        }

        @Override
        public void onMove(int y, boolean isComplete, boolean automatic) {
            if (mFooterView != null && mFooterView instanceof SwipeTrigger && STATUS.isLoadMoreStatus(mStatus)) {
                if (mFooterView.getVisibility() != VISIBLE) {
                    mFooterView.setVisibility(VISIBLE);
                }
                ((SwipeTrigger) mFooterView).onMove(y, isComplete, automatic);
            }
        }

        @Override
        public void onRelease() {
            if (mFooterView != null && mFooterView instanceof SwipeTrigger && STATUS.isReleaseToLoadMore(mStatus)) {
                ((SwipeTrigger) mFooterView).onRelease();
            }
        }

        @Override
        public void onLoadMore() {
            if (mFooterView != null && STATUS.isLoadingMore(mStatus)) {
                if (mFooterView instanceof SwipeLoadMoreTrigger) {
                    ((SwipeLoadMoreTrigger) mFooterView).onLoadMore();
                }
                if (mLoadMoreListener != null) {
                    mLoadMoreListener.onLoadMore();
                }
            }
        }

        @Override
        public void onComplete() {
            if (mFooterView != null && mFooterView instanceof SwipeTrigger) {
                ((SwipeTrigger) mFooterView).onComplete();
            }
        }

        @Override
        public void onReset() {
            if (mFooterView != null && mFooterView instanceof SwipeTrigger && STATUS.isStatusDefault(mStatus)) {
                ((SwipeTrigger) mFooterView).onReset();
                mFooterView.setVisibility(GONE);
            }
        }
    };

    /**
     * 自动滚动
     */
    private class AutoScroller implements Runnable {

        private Scroller mScroller;

        private int mmLastY;

        private boolean mRunning = false;

        private boolean mAbort = false;

        public AutoScroller() {
            mScroller = new Scroller(getContext());
        }

        @Override
        public void run() {
            boolean finish = !mScroller.computeScrollOffset() || mScroller.isFinished();
            int currY = mScroller.getCurrY();
            int yDiff = currY - mmLastY;
            if (finish) {
                finish();
            } else {
                mmLastY = currY;
                SwipeToLoadLayout.this.autoScroll(yDiff);
                post(this);
            }
        }

        private void finish() {
            mmLastY = 0;
            mRunning = false;
            removeCallbacks(this);
            // 用户终止滚动，则不执行
            if (!mAbort) {
                autoScrollFinished();
            }
        }

        /**
         * 用户终止滚动
         */
        public void abortIfRunning() {
            if (mRunning) {
                if (!mScroller.isFinished()) {
                    mAbort = true;
                    mScroller.forceFinished(true);
                }
                finish();
                mAbort = false;
            }
        }

        /**
         * 开始自动滚动
         *
         * @param yScrolled Y轴位移,此位移不是最终Y轴位移。可以当做{@link #updateScroll(float yScrolled)}方法参数
         * @param duration 滚动执行时间
         */
        private void autoScroll(int yScrolled, int duration) {
            removeCallbacks(this);
            mmLastY = 0;
            if (!mScroller.isFinished()) {
                mScroller.forceFinished(true);
            }
            mScroller.startScroll(0, 0, 0, yScrolled, duration);
            post(this);
            mRunning = true;
        }
    }

    /**
     * 设置下拉加载框架的状态
     *
     * @param status 当前状态({@link STATUS})
     */
    private void setStatus(int status) {
        mStatus = status;
    }

    /**
     * 刷新框架内部状态类<br>
     */
    private final static class STATUS {

        /********************************  各种刷新框架状态  ********************************/
        private static final int STATUS_REFRESH_RETURNING = -4;
        private static final int STATUS_REFRESHING = -3;
        private static final int STATUS_RELEASE_TO_REFRESH = -2;
        private static final int STATUS_SWIPING_TO_REFRESH = -1;
        private static final int STATUS_DEFAULT = 0;
        private static final int STATUS_SWIPING_TO_LOAD_MORE = 1;
        private static final int STATUS_RELEASE_TO_LOAD_MORE = 2;
        private static final int STATUS_LOADING_MORE = 3;
        private static final int STATUS_LOAD_MORE_RETURNING = 4;

        private static boolean isRefreshing(final int status) {
            return status == STATUS.STATUS_REFRESHING;
        }

        private static boolean isLoadingMore(final int status) {
            return status == STATUS.STATUS_LOADING_MORE;
        }

        private static boolean isReleaseToRefresh(final int status) {
            return status == STATUS.STATUS_RELEASE_TO_REFRESH;
        }

        private static boolean isReleaseToLoadMore(final int status) {
            return status == STATUS.STATUS_RELEASE_TO_LOAD_MORE;
        }

        private static boolean isSwipingToRefresh(final int status) {
            return status == STATUS.STATUS_SWIPING_TO_REFRESH;
        }

        private static boolean isSwipingToLoadMore(final int status) {
            return status == STATUS.STATUS_SWIPING_TO_LOAD_MORE;
        }

        private static boolean isRefreshStatus(final int status) {
            return status < STATUS.STATUS_DEFAULT;
        }

        public static boolean isLoadMoreStatus(final int status) {
            return status > STATUS.STATUS_DEFAULT;
        }

        private static boolean isStatusDefault(final int status) {
            return status == STATUS.STATUS_DEFAULT;
        }

        private static String getStatus(int status) {
            final String statusInfo;
            switch (status) {
                case STATUS_REFRESH_RETURNING:
                    statusInfo = "status_refresh_returning";
                    break;
                case STATUS_REFRESHING:
                    statusInfo = "status_refreshing";
                    break;
                case STATUS_RELEASE_TO_REFRESH:
                    statusInfo = "status_release_to_refresh";
                    break;
                case STATUS_SWIPING_TO_REFRESH:
                    statusInfo = "status_swiping_to_refresh";
                    break;
                case STATUS_DEFAULT:
                    statusInfo = "status_default";
                    break;
                case STATUS_SWIPING_TO_LOAD_MORE:
                    statusInfo = "status_swiping_to_load_more";
                    break;
                case STATUS_RELEASE_TO_LOAD_MORE:
                    statusInfo = "status_release_to_load_more";
                    break;
                case STATUS_LOADING_MORE:
                    statusInfo = "status_loading_more";
                    break;
                case STATUS_LOAD_MORE_RETURNING:
                    statusInfo = "status_load_more_returning";
                    break;
                default:
                    statusInfo = "status_illegal!";
                    break;
            }
            return statusInfo;
        }
    }
}
