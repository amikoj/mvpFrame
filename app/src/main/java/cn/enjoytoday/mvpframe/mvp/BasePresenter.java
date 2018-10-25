package cn.enjoytoday.mvpframe.mvp;

import android.view.View;

/**
 * ui实现接口
 * @author hfcai
 */
public interface BasePresenter {

    /**
     * 绑定UI界面
     */
    void bindView();

    /**
     * 解除UI界面的绑定
     */
    void unBindView();

    /**
     * 请求数据
     * @param type 请求type
     * @param object 请求参数
     */
    void post(final String type, final Object object);

    /**
     * view 点击接管
     * @param view
     */
    void onClick(View view);


    /**
     * 资源回收
     */
    void destroy();


}
