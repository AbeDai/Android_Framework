package example.abe.com.android_framework.activity.banner;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2016/8/4.
 */
public class AutoPlayViewPager extends ViewPager {

    private static final int SHOW_TIME = 3 * 1000;

    public AutoPlayViewPager(Context context) {
        super(context);
    }

    public AutoPlayViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void start() {
        stop();
        postDelayed(player, SHOW_TIME);
    }

    public void stop() {
        removeCallbacks(player);
    }

    private Runnable player = new Runnable() {
        @Override
        public void run() {
            play();
        }
    };

    private void play() {
        PagerAdapter pagerAdapter = getAdapter();
        if (pagerAdapter != null) {
            int count = pagerAdapter.getCount();
            int currentItem = getCurrentItem();
            currentItem++;
            if (currentItem >= count){
                currentItem = 0;
            }
            setCurrentItem(currentItem);
        }
        start();
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
                if (state == SCROLL_STATE_IDLE)
                    start();
                else if (state == SCROLL_STATE_DRAGGING)
                    stop();
            }
        });
    }
}
