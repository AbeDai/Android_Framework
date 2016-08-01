package example.abe.com.android_framework.activity.volley;

import android.os.Bundle;
import android.util.Log;
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

    private String mGetUrl;
    private RequestQueue mQueue;
    @ViewInject(id = R.id.act_volley_custom_btn_xml_get)
    private Button mBtnXml;
    @ViewInject(id = R.id.act_volley_custom_et_show)
    private EditText mEtShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
        initView();
    }

    private void initData() {
        mGetUrl = "http://flash.weather.com.cn/wmaps/xml/china.xml";
        mQueue = Volley.newRequestQueue(this);
    }

    private void initView() {
        mBtnXml.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.act_volley_custom_btn_xml_get: {
                XMLRequest xmlRequest = new XMLRequest(
                        mGetUrl,
                        mXmlListener,
                        mErrorListener);
                mQueue.add(xmlRequest);
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
                            if ("city".equals(nodeName)) {
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

    Response.ErrorListener mErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            mEtShow.setText(error.getMessage());
        }
    };
}
