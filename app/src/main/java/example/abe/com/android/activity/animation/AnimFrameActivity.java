package example.abe.com.android.activity.animation;

import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;

import com.example.BindView;
import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;

public class AnimFrameActivity extends BaseActivity {

    @BindView(R.id.act_anim_frame_iv_show)
    protected ImageView mIvShow;

    private AnimationDrawable frameAnim;

    @Override
    public int getLayoutID() {
        return R.layout.activity_anim_frame;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
    }

    @OnClick({R.id.act_anim_frame_btn_code, R.id.act_anim_frame_btn_xml})
    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.act_anim_frame_btn_xml:
                doAnimXML();
                break;
            case R.id.act_anim_frame_btn_code:
                doAnimCode();
                break;
        }
    }

    /**
     * Code实现帧动画
     */
    private void doAnimCode() {
        frameAnim = new AnimationDrawable();
        frameAnim.addFrame(getResources().getDrawable(R.drawable.img_frame_anim_0), 50);
        frameAnim.addFrame(getResources().getDrawable(R.drawable.img_frame_anim_1), 50);
        frameAnim.addFrame(getResources().getDrawable(R.drawable.img_frame_anim_2), 50);
        frameAnim.addFrame(getResources().getDrawable(R.drawable.img_frame_anim_3), 50);
        frameAnim.addFrame(getResources().getDrawable(R.drawable.img_frame_anim_4), 50);
        frameAnim.addFrame(getResources().getDrawable(R.drawable.img_frame_anim_5), 50);
        frameAnim.addFrame(getResources().getDrawable(R.drawable.img_frame_anim_6), 50);
        frameAnim.addFrame(getResources().getDrawable(R.drawable.img_frame_anim_7), 50);
        frameAnim.addFrame(getResources().getDrawable(R.drawable.img_frame_anim_8), 50);
        frameAnim.addFrame(getResources().getDrawable(R.drawable.img_frame_anim_9), 50);
        frameAnim.addFrame(getResources().getDrawable(R.drawable.img_frame_anim_10), 50);
        frameAnim.addFrame(getResources().getDrawable(R.drawable.img_frame_anim_11), 50);
        frameAnim.addFrame(getResources().getDrawable(R.drawable.img_frame_anim_12), 50);
        frameAnim.addFrame(getResources().getDrawable(R.drawable.img_frame_anim_13), 50);
        frameAnim.addFrame(getResources().getDrawable(R.drawable.img_frame_anim_14), 50);
        frameAnim.addFrame(getResources().getDrawable(R.drawable.img_frame_anim_15), 50);
        frameAnim.addFrame(getResources().getDrawable(R.drawable.img_frame_anim_16), 50);
        frameAnim.addFrame(getResources().getDrawable(R.drawable.img_frame_anim_17), 50);
        frameAnim.addFrame(getResources().getDrawable(R.drawable.img_frame_anim_18), 50);
        frameAnim.addFrame(getResources().getDrawable(R.drawable.img_frame_anim_19), 50);
        frameAnim.addFrame(getResources().getDrawable(R.drawable.img_frame_anim_20), 50);
        frameAnim.addFrame(getResources().getDrawable(R.drawable.img_frame_anim_21), 50);
        frameAnim.addFrame(getResources().getDrawable(R.drawable.img_frame_anim_22), 50);
        frameAnim.addFrame(getResources().getDrawable(R.drawable.img_frame_anim_23), 50);
        frameAnim.addFrame(getResources().getDrawable(R.drawable.img_frame_anim_24), 50);
        frameAnim.setOneShot(true);
        mIvShow.setImageDrawable(frameAnim);
        frameAnim.start();
    }

    /**
     * Xml实现帧动画
     */
    private void doAnimXML() {
        frameAnim = (AnimationDrawable) getResources().getDrawable(R.drawable.anim_frame_bullet);
        frameAnim.setOneShot(true);
        mIvShow.setImageDrawable(frameAnim);
        frameAnim.start();
    }
}

