package cn.enjoytoday.mvpframe.mvp;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import cn.enjoytoday.mvpframe.MvpApplication;
import cn.enjoytoday.mvpframe.data.DataRepository;
import cn.enjoytoday.mvpframe.data.Injection;
import cn.enjoytoday.mvpframe.utils.LogUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author hfcai
 * fragment实现
 */
public abstract class BaseFragment<T extends BasePresenter,V extends BaseView> extends Fragment implements View.OnClickListener{

    protected T presenter = null;
    private V mView =null;
    protected  boolean isDestroy = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this instanceof BaseView){
            mView = (V) this;
        }
        try {
            if (this instanceof BaseView) {
                mView = (V) this;
            }
            if (mView!=null) {
                Class<T> t = getClassType(0);
                Constructor<T> constructor = t.getConstructor(DataRepository.class, getClassType(1));
                presenter = constructor.newInstance(Injection.provideTasksRepository(MvpApplication.getContext()), mView);
            }
        }catch (Exception e){
            e.printStackTrace();
            LogUtils.e(this,"异常:"+e.getMessage());
        }

        /**
         * 有待商榷，可能还是需要手动绑定
         */
        if (presenter!=null) {
            presenter.bindView();
        }

    }

    @Override
    public Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {
        return super.onCreateAnimator(transit, enter, nextAnim);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isDestroy =true;
    }

    private Class getClassType(int pos){
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return (Class)params[pos];

    }



    protected void addOnClick(View parent, int id){
        if (parent==null && getActivity()!=null) {
            getActivity().findViewById(id).setOnClickListener(this);
        }else if (parent!=null){
            parent.findViewById(id).setOnClickListener(this);
        }
    }


    protected void addOnClick(View parent, int[] ids){
        for (int id:ids){
            addOnClick(parent,id);
        }
    }


    public boolean isActive() {
        return isAdded();
    }


    @Override
    public void onClick(View v) {
        if (v!=null && presenter!=null){
            presenter.onClick(v);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter!=null) {
            presenter.unBindView();
        }
    }





}
