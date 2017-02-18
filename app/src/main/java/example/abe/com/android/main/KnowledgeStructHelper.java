package example.abe.com.android.main;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import example.abe.com.android.model.FunctionModel;
import example.abe.com.android.model.TabModel;
import example.abe.com.framework.util.AssetUtil;

/**
 * Created by abe on 16/8/10.
 */
public class KnowledgeStructHelper {

    private static List<TabModel> mListTab = new ArrayList<>();

    private static String mXmlPath;

    /**
     * 设置XML配置并构建配置
     */
    public static void build(String xmlPath){
        mXmlPath = xmlPath;
        parseActivityConfig();
    }

    /**
     * 解析 XML 配置 到 首页Activity
     */
    private static void parseActivityConfig() {
        String xmlData = AssetUtil.getAssetInputStream(mXmlPath);
        int eventType;

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(xmlData));
            eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (parser.getEventType()) {
                    // 开始解析某个结点
                    case XmlPullParser.START_TAG:
                        startParseTag(parser);
                        break;
                }
                // 获取解析下一个事件
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取标签数组
     * @return 标签数组
     */
    public static List<TabModel> getListTab(){
        return mListTab;
    }

    /**
     * 开始解析XML标签
     *
     * @param parser XML分析器
     */
    private static void startParseTag(XmlPullParser parser) {
        if ("tab".equals(parser.getName())){
            TabModel tabModel = new TabModel();
            tabModel.setTitle(parser.getAttributeValue(null, "title"));
            tabModel.setTabList(new ArrayList<FunctionModel>());
            mListTab.add(tabModel);
        }else if ("activity".equals(parser.getName())) {
            FunctionModel activityModel = new FunctionModel();
            activityModel.setTitle(parser.getAttributeValue(null, "title"));
            activityModel.setContent(parser.getAttributeValue(null, "content"));
            activityModel.setClazz(reflexName2Class(parser.getAttributeValue(null, "class")));
            mListTab.get(mListTab.size()-1).addFunctionModel(activityModel);
        }
    }

    /**
     * 反射获取Class对象
     * @param className 类名（全路径）
     * @return Class对象
     */
    private static Class reflexName2Class(String className){
        Class clazz = MainActivity.class;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }
}
