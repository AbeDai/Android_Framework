package example.abe.com.android.activity.mvvm.viewmodel;

import android.databinding.BindingAdapter;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import example.abe.com.android.activity.mvvm.model.UserModel;
import example.abe.com.framework.util.ToastUtil;

/**
 * Created by abe on 16/11/6.
 */

public class MVVMViewModel {

    private UserModel userModel;

    public MVVMViewModel(){
        userModel = new UserModel();
    }

    public void btnCancelClick(View view){
        ToastUtil.makeText("取消登录");
    }

    public void btnLoginClick(View view){
        if (!TextUtils.isEmpty(userModel.getNumber())
                && !TextUtils.isEmpty(userModel.getPassword())){
            ToastUtil.makeText("Number:" + userModel.getNumber()
                    + "\nPassword:" + userModel.getPassword());
        }
    }

    @BindingAdapter({"android:afterNumberTextChanged"})
    public static void numberTextChange(final EditText editText, final MVVMViewModel viewModel) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                viewModel.userModel.setNumber(s.toString());
            }
        });

    }

    @BindingAdapter({"android:afterPasswordTextChanged"})
    public static void passwordTextChange(final EditText editText, final MVVMViewModel viewModel) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                viewModel.userModel.setPassword(s.toString());
            }
        });

    }

}
