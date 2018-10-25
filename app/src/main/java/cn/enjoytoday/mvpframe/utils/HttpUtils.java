package cn.enjoytoday.mvpframe.utils;

import android.text.TextUtils;
import cn.enjoytoday.mvpframe.data.remote.http.HttpMessageEvent;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author hfcai
 */
public class HttpUtils {

    private static final String TAG = "HttpUtils";
    private static volatile HttpUtils httpUtils =null;

    private HttpUtils(){}

    /**
     * 单例获取
     * @return
     */
    public static HttpUtils getInstance(){
        synchronized (HttpUtils.class){
            if (httpUtils==null){
                httpUtils = new HttpUtils();
            }
        }
        return httpUtils;
    }


    /**
     * http get请求
     * @param url 请求接口地址
     * @param  tag 请求tag
     */
    public static void get(final String url, final String tag){
        LogUtils.e(TAG,"url:"+url);
        OkHttpClient client  = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder().url(url).get().build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtils.e(TAG,"请求失败:"+e.getMessage());
                transfer(tag,null);
            }

            @Override
            public void onResponse(Call call, Response response) {
                LogUtils.e(this,"请求成功,请求url："+response.request().url());
                transfer(tag,response);

            }
        });

    }

    /**
     * 传递返回
     * @param response 返回数据
     */
    private static void transfer(String tag,Response response){
        HttpMessageEvent messageEvent = new HttpMessageEvent();
        messageEvent.setTag(tag);
        try {
            if (response != null && response.body()!=null) {
                ResponseBody body = response.body();
                String res = body.string();
                LogUtils.w(TAG,"请求返回:"+res);
                messageEvent.setJson(res);
            }
        }catch (Exception e){
            e.printStackTrace();
            LogUtils.e(TAG,"发送返回信息系失败:"+e.getMessage());
        }
        EventBus.getDefault().post(messageEvent);
    }


    /**
     * Post请求接口地址
     * @param url 请求链接
     * @param requests 请求数据
     * @param  tag 请求tag
     * @param  type 请求数据格式{0:json,1: X-WWW-FORM-URLENCODED}
     *
     */
    public static void post(String url, Object requests, final String tag, int type){

        OkHttpClient client  = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        //MediaType  设置Content-Type 标头中包含的媒体类型值
        String json = JsonUtils.toJson(requests);
        LogUtils.e(null,"请求Json:"+json);

        RequestBody requestBody ;
        if (type==0){
            requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), Objects.requireNonNull(TextUtils.isEmpty(json) ? "{}" : json));
        }else {
            FormBody.Builder builder = parseFormData(json);
            requestBody = builder.build();

        }

        Request request = new Request.Builder().url(url).post(requestBody).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtils.e(TAG,"请求失败:"+e.getMessage());
                transfer(tag,null);
            }

            @Override
            public void onResponse(Call call, Response response) {
                //请求成功
                LogUtils.e(TAG,"请求成功,请求url："+response.request().url());
                transfer(tag,response);
            }
        });



    }


    private static  FormBody.Builder parseFormData(String json){
        FormBody.Builder builder= new FormBody.Builder();
        if (!TextUtils.isEmpty(json)) {
            JSONObject jsonObject = JSON.parseObject(json);
            Map<String, Object> map = new ConcurrentHashMap<>();
            map = parseJson(map, null, jsonObject);
            LogUtils.e(TAG, "map:" + map);
            for (Map.Entry<String, Object> objectEntry : map.entrySet()) {
                builder.add(objectEntry.getKey(), objectEntry.getValue().toString());
            }
        }
        return builder;
    }


    private static Map<String,Object> parseJson(Map<String,Object> map, String baseKey, JSONObject jsonObject){
        Set<Map.Entry<String,Object>> set=jsonObject.entrySet();
        for (Map.Entry<String,Object> entry:set){
            String key = entry.getKey();
            if (!TextUtils.isEmpty(baseKey)){
                key = baseKey+"["+key+"]";
            }
            Object value = entry.getValue();
            if (value instanceof JSONArray){
                int i=0;
                for (Object o : ((JSONArray) value).toArray()){
                    if (o instanceof JSONObject){
                        String base= key+"["+i+"]";
                        parseJson(map,base, (JSONObject) o);
                    }
                    i++;
                }
            }else {
                //一层
                map.put(key,value.toString());
            }
        }
        return map;
    }





}
