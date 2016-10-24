package example.abe.com.android.activity.album;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import example.abe.com.android.activity.album.model.PhotoBucketModel;
import example.abe.com.android.activity.album.model.PhotoItemModel;
import example.abe.com.framework.util.LogUtil;

/**
 * Created by abe on 16/10/24.
 */
public class PhotoAlbumHelper {

    public interface PhotoAlbumWatcher {
        void onRequestPhotoAlbumSuccessful(List<PhotoBucketModel> list);
    }

    private Context mContext;
    private ContentResolver mContentResolver;
    private HashMap<String, PhotoBucketModel> mBucketList;//图片集列表
    private List<PhotoAlbumWatcher> mWatcherList;//相册观察者
    private boolean mIsNotifying;//是否正在分发
    private static int DISPENSE_WATCHERS_WHAT = 100;
    Handler mUIHandler = new Handler(Looper.getMainLooper()){
        public void handleMessage(Message msg) {
            if (msg.what == DISPENSE_WATCHERS_WHAT){
                dispenseWatchers();
            }
        }
    };

    /**
     * 单例
     *
     * @param context Context
     * @return PhotoAlbumHelper
     */
    public static PhotoAlbumHelper getInstance(Context context) {
        PhotoAlbumHelper instance = new PhotoAlbumHelper(context);
        return instance;
    }

    /**
     * 通知分发照片到观察者
     */
    public void notifyPhotoBucketWatchers() {
        mIsNotifying = true;
        if (mBucketList != null) {
            mUIHandler.sendEmptyMessage(DISPENSE_WATCHERS_WHAT);
            mIsNotifying = false;
        }
    }

    /**
     * 添加相册观察者
     * @param watcher 相册观察者
     */
    public void addPhotoAlbumWatcher(PhotoAlbumWatcher watcher){
        if (watcher != null){
            mWatcherList.add(watcher);
        }
    }

    //分发观察者
    private void dispenseWatchers() {
        if (mBucketList != null) {
            List<PhotoBucketModel> tmpList = new ArrayList<>();
            for (Map.Entry<String, PhotoBucketModel> entry : mBucketList.entrySet()) {
                tmpList.add(entry.getValue());
            }
            for (PhotoAlbumWatcher watcher : mWatcherList) {
                watcher.onRequestPhotoAlbumSuccessful(tmpList);
            }
        }
    }

    //构造函数
    private PhotoAlbumHelper(Context context) {
        if (mContext == null) {
            mContext = context;
            mContentResolver = context.getContentResolver();
        }
        mWatcherList = new ArrayList<>();
        buildAlbum();
    }

    //子线程构造相册
    private void buildAlbum() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //构造相册索引
                buildPhotoBucket();
                //分发观察者
                if (mIsNotifying == true) {
                    mUIHandler.sendEmptyMessage(DISPENSE_WATCHERS_WHAT);
                    mIsNotifying = false;
                }
            }
        });
        thread.start();
    }

    /**
     * 构建相册
     */
    private void buildPhotoBucket() {
        if (mBucketList == null) {
            mBucketList = new HashMap<>();
        }
        String columns[] = new String[]{MediaStore.Images.Media._ID, MediaStore.Images.Media.BUCKET_ID, MediaStore.Images.Media.PICASA_ID, MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DISPLAY_NAME, MediaStore.Images.Media.TITLE, MediaStore.Images.Media.SIZE, MediaStore.Images.Media.BUCKET_DISPLAY_NAME};
        Cursor cur = mContentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null,
                MediaStore.Images.Media.DATE_MODIFIED + " desc");
        if (cur != null && cur.moveToFirst()) {
            int idIndex = cur.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
            int dataIndex = cur.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            int displayNameIndex = cur.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
            int bucketIdIndex = cur.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_ID);
            do {
                //判断照片的名字是否合法，例如.jpg .png 图片名字是不合法的，直接过滤掉
                if (cur.getString(dataIndex).substring(
                        cur.getString(dataIndex).lastIndexOf("/") + 1,
                        cur.getString(dataIndex).lastIndexOf("."))
                        .replaceAll(" ", "").length() <= 0) {
                    LogUtil.d("出现了异常图片的地址：cur.getString(photoPathIndex)=" + cur.getString(dataIndex));
                } else {
                    String _id = cur.getString(idIndex);
                    String path = cur.getString(dataIndex);
                    String bucketName = cur.getString(displayNameIndex);
                    String bucketId = cur.getString(bucketIdIndex);
                    PhotoBucketModel bucket = mBucketList.get(bucketId);
                    if (bucket == null) {
                        bucket = new PhotoBucketModel();
                        bucket.setBucketName(bucketName);
                        mBucketList.put(bucketId, bucket);
                    }
                    bucket.setCount(bucket.getCount()+1);
                    PhotoItemModel photoItemModel = new PhotoItemModel();
                    photoItemModel.setImageId(_id);
                    photoItemModel.setImagePath(path);
                    bucket.getPhotos().add(photoItemModel);
                }
            } while (cur.moveToNext());
            cur.close();
        }
    }
}
