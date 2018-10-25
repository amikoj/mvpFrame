package cn.enjoytoday.mvpframe.data.enums;

/**
 * 数据类型(在线数据和本地数据)
 * @author hfcai
 */
public enum DataType {

    /**
     * 在线数据
     */
    REMOTE(0),
    /**
     * 本地数据
     */
    LOCAL(1);

    private int type;

    DataType(int type){
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
