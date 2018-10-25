package cn.enjoytoday.mvpframe.mvp;

/**
 * ui展示接口
 * @author hfcai
 */
public interface BaseView<T extends BasePresenter>{

    /**
     * 绑定Presenter
     * @param presenter 绑定的presenter.
     */
    void setPresenter(T presenter);

    /**
     * 界面是否绑定
     * @return
     */
    boolean isActive();


    /**
     * 查找view
     */
    void initView();


    /**
     * 结束页面
     */
    void finish();

}
