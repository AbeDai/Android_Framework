package example.abe.com.android.activity.keyboard;

import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.BindView;
import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.util.ToastUtil;

public class KeyboardActivity extends BaseActivity {

    @BindView(R.id.act_keyboard_et_number)
    protected EditText mEtNumber;
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
        mKeyboardUtil.putValidEditTexts(mEtNumber, mEtText, mEtTextPassword);
        mKeyboardUtil.putInvalidEditTexts(mEtNormal);
        mKeyboardUtil.setTextWatcher(new KeyboardUtil.KeyboardWatcher() {
            @Override
            public void onTextChanged(EditText editText, CharSequence string) {
                ToastUtil.makeText(string);
            }

            @Override
            public void onSureClicked(EditText editText, CharSequence string) {
                ToastUtil.makeText("确定:" + string);
            }
        });
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
