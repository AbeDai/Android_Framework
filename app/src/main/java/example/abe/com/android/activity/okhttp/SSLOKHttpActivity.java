package example.abe.com.android.activity.okhttp;

import android.view.View;
import android.widget.EditText;

import com.example.BindView;
import com.example.OnClick;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SSLOKHttpActivity extends BaseActivity {

    @BindView(R.id.act_ssl_okhttp_et_show)
    protected EditText mEtShow;

    private OkHttpClient mClient;

    private static final String CERTS_12306 = "ssl/12306_srca.cer";

    @Override
    public int getLayoutID() {
        return R.layout.activity_sslokhttp;
    }

    @Override
    public void initData() {
        // 添加https证书
        try {
            InputStream is = getAssets().open(CERTS_12306);
            // 这里将证书读取出来，放在配置中byte[]里
            SSLConfig.addCertificate(is);
            is.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public void initView() {
        createOkHttp();
    }

    /**
     * 构建带有证书的OkHttpClient
     */
    public void createOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 添加证书
        List<InputStream> certificates = new ArrayList<>();
        List<byte[]> certs_data = SSLConfig.getCertificatesData();
        // 将字节数组转为数组输入流
        if (certs_data != null && !certs_data.isEmpty()) {
            for (byte[] bytes : certs_data) {
                certificates.add(new ByteArrayInputStream(bytes));
            }
        }
        SSLSocketFactory sslSocketFactory = getSocketFactory(certificates);
        if (sslSocketFactory != null) {
            builder.sslSocketFactory(sslSocketFactory);
        }
        mClient = builder.build();
    }

    /**
     * 添加证书
     *
     * @param certificates
     */
    private static SSLSocketFactory getSocketFactory(List<InputStream> certificates) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            try {
                for (int i = 0, size = certificates.size(); i < size; ) {
                    InputStream certificate = certificates.get(i);
                    String certificateAlias = Integer.toString(i++);
                    keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));
                    if (certificate != null)
                        certificate.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @OnClick({R.id.act_ssl_okhttp_btn_get_12306})
    public void onClick(View v) {
        Request request = new Request.Builder()
                .url("https://kyfw.12306.cn/otn/")
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                setShowText(response.body().string());
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void setShowText(final String str) {
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                mEtShow.setText(str);
            }
        });
    }

    /**
     * Created by abe on 17/4/4.
     */
    public static class SSLConfig {
        // 证书数据
        private static List<byte[]> CERTIFICATES_DATA = new ArrayList<>();

        /**
         * 添加https证书
         *
         * @param inputStream
         */
        public synchronized static void addCertificate(InputStream inputStream) {
            if (inputStream != null) {
                try {
                    int ava = 0;// 数据当次可读长度
                    int len = 0;// 数据总长度
                    ArrayList<byte[]> data = new ArrayList<>();
                    while ((ava = inputStream.available()) > 0) {
                        byte[] buffer = new byte[ava];
                        inputStream.read(buffer);
                        data.add(buffer);
                        len += ava;
                    }

                    byte[] buff = new byte[len];
                    int dstPos = 0;
                    for (byte[] bytes : data) {
                        int length = bytes.length;
                        System.arraycopy(bytes, 0, buff, dstPos, length);
                        dstPos += length;
                    }

                    CERTIFICATES_DATA.add(buff);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        /**
         * https证书
         *
         * @return
         */
        public static List<byte[]> getCertificatesData() {
            return CERTIFICATES_DATA;
        }
    }
}
