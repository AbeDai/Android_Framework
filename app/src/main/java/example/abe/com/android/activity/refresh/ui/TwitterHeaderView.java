package example.abe.com.android.activity.refresh.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.BindView;

import example.abe.com.android.R;
import example.abe.com.framework.refresh.SwipeRefreshHeaderLayout;
import example.abe.com.framework.util.DensityUtil;
import example.abe.com.framework.viewinject.ViewInjectUtils;

/**
 * 自定义刷新头部控件
 */
public class TwitterHeaderView extends SwipeRefreshHeaderLayout {

    @BindView(R.id.layout_twitter_header_view_iv_arrow)
    protected ImageView ivArrow;

    @BindView(R.id.layout_twitter_header_view_iv_success)
    protected ImageView ivSuccess;

    @BindView(R.id.layout_twitter_header_view_progress_bar)
    protected ProgressBar progressBar;

    @BindView(R.id.layout_twitter_header_view_tv_refresh)
    protected TextView tvRefresh;

    private int mHeaderHeight;

    private Animation rotateUp;

    private Animation rotateDown;

    private boolean rotated = false;

    public TwitterHeaderView(Context context) {
        this(context, null);
    }

    public TwitterHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TwitterHeaderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_twitter_header_view, this, true);
        ViewInjectUtils.inject(this);
        mHeaderHeight = DensityUtil.dip2px(context, 80);
        rotateUp = AnimationUtils.loadAnimation(context, R.anim.rotate_up);
        rotateDown = AnimationUtils.loadAnimation(context, R.anim.rotate_down);
    }

    @Override
    public void onRefresh() {
        ivSuccess.setVisibility(GONE);
        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(VISIBLE);
        tvRefresh.setText("刷新中");
    }

    @Override
    public void onPrepare() {
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            ivArrow.setVisibility(VISIBLE);
            progressBar.setVisibility(GONE);
            ivSuccess.setVisibility(GONE);
            if (y > mHeaderHeight) {
                tvRefresh.setText("释放刷新");
                if (!rotated) {
                    ivArrow.clearAnimation();
                    ivArrow.startAnimation(rotateUp);
                    rotated = true;
                }
            } else if (y < mHeaderHeight) {
                if (rotated) {
                    ivArrow.clearAnimation();
                    ivArrow.startAnimation(rotateDown);
                    rotated = false;
                }
                tvRefresh.setText("下拉刷新");
            }
        }
    }

    @Override
    public void onRelease() {
    }

    @Override
    public void onComplete() {
        rotated = false;
        ivSuccess.setVisibility(VISIBLE);
        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(GONE);
        tvRefresh.setText("完成");
    }

    @Override
    public void onReset() {
        rotated = false;
        ivSuccess.setVisibility(GONE);
        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(GONE);
    }

}
