package com.boyue.tlh.mvpframework.factory;


import com.boyue.tlh.mvpframework.presenter.BaseMvpPresenter;
import com.boyue.tlh.mvpframework.view.BaseMvpView;

/**
 * 定义创建Presenter具体实现类
 * Created by Tianluhua on 2018\8\13 0013.
 */
public class PresenterMvpFactoryImpl<V extends BaseMvpView, P extends BaseMvpPresenter<V>> implements PresenterMvpFactory<V, P> {

    /**
     * 需要创建的Presenter的类型
     */
    private final Class<P> mPresenterClass;

    /**
     * 获取Presenter的核心方法
     *
     * @param viewClass
     * @param <V>
     * @param <P>
     * @return
     */
    public static <V extends BaseMvpView, P extends BaseMvpPresenter<V>> PresenterMvpFactoryImpl createFactory(Class<?> viewClass) {
        //获View取层的注释
        CreatePresenter annotation = viewClass.getAnnotation(CreatePresenter.class);
        Class<P> aClass = null;
        if (annotation != null) {
            aClass = (Class<P>) annotation.value();
        }
        return aClass == null ? null : new PresenterMvpFactoryImpl<V, P>(aClass);
    }


    private PresenterMvpFactoryImpl(Class<P> presenterClass) {
        this.mPresenterClass = presenterClass;
    }

    /**
     * 获取Presenter实例
     *
     * @return
     */
    @Override
    public P createMvpPresenter() {
        try {
            return mPresenterClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Presenter创建失败!，检查是否声明了@CreatePresenter(xx.class)注解");
        }
    }
}
