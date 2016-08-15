package example.abe.com.android_framework.main;

import android.content.Intent;
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
}
