package example.abe.com.android.activity.commonview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import example.abe.com.android.R;
import example.abe.com.framework.util.ToastUtil;

public class ImageCommonView extends CommonView {

    protected ImageView mIv1;
    protected ImageView mIv2;
    protected ImageView mIv3;
    protected ImageView mIv4;

    public ImageCommonView(Context context) {
        super(context);
    }

    public ImageCommonView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageCommonView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void clickBottomAction(){
        ToastUtil.makeText("点击-\"显示更多\"");
    }

    @Override
    public void initInnerContent(Context context, FrameLayout innerDecor){
        View view = LayoutInflater.from(context).inflate(R.layout.view_common_image, innerDecor, false);
        innerDecor.addView(view);

        mIv1 = (ImageView) view.findViewById(R.id.view_common_image_iv_1);
        mIv2 = (ImageView) view.findViewById(R.id.view_common_image_iv_2);
        mIv3 = (ImageView) view.findViewById(R.id.view_common_image_iv_3);
        mIv4 = (ImageView) view.findViewById(R.id.view_common_image_iv_4);

        mIv1.setImageResource(R.drawable.ic_audiotrack);
        mIv2.setImageResource(R.drawable.ic_cast_dark);
        mIv3.setImageResource(R.drawable.ic_cast_grey);
        mIv4.setImageResource(R.drawable.ic_cast_light);
    }

    /**
     * 获取标题
     */
    public String getTitle(){
        return "标题";
    }

    /**
     * 获取底部内容
     */
    public String getBottomContent(){
        return "显示更多";
    }
}
