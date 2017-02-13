package example.abe.com.android.activity.animation;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.BindView;
import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;

public class AnimActivity extends BaseActivity {

    @BindView(R.id.act_album_rv)
    protected RecyclerView mRv;

    @Override
    public int getLayoutID() {
        return R.layout.activity_anim;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
    }

    @OnClick({R.id.act_anim_btn_property_anim, R.id.act_anim_btn_view_anim, R.id.act_anim_btn_frame_anim})
    public void onClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.act_anim_btn_property_anim:
                intent.setClass(AnimActivity.this, AnimPropertyActivity.class);
                break;
            case R.id.act_anim_btn_view_anim:
                intent.setClass(AnimActivity.this, AnimViewActivity.class);
                break;
            case R.id.act_anim_btn_frame_anim:
                intent.setClass(AnimActivity.this, AnimFrameActivity.class);
                break;
        }
        startActivity(intent);
    }
}
