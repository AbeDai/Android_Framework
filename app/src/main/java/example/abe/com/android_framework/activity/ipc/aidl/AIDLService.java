package example.abe.com.android_framework.activity.ipc.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AIDLService extends Service{

    private CopyOnWriteArrayList<Person> mPersonList =new CopyOnWriteArrayList<Person>();

    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }

    private void initData() {
        mPersonList.add(new Person("吴佳芳", "浙江科技学院 信息学院 计算机科学与技术 133班"));
        mPersonList.add(new Person("戴波波","浙江科技学院 信息学院 计算机科学与技术 131班"));
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    //Binder实现类
    private Binder mBinder= new IPersonManager.Stub() {
        @Override
        public List<Person> getPersonList() throws RemoteException {
            return mPersonList;
        }

        @Override
        public void addPerson(Person person) throws RemoteException {
            mPersonList.add(person);
        }
    };
}
