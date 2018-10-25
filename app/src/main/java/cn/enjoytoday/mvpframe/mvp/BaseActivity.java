package cn.enjoytoday.mvpframe.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import cn.enjoytoday.mvpframe.data.DataRepository;
import cn.enjoytoday.mvpframe.data.Injection;
import cn.enjoytoday.mvpframe.utils.LogUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 *
 * 基础类的实现
 * @author hfcai
 */
public abstract class BaseActivity <T extends BasePresenter,V extends BaseView > extends AppCompatActivity implements View.OnClickListener{


    protected T presenter = null;
    private V mView =null;

    /**
     * 设置布局
     */
    protected abstract  void inflateLayout();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflateLayout();
        try {
            if (this instanceof BaseView) {
                mView = (V) this;
            }
            if (mView!=null) {
                Class<T> t = getClassType(0);
                Constructor<T> constructor = t.getConstructor(DataRepository.class, getClassType(1));
                presenter = constructor.newInstance(Injection.provideTasksRepository(this.getApplicationContext()), mView);
            }
        }catch (Exception e){
            e.printStackTrace();
            LogUtils.e(this,"异常:"+e.getMessage());
        }
        if (presenter!=null) {
            presenter.bindView();
        }
    }


    private Class getClassType(int pos){
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return (Class)params[pos];

    }




    protected void addOnClick(View parent, int id){
        if (parent==null) {
            findViewById(id).setOnClickListener(this);
        }else{
            parent.findViewById(id).setOnClickListener(this);
        }
    }


    protected void addOnClick(View parent, int[] ids){
        for (int id:ids){
            addOnClick(parent,id);
        }
    }

    public boolean isActive() {
        return true;
    }


    @Override
    public void onClick(View v) {
        if (v!=null && presenter!=null){
            presenter.onClick(v);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null) {
            presenter.unBindView();
        }
    }



}
