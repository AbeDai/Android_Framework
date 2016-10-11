package example.abe.com.android_framework.main;

import java.util.HashMap;
import java.util.Map;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.activity.assets.AssetsActivity;
import example.abe.com.android_framework.activity.banner.BannerActivity;
import example.abe.com.android_framework.activity.cardview.CardViewActivity;
import example.abe.com.android_framework.activity.drawing.DrawActivity;
import example.abe.com.android_framework.activity.eventbus.EventBusActivity;
import example.abe.com.android_framework.activity.eventcenter.EventCenterActivity;
import example.abe.com.android_framework.activity.gridview.GridViewActivity;
import example.abe.com.android_framework.activity.imageloader.ImageLoaderActivity;
import example.abe.com.android_framework.activity.ipc.IpcActivity;
import example.abe.com.android_framework.activity.multithread.MultiThreadActivity;
import example.abe.com.android_framework.activity.recycleview.RecycleViewActivity;
import example.abe.com.android_framework.activity.retrofit.RetrofitActivity;
import example.abe.com.android_framework.activity.socket.SocketActivity;
import example.abe.com.android_framework.activity.suspend.SuspendWindowActivity;
import example.abe.com.android_framework.activity.tablayout.TabLayoutActivity;
import example.abe.com.android_framework.activity.viewpager.ViewPagerActivity;
import example.abe.com.android_framework.activity.volley.VolleyActivity;
import example.abe.com.framework.util.ResourceUtil;

/**
 * Created by abe on 16/8/10.
 */
public class ActivityFactory {

    private static Map<Flags, String> mapTitle;
    private static Map<Flags, String> mapContent;

    static {
        mapTitle = new HashMap<>();
        mapContent = new HashMap<>();
    }

    public enum Flags {
        //RecycleView详解
        RECYCLE_VIEW,

        //网络框架
        RETROFIT,

        //ViewPager使用范例
        VIEW_PAGER,

        //Assets使用范例
        ASSETS,

        //TabLayout使用范例
        TAB_LAYOUT,

        //EventBus使用模板
        EVENT_BUS,

        //CardView用法介绍
        CARD_VIEW,

        //GridView使用介绍
        GRID_VIEW,

        //Volley使用介绍
        VOLLEY,

        //绘图详解
        DRAW,

        //广告栏详解
        BANNER,

        //自定义EventBus
        EVENT_CENTER,

        //Socket使用介绍
        SOCKET,

        //多线程框架
        MULTI_THREAD,

        //自定义图片加载框架
        IMAGE_LOADER,

        //悬浮窗实例
        SUSPEND_WINDOWS,

        //进程通讯总结
        IPC,
    }

    public static Class getClass(Flags flag) {
        Class clazz = null;
        switch (flag) {
            case RECYCLE_VIEW:
                clazz = RecycleViewActivity.class;
                break;
            case RETROFIT:
                clazz = RetrofitActivity.class;
                break;
            case VIEW_PAGER:
                clazz = ViewPagerActivity.class;
                break;
            case ASSETS:
                clazz = AssetsActivity.class;
                break;
            case TAB_LAYOUT:
                clazz = TabLayoutActivity.class;
                break;
            case EVENT_BUS:
                clazz = EventBusActivity.class;
                break;
            case CARD_VIEW:
                clazz = CardViewActivity.class;
                break;
            case GRID_VIEW:
                clazz = GridViewActivity.class;
                break;
            case VOLLEY:
                clazz = VolleyActivity.class;
                break;
            case DRAW:
                clazz = DrawActivity.class;
                break;
            case BANNER:
                clazz = BannerActivity.class;
                break;
            case EVENT_CENTER:
                clazz = EventCenterActivity.class;
                break;
            case SOCKET:
                clazz = SocketActivity.class;
                break;
            case MULTI_THREAD:
                clazz = MultiThreadActivity.class;
                break;
            case IMAGE_LOADER:
                clazz = ImageLoaderActivity.class;
                break;
            case SUSPEND_WINDOWS:
                clazz = SuspendWindowActivity.class;
                break;
            case IPC:
                clazz = IpcActivity.class;
                break;
        }
        return clazz;
    }

    public static String getTitle(Flags flag) {
        String title = null;
        //获取
        if ((title = mapTitle.get(flag)) != null){
            return title;
        }

        //获取资源
        switch (flag) {
            case RECYCLE_VIEW:
                title = ResourceUtil.getString(R.string.act_title_recycle_list);
                break;
            case RETROFIT:
                title = ResourceUtil.getString(R.string.act_title_retrofit);
                break;
            case VIEW_PAGER:
                title = ResourceUtil.getString(R.string.act_title_view_pager);
                break;
            case ASSETS:
                title = ResourceUtil.getString(R.string.act_title_assets);
                break;
            case TAB_LAYOUT:
                title = ResourceUtil.getString(R.string.act_title_tab_layout);
                break;
            case EVENT_BUS:
                title = ResourceUtil.getString(R.string.act_title_event_bus);
                break;
            case CARD_VIEW:
                title = ResourceUtil.getString(R.string.act_title_card_view);
                break;
            case GRID_VIEW:
                title = ResourceUtil.getString(R.string.act_title_grid_view);
                break;
            case VOLLEY:
                title = ResourceUtil.getString(R.string.act_title_volley);
                break;
            case DRAW:
                title = ResourceUtil.getString(R.string.act_title_draw);
                break;
            case BANNER:
                title = ResourceUtil.getString(R.string.act_title_banner);
                break;
            case EVENT_CENTER:
                title = ResourceUtil.getString(R.string.act_title_event_center);
                break;
            case SOCKET:
                title = ResourceUtil.getString(R.string.act_title_socket);
                break;
            case MULTI_THREAD:
                title = ResourceUtil.getString(R.string.act_title_multi_thread);
                break;
            case IMAGE_LOADER:
                title = ResourceUtil.getString(R.string.act_title_image_loader);
                break;
            case SUSPEND_WINDOWS:
                title = ResourceUtil.getString(R.string.act_title_suspend_windows);
                break;
            case IPC:
                title = ResourceUtil.getString(R.string.act_title_ipc);
                break;
        }

        //存储
        mapTitle.put(flag, title);
        return title;
    }

    public static String getContent(Flags flag) {
        String content = null;
        //获取
        if ((content = mapContent.get(flag)) != null){
            return content;
        }

        //获取资源
        switch (flag) {
            case RECYCLE_VIEW:
                content = ResourceUtil.getString(R.string.act_content_recycle_list);
                break;
            case RETROFIT:
                content = ResourceUtil.getString(R.string.act_content_retrofit);
                break;
            case VIEW_PAGER:
                content = ResourceUtil.getString(R.string.act_content_view_pager);
                break;
            case ASSETS:
                content = ResourceUtil.getString(R.string.act_content_assets);
                break;
            case TAB_LAYOUT:
                content = ResourceUtil.getString(R.string.act_content_tab_layout);
                break;
            case EVENT_BUS:
                content = ResourceUtil.getString(R.string.act_content_event_bus);
                break;
            case CARD_VIEW:
                content = ResourceUtil.getString(R.string.act_content_card_view);
                break;
            case GRID_VIEW:
                content = ResourceUtil.getString(R.string.act_content_grid_view);
                break;
            case VOLLEY:
                content = ResourceUtil.getString(R.string.act_content_volley);
                break;
            case DRAW:
                content = ResourceUtil.getString(R.string.act_content_draw);
                break;
            case BANNER:
                content = ResourceUtil.getString(R.string.act_content_banner);
                break;
            case EVENT_CENTER:
                content = ResourceUtil.getString(R.string.act_content_event_center);
                break;
            case SOCKET:
                content = ResourceUtil.getString(R.string.act_content_socket);
                break;
            case MULTI_THREAD:
                content = ResourceUtil.getString(R.string.act_content_multi_thread);
                break;
            case IMAGE_LOADER:
                content = ResourceUtil.getString(R.string.act_content_image_loader);
                break;
            case SUSPEND_WINDOWS:
                content = ResourceUtil.getString(R.string.act_content_suspend_windows);
                break;
            case IPC:
                content = ResourceUtil.getString(R.string.act_content_ipc);
                break;
        }

        //存储
        mapContent.put(flag, content);
        return content;
    }
}
