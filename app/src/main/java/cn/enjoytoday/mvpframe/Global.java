package cn.enjoytoday.mvpframe;

import cn.enjoytoday.mvpframe.data.remote.http.DataMap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * 静态常量
 * @author hfcai
 */
public  class Global {

    /**
     * 域名地址
     */
    protected final static String DOMAIN = BuildConfig.DOMAIN;


    /**
     * 绑定数据信息
     */
    public static final Map<String,DataMap> dataMap = new ConcurrentHashMap<>();

    static {

        dataMap.put("Test",new DataMap("Test",DOMAIN+"test"));
    }



}
