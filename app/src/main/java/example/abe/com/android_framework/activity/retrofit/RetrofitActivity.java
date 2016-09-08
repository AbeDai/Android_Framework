package example.abe.com.android_framework.activity.retrofit;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.BindView;
import com.example.OnClick;

import example.abe.com.android_framework.R;
import example.abe.com.framework.main.BaseActivity;

public class RetrofitActivity extends BaseActivity{

    @BindView(R.id.et_show)
    protected EditText mTvShow;

    @Override
    public int getLayoutID(){
        return R.layout.activity_retrofit;
    }

    @Override
    public void initData(){
    }

    @Override
    public void initView(){
    }

    @OnClick(R.id.btn_person_info)
    public void getPersonInfo(){
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

    @OnClick(R.id.btn_all_car)
    public void getAllCar(){
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

    @OnClick(R.id.btn_setting_info)
    public void getSettingInfo(){
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
}
