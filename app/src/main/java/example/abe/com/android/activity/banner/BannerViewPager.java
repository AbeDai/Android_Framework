package example.abe.com.android.activity.banner;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2016/8/4.
 */
public class BannerViewPager extends ViewPager {

    public enum Direction {
        RIGHT,
        LEFT,
    }

    public static final int DEFAULT_SHOW_TIME = 3 * 1000;
    private int mShowTime;
    private Direction mDirection;

    public BannerViewPager(Context context) {
        super(context);
        initData();
    }

    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    public void setShowTime(int showTime) {
        mShowTime = showTime;
    }

    public int getShowTime() {
        return mShowTime;
    }

    public void setDirection(Direction direction) {
        mDirection = direction;
    }

    public Direction getDirection() {
        return mDirection;
    }

    public void start() {
        stop();
        postDelayed(player, mShowTime);
    }

    public void stop() {
        removeCallbacks(player);
    }

    private void gotoFirst() {
        PagerAdapter pagerAdapter = getAdapter();
        if (pagerAdapter != null) {
            int count = pagerAdapter.getCount();
            int currentItem = getCurrentItem();
            if (currentItem == count - 1) {
                currentItem = 1;
            }
            if (currentItem == 0) {
                currentItem = count - 1;
            }
            setCurrentItem(currentItem, false);
        }
    }

    private void initData() {
        mShowTime = DEFAULT_SHOW_TIME;
        mDirection = Direction.LEFT;
    }

    private Runnable player = new Runnable() {
        @Override
        public void run() {
            play(mDirection);
        }
    };

    private void play(Direction direction) {
        switch (direction) {
            case LEFT:// 如是向左滚动的，currentItem+1播放下一个
                moveToLeft();
                break;
            case RIGHT:// 如是向右滚动的，currentItem-1播放上一个
                moveToRight();
                break;
        }
    }

    // 如是向左滚动的，currentItem+1播放下一个
    private void moveToLeft() {
        PagerAdapter pagerAdapter = getAdapter();
        if (pagerAdapter != null) {
            int count = pagerAdapter.getCount();
            int currentItem = getCurrentItem();
            currentItem++;
            if (currentItem >= count)
                currentItem = 0;
            setCurrentItem(currentItem);
        }
        start();
    }

    // 如是向右滚动的，currentItem-1播放上一个
    private void moveToRight() {
        PagerAdapter pagerAdapter = getAdapter();
        if (pagerAdapter != null) {
            int count = pagerAdapter.getCount();
            int currentItem = getCurrentItem();
            currentItem--;
            if (currentItem < 0)
                currentItem = count - 1;
            setCurrentItem(currentItem);
        }
        start();
    }

    /**
     * 最前一个和最后一个为起到衔接作用    4->1->2->3->4->1
     */
    private boolean isLast() {
        PagerAdapter pagerAdapter = getAdapter();
        if (pagerAdapter != null) {
            int count = pagerAdapter.getCount();
            int currentItem = getCurrentItem();
            if (currentItem == count - 1 || currentItem == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == SCROLL_STATE_IDLE) {
                    if (isLast()) {
                        gotoFirst();
                    }
                    start();
                } else if (state == SCROLL_STATE_DRAGGING) {
                    stop();
                }
            }
        });
    }
}
