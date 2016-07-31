package example.abe.com.android_framework.activity.volley;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.main.BaseActivity;
import example.abe.com.framework.annotation.ContentView;
import example.abe.com.framework.annotation.ViewInject;

@ContentView(id = R.layout.activity_volley_image)
public class VolleyImageActivity extends BaseActivity implements View.OnClickListener {

    private String mImgUrl;
    private RequestQueue mQueue;
    private ImageLoader mLoader;

    @ViewInject(id = R.id.act_volley_image_btn_request)
    private Button mBtnRequest;
    @ViewInject(id = R.id.act_volley_image_btn_loader)
    private Button mBtnLoader;
    @ViewInject(id = R.id.act_volley_image_btn_view)
    private Button mBtnView;
    @ViewInject(id = R.id.act_volley_image_iv)
    private ImageView mIv;
    @ViewInject(id = R.id.act_volley_image_network_image_view)
    private NetworkImageView mIvNetwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
        initView();
    }

    private void initData() {
        mImgUrl = "http://img.mp.itc.cn/upload/20160418/c44022406262471e86551d54945d4c55_th.jpg";
        mQueue = Volley.newRequestQueue(this);
        mLoader = new ImageLoader(mQueue, new BitmapCache());

    }

    private void initView() {
        mBtnRequest.setOnClickListener(this);
        mBtnLoader.setOnClickListener(this);
        mBtnView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.act_volley_image_btn_request:{
                ImageRequest imageRequest = new ImageRequest(
                        mImgUrl,
                        mImageListener,
                        0, 0,
                        Bitmap.Config.ARGB_8888,
                        mErrorListener);
                mQueue.add(imageRequest);
            }break;

            case R.id.act_volley_image_btn_loader:{
                ImageLoader.ImageListener listener = ImageLoader.getImageListener(mIv,
                        R.drawable.icon_custom_dialog, R.mipmap.ic_launcher);
                mLoader.get(mImgUrl, listener,  0, 0);
            }break;

            case R.id.act_volley_image_btn_view:{
                mIvNetwork.setDefaultImageResId(R.drawable.icon_custom_dialog);
                mIvNetwork.setErrorImageResId(R.mipmap.ic_launcher);
                mIvNetwork.setImageUrl(mImgUrl, mLoader);
            }break;
        }
    }

    private Response.Listener<Bitmap> mImageListener = new Response.Listener<Bitmap>() {
        @Override
        public void onResponse(Bitmap response) {
            mIv.setImageBitmap(response);
        }
    };

    private Response.ErrorListener mErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            mIv.setImageResource(R.mipmap.ic_launcher);
        }
    };
}
