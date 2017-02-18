package example.abe.com.android.model;

import java.util.List;

/**
 * Created by abe on 17/2/18.
 */

public class TabModel {
    String title;
    List<FunctionModel> tabList;

    public List<FunctionModel> getTabList() {
        return tabList;
    }

    public void setTabList(List<FunctionModel> tabList) {
        this.tabList = tabList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addFunctionModel(FunctionModel model){
        tabList.add(model);
    }

    public FunctionModel getFunctionModel(int position){
        return tabList.get(position);
    }
}
