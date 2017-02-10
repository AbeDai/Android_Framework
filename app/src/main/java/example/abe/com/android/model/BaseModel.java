package example.abe.com.android.model;

import com.google.gson.Gson;

/**
 * Created by abe on 17/2/9.
 */

public class BaseModel {
    private int error;
    private String msg;
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString(){
        return new Gson().toJson(this);
    }
}
