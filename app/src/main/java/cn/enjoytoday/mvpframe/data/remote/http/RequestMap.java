package cn.enjoytoday.mvpframe.data.remote.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hfcai
 * http请求快速创建类
 */
public class RequestMap {

    private Map<String,Object>  map = new ConcurrentHashMap<>();


    public RequestMap(){}

    public RequestMap(String key,Object value){
        map.put(key,value);
    }

    public void addValue(String key,Object value){
       map.put(key.trim(),value);
    }



    public String toJson(){
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(map));
        return jsonObject.toJSONString();
    }
}
