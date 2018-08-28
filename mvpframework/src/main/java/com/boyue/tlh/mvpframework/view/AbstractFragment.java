package com.boyue.tlh.mvpframework.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.boyue.tlh.mvpframework.factory.PresenterMvpFactory;
import com.boyue.tlh.mvpframework.factory.PresenterMvpFactoryImpl;
import com.boyue.tlh.mvpframework.presenter.BaseMvpPresenter;
import com.boyue.tlh.mvpframework.proxy.BaseMvpProxy;
import com.boyue.tlh.mvpframework.proxy.PresenterProxyInterface;


/**
 * Created by Tianluhua on 2018\8\13 0013.
 */
public class AbstractFragment<V extends BaseMvpView, P extends BaseMvpPresenter<V>> extends Fragment implements PresenterProxyInterface<V, P> {


    /**
     * 调用onSaveInstanceState时存入Bundle的key
     */
    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";

    /**
     * 创建代理对象，传入默认的Presenter的工厂
     */
    private BaseMvpProxy<V, P> mvpProxy = new BaseMvpProxy<>(PresenterMvpFactoryImpl.<V, P>createFactory(getClass()));


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
            mvpProxy.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mvpProxy.onResume((V) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mvpProxy.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(PRESENTER_SAVE_KEY, mvpProxy.onSaveInstanceState());
    }

    /**
     * 可以实现自己的PresenterMvpFactory工厂
     *
     * @param presenterFactroy PresenterFactroy的类型
     */
    @Override
    public void setPresenterFactroy(PresenterMvpFactory<V, P> presenterFactroy) {
        mvpProxy.setPresenterFactroy(presenterFactroy);

    }

    /**
     * 获取创建的Presenter工厂
     *
     * @return PresenterMvpFactory类型
     */
    @Override
    public PresenterMvpFactory<V, P> getPresenterFactroy() {
        return mvpProxy.getPresenterFactroy();
    }

    @Override
    public P getMvpPresenter() {
        return mvpProxy.getMvpPresenter();
    }
}
