package example.abe.com.android_framework.activity.retrofit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.main.BaseActivity;
import example.abe.com.framework.annotation.ContentView;

@ContentView(id = R.layout.activity_retrofit)
public class RetrofitActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final EditText tvShow = (EditText) findViewById(R.id.et_show);

        Button btn = (Button) findViewById(R.id.btn_person_info);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkPresent.getPersonInfo("jui5dhmmimhggmidheodkdhlkndo5g7", "2567", new RetrofitUtil.ABCallback() {
                    @Override
                    public <T> void onSuccess(T data) {
                        PersonModel model = (PersonModel) data;
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
                NetworkPresent.getSetInfo("jui5dhmmimhggmidheodkdhlkndo5g7", new RetrofitUtil.ABCallback() {
                    @Override
                    public <T> void onSuccess(T data) {
                        InfoModel model = (InfoModel) data;
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
                NetworkPresent.getAllCar("pui5dhlmiihkgpidnejdgdhlkodo5g7", new RetrofitUtil.ABCallback() {
                    @Override
                    public <T> void onSuccess(T data) {
                        CarModel model = (CarModel) data;
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
