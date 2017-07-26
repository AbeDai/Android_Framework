package example.abe.com.android.activity.keyboard;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import example.abe.com.android.R;
import example.abe.com.framework.util.LogUtil;

/**
 * Created by abe on 17/7/22.
 */
public class KeyboardUtil {
    private static final String WORD_STR = "abcdefghijklmnopqrstuvwxyz";

    private Context mContext; // 上下文
    private CustomKeyboardView mKeyboardView;// 键盘
    private Keyboard mKeyboardEnglish;// 英文键盘
    private Keyboard mKeyboardNumberABC;// 数字键盘(带有ABC按键,可以切换英文键盘)
    private Keyboard mKeyboardNumberDot;// 数字键盘(带有"."按键)
    private ValidEditTextInner mEtCurrent;// 当前编辑框
    private List<ValidEditTextInner> mListEditText;// 使用自定义键盘的编辑框
    private boolean isNumber = false;// 是否数字键盘
    private boolean isUpper = false;// 是否大写
    private KeyboardWatcher mKeyboardWatcher;

    public KeyboardUtil(Context context) {
        this.mContext = context;
        mKeyboardEnglish = new Keyboard(context, R.xml.keyboard_english);
        mKeyboardNumberABC = new Keyboard(context, R.xml.keyboard_number_abc);
        mKeyboardNumberDot = new Keyboard(context, R.xml.keyboard_number_dot);
        mKeyboardView = (CustomKeyboardView) LayoutInflater.from(context)
                .inflate(R.layout.view_keyboard, null);
        mKeyboardView.setKeyboard(mKeyboardEnglish);
        mKeyboardView.setEnabled(true);
        mKeyboardView.setPreviewEnabled(true);
        mKeyboardView.setOnKeyboardActionListener(mKeyboardActionListener);
        mKeyboardView.setOnTouchListener(mSkipMoveTouch);
        mListEditText = new ArrayList<>();
    }

    public void hideKeyboard() {
        int visibility = mKeyboardView.getVisibility();
        if (visibility == View.VISIBLE) {
            mKeyboardView.setVisibility(View.INVISIBLE);
        }
    }

    public void setKeyboardDocker(ViewGroup docker){
        ViewParent parent = mKeyboardView.getParent();
        if (parent instanceof ViewGroup){
            ((ViewGroup) parent).removeView(mKeyboardView);
        }
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        docker.addView(mKeyboardView, lp);
    }

    public void putInvalidEditTexts(EditText... editTexts){
        for (final EditText editText : editTexts) {
            editText.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    hideKeyboard();
                    return false;
                }
            });
        }
    }

    public void putValidEditTexts(EditText... editTexts) {
        mListEditText.clear();
        for (final EditText editText : editTexts) {
            mListEditText.add(new ValidEditTextInner(editText));
            editText.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    hindSystemKeyboard();
                    ValidEditTextInner inner = findEditText((EditText)v);
                    if (inner == null){
                        return false;
                    }
                    if (inner.isNumberInputType()){
                        showNumberDotKeyboardInner(inner);
                    }else {
                        showEnglishKeyboardInner(inner);
                    }
                    return false;
                }
            });
        }
    }

    public void showEnglishKeyboard(EditText editText) {
        ValidEditTextInner inner = findEditText(editText);
        if (inner == null){
            inner = new ValidEditTextInner(editText);
        }
        showEnglishKeyboardInner(inner);
    }

    public void showNumberKeyboard(EditText editText) {
        ValidEditTextInner inner = findEditText(editText);
        if (inner == null){
            inner = new ValidEditTextInner(editText);
        }
        showNumberDotKeyboardInner(inner);
    }

    public void setTextWatcher(KeyboardWatcher watcher){
        mKeyboardWatcher = watcher;
    }

    private ValidEditTextInner findEditText(EditText editText){
        for (ValidEditTextInner inner : mListEditText) {
            if (inner.editText == editText)
                return inner;
        }
        return null;
    }

    private KeyboardView.OnKeyboardActionListener mKeyboardActionListener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void swipeUp() {
        }

        @Override
        public void swipeRight() {
        }

        @Override
        public void swipeLeft() {
        }

        @Override
        public void swipeDown() {
        }

        @Override
        public void onText(CharSequence text) {
        }

        @Override
        public void onRelease(int primaryCode) {
        }

        @Override
        public void onPress(int primaryCode) {
            if (primaryCode == Keyboard.KEYCODE_SHIFT // 切换大小写
                    || primaryCode == 999999 // 回退
                    || primaryCode == Keyboard.KEYCODE_DONE // 确认
                    || primaryCode == Keyboard.KEYCODE_MODE_CHANGE // 键盘类型切换
                    || primaryCode == 32//空格
                    || primaryCode == 100000//数字键盘中的"."符号
                    || primaryCode == 100001 || primaryCode == 100002) {//数字键盘中的"00"符号
                mKeyboardView.setPreviewEnabled(false);
                return;
            }
            mKeyboardView.setPreviewEnabled(true);
            return;
        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            LogUtil.e(primaryCode + "");
            if (primaryCode == Keyboard.KEYCODE_DONE) {//确认
                if (mKeyboardWatcher != null
                        && mEtCurrent != null
                        && mEtCurrent.editText != null
                        && mEtCurrent.sb != null){
                    mKeyboardWatcher.onSureClicked(mEtCurrent.editText, mEtCurrent.sb.toString());
                }else {
                    hideKeyboard();
                }
            } else if (primaryCode == 999999) {//回退
                mEtCurrent.delete();
            } else if (primaryCode == Keyboard.KEYCODE_SHIFT) {//大小写切换
                changeCaseWordKey();
                mKeyboardView.setKeyboard(mKeyboardEnglish);
            } else if (primaryCode == Keyboard.KEYCODE_MODE_CHANGE) {//键盘类型切换
                changeKeyboardType();
            } else if(primaryCode == 100000){ //数字键盘中的"."符号
                mEtCurrent.insert(".");
            } else if(primaryCode == 100001 || primaryCode == 100002){ //数字键盘中的"00"符号
                mEtCurrent.insert("00");
            } else if (primaryCode == -5){
                // 屏蔽按键 (-5)在点击"00"的时候,会无故触发,所以屏蔽操作
            }else {
                mEtCurrent.insert(Character.toString((char) primaryCode));
            }
        }
    };

    private View.OnTouchListener mSkipMoveTouch = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            //键盘按键不可移动
            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                return true;
            }
            return false;
        }
    };

    private void showEnglishKeyboardInner(ValidEditTextInner inner) {
        mEtCurrent = inner;
        showKeyboard();
        isNumber = false;
        mKeyboardView.setKeyboard(mKeyboardEnglish);
    }

    private void showNumberDotKeyboardInner(ValidEditTextInner inner) {
        mEtCurrent = inner;
        showKeyboard();
        isNumber = true;
        mKeyboardView.setKeyboard(mKeyboardNumberDot);
    }

    private void showNumberABCKeyboardInner(ValidEditTextInner inner) {
        mEtCurrent = inner;
        showKeyboard();
        isNumber = true;
        mKeyboardView.setKeyboard(mKeyboardNumberABC);
    }

    private void changeKeyboardType() {
        if (isNumber) {
            isNumber = false;
            mKeyboardView.setKeyboard(mKeyboardEnglish);
        } else {
            isNumber = true;
            mKeyboardView.setKeyboard(mKeyboardNumberABC);
        }
    }

    private void changeCaseWordKey() {
        List<Keyboard.Key> keyList = mKeyboardEnglish.getKeys();
        if (isUpper) {//大写切换小写
            isUpper = false;
            mKeyboardView.setIsUpper(isUpper);
            for (Keyboard.Key key : keyList) {
                if (key.label != null && isWord(key.label.toString())) {
                    key.label = key.label.toString().toLowerCase();
                    key.codes[0] = key.codes[0] + 32;
                }
            }
        } else {//小写切换大写
            isUpper = true;
            mKeyboardView.setIsUpper(isUpper);
            for (Keyboard.Key key : keyList) {
                if (key.label != null && isWord(key.label.toString())) {
                    key.label = key.label.toString().toUpperCase();
                    key.codes[0] = key.codes[0] - 32;
                }
            }
        }
    }

    private boolean isWord(String str) {
        return WORD_STR.contains(str.toLowerCase());
    }

    private void showKeyboard() {
        int visibility = mKeyboardView.getVisibility();
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            mKeyboardView.setVisibility(View.VISIBLE);
        }
    }

    private void hindSystemKeyboard(){
        if (mKeyboardView == null)
            return;
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mKeyboardView.getWindowToken(), 0);
    }

    private class ValidEditTextInner{
        private static final int TEXT_PASSWORD = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
        private static final int NUMBER_DECIMAL = InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL;
        private static final int TEXT_NORMAL = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL;

        EditText editText;
        StringBuffer sb;
        int inputType;

        private ValidEditTextInner(EditText et){
            editText = et;
            inputType = editText.getInputType();
            sb = new StringBuffer(editText.getText().toString());
            skipSystemKeyboard(editText);
        }

        private void delete(){
            int start = editText.getSelectionStart();
            Editable editable = editText.getText();
            if (editable != null && editable.length() > 0) {
                if (start > 0) {
                    editable.delete(start - 1, start);
                    sb.delete(start-1, start);
                    callOnTextChange();
                }
            }
        }

        private void insert(CharSequence text){
            if (editText.getInputType() == InputType.TYPE_NULL){
                if (inputType == TEXT_PASSWORD){
                    int start = editText.getSelectionStart();
                    int length = editText.length();
                    Editable editable = editText.getText();
                    editable.insert(start, "*");
                    if (length != editable.length()){
                        sb.insert(start, text);
                        callOnTextChange();
                    }
                }else if (inputType == TEXT_NORMAL
                        || inputType == NUMBER_DECIMAL){
                    int start = editText.getSelectionStart();
                    int length = editText.length();
                    Editable editable = editText.getText();
                    editable.insert(start, text);
                    if (length != editable.length()){
                        sb.insert(start, text);
                        callOnTextChange();
                    }
                }
            }else {
                int start = editText.getSelectionStart();
                int length = editText.length();
                Editable editable = editText.getText();
                editable.insert(start, text);
                if (length != editable.length()){
                    sb.insert(start, text);
                    callOnTextChange();
                }
            }
        }

        private void skipSystemKeyboard(EditText editText){
            String methodName = null;
            if (android.os.Build.VERSION.SDK_INT >= 16) {
                // 4.2
                methodName = "setShowSoftInputOnFocus";
            } else if (android.os.Build.VERSION.SDK_INT >= 14) {
                // 4.0
                methodName = "setSoftInputShownOnFocus";
            }
            if (methodName == null) {
                editText.setInputType(InputType.TYPE_NULL);
            } else {
                Class<EditText> cls = EditText.class;
                Method setShowSoftInputOnFocus;
                try {
                    setShowSoftInputOnFocus = cls.getMethod(methodName,
                            boolean.class);
                    setShowSoftInputOnFocus.setAccessible(true);
                    setShowSoftInputOnFocus.invoke(editText, false);
                } catch (NoSuchMethodException e) {
                    editText.setInputType(InputType.TYPE_NULL);
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private boolean isNumberInputType(){
            if (inputType == NUMBER_DECIMAL) //数字普通
                return true;
            return false;
        }

        private void callOnTextChange(){
            if (mKeyboardWatcher != null
                    && sb != null
                    && editText != null){
                mKeyboardWatcher.onTextChanged(editText, sb.toString());
            }
        }
    }

    public interface KeyboardWatcher {

        void onTextChanged(EditText editText, CharSequence string);

        void onSureClicked(EditText editText, CharSequence string);
    }
}
