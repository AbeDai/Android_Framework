package example.abe.com.android_framework.activity.assets;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.main.BaseActivity;
import example.abe.com.framework.annotation.ContentView;
import example.abe.com.framework.annotation.ViewInject;
import example.abe.com.framework.util.ResourceUtil;

@ContentView(id = R.layout.activity_assets)
public class AssetsActivity extends BaseActivity {

    @ViewInject(id = R.id.act_abassets_iv_root_png)
    private ImageView mIvRoot;
    @ViewInject(id = R.id.act_abassets_iv_file_png)
    private ImageView mIvFile;
    @ViewInject(id = R.id.act_abassets_tv_file_list)
    private TextView mTv;

    @Override
    public void initData(){

    }

    @Override
    public void initView(){
        //获取root中图片
        mIvRoot.setImageBitmap(ResourceUtil.getAssetBitmap("root_img.png"));

        //获取file中图片
        mIvFile.setImageBitmap(ResourceUtil.getAssetBitmap("file2/file_img.png"));

        //打印文件目录
        mTv.setText(ResourceUtil.getAssetRootList().toString());
    }
}
