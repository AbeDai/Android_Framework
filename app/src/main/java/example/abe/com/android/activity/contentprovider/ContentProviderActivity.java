package example.abe.com.android.activity.contentprovider;

import android.content.Intent;

import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.android.activity.contentprovider.contacts.ContactsActivity;
import example.abe.com.android.activity.contentprovider.custom.OperateActivity;
import example.abe.com.framework.main.BaseActivity;

public class ContentProviderActivity extends BaseActivity {

    @Override
    public int getLayoutID(){
        return R.layout.activity_content_provider;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
    }

    @OnClick(R.id.act_content_provider_btn_show_contacts)
    public void onShowContacts(){
        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.act_content_provider_btn_create_content_provider)
    public void onCreateContentProvider(){
        Intent intent = new Intent(this, OperateActivity.class);
        startActivity(intent);
    }
}
