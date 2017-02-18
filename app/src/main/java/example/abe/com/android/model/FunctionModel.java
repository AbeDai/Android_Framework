package example.abe.com.android.model;

/**
 * Created by abe on 17/2/17.
 */

public class FunctionModel {

    /**
     * 类对象
     */
    private Class clazz;

    /**
     * 功能标题
     */
    private String title;

    /**
     * 功能详细描述
     */
    private String content;

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
