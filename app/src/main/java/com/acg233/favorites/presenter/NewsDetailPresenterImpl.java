package com.acg233.favorites.presenter;

import android.content.Context;

import com.acg233.favorites.view.impl.NewsDetailView;

import me.lty.basemvplibrary.base.AppBasePresenter;
import me.lty.basemvplibrary.base.View;

/**
 * Describe
 * <p>Version: v1.0</p>
 * <p>Created by: lty</p>
 * <p>Created on: 2017/1/23 上午9:03</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2017年 lty. All rights reserved.</p>
 * <p>Revision：</p>
 */

public class NewsDetailPresenterImpl extends AppBasePresenter<NewsDetailView> {

    public NewsDetailPresenterImpl(Context context) {
        super(context);
    }

    @Override
    public void initialized(Context context) {

    }
}
