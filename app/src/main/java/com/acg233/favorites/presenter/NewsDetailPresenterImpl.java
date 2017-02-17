package com.acg233.favorites.presenter;

import android.support.annotation.NonNull;

import com.acg233.favorites.contract.NewsDetailContract;

/**
 * Describe
 * <p>Version: v1.0</p>
 * <p>Created by: lty</p>
 * <p>Created on: 2017/1/23 上午9:03</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2017年 lty. All rights reserved.</p>
 * <p>Revision：</p>
 */

public class NewsDetailPresenterImpl implements NewsDetailContract.Presenter {

    public NewsDetailPresenterImpl(@NonNull NewsDetailContract.View newsDetailView) {
        newsDetailView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
