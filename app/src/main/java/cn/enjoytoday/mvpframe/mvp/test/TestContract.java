package cn.enjoytoday.mvpframe.mvp.test;

import cn.enjoytoday.mvpframe.mvp.BasePresenter;
import cn.enjoytoday.mvpframe.mvp.BaseView;

/**
 * @author hfcai
 */
public class TestContract {

    /**
     * 欢迎界面接口
     */
    interface  View extends BaseView<Presenter> {

    }

    /**
     * 欢迎实现界面接口
     */
    interface Presenter extends BasePresenter {

    }
}
