package example.abe.com.android.activity.refresh.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.BindView;

import example.abe.com.android.R;
import example.abe.com.framework.refresh.SwipeLoadMoreFooterLayout;
import example.abe.com.framework.util.DensityUtil;
import example.abe.com.framework.viewinject.ViewInjectUtils;

/**
 * 自定义刷新头部控件
 */
public class TwitterFooterView extends SwipeLoadMoreFooterLayout {

    @BindView(R.id.layout_twitter_footer_view_tv_load_more)
    protected TextView tvLoadMore;

    @BindView(R.id.layout_twitter_footer_view_iv_success)
    protected ImageView ivSuccess;

    @BindView(R.id.layout_twitter_footer_view_progressbar)
    protected ProgressBar progressBar;

    private int mFooterHeight;

    public TwitterFooterView(Context context) {
        this(context, null);
    }

    public TwitterFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TwitterFooterView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_twitter_footer_view, this, true);
        ViewInjectUtils.inject(this);
        mFooterHeight = DensityUtil.dip2px(context, 60);
    }

    @Override
    public void onPrepare() {
        ivSuccess.setVisibility(GONE);
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            ivSuccess.setVisibility(GONE);
            progressBar.setVisibility(GONE);
            if (-y >= mFooterHeight) {
                tvLoadMore.setText("释放加载");
            } else {
                tvLoadMore.setText("上滑加载");
            }
        }
    }

    @Override
    public void onLoadMore() {
        tvLoadMore.setText("加载中");
        progressBar.setVisibility(VISIBLE);
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {
        progressBar.setVisibility(GONE);
        ivSuccess.setVisibility(VISIBLE);
    }

    @Override
    public void onReset() {
        ivSuccess.setVisibility(GONE);
    }

}
