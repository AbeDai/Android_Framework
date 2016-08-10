package example.abe.com.android_framework.main;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.main.ActivityFactory.ActivityFlag;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.viewinject.ContentView;
import example.abe.com.framework.viewinject.ViewInject;


@ContentView(id = R.layout.activity_main)
public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @ViewInject(id = R.id.act_main_list)
    private ListView mLv;
    private List<ActivityFlag> mListActFlag;

    @Override
    public void initData(){
        mListActFlag = Arrays.asList(ActivityFlag.values());
    }

    @Override
    public void initView(){
        mLv.setAdapter(new MainAdapter(this, mListActFlag));
        mLv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ActivityFlag tag = mListActFlag.get(position);
        Class clazz = ActivityFactory.getActivityClass(tag);
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
