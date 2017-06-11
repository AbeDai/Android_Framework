package example.abe.com.android.activity.wheel;

import com.example.BindView;
import com.example.OnClick;

import java.util.ArrayList;
import java.util.List;

import example.abe.com.android.R;
import example.abe.com.android.activity.wheel.wheelview.WheelView;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.util.ToastUtil;

public class WheelActivity extends BaseActivity {

    @BindView(R.id.act_wheel_wheel_view)
    protected WheelView<String> mWheelView;

    private MyWheelAdapter mWheelAdapter;

    private List<String> mListData;

    @Override
    public int getLayoutID() {
        return R.layout.activity_wheel;
    }

    @Override
    public void initData() {
        mListData = createData20();
    }

    @Override
    public void initView() {
        mWheelAdapter = new MyWheelAdapter(this);
        mWheelAdapter.setDataList(mListData);
        mWheelView.setWheelAdapter(mWheelAdapter);
        mWheelView.setWheelSize(5);
        mWheelView.setLoop(true);
        mWheelView.setCurrentPosition(3);
        mWheelView.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectedListener<String>() {
            @Override
            public void onItemSelected(int position, String str) {
                ToastUtil.makeText(str);
            }
        });
    }

    @OnClick(R.id.act_wheel_btn_change_data)
    public void onChangeDataClick(){
        if (mListData != null && mListData.size() > 0){
            mListData.set(0, "修改数据");
            mWheelAdapter.notifyDataSetChanged();
        }else {
            ToastUtil.makeText("数据为空");
        }
    }

    @OnClick(R.id.act_main_btn_remove_data)
    public void onRemoveDataClick(){
        if (mListData != null && mListData.size() > 0){
            mListData.remove(0);
            mWheelAdapter.notifyDataSetChanged();
            mWheelView.setCurrentPosition(0);
        }else {
            ToastUtil.makeText("无法删除更多数据");
        }
    }

    @OnClick(R.id.act_main_btn_replace_data)
    public void onReplaceDataClick(){
        mListData = createData30();
        mWheelAdapter.setDataList(mListData);
        mWheelAdapter.notifyDataSetChanged();
        mWheelView.setCurrentPosition(0);
    }

    private List<String> createData20() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++){
            list.add("item" + i);
        }
        return list;
    }

    private List<String> createData30() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++){
            list.add("item" + i);
        }
        return list;
    }

}
