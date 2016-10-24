package example.abe.com.android.activity.album;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.BindView;

import java.util.ArrayList;
import java.util.List;

import example.abe.com.android.R;
import example.abe.com.android.activity.album.adapter.PhotoImageDelegate;
import example.abe.com.android.activity.album.model.PhotoItemModel;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.recycleview.adapter.BaseAdapter;
import example.abe.com.framework.util.ToastUtil;


public class PhotoActivity extends BaseActivity implements BaseAdapter.OnItemClickListener<PhotoItemModel>{

    public static String INTENT_PHOTOS = "photo_activity_photos";

    @BindView(R.id.act_photo_rv)
    protected RecyclerView mRv;
    private BaseAdapter<PhotoItemModel> mAdapter;
    private List<PhotoItemModel> mListData;

    @Override
    public int getLayoutID(){
        return R.layout.activity_photo;
    }

    @Override
    public void initData(){
        mListData = new ArrayList<>();

        Intent intent = getIntent();
        if (intent != null){
            ArrayList<PhotoItemModel> list = intent.getParcelableArrayListExtra(INTENT_PHOTOS);
            mListData.addAll(list);
        }
    }

    @Override
    public void initView(){
        mAdapter = new BaseAdapter<>(this, mListData);
        mAdapter.addItemViewDelegate(new PhotoImageDelegate());
        mAdapter.setOnItemClickListener(this);
        mRv.setLayoutManager(new GridLayoutManager(this, 3));
        mRv.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, PhotoItemModel data, int position) {
        ToastUtil.makeText("id: " + data.getImageId() + "\npath: " + data.getImagePath());
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, PhotoItemModel data, int position) {
        return false;
    }
}
