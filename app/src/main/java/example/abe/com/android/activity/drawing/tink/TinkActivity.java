package example.abe.com.android.activity.drawing.tink;

import android.view.View;

import com.example.BindView;
import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;

public class TinkActivity extends BaseActivity {

    @BindView(R.id.act_tink_view)
    protected TinkView mTink;

    @Override
    public int getLayoutID(){
        return R.layout.activity_tink;
    }

    @Override
    public void initData(){
    }

    @Override
    public void initView(){

    }

    @OnClick({R.id.act_tink_btn_begin_tink})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.act_tink_btn_begin_tink:
                mTink.beginDrawTick();
                break;
        }
    }
}
