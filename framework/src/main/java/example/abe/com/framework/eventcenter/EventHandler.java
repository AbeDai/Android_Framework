package example.abe.com.framework.eventcenter;

import android.os.Handler;
import android.os.Looper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * Created by Administrator on 2016/8/4.
 */
public class EventHandler {

    private Handler mUIHandler;
    private Handler mBgHandler;
    private Thread mBgThread;

    public EventHandler(){
        mUIHandler = new Handler(Looper.getMainLooper());
        mBgThread = new Thread("EventCenter_Background_Thread"){
            @Override
            public void run() {
                Looper.prepare();//循环准备
                mBgHandler = new Handler();
                Looper.loop();//开始循环
            }
        };
        mBgThread.start();//开启线程，会一直在后台运行
    }

    public void postUI(final EventMethod em, final Object arg0){
        mUIHandler.post(new Runnable() {
            @Override
            public void run() {
                Method method = em.getmMethod();
                Object receive = em.getmReveiver();
                try {
                    method.invoke(receive, arg0);
                }catch (IllegalAccessException e){
                    e.printStackTrace();
                }catch (InvocationTargetException e){
                    e.printStackTrace();
                }
            }
        });
    }

    public void postBg(final EventMethod em, final Object arg0){
        mBgHandler.post(new Runnable() {
            @Override
            public void run() {
                Method method = em.getmMethod();
                Object receive = em.getmReveiver();
                try {
                    method.invoke(receive, arg0);
                }catch (IllegalAccessException e){
                    e.printStackTrace();
                }catch (InvocationTargetException e){
                    e.printStackTrace();
                    throw new RuntimeException(e.getTargetException());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
