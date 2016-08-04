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

//TOOD:看看哪里有问题，需要重构，和总结一下。
@ContentView(id = R.layout.activity_banner)
public class BannerActivity extends BaseActivity {

    @ViewInject(id = R.id.act_banner_auto_paly_view_pager1)
    private BannerViewPager mBanner1;
    @ViewInject(id = R.id.act_banner_auto_paly_view_pager2)
    private BannerViewPager mBanner2;
    private BannerAdapter mAdapter1;
    private BannerAdapter mAdapter2;
    private List<View> mData1;
    private List<View> mData2;
    @Override
    public void initData(){
        mData1 = new ArrayList<>();
        mData1.add(getViewForText("5"));
        mData1.add(getViewForText("1"));
        mData1.add(getViewForText("2"));
        mData1.add(getViewForText("3"));
        mData1.add(getViewForText("4"));
        mData1.add(getViewForText("5"));
        mData1.add(getViewForText("1"));

        mData2 = new ArrayList<>();
        mData2.add(getViewForText("5"));
        mData2.add(getViewForText("1"));
        mData2.add(getViewForText("2"));
        mData2.add(getViewForText("3"));
        mData2.add(getViewForText("4"));
        mData2.add(getViewForText("5"));
        mData2.add(getViewForText("1"));
    }

    @Override
    public void initView(){
        mAdapter1 = new BannerAdapter(mData1);
        mBanner1.setAdapter(mAdapter1);
        mBanner1.setShowTime(1000);
        mBanner1.setDirection(BannerViewPager.Direction.LEFT);
        mBanner2.moveToFirst();
        mBanner1.start();

        mAdapter2 = new BannerAdapter(mData2);
        mBanner2.setAdapter(mAdapter2);
        mBanner2.setShowTime(2000);
        mBanner2.setDirection(BannerViewPager.Direction.RIGHT);
        mBanner2.moveToFirst();
        mBanner2.start();
    }

    private View getViewForText(String text){
        Bitmap bitmap = Bitmap.createBitmap(DensityUtil.dip2px(300), DensityUtil.dip2px(300), Bitmap.Config.ARGB_8888);
        float height = bitmap.getHeight();
        float width = bitmap.getWidth();
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(3);
        paint.setTextSize(DensityUtil.dip2px(40));
        canvas.drawText(text, width/2f, height/2f, paint);

        ImageView imageView = new ImageView(this);
        imageView.setImageBitmap(bitmap);
        return imageView;
    }
}
