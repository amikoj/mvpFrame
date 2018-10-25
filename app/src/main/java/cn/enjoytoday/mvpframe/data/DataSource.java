package cn.enjoytoday.mvpframe.data;

/**
 *
 * 数据操作方法
 * @author hfcai
 */
public interface DataSource {

    /**
     * 请求对象缓存
     * @param t
     * @param <T>
     */
    <T> void saveObj(String type, T t);

    /**
     * 清理对象缓存
     * @param type 数据类型
     */
    void delObj(String type);

    /**
     * 获取已存储信息
     * @param type
     * @param object 请求参数
     * @param tClass
     * @param <T>
     * @return
     */
    <T> Object getObj(String type, Object object, Class<T> tClass);

    /**
     * 销毁
     */
    void destroy();

}
