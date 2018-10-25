package cn.enjoytoday.mvpframe.utils;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Type;

/**
 * json 数据请求工具
 * @author hfcai
 */
public class JsonUtils {

    private static final String TAG = "JsonUtils";
    /**
     * Object 转 jsonString
     * @param t 待转换对象
     * @param <T> 泛型
     * @return 返回string
     */
    public static <T> String toJson(T t){
        try {
            if (t!=null) {
                return JSON.toJSON(t).toString();
            }
        }catch (Exception e){
            e.printStackTrace();
            LogUtils.e(t,"Object转Json失败："+e.getMessage());
        }
        return null;
    }


    public static <T> T fromJson(String json, Type clazz) {
        try {
            return JSON.parseObject(json, clazz);
        }catch (Exception e){
            e.printStackTrace();
            LogUtils.e(TAG,e.getMessage());
        }
        return null;
    }


}
