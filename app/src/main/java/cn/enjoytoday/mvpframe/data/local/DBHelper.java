package cn.enjoytoday.mvpframe.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import cn.enjoytoday.mvpframe.MvpApplication;

/**
 *
 * 数据库辅助类
 * @author hfcai
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = MvpApplication.getContext().getPackageName()
            .substring(MvpApplication.getContext().getPackageName().lastIndexOf("."));
    private static final int DB_VERSION = 1;
    public  static final String TABLE_NAME = "caches";
    private final String db_sql = "create table if not exists " + TABLE_NAME
            +"(id integer primary key autoincrement," +
            "json text not null," +
            "type varchar(100) not null unique," +
            "timestamp integer);";



    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (db!=null) {
            db.execSQL(db_sql);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists "+TABLE_NAME;
       db.execSQL(sql);
       onCreate(db);
    }
}
