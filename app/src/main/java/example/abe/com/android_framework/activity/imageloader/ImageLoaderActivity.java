package example.abe.com.android_framework.activity.imageloader;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import example.abe.com.android_framework.R;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.viewinject.ContentView;
import example.abe.com.framework.viewinject.ViewInject;

@ContentView(id = R.layout.activity_image_loader)
public class ImageLoaderActivity extends BaseActivity implements View.OnClickListener {

    @ViewInject(id = R.id.act_image_loader_btn_image_view)
    private Button mBtnImageView;
    @ViewInject(id = R.id.act_image_loader_btn_grid_view)
    private Button mBtnGridView;

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
