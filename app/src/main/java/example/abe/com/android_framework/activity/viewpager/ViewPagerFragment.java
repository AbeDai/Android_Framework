package example.abe.com.android_framework.activity.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import example.abe.com.android_framework.R;

/**
 * Created by abe on 16/8/3.
 */

public class ViewPagerFragment extends Fragment {

        private ViewGroup rootView;
        private TextView tvTitle;
        private TextView tvContent;

        public static Fragment instance(String title, String content){
            ViewPagerFragment fragment = new ViewPagerFragment();
            Bundle args = new Bundle();
            args.putString("title", title);
            args.putString("content", content);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            //数据属性
            Bundle args = getArguments();
            String title = args.getString("title");
            String content = args.getString("content");

            //设置界面
            rootView = (ViewGroup) inflater
                    .inflate(R.layout.fragment_view_pager, container, false);
            tvTitle = (TextView)rootView
                    .findViewById(R.id.fragment_screen_slide_page_tv_title);
            tvContent = (TextView)rootView
                    .findViewById(R.id.fragment_screen_slide_page_tv_content);

            //设置UI
            tvTitle.setText(title);
            tvContent.setText(content);

            return rootView;
        }
    }
