package example.abe.com.android_framework.activity.assets;

import android.widget.ImageView;
import android.widget.TextView;

import example.abe.com.android_framework.R;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.viewinject.ContentView;
import example.abe.com.framework.viewinject.ViewInject;
import example.abe.com.framework.util.AssetUtil;

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
        mIvRoot.setImageBitmap(AssetUtil.getAssetBitmap("root_img.png"));

        //获取file中图片
        mIvFile.setImageBitmap(AssetUtil.getAssetBitmap("file2/file_img.png"));

        //打印文件目录
        mTv.setText(AssetUtil.getAssetRootList().toString());
    }
}
