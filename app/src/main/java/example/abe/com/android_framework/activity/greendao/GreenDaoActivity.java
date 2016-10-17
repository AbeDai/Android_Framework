package example.abe.com.android_framework.activity.greendao;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.BindView;
import com.example.OnClick;

import java.util.ArrayList;
import java.util.List;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.activity.greendao.dao.DBManager;
import example.abe.com.android_framework.activity.greendao.dao.UserModel;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.recycleview.adapter.BaseAdapter;
import example.abe.com.framework.recycleview.base.ItemHeadFootDelegate;
import example.abe.com.framework.recycleview.base.ItemViewDelegate;
import example.abe.com.framework.recycleview.base.ViewHolder;
import example.abe.com.framework.recycleview.wrapper.HeaderAndFooterWrapper;

public class GreenDaoActivity extends BaseActivity {

    @BindView(R.id.act_green_dao_et_age)
    protected EditText mEtAge;
    @BindView(R.id.act_green_dao_et_name)
    protected EditText mEtName;
    @BindView(R.id.act_green_dao_rv)
    protected RecyclerView mRv;
    private List<UserModel> mDatas;
    private RecyclerView.Adapter mAdapter;

    @Override
    public int getLayoutID() {
        return R.layout.activity_green_dao;
    }

    @Override
    public void initData() {
        mDatas = new ArrayList<>();
    }

    @Override
    public void initView() {
        BaseAdapter baseAdapter = new BaseAdapter<>(this, mDatas);
        baseAdapter.addItemViewDelegate(itemDelegate);
        HeaderAndFooterWrapper headWrapper = new HeaderAndFooterWrapper(this, baseAdapter);
        headWrapper.addHeaderDelegate(headDelegate);
        mAdapter = headWrapper;
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(mAdapter);
    }

    @OnClick({R.id.act_green_dao_btn_insert})
    public void onInsert() {
        if (mEtAge.getText().length() != 0 && mEtName.getText().length() != 0) {
            UserModel userModel = new UserModel();
            userModel.setAge(Integer.valueOf(mEtAge.getText().toString()));
            userModel.setName(mEtName.getText().toString());
            DBManager.getInstance(this).insertUser(userModel);
        }
    }

    @OnClick({R.id.act_green_dao_btn_delete})
    public void onDelete() {
        if (mEtAge.getText().length() != 0 && mEtName.getText().length() != 0) {
            UserModel userModel = new UserModel();
            userModel.setId(1L);
            userModel.setName(mEtName.getText().toString());
            userModel.setAge(Integer.valueOf(mEtAge.getText().toString()));
            DBManager.getInstance(this).deleteUser(userModel);
        }
    }

    @OnClick({R.id.act_green_dao_btn_update})
    public void onUpdate() {
        if (mEtAge.getText().length() != 0 && mEtName.getText().length() != 0) {
            UserModel userModel = new UserModel();
            userModel.setId(1L);
            userModel.setName(mEtName.getText().toString());
            userModel.setAge(Integer.valueOf(mEtAge.getText().toString()));
            DBManager.getInstance(this).updateUser(userModel);
        }
    }

    @OnClick({R.id.act_green_dao_btn_query})
    public void onQuery() {
        mDatas.clear();
        mDatas.addAll(DBManager.getInstance(this).queryUserList());
        mAdapter.notifyDataSetChanged();
    }

    private ItemHeadFootDelegate headDelegate = new ItemHeadFootDelegate() {
        @Override
        public View getItemView(Context context, ViewGroup parent) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.item_user_info_view, parent, false);
            return view;
        }

        @Override
        public void bindViewHolder(ViewHolder holder, Object o, int position) {

        }
    };

    private ItemViewDelegate<UserModel> itemDelegate = new ItemViewDelegate<UserModel>() {
        @Override
        public View getItemView(Context context, ViewGroup parent) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.item_user_info_view, parent, false);
            return view;
        }

        @Override
        public boolean isForViewType(UserModel item, int position) {
            return true;
        }

        @Override
        public void bindViewHolder(ViewHolder holder, UserModel userModel, int position) {
            holder.setText(R.id.item_user_info_tv_id, String.valueOf(userModel.getId()));
            holder.setText(R.id.item_user_info_tv_name, userModel.getName());
            holder.setText(R.id.item_user_info_tv_age, String.valueOf(userModel.getAge()));
        }
    };
}