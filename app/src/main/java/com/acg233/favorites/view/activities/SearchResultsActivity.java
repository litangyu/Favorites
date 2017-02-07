package com.acg233.favorites.view.activities;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.acg233.favorites.presenter.SearchResultsPresenterImpl;
import com.umeng.analytics.MobclickAgent;

import me.lty.basemvplibrary.ui.BaseActivity;

/**
 * Describe
 * <p>Version: v1.0</p>
 * <p>Created by: lty</p>
 * <p>Created on: 2017/1/20 下午4:31</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2017年 lty. All rights reserved.</p>
 * <p>Revision：</p>
 */

public class SearchResultsActivity extends BaseActivity<SearchResultsPresenterImpl> {

    @Override
    protected SearchResultsPresenterImpl initPresenter() {
        return new SearchResultsPresenterImpl(this);
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
        getQueryExtra();
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void getQueryExtra() {
        String queryStringExtra =  getIntent().getStringExtra(SearchManager.QUERY);
        Log.d("search",queryStringExtra);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getQueryExtra();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("SearchResultsActivity");
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onStop();
        MobclickAgent.onPageEnd("SearchResultsActivity");
        MobclickAgent.onPause(this);
    }
}
