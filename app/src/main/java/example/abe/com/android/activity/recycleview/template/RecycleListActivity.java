package example.abe.com.android.activity.recycleview.template;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.BindView;

import java.util.ArrayList;
import java.util.List;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.util.LogUtil;

public class RecycleListActivity extends BaseActivity implements ListAdapter.OnItemClickListener {

    @BindView(R.id.act_abrecycle_list_rv)
    protected RecyclerView mRecyclerView;
    private List<String> mData;
    private ListAdapter mAdapter;

    @Override
    public int getLayoutID() {
        return R.layout.activity_recycle_list;
    }

    @Override
    public void initData() {
        mData = new ArrayList<>();
        for (int i = 'a'; i <= 'z'; i++) {
            mData.add(String.valueOf((char) i));
        }
    }

    @Override
    public void initView() {
        //RecycleView视图
        mAdapter = new ListAdapter(this, mData);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);//设置adapter
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//设置布局管理器
        mRecyclerView.addItemDecoration(new DivideDecoration());//添加分割线
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//设置Item增加、移除动画

        //增加删除按钮
        findViewById(R.id.act_abrecycle_list_btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.addData(1);
            }
        });
        findViewById(R.id.act_abrecycle_list_btn_del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.removeData(1);
            }
        });
    }

    @Override
    public void onClickItem(View view, int position) {
        LogUtil.d("onClickItem:\nview--" + view + "\nposition--" + position);
    }

    @Override
    public boolean onLongClickItem(View view, int position) {
        LogUtil.d("onLongClickItem:\nview--" + view + "\nposition--" + position);
        return false;
    }
}

