package example.abe.com.android_framework.activity.suspend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

import example.abe.com.android_framework.R;

/**
 * Created by abe on 16/9/27.
 */
public class MemoryWindow extends LinearLayout {

    private Context mContext;
    private TextView mTvMemory;

    public MemoryWindow(Context context) {
        super(context);
        mContext = context;

        initData();
        initView();
    }

    private void initData() {

    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.layout_memory_window, this);
        mTvMemory = (TextView) findViewById(R.id.layout_memory_window_tv_show_memory);
    }

    public void setTextContent(String text) {
        mTvMemory.setText(text);
    }

    private float mOldX;
    private float mOldY;
    private float mPosX;
    private float mPosY;

    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        float x = event.getRawX();
        float y = event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mOldX = event.getRawX();
                mOldY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                MemoryWindowManager.getInstance().setMemoryWindowPosition(mPosX + x - mOldX, mPosY + y - mOldY);
                break;
            case MotionEvent.ACTION_UP:
                mPosX = mPosX + x - mOldX;
                mPosY = mPosY + y - mOldY;
                break;
        }

        return true;
    }
}
