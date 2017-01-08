package example.abe.com.android.activity.drawing.text;

import android.graphics.Color;
import android.widget.LinearLayout;

import com.example.BindView;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.util.DensityUtil;

public class TextActivity extends BaseActivity {

    @BindView(R.id.activity_text_ll_content)
    protected LinearLayout mLlContent;

    @Override
    public int getLayoutID(){
        return R.layout.activity_text;
    }

    @Override
    public void initData(){
    }

    @Override
    public void initView(){
        MyTextView myTextView1 = new MyTextView(this);
        myTextView1.setText("居中的文本");
        myTextView1.setTextSize(30);
        myTextView1.setTextAlign(MyTextView.TEXT_ALIGN_CENTER_HORIZONTAL | MyTextView.TEXT_ALIGN_CENTER_VERTICAL);
        myTextView1.setTextColor(Color.BLUE);
        myTextView1.setBackgroundColor(Color.RED);
        mLlContent.addView(myTextView1, LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(this, 150));

        MyTextView myTextView2 = new MyTextView(this);
        myTextView2.setText("居左的文本");
        myTextView2.setTextSize(25);
        myTextView2.setTextAlign(MyTextView.TEXT_ALIGN_CENTER_VERTICAL | MyTextView.TEXT_ALIGN_LEFT);
        myTextView2.setTextColor(Color.GREEN);
        myTextView2.setBackgroundColor(Color.YELLOW);
        mLlContent.addView(myTextView2, LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(this, 150));

        MyTextView myTextView3 = new MyTextView(this);
        myTextView3.setText("右下的文本");
        myTextView3.setTextSize(15);
        myTextView3.setTextAlign(MyTextView.TEXT_ALIGN_BOTTOM | MyTextView.TEXT_ALIGN_RIGHT);
        myTextView3.setTextColor(Color.RED);
        myTextView3.setBackgroundColor(Color.BLUE);
        mLlContent.addView(myTextView3, LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(this, 150));

        MyTextView myTextView4 = new MyTextView(this);
        myTextView4.setText("左下的文本");
        myTextView4.setTextSize(15);
        myTextView4.setTextAlign(MyTextView.TEXT_ALIGN_BOTTOM | MyTextView.TEXT_ALIGN_LEFT);
        myTextView4.setTextColor(Color.YELLOW);
        myTextView4.setBackgroundColor(Color.GREEN);
        mLlContent.addView(myTextView4, LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(this, 150));

        MyTextView myTextView5 = new MyTextView(this);
        myTextView5.setText("中下的文本");
        myTextView5.setTextSize(35);
        myTextView5.setTextAlign(MyTextView.TEXT_ALIGN_BOTTOM | MyTextView.TEXT_ALIGN_CENTER_HORIZONTAL);
        myTextView5.setTextColor(Color.GRAY);
        myTextView5.setBackgroundColor(Color.RED);
        mLlContent.addView(myTextView5, LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(this, 150));

        MyTextView myTextView6 = new MyTextView(this);
        myTextView6.setText("居右的文本");
        myTextView6.setTextSize(25);
        myTextView6.setTextAlign(MyTextView.TEXT_ALIGN_RIGHT | MyTextView.TEXT_ALIGN_CENTER_VERTICAL);
        myTextView6.setTextColor(Color.BLUE);
        myTextView6.setBackgroundColor(Color.YELLOW);
        mLlContent.addView(myTextView6, LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(this, 150));

        MyTextView myTextView7 = new MyTextView(this);
        myTextView7.setText("左上的文本");
        myTextView7.setTextSize(25);
        myTextView7.setTextAlign(MyTextView.TEXT_ALIGN_TOP | MyTextView.TEXT_ALIGN_LEFT);
        myTextView7.setTextColor(Color.GREEN);
        myTextView7.setBackgroundColor(Color.CYAN);
        mLlContent.addView(myTextView7, LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(this, 150));

        MyTextView myTextView8 = new MyTextView(this);
        myTextView8.setText("中上的文本");
        myTextView8.setTextSize(25);
        myTextView8.setTextAlign(MyTextView.TEXT_ALIGN_TOP | MyTextView.TEXT_ALIGN_CENTER_HORIZONTAL);
        myTextView8.setTextColor(Color.RED);
        myTextView8.setBackgroundColor(Color.GREEN);
        mLlContent.addView(myTextView8, LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(this, 150));

        MyTextView myTextView9 = new MyTextView(this);
        myTextView9.setText("右上的文本");
        myTextView9.setTextSize(25);
        myTextView9.setTextAlign(MyTextView.TEXT_ALIGN_TOP | MyTextView.TEXT_ALIGN_RIGHT);
        myTextView9.setTextColor(Color.YELLOW);
        myTextView9.setBackgroundColor(Color.BLUE);
        mLlContent.addView(myTextView9, LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(this, 150));
    }
}
