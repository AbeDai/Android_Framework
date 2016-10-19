package example.abe.com.android.activity.retrofit;

import java.util.HashMap;

import static example.abe.com.android.activity.retrofit.RetrofitUtil.handleCall;


/**
 * Created by abe on 16/5/22.
 */
public class NetworkPresent {

    public static void getPersonInfo(String secretId, String userId, RetrofitUtil.ABCallback abCallback) {
        HashMap<String, String> args = initArgs();
        args.put("cla", "system");
        args.put("m", "getUserInfo");
        args.put("secretId", secretId);
        args.put("userId", userId);
        handleCall(abCallback, args, PersonModel.class);
    }

    public static void getSetInfo(String secretId, RetrofitUtil.ABCallback abCallback) {
        HashMap<String, String> args = initArgs();
        args.put("cla", "system");
        args.put("m", "getConfigData");
        args.put("secretId", secretId);
        handleCall(abCallback, args, InfoModel.class);
    }

    public static void getAllCar(String secretId, RetrofitUtil.ABCallback abCallback) {
        HashMap<String, String> args = initArgs();
        args.put("cla", "monitor");
        args.put("m", "getAllCarList");
        args.put("secretId", secretId);
        handleCall(abCallback, args, CarModel.class);
    }

    private static HashMap<String, String> initArgs() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("ver", "2.0");
        return hashMap;
    }
}


