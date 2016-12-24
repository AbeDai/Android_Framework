package example.abe.com.android.activity.drawing.shader;

import com.example.BindView;
import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;

public class ShaderActivity extends BaseActivity {

    @BindView(R.id.act_shader_iv_shader_view)
    protected ShaderView mShaderView;

    @Override
    public int getLayoutID(){
        return R.layout.activity_shader;
    }

    @Override
    public void initData(){
    }

    @Override
    public void initView(){
    }

    @OnClick(R.id.act_shader_btn_custom_shader)
    public void onBtnClick() {

    }
}
