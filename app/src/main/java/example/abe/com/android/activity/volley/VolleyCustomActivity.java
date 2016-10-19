package example.abe.com.android.activity.volley;

import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.BindView;
import com.example.OnClick;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;

public class VolleyCustomActivity extends BaseActivity{

    private String mXMLUrl;
    private String mJsonGetUrl;
    private RequestQueue mQueue;
    @BindView(R.id.act_volley_custom_et_show)
    protected EditText mEtShow;

    @Override
    public int getLayoutID(){
        return R.layout.activity_volley_custom;
    }

    @Override
    public void initData(){
        mXMLUrl = "http://flash.weather.com.cn/sk2/101220607.xml";
        mJsonGetUrl = "http://ditu.amap.com/service/pl/pl.json?rand=635840524184357321";
        mQueue = Volley.newRequestQueue(this);
    }

    @Override
    public void initView(){
    }

    @OnClick({R.id.act_volley_custom_btn_xml_get, R.id.act_volley_custom_btn_gson_get})
    public void onClick(View v) {
        Request request = null;
        switch (v.getId()) {
            case R.id.act_volley_custom_btn_xml_get:
                request = new XMLRequest(
                        mXMLUrl,
                        mXmlListener,
                        mErrorListener);
                break;

            case R.id.act_volley_custom_btn_gson_get:
                request = new GsonRequest<>(
                        mJsonGetUrl,
                        PlModel.class,
                        mGsonListener,
                        mErrorListener);
                break;
        }
        mQueue.add(request);
    }

    Response.Listener<XmlPullParser> mXmlListener = new Response.Listener<XmlPullParser>() {
        @Override
        public void onResponse(XmlPullParser response) {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                int eventType = response.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    switch (eventType) {
                        case XmlPullParser.START_TAG:
                            String nodeName = response.getName();
                            if ("qw".equals(nodeName)) {
                                String quName = response.getAttributeValue(0);
                                String stateDetailed = response.getAttributeValue(5);
                                stringBuilder.append(quName + ": " + stateDetailed + "\n");
                            }
                            break;
                    }
                    eventType = response.next();
                }
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mEtShow.setText(stringBuilder.toString());
        }
    };

    Response.Listener<PlModel> mGsonListener = new Response.Listener<PlModel>() {
        @Override
        public void onResponse(PlModel pl) {
            mEtShow.setText(pl.toString());
        }
    };

    Response.ErrorListener mErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            mEtShow.setText(error.toString());
        }
    };
}
