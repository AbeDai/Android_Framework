package example.abe.com.android.main;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.BindView;
import com.example.PermissionFail;
import com.example.PermissionSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import example.abe.com.android.R;
import example.abe.com.android.main.ActivityFactory.Flags;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.permission.PermissionUtils;

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener,
        TextView.OnEditorActionListener {

    private static final int STORAGE_PERMISSIONS_REQUEST = 100;
    @BindView(R.id.act_main_list)
    protected ListView mLv;
    @BindView(R.id.act_main_et_search)
    protected EditText mEtSearch;
    private MainAdapter mAdapter;

    @Override
    public int getLayoutID(){
        return R.layout.activity_main;
    }

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

        AlertDialog.Builder builder = new AlertDialog
                .Builder(this)
                .setTitle("存储读写权限")
                .setMessage("这项权限对我们非常重要，取消授权将对App的正常运行产生影响！")
                .setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();

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
        PermissionUtils.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    private void requestPermissions(){
        PermissionUtils
                .with(this)
                .addRequestCode(STORAGE_PERMISSIONS_REQUEST)
                .permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .request();
    }

    @PermissionSuccess(requestCode = STORAGE_PERMISSIONS_REQUEST)
    public void onRequestStorageSuccess(){

    }

    @PermissionFail(requestCode = STORAGE_PERMISSIONS_REQUEST)
    public void onRequestStorageFail(){
        AlertDialog.Builder builder = new AlertDialog
                .Builder(this)
                .setTitle("存储读写权限")
                .setMessage("这项权限对我们非常重要，取消授权将对App的正常运行产生影响！")
                .setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }
}
