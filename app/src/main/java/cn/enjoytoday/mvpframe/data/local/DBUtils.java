package cn.enjoytoday.mvpframe.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import cn.enjoytoday.mvpframe.utils.LogUtils;

/**
 * @author hfcai
 */
public class DBUtils {

    private Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    private static volatile DBUtils dbUtils;

    private DBUtils(Context context) {
        this.context = context;
    }


    /**
     * 单例获取
     *
     * @param context 上下文
     * @return
     */
    public static DBUtils getInstance(@NonNull Context context) {
        if (dbUtils == null) {
            dbUtils = new DBUtils(context);
        }
        return dbUtils;
    }


    private void openDB() {
        if (sqLiteDatabase == null) {
            dbHelper = new DBHelper(context);
            sqLiteDatabase = dbHelper.getWritableDatabase();
        }
    }


    private void closeDB() {
        if (sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
            sqLiteDatabase = null;
        }
    }

    /**
     * @param tableName     待操作表
     * @param contentValues 待插入信息
     */
    public void insert(String tableName, ContentValues contentValues) {
        openDB();
        sqLiteDatabase.insert(tableName, null, contentValues);
        closeDB();
    }


    public void insert(String tableName, Cache cache){
        openDB();
        ContentValues contentValues =new ContentValues();
        if (cache.getId()!=null){
            contentValues.put("id",cache.getId());
        }

        if (!TextUtils.isEmpty(cache.getJson())){
            contentValues.put("json",cache.getJson());
        }
        if (cache.getType()!=null){
            contentValues.put("type",cache.getType());
        }
        contentValues.put("timestamp",System.currentTimeMillis()+"");
        sqLiteDatabase.insert(tableName, null, contentValues);
        closeDB();
    }


    /**
     * 更新数据
     *
     * @param tableName     表名
     * @param contentValues 待更新信息
     */
    public void update(String tableName, ContentValues contentValues, String type) {
        openDB();
        contentValues.put("timestamp", System.currentTimeMillis());
        sqLiteDatabase.update(tableName, contentValues, "type=?", new String[]{type });
        closeDB();
    }



    public void update(String tableName, Cache cache, String type){
        openDB();
        ContentValues contentValues =new ContentValues();
        if (cache.getId()!=null){
            contentValues.put("id",cache.getId());
        }

        if (!TextUtils.isEmpty(cache.getJson())){
            contentValues.put("json",cache.getJson());
        }
        if (cache.getType()!=null){
            contentValues.put("type",cache.getType());
        }
        contentValues.put("timestamp",System.currentTimeMillis()+"");
        sqLiteDatabase.update(tableName, contentValues, "type=?", new String[]{type });
        closeDB();
    }

    /**
     * 删除数据
     *
     * @param tableName 表名
     * @param type      数据类型
     */
    public void delete(String tableName, String type) {
        openDB();
        sqLiteDatabase.delete(tableName, "type=?", new String[]{type + ""});
        closeDB();

    }

    /**
     * 查询数据.
     *
     * @param tableName
     * @param type
     * @return
     */
    public Cache query(String tableName, String type) {
        openDB();
        Cursor cursor = sqLiteDatabase.query(tableName, null, "type=?", new String[]{type + ""},
                null, null, null);
        Cache cache = null;
        try {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String type_ = cursor.getString(cursor.getColumnIndex("type"));
                String json = cursor.getString(cursor.getColumnIndex("json"));
                long timeTamp = cursor.getLong(cursor.getColumnIndex("timestamp"));
                cache = new Cache();
                cache.setId(id);
                cache.setJson(json);
                cache.setTimestamp(timeTamp);
                cache.setType(type_);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e(this, "查询数据异常:" + e.getMessage());
        }
        closeDB();
        return cache;
    }


    /**
     * 是否存在
     * @param tableName
     * @param type
     * @return
     */
    public boolean isExists(String tableName, String type){
        Cache cache = query(tableName,type);
        return cache != null;
    }


}
