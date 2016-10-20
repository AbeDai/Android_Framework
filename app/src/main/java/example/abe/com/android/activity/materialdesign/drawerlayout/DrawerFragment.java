package example.abe.com.android.activity.materialdesign.drawerlayout;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.BindView;

import java.util.ArrayList;
import java.util.List;

import example.abe.com.android.R;
import example.abe.com.android.activity.banner.BannerFragment;
import example.abe.com.framework.main.BaseFragment;
import example.abe.com.framework.recycleview.adapter.BaseAdapter;

/**
 * Created by abe on 16/10/20.
 */

public class DrawerFragment extends BaseFragment {

    @BindView(R.id.frag_drawer_layout_rv)
    protected RecyclerView mRv;
    private BaseAdapter<TextModel> mAdapter;
    private List<TextModel> mListData;

    public static BannerFragment instance() {
        return new BannerFragment();
    }

    @Override
    public int getLayoutID(){
        return R.layout.fragment_drawer_layout;
    }

    @Override
    public void initData() {
        mListData = new ArrayList<>();
        mListData.add(new TextModel("111111111"));
        mListData.add(new TextModel("222222222"));
        mListData.add(new TextModel("333333333"));
        mListData.add(new TextModel("444444444"));
        mListData.add(new TextModel("555555555"));
    }

    @Override
    public void initView() {
        mAdapter = new BaseAdapter<>(this.getContext(), mListData);
        mAdapter.addItemViewDelegate(new TextDelegate());
        mAdapter.setOnItemClickListener(itemClickListener);
        mRv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mRv.setAdapter(mAdapter);
    }

    private BaseAdapter.OnItemClickListener itemClickListener =  new BaseAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, RecyclerView.ViewHolder holder, Object data, int position) {

        }

        @Override
        public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, Object data, int position) {
            return true;
        }
    };
}
