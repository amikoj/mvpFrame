package cn.enjoytoday.mvpframe.data.local;

import java.io.Serializable;

/**
 * 数据库缓存数据
 * @author hfcai
 */
public class Cache implements Serializable {

    /**
     * 数据id
     */
    private Integer id;
    /**
     * 数据内容
     */
    private String json;
    /**
     * 数据类型
     */
    private String type;
    /**
     * 数据更新时间
     */
    private long timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
