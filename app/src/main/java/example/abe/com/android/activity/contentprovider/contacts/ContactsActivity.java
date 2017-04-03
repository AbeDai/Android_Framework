package example.abe.com.android.activity.contentprovider.contacts;

import android.Manifest;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;

import com.example.BindView;
import com.example.OnClick;
import com.example.PermissionFail;
import com.example.PermissionSuccess;

import java.util.ArrayList;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.permission.PermissionUtils;

public class ContactsActivity extends BaseActivity {

    private static final int CONTACTS_PERMISSIONS_REQUEST = 101;

    @BindView(R.id.act_contacts_edit_text_show)
    protected TextView mTvShow;

    @Override
    public int getLayoutID() {
        return R.layout.activity_contacts;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
        requestPermissions();
    }

    @OnClick(R.id.act_contacts_btn_get_all_contacts)
    public void queryAllContacts() {
        StringBuilder sb = new StringBuilder();
        //查询raw_contacts表获得联系人的id
        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse("content://com.android.contacts/data/phones");
        //查询联系人数据
        Cursor cursor = resolver.query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
            //获取联系人姓名,手机号码
            String cName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String cNum = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            sb.append("姓名:" + cName + " | " + "号码:" + cNum + "\n");
        }
        cursor.close();
        mTvShow.setText(sb);
    }

    @OnClick(R.id.act_contacts_btn_get_contact)
    public void queryContact() {
        StringBuilder sb = new StringBuilder();
        Uri uri = Uri.parse("content://com.android.contacts/data/phones/filter/" + "15757585822");
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uri, null, null, null, null);
        if (cursor.moveToFirst()) {
            //获取联系人姓名,手机号码
            String cName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String cNum = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            sb.append("姓名:" + cName + " | " + "号码:" + cNum + "\n");
        }
        cursor.close();
        mTvShow.setText(sb);
    }

    @OnClick(R.id.act_contacts_btn_add_contact)
    public void addContact() {
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        Uri dataUri = Uri.parse("content://com.android.contacts/data");
        ArrayList<ContentProviderOperation> operations = new ArrayList<>();
        ContentProviderOperation op1 = ContentProviderOperation.newInsert(uri)
                .withValue("account_name", null)
                .build();
        ContentProviderOperation op2 = ContentProviderOperation.newInsert(dataUri)
                .withValueBackReference("raw_contact_id", 0)
                .withValue("mimetype", "vnd.android.cursor.item/name")
                .withValue("data2", "Coder-pig")
                .build();
        ContentProviderOperation op3 = ContentProviderOperation.newInsert(dataUri)
                .withValueBackReference("raw_contact_id", 0)
                .withValue("mimetype", "vnd.android.cursor.item/phone_v2")
                .withValue("data1", "15757585822")
                .withValue("data2", "2")
                .build();
        ContentProviderOperation op4 = ContentProviderOperation.newInsert(dataUri)
                .withValueBackReference("raw_contact_id", 0)
                .withValue("mimetype", "vnd.android.cursor.item/email_v2")
                .withValue("data1", "644670532@qq.com")
                .withValue("data2", "2")
                .build();
        operations.add(op1);
        operations.add(op2);
        operations.add(op3);
        operations.add(op4);
        //将上述内容添加到手机联系人中
        ContentResolver resolver = getContentResolver();
        try{
            resolver.applyBatch("com.android.contacts", operations);
        }catch (Exception e){
            e.printStackTrace();
        }
        //操作成功
        mTvShow.setText("添加成功");
    }

    /** 权限操作 **/

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],
                                           int[] grantResults) {
        PermissionUtils.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    private void requestPermissions() {
        PermissionUtils
                .with(this)
                .addRequestCode(CONTACTS_PERMISSIONS_REQUEST)
                .permissions(Manifest.permission.WRITE_CONTACTS)
                .request();
    }

    @PermissionSuccess(requestCode = CONTACTS_PERMISSIONS_REQUEST)
    public void onRequestContactsSuccess() {
    }

    @PermissionFail(requestCode = CONTACTS_PERMISSIONS_REQUEST)
    public void onRequestContactsFail() {
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
