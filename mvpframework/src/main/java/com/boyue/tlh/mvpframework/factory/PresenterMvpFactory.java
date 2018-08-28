package com.boyue.tlh.mvpframework.factory;


import com.boyue.tlh.mvpframework.presenter.BaseMvpPresenter;
import com.boyue.tlh.mvpframework.view.BaseMvpView;

/**
 * 定义创建Presenter的接口
 * Created by Tianluhua on 2018\8\13 0013.
 */
public interface PresenterMvpFactory<V extends BaseMvpView, P extends BaseMvpPresenter> {

    /**
     * 创建Presenter的接口方法
     *
     * @return 需要创建的Presenter
     */
    P createMvpPresenter();

}
