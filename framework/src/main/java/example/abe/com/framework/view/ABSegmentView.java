package example.abe.com.framework.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import example.abe.com.framework.R;
import example.abe.com.framework.util.ABLog;

/**
 * 自定义iOS SegmentView
 */
public class ABSegmentView extends LinearLayout {

    private String mTextLeft = "";
    private String mTextRight = "";
    private int mTextColorSelector = R.color.ab_seg_text_color_selector;
    private int mLeftBgSelector = R.drawable.ab_seg_left_bg_selector;
    private int mRightBgSelector = R.drawable.ab_seg_right_bg_selector;
    private int mTextSize = 30;

    private Context mContext;
    private TextView mTvLeft;
    private TextView mTvRight;
    private onSegmentViewClickListener mListener;


    public ABSegmentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context, attrs);
        initView();
    }

    public ABSegmentView(Context context) {
        super(context);
        initData(context, null);
        initView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        ABLog.e("widthMeasureSpec:" + widthMeasureSpec + "  heightMeasureSpec:" + heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 设置字体大小
     *
     * @param px 字体大小（单位:raw pixels）
     */
    public void setSegmentTextSize(int px) {
        mTvLeft.setTextSize(TypedValue.COMPLEX_UNIT_PX, px);
        mTvRight.setTextSize(TypedValue.COMPLEX_UNIT_PX, px);
    }

    /**
     * 设置文字
     *
     * @param text     文字内容
     * @param position SegmentPosition枚举
     */
    public void setSegmentText(String text, SegmentPosition position) {
        if (position == SegmentPosition.LEFT) {
            mTextLeft = text;
            mTvLeft.setText(mTextLeft);
        }
        if (position == SegmentPosition.RIGHT) {
            mTextRight = text;
            mTvRight.setText(mTextRight);
        }
    }

    /**
     * 设置文字
     *
     * @param id       XML资源id
     * @param position SegmentPosition枚举
     */
    public void setSegmentBackgroud(int id, SegmentPosition position) {
        if (position == SegmentPosition.LEFT) {
            mLeftBgSelector = id;
            mTvLeft.setBackgroundResource(mLeftBgSelector);
        }
        if (position == SegmentPosition.RIGHT) {
            mRightBgSelector = id;
            mTvRight.setBackgroundResource(mRightBgSelector);
        }
    }

    /**
     * 设置SegmentView字体变化的selector
     *
     * @param id selector的XML文件地址(注：必须是符合条件的XML文件ID)
     */
    public void setTextColorSelector(int id) {
        mTextColorSelector = id;
        mTvLeft.setTextColor(getColorStateList(mTextColorSelector));
        mTvRight.setTextColor(getColorStateList(mTextColorSelector));
    }

    /**
     * 设置监听
     *
     * @param listener 监听类
     */
    public void setOnSegmentViewClickListener(onSegmentViewClickListener listener) {
        this.mListener = listener;
    }

    /**
     * 根据位置，获取子TextView
     *
     * @param position 位置
     * @return 子TextView
     */
    public TextView getChildView(SegmentPosition position) {
        if (position == SegmentPosition.LEFT) {
            return mTvLeft;
        }
        if (position == SegmentPosition.RIGHT) {
            return mTvRight;
        }
        return null;
    }

    /**
     * 数据初始化
     * @param context 上下文环境
     * @param attrs XML属性
     */
    private void initData(Context context, AttributeSet attrs) {
        mContext = context;

        if (attrs == null) return;

        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.segmentView);
        mTextLeft += typedArray.getString(R.styleable.segmentView_text_left);
        mTextRight += typedArray.getString(R.styleable.segmentView_text_right);
        mTextColorSelector = typedArray.getResourceId(R.styleable.segmentView_text_color_selector, R.color.ab_seg_text_color_selector);
        mLeftBgSelector = typedArray.getResourceId(R.styleable.segmentView_background_selector_left, R.drawable.ab_seg_left_bg_selector);
        mRightBgSelector = typedArray.getResourceId(R.styleable.segmentView_background_selector_right, R.drawable.ab_seg_right_bg_selector);
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.segmentView_text_size, 30);
        typedArray.recycle();
    }

    /**
     * 视图初始化
     */
    private void initView() {

        LayoutParams layoutParams = new LayoutParams(0, LayoutParams.MATCH_PARENT, 1);
        layoutParams.setMargins(0, 0, -1, 0);
        mTvLeft = new TextView(mContext);
        mTvLeft.setLayoutParams(layoutParams);
        mTvLeft.setGravity(Gravity.CENTER);
        mTvLeft.setPadding(3, 6, 3, 6);
        mTvLeft.setBackgroundResource(mLeftBgSelector);
        mTvLeft.setTextColor(getColorStateList(mTextColorSelector));
        mTvLeft.setSelected(true);
        mTvLeft.setText(mTextLeft);

        layoutParams = new LayoutParams(0, LayoutParams.MATCH_PARENT, 1);
        layoutParams.setMargins(-1, 0, 0, 0);
        mTvRight = new TextView(mContext);
        mTvRight.setLayoutParams(layoutParams);
        mTvRight.setGravity(Gravity.CENTER);
        mTvRight.setPadding(3, 6, 3, 6);
        mTvRight.setBackgroundResource(mRightBgSelector);
        mTvRight.setTextColor(getColorStateList(mTextColorSelector));
        mTvRight.setText(mTextRight);

        setSegmentTextSize(mTextSize);

        this.setOrientation(LinearLayout.HORIZONTAL);
        this.setBackgroundColor(Color.TRANSPARENT);
        this.removeAllViews();
        this.addView(mTvLeft);
        this.addView(mTvRight);
        this.invalidate();

        mTvLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTvLeft.isSelected()) return;

                mTvLeft.setSelected(true);
                mTvRight.setSelected(false);
                if (mListener != null) {
                    mListener.onSegmentViewClick(ABSegmentView.this, SegmentPosition.LEFT);
                }
            }
        });

        mTvRight.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mTvRight.isSelected()) return;

                mTvRight.setSelected(true);
                mTvLeft.setSelected(false);
                if (mListener != null) {
                    mListener.onSegmentViewClick(ABSegmentView.this, SegmentPosition.RIGHT);
                }
            }
        });
    }

    /**
     * 根据XML，获取ColorStateList
     *
     * @param id XML文件ID
     * @return ColorStateList对象
     */
    private ColorStateList getColorStateList(int id) {
        try {
            XmlPullParser xrp = getResources().getXml(id);
            return ColorStateList.createFromXml(getResources(), xrp);
        } catch (IOException e) {
            throw  new ClassCastException("Abe_SegmentView: ColorStateList IOException failed!");
        } catch (XmlPullParserException e) {
            throw  new ClassCastException("Abe_SegmentView: ColorStateList XmlPullParserException failed!");
        } catch (Resources.NotFoundException e) {
            throw new ClassCastException("Abe_SegmentView: ColorStateList NotFoundException failed!");
        } catch (Exception e) {
            throw new ClassCastException("Abe_SegmentView: ColorStateList failed!");
        }
    }

    /**
     * segment的位置
     */
    public static enum SegmentPosition {
        LEFT, RIGHT
    }

    /**
     * 监听类接口
     */
    public interface onSegmentViewClickListener {

        /**
         * 状态改变时，触发
         *
         * @param v        ViewGroup总视图
         * @param position 点击位置（SegmentPosition类型）
         */
        void onSegmentViewClick(ABSegmentView v, SegmentPosition position);
    }
}