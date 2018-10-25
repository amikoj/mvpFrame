package cn.enjoytoday.mvpframe.mvp;

import android.support.annotation.NonNull;
import cn.enjoytoday.mvpframe.data.DataRepository;
import cn.enjoytoday.mvpframe.data.remote.http.BaseResponse;
import cn.enjoytoday.mvpframe.utils.JsonUtils;
import cn.enjoytoday.mvpframe.utils.LogUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * 基础Presenter实现
 * @author hfcai
 */
public abstract class AbstractPresenter<T extends BaseView> implements BasePresenter {

    protected T mView;

    private final ExecutorService executorService = Executors.newFixedThreadPool(100);
    @NonNull
    protected final DataRepository repository;

    /**
     *
     * @param repository 数据仓库
     * @param mView ui显示
     */
    public AbstractPresenter(@NonNull DataRepository repository,
                         @NonNull T mView){
        this.repository = checkNotNull(repository,"repository must not be null.");
        this.mView = checkNotNull(mView,"mView must not be null.");
        this.mView.setPresenter(this);
    }

    @Override
    public void bindView() {
        mView.initView();
    }

    @Override
    public void unBindView() {
        repository.destroy();
    }


    /***
     * presenter 请求model数据调用此方法(网络请求或本地数据请求),请求type为数据请求定位{@link cn.enjoytoday.mvpframe.Global }
     * 请求返回数据将调用{@link #onMessage(String, BaseResponse)}
     *
     * @param type 请求type
     * @param object 请求参数
     */
    @Override
    public void post(final String type, final Object object){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                BaseResponse baseResponse = (BaseResponse) repository
                        .getObj(type,object,BaseResponse.class);
                LogUtils.i(this,"baseResonse:"+JsonUtils.toJson(baseResponse));
                onMessage(type,baseResponse);
            }
        });
    }


    /**
     * 与model交互请求产生的数据由此回调
     * @param type 请求数据type
     * @param object 返回数据
     */
    public abstract  void onMessage(String type,BaseResponse object);


    /**
     * 资源回收
     */
    @Override
    public void destroy() {
        executorService.shutdownNow();
    }
}
