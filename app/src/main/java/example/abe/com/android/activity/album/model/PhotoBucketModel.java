package example.abe.com.android.activity.album.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


/**
 * 单个目录下的相册对象
 */
public class PhotoBucketModel implements Parcelable {
    private int count;
    private String bucketName;
    private ArrayList<PhotoItemModel> photos;

    public PhotoBucketModel() {
        count = 0;
        bucketName = "";
        photos = new ArrayList<>();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public ArrayList<PhotoItemModel> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<PhotoItemModel> photos) {
        this.photos = photos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.count);
        dest.writeString(this.bucketName);
        dest.writeList(this.photos);
    }

    protected PhotoBucketModel(Parcel in) {
        this.count = in.readInt();
        this.bucketName = in.readString();
        this.photos = new ArrayList<PhotoItemModel>();
        in.readList(this.photos, PhotoItemModel.class.getClassLoader());
    }

    public static final Parcelable.Creator<PhotoBucketModel> CREATOR = new Parcelable.Creator<PhotoBucketModel>() {
        @Override
        public PhotoBucketModel createFromParcel(Parcel source) {
            return new PhotoBucketModel(source);
        }

        @Override
        public PhotoBucketModel[] newArray(int size) {
            return new PhotoBucketModel[size];
        }
    };
}
