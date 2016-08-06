package example.abe.com.android_framework.activity.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.main.BaseFragment;
import example.abe.com.framework.viewinject.ContentView;
import example.abe.com.framework.viewinject.ViewInject;

/**
 * Created by abe on 16/8/3.
 */
@ContentView(id = R.layout.fragment_view_pager)
public class ViewPagerFragment extends BaseFragment {

  @ViewInject(id = R.id.fragment_screen_slide_page_tv_title)
    private TextView tvTitle;
    @ViewInject(id = R.id.fragment_screen_slide_page_tv_content)
    private TextView tvContent;
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
