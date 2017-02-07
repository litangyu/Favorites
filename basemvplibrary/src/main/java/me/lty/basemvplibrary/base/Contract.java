package me.lty.basemvplibrary.base;

import android.content.Context;

/**
 * Describe
 * <p>Version: v1.0</p>
 * <p>Created by: lty</p>
 * <p>Created on: 2016/9/19 下午2:15
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2016年 lty. All rights reserved.</p>
 * <p>Revision：</p>
 */
public interface Contract {

    interface BaseView extends View {

        /**
         * 显示控件 View.VISIBLE
         */
        void showView(android.view.View view);

        /**
         * 隐藏控件 View.INVISIBLE
         */
        void hideView(android.view.View view);

        /**
         * 隐藏控件 View.GONE
         */
        void disappearView(android.view.View view);
    }

    interface BasePresenter<V extends View> extends Presenter {

        void initialized(Context context);

        /**
         * 绑定View
         *
         * @param view View实现类
         */
        void attachView(V view);

        /**
         * 解绑View
         */
        void detachView();
    }
}
