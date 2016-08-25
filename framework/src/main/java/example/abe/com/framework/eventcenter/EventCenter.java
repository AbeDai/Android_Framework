package example.abe.com.framework.eventcenter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/4.
 */
public class EventCenter {
    public static String METHOD_NAME_UI = "onEventUI";
    public static String METHOD_NAME_BG = "onEventBg";

    private static EventCenter instance;
    private EventHandler mEventHandler;
    private Map<Class<?>, List<EventMethod>> mMapEventMethods;
    public static EventCenter getDefault(){
        if (instance == null){
            init();
        }
        return instance;
    }

    private synchronized static void init(){
        instance = new EventCenter();
    }

    private EventCenter(){
        mMapEventMethods = new HashMap<>();
        mEventHandler = new EventHandler();
    }

    public void register(Object registrar){
        Class<?> clazz = registrar.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            Class<?>[] params = method.getParameterTypes();
            if (method.getName().equals(METHOD_NAME_UI)){
                if (params.length > 0){
                    List<EventMethod> list;
                    if (mMapEventMethods.containsKey(params[0])){
                        list = mMapEventMethods.get(params[0]);
                    }else{
                        list = new ArrayList<>();
                    }
                    EventMethod eventMethod = new EventMethod(EventMethod.Type.UI, method, registrar);
                    list.add(eventMethod);
                    mMapEventMethods.put(params[0], list);
                }
            }
            if (method.getName().equals(METHOD_NAME_BG)){
                if (params.length > 0){
                    List<EventMethod> list;
                    if (mMapEventMethods.containsKey(params[0])){
                        list = mMapEventMethods.get(params[0]);
                    }else{
                        list = new ArrayList<>();
                    }
                    EventMethod eventMethod = new EventMethod(EventMethod.Type.BG, method, registrar);
                    list.add(eventMethod);
                    mMapEventMethods.put(params[0], list);
                }
            }
        }
    }

    public void unRigister(Object unRegistrar){

        for (Iterator i1 = mMapEventMethods.keySet().iterator(); i1.hasNext();){

            Class<?> clazz = (Class)i1.next();
            List<EventMethod> list = mMapEventMethods.get(clazz);

            for (Iterator<EventMethod> i2 = list.iterator(); i2.hasNext();){
                EventMethod em = i2.next();
                if (em.getmReveiver().equals(unRegistrar)){
                    i2.remove();
                }
            }

            if (list.isEmpty()){
                i1.remove();
            }
        }
    }

    public void post(Object event){
        Class<?> clazz = event.getClass();

        List<EventMethod> list = mMapEventMethods.get(clazz);
        if (list != null){
            for (EventMethod em: list){

                if (em.getmType() == EventMethod.Type.UI){
                    mEventHandler.postUI(em, event);
                }

                if (em.getmType() == EventMethod.Type.BG){
                    mEventHandler.postBg(em, event);
                }
            }
        }
    }


}
