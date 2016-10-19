package example.abe.com.android.activity.multithread;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.view.View;
import android.widget.ProgressBar;

import com.example.BindView;
import com.example.OnClick;

import example.abe.com.android.R;
import example.abe.com.framework.main.BaseActivity;
import example.abe.com.framework.util.ToastUtil;

public class AsyncTaskActivity extends BaseActivity {

    @BindView(R.id.act_async_task_progress_bar)
    protected ProgressBar mProgressBar;

    private MyTask mTask;

    @Override
    public int getLayoutID() {
        return R.layout.activity_async_task;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
    }

    @OnClick({R.id.act_async_task_btn_start})
    public void taskStart() {
        mTask = new MyTask();
        mTask.execute(10);
    }

    @OnClick({R.id.act_async_task_btn_cancel})
    public void taskCancel(View v) {
        mTask.cancel(false);
    }

    private class MyTask extends AsyncTask<Integer, Integer, String> {

        //UI线程,异步处理执行之前调用
        protected void onPreExecute() {
            ToastUtil.makeText("开始AsyncTask任务");
        }

        //UI线程,异步处理中调用,试试更新界面
        protected void onProgressUpdate(Integer... values) {
            mProgressBar.setProgress(values[0]);
            mProgressBar.setMax(values[1]);
        }

        //UI线程,异步处理完毕调用
        protected void onPostExecute(String result) {
            ToastUtil.makeText("AsyncTask任务完成：" + result);
        }

        //UI线程，任务被取消时调用
        protected void onCancelled() {
            ToastUtil.makeText("AsyncTask任务取消");
        }

        //异步线程,异步处理执行代码
        protected String doInBackground(Integer... params) {
            int max = 1000;
            int count = params[0];
            for (int i = 0; i <= count; i++) {
                SystemClock.sleep(1000);
                publishProgress(i * max / count, max);
            }
            return "加载好了";
        }
    }
}
