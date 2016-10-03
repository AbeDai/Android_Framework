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
    private Binder mBinder= new IGameManager.Stub() {
        @Override
        public List<Person> getGameList() throws RemoteException {
            return mPersonList;
        }

        @Override
        public void addGame(Person person) throws RemoteException {
            mPersonList.add(person);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mPersonList.add(new Person("九阴真经ol", "最好玩的武侠网游"));
        mPersonList.add(new Person("大航海时代ol","最好玩的航海网游"));

    }
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
