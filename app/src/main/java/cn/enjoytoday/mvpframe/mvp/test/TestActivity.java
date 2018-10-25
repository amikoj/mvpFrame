package cn.enjoytoday.mvpframe.mvp.test;

import android.os.Bundle;
import cn.enjoytoday.mvpframe.R;
import cn.enjoytoday.mvpframe.mvp.BaseActivity;

/**
 * 测试activity
 * @author hfcai
 */
public class TestActivity extends BaseActivity<TestPresenter,TestContract.View> implements TestContract.View  {

    @Override
    protected void inflateLayout() {
        setContentView(R.layout.activity_test);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setPresenter(TestContract.Presenter presenter) {
        this.presenter = (TestPresenter) presenter;
    }

    @Override
    public void initView() {


    }
}
