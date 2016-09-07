package example.abe.com.android_framework.activity.retrofit;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.BindView;

import example.abe.com.android_framework.R;
import example.abe.com.framework.main.BaseActivity;

public class RetrofitActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.et_show)
    protected EditText mTvShow;
    @BindView(R.id.btn_person_info)
    protected Button mBtnPersonInfo;
    @BindView(R.id.btn_setting_info)
    protected Button mBtnSettingInfo;
    @BindView(R.id.btn_all_car)
    protected Button mBtnAllCar;

    @Override
    public int getLayoutID(){
        return R.layout.activity_retrofit;
    }

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
