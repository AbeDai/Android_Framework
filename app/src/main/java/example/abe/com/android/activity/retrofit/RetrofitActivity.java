package example.abe.com.android.activity.retrofit;

import android.widget.EditText;

import com.example.BindView;
import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.android.model.BaseModel;
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

    @OnClick(R.id.act_retrofit_login)
    public void getLogin(){
        NetworkPresent.getLogin("daiyibo", "123", new NetworkPresent.ABCallback() {
            @Override
            public void onSuccess(BaseModel data) {
                mTvShow.setText(data.toString());
            }

            @Override
            public void onFailure(BaseModel data) {
                mTvShow.setText(data.toString());
            }
        });
    }

    @OnClick(R.id.act_retrofit_logout)
    public void getLogout(){
        NetworkPresent.getLogout("daiyibo", new NetworkPresent.ABCallback() {
            @Override
            public void onSuccess(BaseModel data) {
                mTvShow.setText(data.toString());
            }

            @Override
            public void onFailure(BaseModel data) {
                mTvShow.setText(data.toString());
            }
        });
    }

}
