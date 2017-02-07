package com.acg233.favorites.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.acg233.favorites.presenter.MyFavoritesPresenterImpl;
import com.umeng.analytics.MobclickAgent;

import me.lty.basemvplibrary.ui.BaseActivity;

/**
 * Describe
 * <p>Version: v1.0</p>
 * <p>Created by: lty</p>
 * <p>Created on: 2017/1/24 上午9:13</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2017年 lty. All rights reserved.</p>
 * <p>Revision：</p>
 */

public class MyFavoritesActivity extends BaseActivity<MyFavoritesPresenterImpl> {



    @Override
    protected MyFavoritesPresenterImpl initPresenter() {
        return null;
    }

    @Override
    protected int initContentView() {
        return 0;
    }

    @Override
    protected void bindView() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("MyFavoritesActivity");
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onStop();
        MobclickAgent.onPageEnd("MyFavoritesActivity");
        MobclickAgent.onPause(this);
    }
}
