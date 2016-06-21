package example.abe.com.android_framework.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import example.abe.com.android_framework.R;

public class ABAssetsActivity extends AppCompatActivity {

    private ImageView mIvRoot;
    private ImageView mIvFile;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abassets);

        //获取root中图片
        mIvRoot = (ImageView) findViewById(R.id.act_abassets_iv_root_png);
        mIvRoot.setImageBitmap(getAssetBitmap("root_img.png"));

        //获取file中图片
        mIvFile = (ImageView) findViewById(R.id.act_abassets_iv_file_png);
        mIvFile.setImageBitmap(getAssetBitmap("file2/file_img.png"));

        //打印文件目录
        mTv = (TextView) findViewById(R.id.act_abassets_tv_file_list);
        mTv.setText(getAssetRootList().toString());
    }

    //获取图片资源
    public Bitmap getAssetBitmap(String fileName) {

        Bitmap bitmap = null;
        try {
            //打开文件
            InputStream inputStream = getAssets().open(fileName);

            //图片操作
            bitmap = BitmapFactory.decodeStream(inputStream);

            //关闭文件
            try {
                inputStream.close();
            } catch (IOException e) {
                Log.e("Abe", "关闭输入流失败!");
            }
        } catch (IOException e) {
            Log.e("Abe", "asset获取文件: " + fileName + " 失败!");
        }

        return bitmap;
    }

    //获取根目录列表
    public List<String> getAssetRootList() {
        List<String> list = null;
        try {
            String[] array = getAssets().list("");
            list = Arrays.asList(array);
        } catch (IOException e) {
            Log.e("Abe", "asset获取根目录失败!");
        }
        return list;
    }
}
