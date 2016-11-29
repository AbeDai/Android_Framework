package example.abe.com.android.activity.drawing.surfaceview;

import com.example.BindView;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;

/**
 * Created by abe on 16/11/28.
 */

public class SurfaceViewActivity extends BaseActivity {

    @BindView(R.id.act_surface_view_surface_ui)
    protected SurfaceUI mSurfaceUI;

    @Override
    public int getLayoutID(){
        return R.layout.activity_surface_view;
    }

    @Override
    public void initData(){
    }

    @Override
    public void initView(){
    }
}
