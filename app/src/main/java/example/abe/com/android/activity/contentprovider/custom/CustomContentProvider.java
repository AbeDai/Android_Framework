package example.abe.com.android.activity.contentprovider.custom;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import example.abe.com.framework.util.LogUtil;
import example.abe.com.framework.util.SharedPreferencesUtil;

/**
 * Created by abe on 17/4/3.
 */

public class CustomContentProvider extends ContentProvider {

    @Override
    public boolean onCreate() {
        LogUtil.i("onCreate()");
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        LogUtil.i(uri.toString());
        return null;
    }

    @Override
    public String getType(Uri uri) {
        LogUtil.i(uri.toString());
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        LogUtil.i(uri.toString());
        SharedPreferencesUtil.put("name", (String)values.get("name"));
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        LogUtil.i(uri.toString());
        SharedPreferencesUtil.remove("name");
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        LogUtil.i(uri.toString());
        return 0;
    }

}
