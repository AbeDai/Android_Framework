package example.abe.com.android.activity.commonview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import example.abe.com.android.R;
import example.abe.com.framework.util.ToastUtil;

public class TextCommonView extends CommonView {

    protected TextView mTv;

    public TextCommonView(Context context) {
        super(context);
    }

    public TextCommonView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextCommonView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void clickBottomAction(){
        ToastUtil.makeText("点击-\"显示全文\"");
    }

    @Override
    public void initInnerContent(Context context, FrameLayout innerDecor){
        View view = LayoutInflater.from(context).inflate(R.layout.view_common_text, innerDecor, false);
        innerDecor.addView(view);

        mTv = (TextView) view.findViewById(R.id.view_common_tv_text);
        mTv.setText("家庭经济并不富裕我从小就养成了节俭的习惯，因为体验了贫困所带来的不便，所以我一直渴望富裕的生活。但是我知道要想走出贫困靠天靠地都只是徒劳，只有靠自己的努力才能创造出属于自己的一片天空，所以我坚信天道酬勤。");
    }

    /**
     * 获取标题
     */
    public String getTitle(){
        return "自我介绍";
    }

    /**
     * 获取底部内容
     */
    public String getBottomContent(){
        return "显示全文";
    }
}
