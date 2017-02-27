package com.acg233.favorites.view.activities;

import android.net.Uri;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.acg233.favorites.R;
import com.acg233.favorites.contract.NewsDetailContract;
import com.acg233.favorites.presenter.NewsDetailPresenterImpl;
import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;

/**
 * Describe
 * <p>Version: v1.0</p>
 * <p>Created by: lty</p>
 * <p>Created on: 2017/1/23 上午9:02</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2017年 lty. All rights reserved.</p>
 * <p>Revision：</p>
 */

public class NewsDetailActivity extends ToolbarActivity implements NewsDetailContract.View {

    private static final String TAG = NewsDetailActivity.class.getSimpleName();

    @BindView(R.id.fab_news_detail)
    protected FloatingActionButton mFab;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @BindView(R.id.backdrop)
    protected SimpleDraweeView mBackdrop;
    @BindView(R.id.tv_title)
    protected TextView mTv_text;

    private NewsDetailPresenterImpl mPresenter;

    @Override
    protected int initContentView() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void initView() {
        super.initView();
        mCollapsingToolbar.setTitle("测试啦");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.getWindow().getDecorView().setSystemUiVisibility(View
                    .SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        mFab.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.ic_favorite_on));
    }

    @Override
    public void setPresenter(NewsDetailContract.Presenter presenter) {
        mPresenter = (NewsDetailPresenterImpl) presenter;
    }

    @Override
    protected void initData() {
        Uri uri = Uri.parse(
                "https://gss0.baidu" +
                        ".com/94o3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item" +
                        "/f3d3572c11dfa9ece297da446ad0f703918fc12c.jpg");
        mBackdrop.setImageURI(uri);
        mTv_text.setText("012 [12月]2016年12月里番合集\n" +
                "magnet:?xt=urn:btih:9150B382EE3D301094CCB80B2DACA7BAB1101DAE");
    }

    @Override
    protected void setListener() {
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(TAG);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(TAG);
        MobclickAgent.onPause(this);
    }
}
