package cn.enjoytoday.mvpframe.data.remote;

import android.text.TextUtils;
import cn.enjoytoday.mvpframe.Global;
import cn.enjoytoday.mvpframe.data.DataSource;
import cn.enjoytoday.mvpframe.data.enums.Method;
import cn.enjoytoday.mvpframe.data.remote.http.BaseResponse;
import cn.enjoytoday.mvpframe.data.remote.http.DataMap;
import cn.enjoytoday.mvpframe.data.remote.http.HttpMessageEvent;
import cn.enjoytoday.mvpframe.utils.HttpUtils;
import cn.enjoytoday.mvpframe.utils.JsonUtils;
import cn.enjoytoday.mvpframe.utils.LogUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 在线数据获取
 * @author hfcai
 */
public class RemoteDataSource implements DataSource {

    /**
     * 阻塞队列
     */
    private final static ArrayBlockingQueue<HttpMessageEvent> queue= new ArrayBlockingQueue<>(100);
    private static RemoteDataSource INSTANCE;

    private RemoteDataSource() {
        EventBus.getDefault().register(this);
    }


    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource();
        }
        return INSTANCE;
    }



    @Override
    public <T> void saveObj(String type, T t) {
        //在线数据保存
        return;
    }

    @Override
    public void delObj(String type) {
        //在线数据删除

    }


    /**
     * 通知类
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onMessageEvent(HttpMessageEvent event) {
        LogUtils.i(this,"onMessageEvent:");
        try {
            queue.put(event);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e(this, "get Exception:" + e.getMessage());
        }


    }

    /**
     * 处理
     * @param dataMap
     * @param event
     * @param tClass
     * @param <T>
     * @return
     */
    private <T> T handleEvent(DataMap dataMap, HttpMessageEvent event, Class<T> tClass){
        if (TextUtils.isEmpty(event.getJson())){
            return null;
        }
        if (dataMap.isStandard()){
            /**
             * 标准数据格式
             */
            BaseResponse baseResponse = JsonUtils.fromJson(event.getJson(),BaseResponse.class);
            if (baseResponse!=null && baseResponse.getStatus()==200){
                return JsonUtils.fromJson(event.getJson(),tClass);
            }
        }

        return null;

    }


    @Override
    public <T> Object getObj(String type,Object object,Class<T> tClass) {
        Object o=null;
        try {
            Map<String, DataMap> dataMap = Global.dataMap;
            if (dataMap.containsKey(type)) {
                DataMap data = dataMap.get(type);
                if (data != null) {
                    if (data.getMethod() == Method.POST) {
                        HttpUtils.post(data.getUrl(), object, type, 1);
                    } else {
                        HttpUtils.get(data.getUrl(), type);
                    }
                }
                o= handleEvent(data,queue.take(),tClass);
                LogUtils.w(this,"getObj:"+JsonUtils.toJson(o));
            }
        }catch (Exception e){
            e.printStackTrace();
            LogUtils.e(this,"获取异常:"+e.getMessage());
        }

        return o;
    }

    @Override
    public void destroy() {
        EventBus.getDefault().unregister(this);
    }
}
