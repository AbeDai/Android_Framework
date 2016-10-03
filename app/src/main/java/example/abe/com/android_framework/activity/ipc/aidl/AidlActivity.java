package example.abe.com.android_framework.activity.ipc.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.example.OnClick;

import java.util.List;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.activity.imageloader.ImageLoaderGridViewActivity;
import example.abe.com.framework.main.BaseActivity;

public class AIDLActivity extends BaseActivity {

    @Override
    public int getLayoutID(){
        return R.layout.activity_aidl;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
    }

    @OnClick({R.id.act_messenger_btn_messenger, R.id.act_messenger_btn_aidl})
    public void onBtnClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.act_messenger_btn_messenger:
                Intent mIntent=new Intent(AIDLActivity.this, AIDLService.class);
                bindService(mIntent,mServiceConnection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.act_messenger_btn_aidl:
                intent.setClass(this, ImageLoaderGridViewActivity.class);
                break;
        }
        startActivity(intent);
    }

    private ServiceConnection mServiceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IGameManager iGameManager=IGameManager.Stub.asInterface(service);
            Person person =new Person("月影传说","最好玩的武侠单机游戏");
            try {
                iGameManager.addGame(person);
                List<Person> mList=iGameManager.getGameList();
                for(int i=0;i<mList.size();i++){
                    Person mPerson =mList.get(i);
                    Log.i(TAG, mPerson.name +"---"+ mPerson.describe);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
