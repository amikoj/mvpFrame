package cn.enjoytoday.mvpframe.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * log日志采集
 * @author hfcai
 */
public class LogUtils {

    private static final String TAG = "LogUtils";

    /**
     * 出错信息或重点标注信息
     * @param object
     * @param msg
     */
    public static void e(Object object, String msg){
        Log.e(object==null?TAG:object.getClass().getSimpleName(),
                TextUtils.isEmpty(msg)?"":msg);
    }

    public static void e(String tag,String msg){
        if (TextUtils.isEmpty(tag) || TextUtils.isEmpty(msg)){
            Log.e(TAG,msg);
        }
        Log.e(tag,msg);
    }



    public static void d(Object object, String msg){
        Log.d(object==null?TAG:object.getClass().getSimpleName(),
                TextUtils.isEmpty(msg)?"":msg);
    }

    public static void w(Object object, String msg){
        Log.w(object==null?TAG:object.getClass().getSimpleName(),
                TextUtils.isEmpty(msg)?"":msg);
    }


    public static void i(Object object, String msg){
        Log.i(object==null?TAG:object.getClass().getSimpleName(),
                TextUtils.isEmpty(msg)?"":msg);
    }


}
