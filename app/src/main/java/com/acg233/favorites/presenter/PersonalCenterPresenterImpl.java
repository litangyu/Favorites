package com.acg233.favorites.presenter;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;

import com.acg233.favorites.view.impl.HomeView;
import com.acg233.favorites.view.impl.PersonalCenterView;

import me.lty.basemvplibrary.base.AppBasePresenter;

/**
 * Describe
 * <p>Version: v1.0</p>
 * <p>Created by: lty</p>
 * <p>Created on: 2017/1/24 上午9:57</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2017年 lty. All rights reserved.</p>
 * <p>Revision：</p>
 */

public class PersonalCenterPresenterImpl extends AppBasePresenter<HomeView> {

    public PersonalCenterPresenterImpl(Context context) {
        super(context);
    }

    @Override
    public void initialized(Context context) {

    }

    public DrawerLayout getDrawerLayout(){
        return mView.getDrawerLayout();
    }
}
