package com.boyue.tlh.mvpframework.proxy;


import com.boyue.tlh.mvpframework.factory.PresenterMvpFactory;
import com.boyue.tlh.mvpframework.presenter.BaseMvpPresenter;
import com.boyue.tlh.mvpframework.view.BaseMvpView;

/**
 * Created by Tianluhua on 2018\8\13 0013.
 */
public interface PresenterProxyInterface<V extends BaseMvpView, P extends BaseMvpPresenter<V>> {

    /**
     * 设置创建Presenter的工厂
     *
     * @param presenterFactroy PresenterFactroy的类型
     */
    void setPresenterFactroy(PresenterMvpFactory<V, P> presenterFactroy);


    /**
     * 获取Presenter的工厂类
     *
     * @return 返回PresenterFactroy的类型
     */
    PresenterMvpFactory<V, P> getPresenterFactroy();


    /**
     * 获取创建的Presenter
     *
     * @return 指定类型的Presenter
     */
    P getMvpPresenter();
}
