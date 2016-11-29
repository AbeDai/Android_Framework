package example.abe.com.android.activity.span;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.example.BindView;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.util.ToastUtil;

public class SpannableStringActivity extends BaseActivity {

    @BindView(R.id.act_spannable_string_tv)
    protected TextView mTv;

    @Override
    public int getLayoutID(){
        return R.layout.activity_spannable_string;
    }

    @Override
    public void initData(){
    }

    @Override
    public void initView() {
        mTv.setText(getSpannableString());
    }

    public SpannableString getSpannableString() {
        //ForegroundColorSpan 前景色
        SpannableString spannableString = new SpannableString("设置文字的前景色为淡蓝色");
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#0099EE"));
        spannableString.setSpan(colorSpan, 9, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

//        //BackgroundColorSpan 背景色
//        SpannableString spannableString = new SpannableString("设置文字的背景色为淡绿色");
//        BackgroundColorSpan colorSpan = new BackgroundColorSpan(Color.parseColor("#AC00FF30"));
//        spannableString.setSpan(colorSpan, 9, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

//        //RelativeSizeSpan 相对大小
//        SpannableString spannableString = new SpannableString("万丈高楼平地起");
//        RelativeSizeSpan sizeSpan01 = new RelativeSizeSpan(1.2f);
//        RelativeSizeSpan sizeSpan02 = new RelativeSizeSpan(1.4f);
//        RelativeSizeSpan sizeSpan03 = new RelativeSizeSpan(1.6f);
//        RelativeSizeSpan sizeSpan04 = new RelativeSizeSpan(1.8f);
//        RelativeSizeSpan sizeSpan05 = new RelativeSizeSpan(1.6f);
//        RelativeSizeSpan sizeSpan06 = new RelativeSizeSpan(1.4f);
//        RelativeSizeSpan sizeSpan07 = new RelativeSizeSpan(1.2f);
//        spannableString.setSpan(sizeSpan01, 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//        spannableString.setSpan(sizeSpan02, 1, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//        spannableString.setSpan(sizeSpan03, 2, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//        spannableString.setSpan(sizeSpan04, 3, 4, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//        spannableString.setSpan(sizeSpan05, 4, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//        spannableString.setSpan(sizeSpan06, 5, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//        spannableString.setSpan(sizeSpan07, 6, 7, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

//        //StrikethroughSpan 删除线
//        SpannableString spannableString = new SpannableString("为文字设置删除线");
//        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
//        spannableString.setSpan(strikethroughSpan, 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

//        //UnderlineSpan 下划线
//        SpannableString spannableString = new SpannableString("为文字设置下划线");
//        UnderlineSpan underlineSpan = new UnderlineSpan();
//        spannableString.setSpan(underlineSpan, 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

//        //SuperscriptSpan 上标
//        SpannableString spannableString = new SpannableString("为文字设置上标");
//        SuperscriptSpan superscriptSpan = new SuperscriptSpan();
//        spannableString.setSpan(superscriptSpan, 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

//        //SubscriptSpan 下标
//        SpannableString spannableString = new SpannableString("为文字设置下标");
//        SubscriptSpan subscriptSpan = new SubscriptSpan();
//        spannableString.setSpan(subscriptSpan, 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

//        //StyleSpan 文字设置风格（粗体、斜体）
//        SpannableString spannableString = new SpannableString("为文字设置粗体、斜体风格");
//        StyleSpan styleSpan_B  = new StyleSpan(Typeface.BOLD);
//        StyleSpan styleSpan_I  = new StyleSpan(Typeface.ITALIC);
//        spannableString.setSpan(styleSpan_B, 5, 7, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//        spannableString.setSpan(styleSpan_I, 8, 10, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

//        //ImageSpan 文本图片
//        SpannableString spannableString = new SpannableString("在文本中添加表情（表情）");
//        Drawable drawable = ResourceUtil.getDrawable(R.drawable.ic_launcher);
//        drawable.setBounds(0, 0, 42, 42);
//        ImageSpan imageSpan = new ImageSpan(drawable);
//        spannableString.setSpan(imageSpan, 6, 8, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

//        //ClickableSpan 可点击的文本
//        SpannableString spannableString = new SpannableString("为文字设置点击事件");
//        MyClickableSpan clickableSpan = new MyClickableSpan();
//        spannableString.setSpan(clickableSpan, 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//        mTv.setMovementMethod(LinkMovementMethod.getInstance());

//        //URLSpan 超链接文本
//        SpannableString spannableString = new SpannableString("为文字设置超链接");
//        URLSpan urlSpan = new URLSpan("http://www.jianshu.com/users/dbae9ac95c78");
//        spannableString.setSpan(urlSpan, 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//        mTv.setMovementMethod(LinkMovementMethod.getInstance());

        return spannableString;
    }

    class MyClickableSpan extends ClickableSpan {

        public MyClickableSpan() {
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            //取消下划线
            ds.setUnderlineText(false);
        }

        @Override
        public void onClick(View widget) {
            ToastUtil.makeText("点击文字");
        }
    }
}
