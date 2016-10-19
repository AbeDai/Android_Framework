package example.abe.com.android.activity.imageloader;

import android.content.Intent;
import android.view.View;

import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;

public class ImageLoaderActivity extends BaseActivity{

    @Override
    public int getLayoutID(){
        return R.layout.activity_image_loader;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
    }

    @OnClick({R.id.act_image_loader_btn_image_view, R.id.act_image_loader_btn_grid_view})
    public void onBtnClick(View v) {
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
