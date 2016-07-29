package example.abe.com.android_framework.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import example.abe.com.android_framework.R;
import example.abe.com.framework.Annotation.ContentView;
import example.abe.com.framework.Annotation.ViewInject;

@ContentView(id = R.layout.activity_card_view)
public class CardViewActivity extends BaseActivity {

    @ViewInject(id = R.id.act_card_view_root)
    ViewGroup root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root.addView(getCardView());
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
