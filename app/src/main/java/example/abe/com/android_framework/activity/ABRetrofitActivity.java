package example.abe.com.android_framework.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.network.ABNetworkPresent;
import example.abe.com.android_framework.network.AllCarModel;
import example.abe.com.android_framework.network.PersonInfoModel;
import example.abe.com.android_framework.network.SetInfoModel;
import example.abe.com.framework.network.ABRetrofitUtil;
import example.abe.com.framework.util.ABLog;

public class ABRetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abretrofit);

        final EditText tvShow = (EditText) findViewById(R.id.et_show);

        Button btn = (Button) findViewById(R.id.btn_person_info);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ABNetworkPresent.getPersonInfo("jui5dhmmimhggmidheodkdhlkndo5g7", "2567", new ABRetrofitUtil.ABCallback() {
                    @Override
                    public <T> void onSuccess(T data) {
                        PersonInfoModel model = (PersonInfoModel) data;
                        tvShow.setText(model.toString());
                    }

                    @Override
                    public void onFailure(String message) {
                        tvShow.setText(message);
                    }
                });
            }
        });

        btn = (Button) findViewById(R.id.btn_setting_info);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ABNetworkPresent.getSetInfo("jui5dhmmimhggmidheodkdhlkndo5g7", new ABRetrofitUtil.ABCallback() {
                    @Override
                    public <T> void onSuccess(T data) {
                        SetInfoModel model = (SetInfoModel) data;
                        tvShow.setText(model.toString());
                    }

                    @Override
                    public void onFailure(String message) {
                        tvShow.setText(message);
                    }
                });
            }
        });

        btn = (Button) findViewById(R.id.btn_all_car);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ABNetworkPresent.getAllCar("pui5dhlmiihkgpidnejdgdhlkodo5g7", new ABRetrofitUtil.ABCallback() {
                    @Override
                    public <T> void onSuccess(T data) {
                        AllCarModel model = (AllCarModel) data;
                        tvShow.setText(model.toString());
                    }

                    @Override
                    public void onFailure(String message) {
                        tvShow.setText(message);
                    }
                });
            }
        });


    }


}
