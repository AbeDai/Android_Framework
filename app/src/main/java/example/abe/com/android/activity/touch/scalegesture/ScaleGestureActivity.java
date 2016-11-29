package example.abe.com.android.activity.touch.scalegesture;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;

import com.example.BindView;
import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.util.ResourceUtil;

public class ScaleGestureActivity extends BaseActivity {

    @BindView(R.id.act_scale_gesture_iv)
    protected ImageView mIv;

    private ScaleGestureDetector mScaleGestureDetector;
    private Matrix mMatrix;
    private Bitmap mBitmap;

    @Override
    public int getLayoutID() {
        return R.layout.activity_scale_gesture;
    }

    @Override
    public void initData() {
        mMatrix = new Matrix();
        mMatrix.setScale(1, 1);
    }

    @Override
    public void initView() {
        mIv.setScaleType(ImageView.ScaleType.MATRIX);
        mScaleGestureDetector = new ScaleGestureDetector(this, scaleGestureListener);
    }

    @OnClick(R.id.act_scale_gesture_btn_show_img)
    public void showImage(View v) {
        mBitmap = ResourceUtil.getBitmap(R.drawable.icon_card_view);
        mIv.setImageBitmap(mBitmap);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //返回给ScaleGestureDetector来处理
        return mScaleGestureDetector.onTouchEvent(event);
    }

    /**
     * 缩放包装类监听
     */
    private ScaleGestureDetector.OnScaleGestureListener scaleGestureListener = new ScaleGestureDetector.OnScaleGestureListener() {

        //上次手势缩放比例
        private float preScale = 1;

        //当前手势缩放比例
        private float curScale = 1;

        @Override
        public boolean onScale(ScaleGestureDetector detector) {

            float scale = detector.getScaleFactor();
            //缩放系数计算
            curScale = calculateScaleFactor(preScale, scale);
            //缩放比例
            mMatrix.setScale(curScale, curScale);
            //显示
            mIv.setImageMatrix(mMatrix);

            return false;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return true;//返回true,才会进入onScale()
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            //缩放系数计算
            preScale = calculateScaleFactor(preScale, detector.getScaleFactor());
        }

        //最大缩放比例
        private static final float SCALE_MAX = 3f;
        //最小缩放比例
        private static final float SCALE_MIN = 0.5f;

        /**
         * 计算最终缩放范围<br>
         *     如果缩放范围超出规定范围（显示临界值）
         */
        private float calculateScaleFactor(float oldScale, float curScale){
            if (oldScale * curScale > SCALE_MAX){
                return SCALE_MAX;
            }else if (oldScale * curScale < SCALE_MIN){
                return SCALE_MIN;
            }else {
                return preScale * curScale;
            }
        }
    };
}
