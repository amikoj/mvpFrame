package cn.enjoytoday.mvpframe.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import cn.enjoytoday.mvpframe.Global;
import cn.enjoytoday.mvpframe.data.enums.DataType;
import cn.enjoytoday.mvpframe.data.remote.http.DataMap;

import java.util.Map;


/**
 *
 * 数据仓库
 * @author hfcai
 */
public class DataRepository implements DataSource {

    @Nullable
    private static DataRepository INSTANCE = null;

    @NonNull
    private final DataSource remoteDataSource;

    @NonNull
    private final DataSource localDataSource;


    private DataRepository(@NonNull DataSource remoteDataSource,
                           @NonNull DataSource localDataSource){
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param remoteDataSource the backend data source
     * @param localDataSource  the device storage data source
     * @return the {@link DataRepository} instance
     */
    public static DataRepository getInstance(@NonNull DataSource remoteDataSource,
                                             @NonNull DataSource localDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new DataRepository(remoteDataSource, localDataSource);
        }
        return INSTANCE;
    }



    /**
     * Used to force {@link #getInstance(DataSource, DataSource)} to create a new instance
     * next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }


    @Override
    public <T> void saveObj(String type,T t) {
        //保存数据
       localDataSource.saveObj(type,t);
    }


    /**
     * 删除数据缓存
     * @param type 数据类型
     */
    @Override
    public  void delObj(String type) {
        localDataSource.delObj(type);
    }

    /**
     * 获取数据,可通过{@link Global#dataMap}添加配置数据源以及配置源的规则{@link DataMap}
     *
     * @param type 数据源type
     * @param obj 数据请求参数({@link DataType#REMOTE})
     * @param tClass 返回数据类型
     * @return
     */
    @Override
    public <T> Object getObj(String type,Object obj, Class<T> tClass) {
        Object object=null;
        Map<String, DataMap> dataMap = Global.dataMap;
        DataMap map = dataMap.get(type);
        if (map!=null){
          DataType dataType =  map.getDataType();
          object = (dataType==DataType.REMOTE?remoteDataSource:localDataSource).getObj(type,obj,tClass);
          if (object==null){
              object = (dataType==DataType.REMOTE?localDataSource:remoteDataSource).getObj(type,obj,tClass);
          }
          if(map.isCache() && dataType==DataType.REMOTE && object!=null){
              saveObj(type,object);
          }
        }
        return object;
    }

    /**
     * 回收资源
     */
    @Override
    public void destroy() {
        localDataSource.destroy();
        remoteDataSource.destroy();
    }


}


