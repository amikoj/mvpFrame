package cn.enjoytoday.mvpframe.mvp.test;

import android.support.annotation.NonNull;
import android.view.View;
import cn.enjoytoday.mvpframe.Global;
import cn.enjoytoday.mvpframe.data.DataRepository;
import cn.enjoytoday.mvpframe.data.remote.http.BaseResponse;
import cn.enjoytoday.mvpframe.mvp.AbstractPresenter;
import cn.enjoytoday.mvpframe.utils.JsonUtils;
import cn.enjoytoday.mvpframe.utils.LogUtils;

/**
 * 测试界面
 * @author hfcai
 */
public class TestPresenter extends AbstractPresenter<TestContract.View> implements TestContract.Presenter {



    public TestPresenter(@NonNull DataRepository repository, @NonNull TestContract.View mView){
       super(repository,mView);
    }


    @Override
    public void bindView() {
        super.bindView();
        post("Test",null);
    }

    @Override
    public void unBindView() {

    }

    @Override
    public void onMessage(String type, BaseResponse object) {
        switch (type){
            case "Test":
                LogUtils.e(this,"response:"+JsonUtils.toJson(object));
                break;
            default:
                break;
        }

    }

    @Override
    public void onClick(View view) {

    }
}
