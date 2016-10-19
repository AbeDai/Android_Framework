package example.abe.com.android.activity.recycleview.example.baseadapter;

/**
 * Created by abe on 16/10/11.
 */
public class ImageTextModel {

    private String imgUrl;
    private String text;

    public ImageTextModel(String imgUrl, String text){
        this.imgUrl = imgUrl;
        this.text = text;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
