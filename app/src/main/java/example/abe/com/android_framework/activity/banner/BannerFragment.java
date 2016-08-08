package example.abe.com.android_framework.activity.banner;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.activity.volley.BitmapCache;
import example.abe.com.framework.main.BaseFragment;
import example.abe.com.framework.util.DensityUtil;
import example.abe.com.framework.viewinject.ContentView;
import example.abe.com.framework.viewinject.ViewInject;

/**
 * Created by abe on 16/8/6.
 */
@ContentView(id = R.layout.fragment_banner)
public class BannerFragment extends BaseFragment {

    private static String DATA_TITLES = "data_titles";
    private static String DATA_URLS = "data_url";

    @ViewInject(id = R.id.frag_banner_view_pager)
    private BannerViewPager mBanner;
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
                data.add(getNetworkImageView(url));
            }
            //过渡视图
            data.addFirst(getNetworkImageView(urls.get(urls.size() - 1)));
            data.addLast(getNetworkImageView(urls.get(0)));
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

    private NetworkImageView getNetworkImageView(String url){

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        ImageLoader loader = new ImageLoader(queue, new BitmapCache());

        NetworkImageView imageView = new NetworkImageView(getActivity());
        imageView.setDefaultImageResId(android.R.drawable.ic_delete);
        imageView.setErrorImageResId(R.mipmap.ic_launcher);
        imageView.setImageUrl(url, loader);

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
