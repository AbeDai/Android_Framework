package example.abe.com.android_framework.activity.ipc.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.TextView;

import com.example.BindView;
import com.example.OnClick;

import java.util.ArrayList;
import java.util.List;

import example.abe.com.android_framework.R;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.util.LogUtil;

public class AIDLActivity extends BaseActivity {

    @BindView(R.id.act_aidl_tv_show)
    protected TextView mTvShow;

    private IPersonManager mPersonManager;

    @Override
    public int getLayoutID() {
        return R.layout.activity_aidl;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
    }

    @OnClick({R.id.act_aidl_btn_server_remote, R.id.act_aidl_btn_send_messenger, R.id.act_aidl_btn_show_content})
    public void onBtnClick(View v) {
        switch (v.getId()) {
            case R.id.act_aidl_btn_server_remote:
                //开启后台进程服务
                Intent intent = new Intent(this, AIDLService.class);
                bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.act_aidl_btn_send_messenger:
                //向后台发送消息
                Person person = new Person("戴益波", "浙江科技学院 信息学院 计算机科学与技术 132班");
                try {
                    //给服务端人员名单添加内容
                    mPersonManager.addPerson(person);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.act_aidl_btn_show_content:
                List<Person> list = new ArrayList<>();
                try {
                    //获取服务端人员名单
                    list = mPersonManager.getPersonList();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                //打印内容
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < list.size(); i++) {
                    Person mPerson = list.get(i);
                    sb.append(mPerson.toString());
                    sb.append("\n");
                }
                mTvShow.setText(sb.toString());
                break;
        }
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mPersonManager = IPersonManager.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
