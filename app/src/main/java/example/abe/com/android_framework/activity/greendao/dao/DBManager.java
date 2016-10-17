package example.abe.com.android_framework.activity.greendao.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by abe on 16/10/17.
 */
public class DBManager {
    private final static String dbName = "test_db";

    private static DBManager mInstance;

    private DaoMaster.DevOpenHelper openHelper;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private Context context;

    public DBManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        SQLiteDatabase db = openHelper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    /**
     * 获取单例引用
     *
     * @param context
     * @return
     */
    public static DBManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 插入一条记录
     *
     * @param user
     */
    public void insertUser(UserModel user) {
        UserModelDao userDao = daoSession.getUserModelDao();
        userDao.insert(user);
    }

    /**
     * 插入用户集合
     *
     * @param users
     */
    public void insertUserList(List<UserModel> users) {
        if (users == null || users.isEmpty()) {
            return;
        }
        UserModelDao userDao = daoSession.getUserModelDao();
        userDao.insertInTx(users);
    }

    /**
     * 删除一条记录
     *
     * @param user
     */
    public void deleteUser(UserModel user) {
        UserModelDao userDao = daoSession.getUserModelDao();
        userDao.delete(user);
    }

    /**
     * 更新一条记录
     *
     * @param user
     */
    public void updateUser(UserModel user) {
        UserModelDao userDao = daoSession.getUserModelDao();
        userDao.update(user);
    }

    /**
     * 查询用户列表
     */
    public List<UserModel> queryUserList() {
        UserModelDao userDao = daoSession.getUserModelDao();
        QueryBuilder<UserModel> qb = userDao.queryBuilder();
        List<UserModel> list = qb.list();
        return list;
    }

    /**
     * 查询用户列表
     */
    public List<UserModel> queryUserList(int age, String name) {
        UserModelDao userDao = daoSession.getUserModelDao();
        QueryBuilder<UserModel> qb = userDao.queryBuilder()
                .where(UserModelDao.Properties.Age.gt(age), UserModelDao.Properties.Name.gt(name))
                .orderAsc(UserModelDao.Properties.Age);
        List<UserModel> list = qb.list();
        return list;
    }

    /**
     * 查询用户列表
     */
    public List<UserModel> queryUserList(int age) {
        UserModelDao userDao = daoSession.getUserModelDao();
        QueryBuilder<UserModel> qb = userDao.queryBuilder()
                .where(UserModelDao.Properties.Age.gt(age))
                .orderAsc(UserModelDao.Properties.Age);
        List<UserModel> list = qb.list();
        return list;
    }
}
