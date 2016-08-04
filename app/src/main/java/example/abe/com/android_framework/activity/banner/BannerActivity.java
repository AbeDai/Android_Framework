package example.abe.com.android_framework.activity.banner;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.main.BaseActivity;
import example.abe.com.framework.annotation.ContentView;
import example.abe.com.framework.annotation.ViewInject;
import example.abe.com.framework.util.DensityUtil;

//TODO:需要左右两边，都存在一个View页面，实现流畅滚动效果
//TODO:需要设置自动滚动的方向。
//TODO:最好就是画一个流程图，然后将功能写上去。在进行封装。
//TODO:需要在封装一下
@ContentView(id = R.layout.activity_banner)
public class BannerActivity extends BaseActivity {

    @ViewInject(id = R.id.act_banner_auto_paly_view_pager)
    private AutoPlayViewPager mBanner;
    private BannerPagerAdapter mAdapter;
    private List<View> mData;

    @Override
    public void initData(){
        mData = new ArrayList<>();
        mData.add(getViewForText("1"));
        mData.add(getViewForText("2"));
        mData.add(getViewForText("3"));
        mData.add(getViewForText("4"));
        mData.add(getViewForText("5"));
    }

    @Override
    public void initView(){
        mAdapter = new BannerPagerAdapter(mData);
        mBanner.setAdapter(mAdapter);
        mBanner.start();
    }

    private View getViewForText(String text){
        Bitmap bitmap = Bitmap.createBitmap(DensityUtil.dip2px(300), DensityUtil.dip2px(300), Bitmap.Config.ARGB_8888);
        float height = bitmap.getHeight();
        float width = bitmap.getWidth();
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(3);
        canvas.drawText(text, width/2f, height/2f, paint);

        ImageView imageView = new ImageView(this);
        imageView.setImageBitmap(bitmap);
        return imageView;
    }
}
