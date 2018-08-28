package com.boyue.tlh.mvpframework.factory;


import com.boyue.tlh.mvpframework.presenter.BaseMvpPresenter;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 定义创建Presenter注解
 * Created by Tianluhua on 2018\8\13 0013.
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface CreatePresenter {
    Class<? extends BaseMvpPresenter> value();
}
