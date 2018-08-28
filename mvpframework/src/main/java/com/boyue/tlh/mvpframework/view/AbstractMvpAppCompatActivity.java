package com.boyue.tlh.mvpframework.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.boyue.tlh.mvpframework.factory.PresenterMvpFactory;
import com.boyue.tlh.mvpframework.factory.PresenterMvpFactoryImpl;
import com.boyue.tlh.mvpframework.presenter.BaseMvpPresenter;
import com.boyue.tlh.mvpframework.proxy.BaseMvpProxy;
import com.boyue.tlh.mvpframework.proxy.PresenterProxyInterface;


/**
 * Created by Tianluhua on 2018\8\13 0013.
 */
public class AbstractMvpAppCompatActivity<V extends BaseMvpView, P extends BaseMvpPresenter<V>> extends AppCompatActivity implements PresenterProxyInterface<V, P> {

    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";
    /**
     * 创建被代理对象,传入默认Presenter的工厂
     */
    private BaseMvpProxy<V, P> mProxy = new BaseMvpProxy<>(PresenterMvpFactoryImpl.<V, P>createFactory(getClass()));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("perfect-mvp", "V onCreate");
        Log.e("perfect-mvp", "V onCreate mProxy = " + mProxy);
        Log.e("perfect-mvp", "V onCreate this = " + this.hashCode());

        if (savedInstanceState != null) {
            mProxy.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_SAVE_KEY));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("perfect-mvp", "V onResume");
        mProxy.onResume((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("perfect-mvp", "V onDestroy = ");
        mProxy.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("perfect-mvp", "V onSaveInstanceState");
        outState.putBundle(PRESENTER_SAVE_KEY, mProxy.onSaveInstanceState());
    }

    @Override
    public void setPresenterFactroy(PresenterMvpFactory<V, P> presenterFactroy) {
        Log.e("perfect-mvp", "V setPresenterFactory");
        mProxy.setPresenterFactroy(presenterFactroy);

    }

    @Override
    public PresenterMvpFactory<V, P> getPresenterFactroy() {
        Log.e("perfect-mvp", "V getPresenterFactory");
        return mProxy.getPresenterFactroy();
    }

    @Override
    public P getMvpPresenter() {
        Log.e("perfect-mvp", "V getMvpPresenter");
        return mProxy.getMvpPresenter();
    }
}
