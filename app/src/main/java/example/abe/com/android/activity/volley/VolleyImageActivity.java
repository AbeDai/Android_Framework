package example.abe.com.android.activity.volley;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.example.BindView;
import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.android.utils.ImageURLUtil;
import example.abe.com.framework.main.BaseActivity;

public class VolleyImageActivity extends BaseActivity{

    private String mImgUrl;
    private RequestQueue mQueue;
    private ImageLoader mLoader;

    @BindView(R.id.act_volley_image_iv)
    protected ImageView mIv;
    @BindView(R.id.act_volley_image_network_image_view)
    protected NetworkImageView mIvNetwork;

    @Override
    public int getLayoutID(){
        return R.layout.activity_volley_image;
    }

    @Override
    public void initData(){
        mImgUrl = ImageURLUtil.getRandomImageUrl();
        mQueue = Volley.newRequestQueue(this);
        mLoader = new ImageLoader(mQueue, new BitmapCache());
    }

    @Override
    public void initView(){
    }

    @OnClick({R.id.act_volley_image_btn_request, R.id.act_volley_image_btn_loader, R.id.act_volley_image_btn_view})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.act_volley_image_btn_request:{
                ImageRequest imageRequest = new ImageRequest(
                        mImgUrl,
                        mImageListener,
                        0, 0,
                        Bitmap.Config.ARGB_8888,
                        mErrorListener);
                imageRequest.setShouldCache(false);
                mQueue.add(imageRequest);
            }break;

            case R.id.act_volley_image_btn_loader:{
                ImageLoader.ImageListener listener = ImageLoader.getImageListener(mIv,
                        R.drawable.icon_custom_dialog, R.drawable.ic_launcher);
                mLoader.get(mImgUrl, listener,  0, 0);
            }break;

            case R.id.act_volley_image_btn_view:{
                mIvNetwork.setDefaultImageResId(R.drawable.icon_custom_dialog);
                mIvNetwork.setErrorImageResId(R.drawable.ic_launcher);
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
            mIv.setImageResource(R.drawable.ic_launcher);
        }
    };
}
