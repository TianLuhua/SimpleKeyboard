package com.boyue.tlh.mvpframework.proxy;

import android.os.Bundle;
import android.util.Log;

import com.boyue.tlh.mvpframework.factory.PresenterMvpFactory;
import com.boyue.tlh.mvpframework.presenter.BaseMvpPresenter;
import com.boyue.tlh.mvpframework.view.BaseMvpView;


/**
 * Created by Tianluhua on 2018\8\13 0013.
 */
public class BaseMvpProxy<V extends BaseMvpView, P extends BaseMvpPresenter<V>> implements PresenterProxyInterface<V, P> {

    /**
     * 获取onSaveInstanceState中bundle的key
     */
    private static final String PRESENTER_KEY = "presenter_key";


    /**
     * Presenter工厂类
     */
    private PresenterMvpFactory<V, P> mFactroy;
    private P mPresenter;
    private Bundle mBundle;
    private boolean mIsAttchView;

    public BaseMvpProxy(PresenterMvpFactory<V, P> presenterMvpFactory) {
        this.mFactroy = presenterMvpFactory;
    }


    @Override
    public void setPresenterFactroy(PresenterMvpFactory<V, P> presenterFactroy) {

        if (mPresenter != null) {
            throw new IllegalArgumentException("这个方法只能在getMvpPresenter()之前调用，如果Presenter已经创建则不能再修改");
        }

        this.mFactroy = presenterFactroy;
    }

    @Override
    public PresenterMvpFactory<V, P> getPresenterFactroy() {
        return mFactroy;
    }

    @Override
    public P getMvpPresenter() {
        Log.e("perfect-mvp", "Proxy getMvpPresenter");
        if (mFactroy != null) {
            if (mPresenter == null) {
                mPresenter = mFactroy.createMvpPresenter();
                mPresenter.onCreatePersenter(mBundle == null ? null : mBundle.getBundle(PRESENTER_KEY));
            }
        }
        Log.e("perfect-mvp", "Proxy getMvpPresenter = " + mPresenter);
        return mPresenter;
    }

    /**
     * 绑定Presenter和view
     *
     * @param mvpView
     */
    public void onResume(V mvpView) {
        getMvpPresenter();
        Log.e("perfect-mvp", "Proxy onResume");
        if (mPresenter != null && !mIsAttchView) {
            mPresenter.onAttachMvpView(mvpView);
            mIsAttchView = true;
        }
    }

    /**
     * 销毁Presenter持有的View
     */
    private void onDetachMvpView() {
        Log.e("perfect-mvp", "Proxy onDetachMvpView = ");
        if (mPresenter != null && mIsAttchView) {
            mPresenter.onDetachMvpView();
            mIsAttchView = false;
        }
    }

    /**
     * 销毁Presenter
     */
    public void onDestroy() {
        Log.e("perfect-mvp", "Proxy onDestroy = ");
        if (mPresenter != null) {
            onDetachMvpView();
            mPresenter.onDestroyPersenter();
            mPresenter = null;
        }
    }

    /**
     * 意外销毁的时候调用
     *
     * @return Bundle，存入回调给Presenter的Bundle和当前Presenter的id
     */
    public Bundle onSaveInstanceState() {
        Log.e("perfect-mvp", "Proxy onSaveInstanceState = ");
        Bundle bundle = new Bundle();
        getMvpPresenter();
        if (mPresenter != null) {
            Bundle presenterBundle = new Bundle();
            //回调Presenter
            mPresenter.onSaveInstanceState(presenterBundle);
            bundle.putBundle(PRESENTER_KEY, presenterBundle);
        }
        return bundle;
    }

    /**
     * 意外关闭恢复Presenter
     *
     * @param savedInstanceState 意外关闭时存储的Bundler
     */
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.e("perfect-mvp", "Proxy onRestoreInstanceState = ");
        Log.e("perfect-mvp", "Proxy onRestoreInstanceState Presenter = " + mPresenter);
        mBundle = savedInstanceState;

    }


}
