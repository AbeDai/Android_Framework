package example.abe.com.android_framework.activity.retrofit;

/**
 * Created by abe on 16/5/22.
 */

public class PersonModel {
    public Integer cityId;
    public String companyName;
    public String email;
    public String phoneNum;
    public Integer proviceId;
    public String remark;
    public Integer userId;
    public String userName;
    public String userRealName;

    public String toString(){
        return "cityId:" + cityId + ", companyName:" + companyName+ ", email:" + email
                + ", phoneNum:" + phoneNum+ ", proviceId:" + proviceId+ ", remark:" + remark
                + ", userId:" + userId+ ", userName:" + userName+ ", userRealName:" + userRealName;
    }
}
