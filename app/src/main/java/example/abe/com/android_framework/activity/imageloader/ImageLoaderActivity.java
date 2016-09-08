package example.abe.com.android_framework.activity.imageloader;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.BindView;

import example.abe.com.android_framework.R;
import example.abe.com.framework.main.BaseActivity;

public class ImageLoaderActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.act_image_loader_btn_image_view)
    protected Button mBtnImageView;
    @BindView(R.id.act_image_loader_btn_grid_view)
    protected Button mBtnGridView;

    @Override
    public int getLayoutID(){
        return R.layout.activity_image_loader;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
        mBtnImageView.setOnClickListener(this);
        mBtnGridView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.act_image_loader_btn_image_view:
                intent.setClass(this, ImageLoaderImageViewActivity.class);
                break;
            case R.id.act_image_loader_btn_grid_view:
                intent.setClass(this, ImageLoaderGridViewActivity.class);
                break;
        }
        startActivity(intent);
    }
}
