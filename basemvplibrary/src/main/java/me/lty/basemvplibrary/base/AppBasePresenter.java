package me.lty.basemvplibrary.base;

import android.content.Context;

import me.lty.basemvplibrary.base.AppRuntimeException.ViewNotAttachedException;

/**
 * Describe
 * <p>Version: v1.0</p>
 * <p>Created by: lty</p>
 * <p>Created on: 2016/9/19 下午2:15
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2016年 lty. All rights reserved.</p>
 * <p>Revision：</p>
 */
public abstract class AppBasePresenter<T extends Contract.BaseView>
        implements Contract.BasePresenter<T> {

    protected Context mContext;
    protected T mView;

    public AppBasePresenter(Context context) {
        mContext =context;
    }

    /**
     * 绑定View
     *
     * @param view view实现类
     */
    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    /**
     * 解绑View
     */
    @Override
    public void detachView() {
        try {
            this.mView = null;
        } catch (ViewNotAttachedException e) {
            e.printStackTrace();
        }
    }

    public boolean isViewAttached() {
        return mView != null;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new ViewNotAttachedException();
    }

}
