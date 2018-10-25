package cn.enjoytoday.mvpframe;

import android.app.Application;
import android.content.Context;

/**
 * @author hfcai
 */
public class MvpApplication extends Application {


    protected static Context context;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        MvpApplication.context = context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }



}
