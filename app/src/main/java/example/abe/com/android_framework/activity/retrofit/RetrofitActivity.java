package example.abe.com.android_framework.activity.retrofit;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import example.abe.com.android_framework.R;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.viewinject.annotation.ContentView;
import example.abe.com.framework.viewinject.ViewInject;

@ContentView(id = R.layout.activity_retrofit)
public class RetrofitActivity extends BaseActivity implements View.OnClickListener{

    @ViewInject(id = R.id.et_show)
    private EditText mTvShow;
    @ViewInject(id = R.id.btn_person_info)
    private Button mBtnPersonInfo;
    @ViewInject(id = R.id.btn_setting_info)
    private Button mBtnSettingInfo;
    @ViewInject(id = R.id.btn_all_car)
    private Button mBtnAllCar;

    @Override
    public void initData(){

    }

    @Override
    public void initView(){
        mBtnPersonInfo.setOnClickListener(this);
        mBtnSettingInfo.setOnClickListener(this);
        mBtnAllCar.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_person_info:
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
                break;

            case R.id.btn_setting_info:
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
                break;

            case R.id.btn_all_car:
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
                break;
        }

    }

}
