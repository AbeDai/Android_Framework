package example.abe.com.framework.main;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import example.abe.com.framework.viewinject.ViewInjectUtil;

/**
 * Created by abe on 16/8/6.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {

        View contentView = inflater.inflate(getLayoutID(), container, false);
        ViewInjectUtil.inject(this, contentView);
        initData();
        initView();

        return contentView;
    }

    abstract public void initData();

    abstract public void initView();

    abstract public int getLayoutID();
}