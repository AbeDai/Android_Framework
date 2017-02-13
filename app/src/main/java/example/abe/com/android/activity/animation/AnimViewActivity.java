package example.abe.com.android.activity.animation;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;

import com.example.BindView;
import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;

public class AnimViewActivity extends BaseActivity {

    @BindView(R.id.act_anim_view_view_show)
    protected View mViewShow;

    @Override
    public int getLayoutID() {
        return R.layout.activity_anim_view;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
    }

    @OnClick({R.id.act_anim_view_btn_xml, R.id.act_anim_view_btn_code})
    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.act_anim_view_btn_xml:
                doAnimXML();
                break;
            case R.id.act_anim_view_btn_code:
                doAnimCode();
                break;
        }
    }

    /**
     * Xml添加视图动画
     */
    private void doAnimXML() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.hyperspace_jump);
        mViewShow.startAnimation(anim);
    }

    /**k
     * Code添加视图动画
     */
    private void doAnimCode() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1F, 0.5F);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, 2F, 1F, 4F, 0F, 1F);

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animationSet.setDuration(1000);

        mViewShow.startAnimation(animationSet);
    }
}
