package example.abe.com.android_framework.main;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.main.ActivityFactory.Flags;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.util.ToastUtil;
import example.abe.com.framework.viewinject.ContentView;
import example.abe.com.framework.viewinject.ViewInject;

@ContentView(id = R.layout.activity_main)
public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener,
        TextView.OnEditorActionListener {

    @ViewInject(id = R.id.act_main_list)
    private ListView mLv;
    @ViewInject(id = R.id.act_main_et_search)
    private EditText mEtSearch;
    private MainAdapter mAdapter;
    private static final int MY_PERMISSIONS_REQUEST = 100;

    @Override
    public void initData() {
        
    }

    @Override
    public void initView() {
        List<Flags> listActFlag = new ArrayList<>(Arrays.asList(Flags.values()));
        mAdapter = new MainAdapter(this, listActFlag);
        mLv.setAdapter(mAdapter);
        mLv.setOnItemClickListener(this);

        mEtSearch.setOnEditorActionListener(this);

        requestPermissions();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Flags tag = mAdapter.getListFlagFilter().get(position);
        Class clazz = ActivityFactory.getClass(tag);
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            mAdapter.filter(v.getText().toString());
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],
                                           int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ToastUtil.makeText("权限申请成功");
                } else {
                    ToastUtil.makeText("权限申请失败");
                }
                return;
            }
        }
    }

    /**
     * 获取运行时权限
     */
    private void requestPermissions(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                //再次提醒
                AlertDialog.Builder builder = new AlertDialog
                        .Builder(this)
                        .setMessage("这项权限对我们非常重要，取消授权将对App的正常运行产生影响！")
                        .setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                //再次授权
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        MY_PERMISSIONS_REQUEST);
                            }
                        });
                builder.create().show();
            } else {
                //需要授权
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST);
            }
        }else{
            //已经授权
        }
    }
}
