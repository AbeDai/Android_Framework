package example.abe.com.android.activity.drawing.gaussianblur;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.BindView;

import example.abe.com.android.R;
import example.abe.com.android.activity.drawing.matrix.MatrixImageView;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.util.ResourceUtil;
import example.abe.com.framework.util.ToastUtil;

public class GaussianBlurActivity extends BaseActivity {

    @BindView(R.id.act_gaussian_blur_iv_original_show)
    protected ImageView mIvOriginalShow;

    @BindView(R.id.act_gaussian_blur_iv_gaussian_show)
    protected ImageView mIvGaussianShow;

    @BindView(R.id.act_gaussian_blur_tv_time)
    protected TextView mTvTime;

    @BindView(R.id.act_matrix_matrix_view)
    protected MatrixImageView matrixView;

    @BindView(R.id.act_gaussian_blur_seek_bar)
    protected SeekBar mSeekBar;

    private long mTime;

    @Override
    public int getLayoutID() {
        return R.layout.activity_gaussian_blur;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
        mSeekBar.setOnSeekBarChangeListener(seekListener);
    }

    // 拖动条事件
    private SeekBar.OnSeekBarChangeListener seekListener = new SeekBar.OnSeekBarChangeListener() {

        // 拖动条停止执行
        public void onStopTrackingTouch(SeekBar seekBar) {
            ToastUtil.makeText("Radius（高斯模糊）：" + mSeekBar.getProgress());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final Bitmap originalBitmap = ResourceUtil.getBitmap(R.drawable.icon_card_view);
                    mTime = System.currentTimeMillis();
                    final Bitmap gaussianBitmap = GaussianBlurUtil.blur(originalBitmap, mSeekBar.getProgress(), false);
                    mTime = System.currentTimeMillis() - mTime;
                    uiHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mIvOriginalShow.setImageBitmap(originalBitmap);
                            mIvGaussianShow.setImageBitmap(gaussianBitmap);
                            mTvTime.setText(String.valueOf(mTime));
                        }
                    });
                }
            }).start();
        }

        // 开始执行
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        // 拖动中
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
        }
    };
}
