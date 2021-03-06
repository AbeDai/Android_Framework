package example.abe.com.android.activity.assets;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.BindView;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.util.AssetUtil;


public class AssetsActivity extends BaseActivity {

    @BindView(R.id.act_abassets_iv_root_png)
    protected ImageView mIvRoot;
    @BindView(R.id.act_abassets_iv_file_png)
    protected ImageView mIvFile;
    @BindView(R.id.act_abassets_tv_file_list)
    protected TextView mTv;

    @Override
    public int getLayoutID(){
        return R.layout.activity_assets;
    }

    @Override
    public void initData(){
    }

    @Override
    public void initView(){
        //获取root中图片
        mIvRoot.setImageBitmap(AssetUtil.getAssetBitmap("img/root_img.png"));

        //获取file中图片
        mIvFile.setImageBitmap(AssetUtil.getAssetBitmap("img/file_img.png"));

        //打印文件目录
        mTv.setText(AssetUtil.getAssetRootList().toString());
    }
}
