package cn.enjoytoday.mvpframe.data.remote.http;

import java.io.Serializable;

/**
 *
 * 网络请求返回response
 * @author hfcai
 */
public class BaseResponse<T> implements Serializable {

    /**
     * 全局响应参数(http 状态 200 成功 400 参数错误 404 请求错误)
     */
    private Integer status;
    /**
     * 全局响应参数(提示信息)
     */
    private String message;

    /**
     * 请求返回结果
     */
    private T data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
