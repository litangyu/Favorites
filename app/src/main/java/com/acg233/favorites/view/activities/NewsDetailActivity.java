package com.acg233.favorites.view.activities;

import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.acg233.favorites.R;
import com.acg233.favorites.presenter.NewsDetailPresenterImpl;
import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.lty.basemvplibrary.ui.BaseActivity;

/**
 * Describe
 * <p>Version: v1.0</p>
 * <p>Created by: lty</p>
 * <p>Created on: 2017/1/23 上午9:02</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2017年 lty. All rights reserved.</p>
 * <p>Revision：</p>
 */

public class NewsDetailActivity extends BaseActivity<NewsDetailPresenterImpl> {

    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;
    @BindView(R.id.tv_text)
    protected TextView mTv_text;
    @BindView(R.id.backdrop)
    protected SimpleDraweeView mBackdrop;


    @Override
    protected NewsDetailPresenterImpl initPresenter() {
        return new NewsDetailPresenterImpl(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void bindView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        mToolbar.setTitle("001 [12月]  asfdasfdafasf");
        setSupportActionBar(mToolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_news_detail);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void initData() {
        mTv_text.setText("012 [12月]2016年12月里番合集\n" +
                "magnet:?xt=urn:btih:9150B382EE3D301094CCB80B2DACA7BAB1101DAE");

        Uri uri = Uri.parse(
                "https://gss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/f3d3572c11dfa9ece297da446ad0f703918fc12c.jpg");
        mBackdrop.setImageURI(uri);

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
        MobclickAgent.onPageStart("NewsDetailActivity");
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onStop();
        MobclickAgent.onPageEnd("NewsDetailActivity");
        MobclickAgent.onPause(this);
    }
}
