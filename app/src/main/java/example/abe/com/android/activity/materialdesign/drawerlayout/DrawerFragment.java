package example.abe.com.android.activity.materialdesign.drawerlayout;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.BindView;

import java.util.ArrayList;
import java.util.List;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseFragment;
import example.abe.com.framework.recycleview.adapter.BaseAdapter;
import example.abe.com.framework.util.ToastUtil;

/**
 * Created by abe on 16/10/20.
 */
public class DrawerFragment extends BaseFragment {

    @BindView(R.id.frag_drawer_layout_rv)
    protected RecyclerView mRv;
    private BaseAdapter<TextModel> mAdapter;
    private List<TextModel> mListData;

    public static DrawerFragment instance() {
        return new DrawerFragment();
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_drawer_layout;
    }

    @Override
    public void initData() {
        mListData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            String text = String.format("%d%d%d%d%d", i, i, i, i, i);
            mListData.add(new TextModel(text));
        }
    }

    @Override
    public void initView() {
        mAdapter = new BaseAdapter<>(this.getContext(), mListData);
        mAdapter.addItemViewDelegate(new TextDelegate());
        mAdapter.setOnItemClickListener(itemClickListener);
        mRv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mRv.setAdapter(mAdapter);
    }

    private BaseAdapter.OnItemClickListener itemClickListener = new BaseAdapter.OnItemClickListener<TextModel>() {
        @Override
        public void onItemClick(View view, RecyclerView.ViewHolder holder, TextModel data, int position) {
            ((DrawerLayoutActivity)DrawerFragment.this.getActivity()).setContentText(data.getText());
            ((DrawerLayoutActivity)DrawerFragment.this.getActivity()).closeDrawer();
        }

        @Override
        public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, TextModel data, int position) {
            ToastUtil.makeText("onItemLongClick");
            return true;
        }
    };
}
