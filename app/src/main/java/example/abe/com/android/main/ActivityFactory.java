package example.abe.com.android.main;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import example.abe.com.android.model.ClazzModel;
import example.abe.com.framework.util.AssetUtil;

/**
 * Created by abe on 16/8/10.
 */
public class ActivityFactory {

    private static ActivityFactory instance;

    private List<ClazzModel> mListClazz = new ArrayList<>();

    private String mXmlPath;

    static {
        instance = new ActivityFactory();
    }

    public static ActivityFactory getInstance() {
        return instance;
    }

    private ActivityFactory() {
    }

    /**
     * 设置XML配置路径
     *
     * @param xmlPath xml路径
     */
    public ActivityFactory config(String xmlPath) {
        mXmlPath = xmlPath;
        return instance;
    }

    /**
     * 解析 XML 配置 到 首页Activity
     */
    public void parseActivityConfig() {
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
     * 开始解析XML标签
     *
     * @param parser XML分析器
     */
    private void startParseTag(XmlPullParser parser) {
        if ("activity".equals(parser.getName())) {
            ClazzModel clazzModel = new ClazzModel();
            clazzModel.setClazz(parser.getAttributeValue(null, "class"));
            clazzModel.setTitle(parser.getAttributeValue(null, "title"));
            clazzModel.setContent(parser.getAttributeValue(null, "content"));
            mListClazz.add(clazzModel);
        }
    }

    public List<ClazzModel> getClazzList() {
        return mListClazz;
    }

    public Class getClazz(int position) {
        ClazzModel clazzModel = mListClazz.get(position);
        String className = clazzModel.getClazz();
        Class clazz = MainActivity.class;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }

    public int getClazzSize() {
        return mListClazz.size();
    }

    public ClazzModel getClazzModel(int position){
        return mListClazz.get(position);
    }
}
