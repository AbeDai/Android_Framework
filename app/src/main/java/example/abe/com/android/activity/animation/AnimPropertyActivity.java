package example.abe.com.android.activity.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;

import com.example.BindView;
import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;

public class AnimPropertyActivity extends BaseActivity {

    @BindView(R.id.act_anim_view_show)
    protected View mViewShow;

    @Override
    public int getLayoutID() {
        return R.layout.activity_anim_property;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
    }

    @OnClick({R.id.act_anim_property_btn_attribute_set, R.id.act_anim_property_btn_single_object_animation, R.id.act_anim_property_btn_multi_object_animation, R.id.act_anim_property_btn_value_animation})
    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.act_anim_property_btn_single_object_animation:
                doSingleObjectAnim();
                break;
            case R.id.act_anim_property_btn_multi_object_animation:
                doMultiObjectAnim();
                break;
            case R.id.act_anim_property_btn_value_animation:
                doValueAnim();
                break;
            case R.id.act_anim_property_btn_attribute_set:
                doAttributeSet();
                break;
        }
    }

    /**
     * 动画集合
     */
    private void doAttributeSet() {
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(mViewShow, "x", 0f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(mViewShow, "y", 0f);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(mViewShow, "alpha", 0.5f, 1f);

        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(2000);
        animSet.setInterpolator(new LinearInterpolator());
        //三个动画执行顺序：anim1 -> anim2 -> anim3
        animSet.play(anim1).before(anim2);
        animSet.play(anim3).after(anim2);
        animSet.start();
    }

    /**
     * 单属性动画
     */
    private void doSingleObjectAnim() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mViewShow, "x", 200F);
        animator.setDuration(1000);
        animator.start();
    }

    /**
     * 多属性动画
     */
    private void doMultiObjectAnim() {
        //多个属性
        PropertyValuesHolder pvhAlpha = PropertyValuesHolder.ofFloat("alpha", 1f, 0.5f);
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("x", 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("y", 1f);

        //动画设置
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(mViewShow, pvhAlpha, pvhX, pvhY);
        animator.setDuration(1000);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
    }

    /**
     * 自定义ValueAnimation动画
     */
    private void doValueAnim() {
        ValueAnimator animator = new ValueAnimator();
        animator.setObjectValues(new PointF(0, 0));

        //模拟线性插值器
        animator.setInterpolator(new TimeInterpolator() {
            @Override
            public float getInterpolation(float input) {
                return input;
            }
        });

        //自定义抛物线
        animator.setEvaluator(new TypeEvaluator<PointF>() {
            @Override
            public PointF evaluate(float fraction, PointF startValue,
                                   PointF endValue) {
                PointF point = new PointF();
                point.x = 200 * fraction * 3;
                point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);
                return point;
            }
        });

        //监听动画更新，同时设给对应属性值
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF point = (PointF) animation.getAnimatedValue();
                mViewShow.setX(point.x);
                mViewShow.setY(point.y);
            }
        });

        animator.setDuration(1000).start();
    }

}
