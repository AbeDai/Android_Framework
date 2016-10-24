package example.abe.com.android.activity.album.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by abe on 16/10/24.
 */
public class PhotoItemModel implements Parcelable {

    //图片ID
    private String imageId;
    //原图路径
    private String imagePath;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.imageId);
        dest.writeString(this.imagePath);
    }

    public PhotoItemModel() {
    }

    protected PhotoItemModel(Parcel in) {
        this.imageId = in.readString();
        this.imagePath = in.readString();
    }

    public static final Parcelable.Creator<PhotoItemModel> CREATOR = new Parcelable.Creator<PhotoItemModel>() {
        @Override
        public PhotoItemModel createFromParcel(Parcel source) {
            return new PhotoItemModel(source);
        }

        @Override
        public PhotoItemModel[] newArray(int size) {
            return new PhotoItemModel[size];
        }
    };
}
