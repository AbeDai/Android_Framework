package example.abe.com.android_framework.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;
import java.util.Date;

import example.abe.com.android_framework.R;
import example.abe.com.framework.Annotation.ContentView;
import example.abe.com.framework.util.ABLog;
import example.abe.com.framework.util.ABTimeUtil;

@ContentView(id = R.layout.activity_time_util)
public class TimeUtilActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //简单示例
        String nowStr = ABTimeUtil.getStrCurrentCH(ABTimeUtil.FORMAT_YYYYMMDD);
        ABLog.v(nowStr);

        Date date = ABTimeUtil.getDateCH(nowStr, ABTimeUtil.FORMAT_YYYYMMDD);
        ABLog.v(date.toString());

        int week = ABTimeUtil.getFieldCH(date, Calendar.DAY_OF_WEEK);
        int month = ABTimeUtil.getFieldCH(date, Calendar.MONTH);
        ABLog.v("week: " + week);
        ABLog.v("month: " + month);
    }
}
