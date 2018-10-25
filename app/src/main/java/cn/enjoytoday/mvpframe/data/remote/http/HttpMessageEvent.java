package cn.enjoytoday.mvpframe.data.remote.http;

/**
 * 网络请求信息
 * @author hfcai
 */
public class HttpMessageEvent {
    /**
     * 发送请求事件
     */
    private String tag;

    /**
     * 返回数据
     */
    private String json;

    public HttpMessageEvent(){}

    public HttpMessageEvent(String tag,String json){
        this.tag = tag;
        this.json = json;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
