package example.abe.com.android_framework.activity.volley;

/**
 * Created by Administrator on 2016/8/1.
 */
public class PlModel {
    public String code;
    public String lat;
    public String lng;
    public String cip;

    public String toString() {
        return "code: " + code + "\n" +
                "lat: " + lat + "\n" +
                "lng: " + lng + "\n" +
                "cip: " + cip + "\n";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getCip() {
        return cip;
    }

    public void setCip(String cip) {
        this.cip = cip;
    }
}