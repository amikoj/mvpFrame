package cn.enjoytoday.mvpframe.data;

import android.content.Context;
import android.support.annotation.NonNull;
import cn.enjoytoday.mvpframe.data.local.LocalDataSource;
import cn.enjoytoday.mvpframe.data.remote.RemoteDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author hfcai
 */
public class Injection {

    public static DataRepository provideTasksRepository(@NonNull Context context) {
        checkNotNull(context);
        return DataRepository.getInstance(RemoteDataSource.getInstance(),
                LocalDataSource.getInstance(context));
    }

}
