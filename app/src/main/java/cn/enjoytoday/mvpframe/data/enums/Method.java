package cn.enjoytoday.mvpframe.data.enums;

/**
 * @author hfcai
 */

public enum Method {
    /**
     * post请求
     */
    POST("POST"),
    /**
     * get 请求
     */
    GET("GET");
    String method;
    Method(String method){
        this.method =method;
    }

    public String getMethod() {
        return method;
    }
}
