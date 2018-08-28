package com.boyue.tlh.mvpframework.presenter;

import android.os.Bundle;
import android.util.Log;

import com.boyue.tlh.mvpframework.view.BaseMvpView;


/**
 * Presenter基类
 * Created by Tianluhua on 2018\8\13 0013.
 */
public class BaseMvpPresenter<V extends BaseMvpView> {

    /**
     * V层View
     */
    private V mView;


    /**
     * Presenter被创建时候调用
     *
     * @param savedState
     */
    public void onCreatePersenter(Bundle savedState) {
        Log.e("perfect-mvp", "P onCreatePersenter = ");
    }

    /**
     * 绑定View
     *
     * @param mView
     */
    public void onAttachMvpView(V mView) {
        this.mView = mView;
        Log.e("perfect-mvp", "P onResume");
    }

    public void onDetachMvpView() {
        this.mView = null;
        Log.e("perfect-mvp", "P onDetachMvpView = ");
    }

    /**
     * Presenter销毁时候被调用
     */
    public void onDestroyPersenter() {
        Log.e("perfect-mvp", "P onDestroy = ");
    }

    /**
     * 在Presenter意外销毁时候被调用
     */
    public void onSaveInstanceState(Bundle outState) {
        Log.e("perfect-mvp", "P onSaveInstanceState = ");
    }

    /**
     * 获取V层接口View
     *
     * @return 返回当前的mView
     */
    public V getMvpView() {
        return mView;
    }


}
