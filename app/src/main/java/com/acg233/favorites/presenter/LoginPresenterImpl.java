package com.acg233.favorites.presenter;

import android.support.annotation.NonNull;

import com.acg233.favorites.contract.HomeContract;
import com.acg233.favorites.contract.LoginContract;

/**
 * Describe
 * <p>Version: v1.0</p>
 * <p>Created by: lty</p>
 * <p>Created on: 2017/1/24 上午9:44</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2017年 lty. All rights reserved.</p>
 * <p>Revision：</p>
 */

public class LoginPresenterImpl implements LoginContract.Presenter {

    public LoginPresenterImpl(@NonNull LoginContract.View loginView) {
        loginView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
