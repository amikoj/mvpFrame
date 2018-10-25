package cn.enjoytoday.mvpframe.data.remote.http;


import cn.enjoytoday.mvpframe.data.enums.DataType;
import cn.enjoytoday.mvpframe.data.enums.Method;

/**
 * 信息体
 * @author hfcai
 */
public class DataMap {

    /**
     * 请求数据定位type
     */
    private String type;
    /**
     * 在线请求数据url
     */
    private String url;
    /**
     * 优先获取数据方式
     */
    private DataType dataType = DataType.REMOTE;
    /**
     * 请求方法
     */
    private Method method = Method.POST;
    /**
     * 格式是否为标准格式
     */
    private boolean isStandard = true;

    /**
     * 是否保存缓存
     */
    private boolean isCache = true;




    public DataMap(String type, String url) {
        this.type = type;
        this.url = url;
    }


    public DataMap(String type, String url,Method method) {
        this.type = type;
        this.url = url;
        this.method = method;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public boolean isStandard() {
        return isStandard;
    }

    public void setStandard(boolean standard) {
        isStandard = standard;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public boolean isCache() {
        return isCache;
    }

    public void setCache(boolean cache) {
        isCache = cache;
    }
}
