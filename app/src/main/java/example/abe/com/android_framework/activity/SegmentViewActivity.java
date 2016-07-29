package example.abe.com.android_framework.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import example.abe.com.android_framework.R;
import example.abe.com.framework.Annotation.ContentView;
import example.abe.com.framework.util.ABLog;
import example.abe.com.framework.view.ABSegmentView;

@ContentView(id = R.layout.activity_segment_view)
public class SegmentViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout ll = (LinearLayout)findViewById(R.id.ll);

        //CODE默认方式
        ABSegmentView view1 = new ABSegmentView(this);
        view1.setOnSegmentViewClickListener(new ABSegmentView.onSegmentViewClickListener() {
            @Override
            public void onSegmentViewClick(ABSegmentView v, ABSegmentView.SegmentPosition position) {
                ABLog.v("CODE默认方式: " + position.toString());
            }
        });
        ll.addView(view1, new LinearLayout.LayoutParams(300, 100, 0));

        //CODE自定义方式
        ABSegmentView view2 = new ABSegmentView(this);
        view2.setTextColorSelector(R.drawable.seg_text_btn_selector);
        view2.setSegmentBackgroud(R.drawable.seg_left_bg_selector, ABSegmentView.SegmentPosition.LEFT);
        view2.setSegmentBackgroud(R.drawable.seg_right_bg_selector, ABSegmentView.SegmentPosition.RIGHT);
        view2.setSegmentText("所有车辆", ABSegmentView.SegmentPosition.LEFT);
        view2.setSegmentText("我的关注", ABSegmentView.SegmentPosition.RIGHT);
        view2.setSegmentTextSize(30);
        view2.setOnSegmentViewClickListener(new ABSegmentView.onSegmentViewClickListener() {
            @Override
            public void onSegmentViewClick(ABSegmentView v, ABSegmentView.SegmentPosition position) {
                ABLog.v("CODE自定义方式: " + position.toString());
            }
        });
        ll.addView(view2, new LinearLayout.LayoutParams(250, 100, 0));

        //XML默认
        ABSegmentView view3 = (ABSegmentView)findViewById(R.id.default_segment_view);
        view3.setOnSegmentViewClickListener(new ABSegmentView.onSegmentViewClickListener() {
            @Override
            public void onSegmentViewClick(ABSegmentView v, ABSegmentView.SegmentPosition position) {
                ABLog.v("XML默认: " + position.toString());
            }
        });

        //XML自定义
        ABSegmentView view4 = (ABSegmentView)findViewById(R.id.custom_segment_view);
        view4.setOnSegmentViewClickListener(new ABSegmentView.onSegmentViewClickListener() {
            @Override
            public void onSegmentViewClick(ABSegmentView v, ABSegmentView.SegmentPosition position) {
                ABLog.v("XML自定义: " + position.toString());
            }
        });
    }
}
