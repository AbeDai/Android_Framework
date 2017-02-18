package example.abe.com.android.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.BindView;

import example.abe.com.android.R;
import example.abe.com.android.model.FunctionModel;
import example.abe.com.framework.main.BaseFragment;
import example.abe.com.framework.recycleview.adapter.BaseAdapter;

/**
 * Created by abe on 17/2/18.
 */

public class MainListFragment extends BaseFragment implements BaseAdapter.OnItemClickListener<FunctionModel>{

    @BindView(R.id.frag_main_list_recycle_view)
    protected RecyclerView mRv;

    private BaseAdapter<FunctionModel> mAdapter;

    private int mTabIndex;

    public static MainListFragment newInstance(int tabIndex) {
        MainListFragment fragment = new MainListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("tab_index", tabIndex);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_main_list;
    }

    @Override
    public void initData() {
        mTabIndex = getArguments().getInt("tab_index");
    }

    @Override
    public void initView() {
        mAdapter = new BaseAdapter<>(getContext(), KnowledgeStructHelper.getListTab().get(mTabIndex).getTabList());
        mAdapter.addItemViewDelegate(new MainAdapterDelegate());
        mAdapter.setOnItemClickListener(this);
        mRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mRv.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, FunctionModel data, int position) {
        Class clazz = KnowledgeStructHelper.getListTab().get(mTabIndex).getTabList().get(position).getClazz();
        Intent intent = new Intent(getActivity(), clazz);
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, FunctionModel data, int position) {
        return false;
    }
}
