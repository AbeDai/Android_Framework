package example.abe.com.android.activity.materialdesign.cardview;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.BindView;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;

public class CardViewActivity extends BaseActivity {

    @BindView(R.id.act_card_view_root)
    protected ViewGroup root;

    @Override
    public int getLayoutID(){
        return R.layout.activity_card_view;
    }

    @Override
    public void initData(){

    }

    @Override
    public void initView(){
        View content = getCardView();
        root.addView(content);
    }

    private View getCardView(){
        //内容
        TextView textView = new TextView(this);
        textView.setText("代码添加CardView");
        textView.setTextColor(Color.WHITE);
        textView.setLayoutParams(new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        //CardView添加
        CardView cardView = new CardView(this);
        cardView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        cardView.setCardBackgroundColor(Color.RED);
        cardView.setContentPadding(100, 100, 100, 0);
        cardView.addView(textView);

        return cardView;
    }

}
