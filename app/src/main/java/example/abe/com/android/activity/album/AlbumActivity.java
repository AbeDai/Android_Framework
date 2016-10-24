package example.abe.com.android.activity.album;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.BindView;
import com.example.OnClick;

import java.util.ArrayList;
import java.util.List;

import example.abe.com.android.R;
import example.abe.com.android.activity.album.adapter.PhotoBucketDelegate;
import example.abe.com.android.activity.album.model.PhotoBucketModel;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.recycleview.adapter.BaseAdapter;

import static example.abe.com.android.activity.album.PhotoActivity.INTENT_PHOTOS;

public class AlbumActivity extends BaseActivity implements PhotoAlbumHelper.PhotoAlbumWatcher,
        BaseAdapter.OnItemClickListener<PhotoBucketModel> {

    @BindView(R.id.act_album_rv)
    protected RecyclerView mRv;
    private BaseAdapter<PhotoBucketModel> mAdapter;
    private List<PhotoBucketModel> mListData;
    private PhotoAlbumHelper helper;

    @Override
    public int getLayoutID() {
        return R.layout.activity_album;
    }

    @Override
    public void initData() {
        helper = PhotoAlbumHelper.getInstance(this);
        helper.addPhotoAlbumWatcher(this);
        helper.notifyPhotoBucketWatchers();
        mListData = new ArrayList<>();
    }

    @Override
    public void initView() {
        mAdapter = new BaseAdapter<>(this, mListData);
        mAdapter.addItemViewDelegate(new PhotoBucketDelegate());
        mAdapter.setOnItemClickListener(this);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(mAdapter);
    }

    @OnClick({R.id.act_album_btn_refresh})
    public void onRefreshClick() {
        helper.notifyPhotoBucketWatchers();
    }

    @Override
    public void onRequestPhotoAlbumSuccessful(List<PhotoBucketModel> list) {
        mListData.clear();
        mListData.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, PhotoBucketModel data, int position) {
        Intent intent = new Intent(this, PhotoActivity.class);
        intent.putParcelableArrayListExtra(INTENT_PHOTOS, data.getPhotos());
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, PhotoBucketModel data, int position) {
        return false;
    }
}
