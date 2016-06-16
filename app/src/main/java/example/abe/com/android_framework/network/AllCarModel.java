package example.abe.com.android_framework.network;

import java.util.Iterator;
import java.util.List;

/**
 * Created by abe on 16/5/23.
 */

public class AllCarModel {
    public Integer total;
    public List<CarInfoModel> list;

    public String toString(){
        String str = "total:" + total + "\n" + "list:\n";
        Iterator<CarInfoModel> i = list.iterator();
        while (i.hasNext()){
            CarInfoModel model = i.next();
            str += model.toString() + "\n";
        }
        return str;
    }

    public class CarInfoModel{
        public Integer carId;
        public String carNo;
        public Integer carStatus;
        public Integer dir;
        public Double lg;
        public Double lt;
        public Integer orgId;
        public Integer speed;

        public String toString(){
            return "carId:" + carId + "carNo:" + carNo + "carStatus:" + carStatus +
                    "dir:" + dir + "carId:" + carId + "lg:" + lg +
                    "lt:" + lt + "orgId:" + orgId + "speed:" + speed;
        }
    }
}
