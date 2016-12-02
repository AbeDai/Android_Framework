package example.abe.com.android.activity.viewstub;

import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import com.example.BindView;
import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.util.ToastUtil;

public class ViewStubActivity extends BaseActivity {

    @BindView(R.id.act_view_stub_view_stub)
    protected ViewStub mViewStub;

    @BindView(R.id.act_view_stub_btn_show)
    protected Button mBtnShow;

    @BindView(R.id.act_view_stub_btn_hide)
    protected Button mBtnHide;

    @BindView(R.id.act_view_stub_btn_change_start_count)
    protected Button mBtnChangeStartCount;

    protected LinearLayout mLlStart;

    @Override
    public int getLayoutID() {
        return R.layout.activity_view_stub;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
        mViewStub.setOnInflateListener(new ViewStub.OnInflateListener() {
            @Override
            public void onInflate(ViewStub stub, View inflated) {
                ToastUtil.makeText("ViewStub is loaded!");
                mLlStart = (LinearLayout)ViewStubActivity.this.findViewById(R.id.act_view_stub_id_view_stub);
            }
        });
    }

    @OnClick({R.id.act_view_stub_btn_show, R.id.act_view_stub_btn_hide, R.id.act_view_stub_btn_change_start_count})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.act_view_stub_btn_show:
                try {
                    LinearLayout layout = (LinearLayout) mViewStub.inflate();
                    RatingBar bar = (RatingBar) layout.findViewById(R.id.include_start_bar);
                    bar.setNumStars(4);
                } catch (Exception e) {
                    mViewStub.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.act_view_stub_btn_hide:
                mViewStub.setVisibility(View.GONE);
                break;

            case R.id.act_view_stub_btn_change_start_count:
                RatingBar rBar = (RatingBar) mLlStart.findViewById(R.id.include_start_bar);
                float numStart = rBar.getRating();
                numStart++;
                if (numStart > 4) {
                    numStart = 0;
                }
                rBar.setRating(numStart);
                break;
        }

    }
}
