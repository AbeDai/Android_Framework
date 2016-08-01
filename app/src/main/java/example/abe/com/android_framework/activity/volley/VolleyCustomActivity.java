package example.abe.com.android_framework.activity.volley;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.main.BaseActivity;
import example.abe.com.framework.annotation.ContentView;
import example.abe.com.framework.annotation.ViewInject;

@ContentView(id = R.layout.activity_volley_custom)
public class VolleyCustomActivity extends BaseActivity implements View.OnClickListener{

    private String mXMLUrl;
    private String mJsonUrl;
    private RequestQueue mQueue;
    @ViewInject(id = R.id.act_volley_custom_btn_xml_get)
    private Button mBtnXml;
    @ViewInject(id = R.id.act_volley_custom_btn_gson_get)
    private Button mBtnGson;
    @ViewInject(id = R.id.act_volley_custom_et_show)
    private EditText mEtShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
        initView();
    }

    private void initData() {
        mXMLUrl = "http://flash.weather.com.cn/sk2/101220607.xml";
        mJsonUrl = "http://ditu.amap.com/service/pl/pl.json?rand=635840524184357321";
        mQueue = Volley.newRequestQueue(this);
    }

    private void initView() {
        mBtnXml.setOnClickListener(this);
        mBtnGson.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.act_volley_custom_btn_xml_get: {
                XMLRequest xmlRequest = new XMLRequest(
                        mXMLUrl,
                        mXmlListener,
                        mErrorListener);
                mQueue.add(xmlRequest);
            }
            break;

            case R.id.act_volley_custom_btn_gson_get: {
                GsonRequest gsonRequest = new GsonRequest<>(
                        mJsonUrl,
                        PlModel.class,
                        mGsonListener,
                        mErrorListener);
                mQueue.add(gsonRequest);
            }
            break;
        }
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
