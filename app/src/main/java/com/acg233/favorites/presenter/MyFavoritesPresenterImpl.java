package com.acg233.favorites.presenter;

import android.support.annotation.NonNull;

import com.acg233.favorites.contract.LoginContract;
import com.acg233.favorites.contract.MyFavoritesContract;

/**
 * Describe
 * <p>Version: v1.0</p>
 * <p>Created by: lty</p>
 * <p>Created on: 2017/1/24 上午9:47</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2017年 lty. All rights reserved.</p>
 * <p>Revision：</p>
 */

public class MyFavoritesPresenterImpl implements MyFavoritesContract.Presenter {

    public MyFavoritesPresenterImpl(@NonNull MyFavoritesContract.View myFavoritesView) {
        myFavoritesView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
