package example.abe.com.android.activity.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.example.BindView;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseFragment;

/**
 * Created by abe on 16/8/3.
 */
public class ViewPagerFragment extends BaseFragment {

    @BindView(R.id.fragment_screen_slide_page_tv_title)
    protected TextView tvTitle;
    @BindView(R.id.fragment_screen_slide_page_tv_content)
    protected TextView tvContent;
    private String mTitle;
    private String mContent;

    public static Fragment newInstance(String title, String content) {
        ViewPagerFragment fragment = new ViewPagerFragment();

        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("content", content);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public int getLayoutID(){
        return R.layout.fragment_view_pager;
    }

    @Override
    public void initData(){
        Bundle args = getArguments();
        mTitle = args.getString("title");
        mContent = args.getString("content");
    }

    @Override
    public void initView(){
        tvTitle.setText(mTitle);
        tvContent.setText(mContent);
    }
}
