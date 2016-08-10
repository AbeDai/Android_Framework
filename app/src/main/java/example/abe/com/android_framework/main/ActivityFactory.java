package example.abe.com.android_framework.main;

import example.abe.com.android_framework.R;
import example.abe.com.android_framework.activity.assets.AssetsActivity;
import example.abe.com.android_framework.activity.banner.BannerActivity;
import example.abe.com.android_framework.activity.cardview.CardViewActivity;
import example.abe.com.android_framework.activity.drawing.DrawActivity;
import example.abe.com.android_framework.activity.eventbus.EventBusActivity;
import example.abe.com.android_framework.activity.eventcenter.EventCenterActivity;
import example.abe.com.android_framework.activity.gridview.GridViewActivity;
import example.abe.com.android_framework.activity.recyclelist.RecycleListActivity;
import example.abe.com.android_framework.activity.retrofit.RetrofitActivity;
import example.abe.com.android_framework.activity.tablayout.TabLayoutActivity;
import example.abe.com.android_framework.activity.viewpager.ViewPagerActivity;
import example.abe.com.android_framework.activity.volley.VolleyActivity;
import example.abe.com.framework.util.ResourceUtil;

/**
 * Created by abe on 16/8/10.
 */
public class ActivityFactory {

    public enum ActivityFlag {
        //RecycleView使用范例
        RECYCLE_LIST,

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
    }

    static Class getActivityClass(ActivityFlag flag){
        Class clazz = null;
        switch (flag) {
            case RECYCLE_LIST:
                clazz = RecycleListActivity.class;
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
        }
        return clazz;
    }

    static String getActivityTitle(ActivityFlag flag){
        String title = null;
        switch (flag) {
            case RECYCLE_LIST:
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
        }
        return title;
    }

    static String getActivityContent(ActivityFlag flag){
        String title = null;
        switch (flag) {
            case RECYCLE_LIST:
                title = ResourceUtil.getString(R.string.act_content_recycle_list);
                break;

            case RETROFIT:
                title = ResourceUtil.getString(R.string.act_content_retrofit);
                break;

            case VIEW_PAGER:
                title = ResourceUtil.getString(R.string.act_content_view_pager);
                break;

            case ASSETS:
                title = ResourceUtil.getString(R.string.act_content_assets);
                break;

            case TAB_LAYOUT:
                title = ResourceUtil.getString(R.string.act_content_tab_layout);
                break;

            case EVENT_BUS:
                title = ResourceUtil.getString(R.string.act_content_event_bus);
                break;

            case CARD_VIEW:
                title = ResourceUtil.getString(R.string.act_content_card_view);
                break;

            case GRID_VIEW:
                title = ResourceUtil.getString(R.string.act_content_grid_view);
                break;

            case VOLLEY:
                title = ResourceUtil.getString(R.string.act_content_volley);
                break;

            case DRAW:
                title = ResourceUtil.getString(R.string.act_content_draw);
                break;

            case BANNER:
                title = ResourceUtil.getString(R.string.act_content_banner);
                break;

            case EVENT_CENTER:
                title = ResourceUtil.getString(R.string.act_content_event_center);
                break;
        }
        return title;
    }
}
