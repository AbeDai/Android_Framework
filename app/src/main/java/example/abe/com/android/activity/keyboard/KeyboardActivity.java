package example.abe.com.android.activity.keyboard;

import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.BindView;
import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;

public class KeyboardActivity extends BaseActivity {

    @BindView(R.id.act_keyboard_et_number)
    protected EditText mEtNumber;
    @BindView(R.id.act_keyboard_et_number_password)
    protected EditText mEtNumberPassword;
    @BindView(R.id.act_keyboard_et_text)
    protected EditText mEtText;
    @BindView(R.id.act_keyboard_et_text_password)
    protected EditText mEtTextPassword;
    @BindView(R.id.act_keyboard_et_normal)
    protected EditText mEtNormal;
    @BindView(R.id.act_keyboard_fl_keyboard_docker)
    protected FrameLayout mFlKeyboardDocker;

    private KeyboardUtil mKeyboardUtil;

    @Override
    public int getLayoutID() {
        return R.layout.activity_keyboard;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
        mFlKeyboardDocker = (FrameLayout) findViewById(R.id.act_keyboard_fl_keyboard_docker);
        mKeyboardUtil = new KeyboardUtil(this);
        mKeyboardUtil.setKeyboardDocker(mFlKeyboardDocker);
        mKeyboardUtil.putValidEditTexts(mEtNumber, mEtNumberPassword, mEtText, mEtTextPassword);
        mKeyboardUtil.putInvalidEditTexts(mEtNormal);
    }

    @OnClick({R.id.act_keyboard_btn_hide, R.id.act_keyboard_btn_number, R.id.act_keyboard_btn_text})
    public void onClickView(View view) {
        switch (view.getId()){
            case R.id.act_keyboard_btn_text:
                mKeyboardUtil.showEnglishKeyboard(mEtNumber);
                break;
            case R.id.act_keyboard_btn_number:
                mKeyboardUtil.showNumberKeyboard(mEtNumber);
                break;
            case R.id.act_keyboard_btn_hide:
                mKeyboardUtil.hideKeyboard();
                break;
        }
    }
}
