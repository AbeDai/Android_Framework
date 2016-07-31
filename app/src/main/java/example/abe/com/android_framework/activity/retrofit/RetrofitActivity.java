package example.abe.com.android_framework.activity.retrofit;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.main.BaseActivity;
import example.abe.com.framework.annotation.ContentView;
import example.abe.com.framework.annotation.ViewInject;

@ContentView(id = R.layout.activity_retrofit)
public class RetrofitActivity extends BaseActivity {

    @ViewInject(id = R.id.et_show)
    private EditText mTvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViewById(R.id.btn_person_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkPresent.getPersonInfo("jui5dhmmimhggmidheodkdhlkndo5g7", "2567", new RetrofitUtil.ABCallback() {
                    @Override
                    public <T> void onSuccess(T data) {
                        PersonModel model = (PersonModel) data;
                        mTvShow.setText(model.toString());
                    }

                    @Override
                    public void onFailure(String message) {
                        mTvShow.setText(message);
                    }
                });
            }
        });

        findViewById(R.id.btn_setting_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkPresent.getSetInfo("jui5dhmmimhggmidheodkdhlkndo5g7", new RetrofitUtil.ABCallback() {
                    @Override
                    public <T> void onSuccess(T data) {
                        InfoModel model = (InfoModel) data;
                        mTvShow.setText(model.toString());
                    }

                    @Override
                    public void onFailure(String message) {
                        mTvShow.setText(message);
                    }
                });
            }
        });

        findViewById(R.id.btn_all_car).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkPresent.getAllCar("pui5dhlmiihkgpidnejdgdhlkodo5g7", new RetrofitUtil.ABCallback() {
                    @Override
                    public <T> void onSuccess(T data) {
                        CarModel model = (CarModel) data;
                        mTvShow.setText(model.toString());
                    }

                    @Override
                    public void onFailure(String message) {
                        mTvShow.setText(message);
                    }
                });
            }
        });


    }


}
