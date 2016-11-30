package example.abe.com.android.activity.span;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.widget.TextView;

import com.example.BindView;

import java.util.HashMap;

import example.abe.com.android.R;
import example.abe.com.framework.imageloader.ImageLoader;
import example.abe.com.framework.imageloader.handle.BitmapHandle;
import example.abe.com.framework.main.BaseActivity;

public class SpannableHtmlActivity extends BaseActivity {

    @BindView(R.id.act_spannable_html_tv)
    protected TextView mTv;

    private ImageGetter imageGetter;

    public static final String HTML_CONTENT = "<html><head><title>TextView使用HTML</title></head><body><p><strong>强调</strong></p><p><em>斜体</em></p>"
            + "<p><a href=\"http://www.dreamdu.com/xhtml/\">超链接HTML入门</a>学习HTML!</p><p><font color=\"#aabb00\">颜色1"
            + "</p><p><font color=\"#00bbaa\">颜色2</p><h1>标题1</h1><h3>标题2</h3><h6>标题3</h6><p>大于>小于<</p><p>" +
            "下面是网络图片</p><img src=\"http://avatar.csdn.net/0/3/8/2_zhang957411207.jpg\"/></body></html>";

    @Override
    public int getLayoutID() {
        return R.layout.activity_spannable_html;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
        imageGetter = new ImageGetter();
        mTv.setText(Html.fromHtml(HTML_CONTENT, imageGetter, null));
    }

    private HashMap<String, Drawable> drawableHashMap = new HashMap<>();

    private class ImageGetter implements Html.ImageGetter {
        @Override
        public Drawable getDrawable(final String source) {
            ImageLoader.getInstance().getBitmap(source, new BitmapHandle.BitmapHandleListener() {
                @Override
                public void onHandle(Bitmap bitmap) {
                    Drawable drawable = new BitmapDrawable(bitmap);
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                    drawableHashMap.put(source, drawable);
                    mTv.setText(Html.fromHtml(HTML_CONTENT, imageGetter, null));
                }
            });
            return drawableHashMap.get(source);
        }
    }
}
