package example.abe.com.android_framework.network;

import java.util.HashMap;

import example.abe.com.android_framework.model.AllCarModel;
import example.abe.com.android_framework.model.PersonInfoModel;
import example.abe.com.android_framework.model.SetInfoModel;
import example.abe.com.framework.network.ABRetrofitUtil.ABCallback;

import static example.abe.com.framework.network.ABRetrofitUtil.handleCall;

/**
 * Created by abe on 16/5/22.
 */
public class ABNetworkPresent {

    public static void getPersonInfo(String secretId, String userId, ABCallback abCallback) {
        HashMap<String, String> args = initArgs();
        args.put("cla", "system");
        args.put("m", "getUserInfo");
        args.put("secretId", secretId);
        args.put("userId", userId);
        handleCall(abCallback, args, PersonInfoModel.class);
    }

    public static void getSetInfo(String secretId, ABCallback abCallback) {
        HashMap<String, String> args = initArgs();
        args.put("cla", "system");
        args.put("m", "getConfigData");
        args.put("secretId", secretId);
        handleCall(abCallback, args, SetInfoModel.class);
    }

    public static void getAllCar(String secretId, ABCallback abCallback) {
        HashMap<String, String> args = initArgs();
        args.put("cla", "monitor");
        args.put("m", "getAllCarList");
        args.put("secretId", secretId);
        handleCall(abCallback, args, AllCarModel.class);
    }

    private static HashMap<String, String> initArgs() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("ver", "2.0");
        return hashMap;
    }
}


