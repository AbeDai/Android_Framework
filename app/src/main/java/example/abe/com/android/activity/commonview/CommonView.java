package example.abe.com.android.activity.commonview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import example.abe.com.android.R;

/**
 * Created by abe on 17/5/1.
 */
public abstract class CommonView extends FrameLayout implements View.OnClickListener {

    public FrameLayout mFlInner;
    public TextView mTvTitle;
    public TextView mTvShowMore;
    private View mRoot;

    public CommonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public CommonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CommonView(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context){
        mRoot = LayoutInflater.from(context).inflate(R.layout.view_common, this, false);
        addView(mRoot);

        mFlInner = (FrameLayout) mRoot.findViewById(R.id.common_fl_inner);
        mTvTitle = (TextView) mRoot.findViewById(R.id.common_view_tv_title);
        mTvShowMore = (TextView) mRoot.findViewById(R.id.common_view_tv_show_more);
        mTvShowMore.setOnClickListener(this);

        //初始化嵌入内容
        initInnerContent(context, mFlInner);

        //设置公共视图内容
        mTvTitle.setText(getTitle());
        mTvShowMore.setText(getBottomContent());
    }

    @Override
    public void onClick(View v){
        clickBottomAction();
    }

    /**
     * 点击底部按钮操作
     */
    public abstract void clickBottomAction();

    /**
     * 获取标题
     */
    public abstract String getTitle();

    /**
     * 获取底部内容
     */
    public abstract String getBottomContent();

    /**
     * 初始化嵌入视图
     * @param context 下上文
     * @param innerDecor 嵌入容器
     */
    public abstract void initInnerContent(Context context, FrameLayout innerDecor);

}
