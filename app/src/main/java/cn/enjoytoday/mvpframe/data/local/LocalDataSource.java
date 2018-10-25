package cn.enjoytoday.mvpframe.data.local;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import cn.enjoytoday.mvpframe.data.DataSource;
import cn.enjoytoday.mvpframe.utils.JsonUtils;
import cn.enjoytoday.mvpframe.utils.LogUtils;
import com.alibaba.fastjson.JSON;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 本地数据操作实现
 * @author hfcai
 */
public class LocalDataSource implements DataSource {

    @Nullable
    private static LocalDataSource INSTANCE;
    private Context context;

    private LocalDataSource(@NonNull Context context) {
        this.context = checkNotNull(context, "context cannot be null");
    }

    public static LocalDataSource getInstance(
            @NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new LocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public <T> void saveObj(String type,T t) {
        DBUtils dbUtils = DBUtils.getInstance(context);
        Cache cache =  dbUtils.query(DBHelper.TABLE_NAME,type);
        if (cache==null){
            String json = (t instanceof  String)?t.toString():JsonUtils.toJson(t);
            cache = new Cache();
            cache.setType(type);
            cache.setJson(json);
            dbUtils.insert(DBHelper.TABLE_NAME,cache);
        }else {
            dbUtils.update(DBHelper.TABLE_NAME,cache,type);
        }

    }


    @Override
    public  void delObj(String type) {
        DBUtils dbUtils = DBUtils.getInstance(context);
        dbUtils.delete(DBHelper.TABLE_NAME,type);
    }

    @Override
    public <T> Object getObj(String type,Object obj,Class<T> tClass) {
        Object object = null;
        try {
            DBUtils dbUtils = DBUtils.getInstance(context);
            Cache cache =  dbUtils.query(DBHelper.TABLE_NAME,type);
            String json  =null;
            if (cache != null) {
                json = cache.getJson().trim();
            }

            LogUtils.w(this,"json:"+json);
            if (tClass==String.class){
                return json;
            }
            if (!TextUtils.isEmpty(json)) {
                if (json.startsWith("{")) {
                    object = JsonUtils.fromJson(json, tClass);
                } else if (json.startsWith("[")) {
                    //数组
                    object = JSON.parseArray(json, tClass);
                } else {
                    LogUtils.e(this, "数据格式异常,解析出错！");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            LogUtils.e(this,"本地获取数据失败！");
        }
        return  object;
    }

    @Override
    public void destroy() {
        return;
    }


}

