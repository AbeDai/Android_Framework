package example.abe.com.android.activity.banner;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.BindView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import example.abe.com.android.R;
import example.abe.com.framework.imageloader.ImageLoader;
import example.abe.com.framework.main.BaseFragment;
import example.abe.com.framework.util.DensityUtil;

/**
 * Created by abe on 16/8/6.
 */
public class BannerFragment extends BaseFragment {

    @BindView(R.id.frag_banner_view_pager)
    protected BannerViewPager mBanner;
    private static String DATA_TITLES = "data_titles";
    private static String DATA_URLS = "data_url";
    private BannerAdapter mAdapter;
    private List<View> mData;

    public static BannerFragment newInstanceWithTitle(List<String> titles) {
        BannerFragment fragment = new BannerFragment();

        Bundle bundle = new Bundle();
        bundle.putStringArrayList(DATA_TITLES, new ArrayList<>(titles));
        fragment.setArguments(bundle);

        return fragment;
    }

    public static BannerFragment newInstanceWithURL(List<String> urls) {
        BannerFragment fragment = new BannerFragment();

        Bundle bundle = new Bundle();
        bundle.putStringArrayList(DATA_URLS, new ArrayList<>(urls));
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getLayoutID(){
        return R.layout.fragment_banner;
    }

    @Override
    public void initData() {
        //标题-滚动视图
        ArrayList<String> titles = getArguments().getStringArrayList(DATA_TITLES);
        if (titles != null) {
            LinkedList<View> data = new LinkedList<>();
            for (String title : titles) {
                data.add(getViewForText(title));
            }
            //过渡视图
            data.addFirst(getViewForText(titles.get(titles.size() - 1)));
            data.addLast(getViewForText(titles.get(0)));
            mData = new ArrayList<>(data);
        }

        //URL-滚动视图
        ArrayList<String> urls = getArguments().getStringArrayList(DATA_URLS);
        if (urls != null) {
            LinkedList<View> data = new LinkedList<>();
            for (String url : urls) {
                data.add(getImageView(url));
            }
            //过渡视图
            data.addFirst(getImageView(urls.get(urls.size() - 1)));
            data.addLast(getImageView(urls.get(0)));
            mData = new ArrayList<>(data);
        }
    }

    @Override
    public void initView() {
        mAdapter = new BannerAdapter(mData);
        mBanner.setAdapter(mAdapter);
        mBanner.setCurrentItem(1);
        mBanner.start();
    }

    private ImageView getImageView(String url){
        ImageView imageView = new ImageView(getActivity());
        ImageLoader.getInstance().getImage(imageView, url);

        return imageView;
    }

    private ImageView getViewForText(String text) {
        Bitmap bitmap = Bitmap.createBitmap(DensityUtil.dip2px(300), DensityUtil.dip2px(300), Bitmap.Config.ARGB_8888);
        float height = bitmap.getHeight();
        float width = bitmap.getWidth();
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(3);
        paint.setTextSize(DensityUtil.dip2px(40));
        canvas.drawText(text, width / 2f, height / 2f, paint);

        ImageView imageView = new ImageView(getActivity());
        imageView.setImageBitmap(bitmap);
        return imageView;
    }
}
