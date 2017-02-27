package com.acg233.favorites.view.fragments;

import android.view.View;
import android.widget.TextView;

import com.acg233.favorites.R;
import com.acg233.favorites.contract.NewsDetailContract;
import com.acg233.favorites.presenter.NewsDetailPresenterImpl;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.lty.basemvplibrary.ui.BaseFragment;

/**
 * Describe
 * <p>Version: v1.0</p>
 * <p>Created by: lty</p>
 * <p>Created on: 2017/2/24 下午2:13</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2017年 lty. All rights reserved.</p>
 * <p>Revision：</p>
 */

public class NewsDetailFragment extends BaseFragment implements NewsDetailContract.View{

    private static final String TAG = NewsDetailFragment.class.getSimpleName();

    @BindView(R.id.tv_title)
    protected TextView mTv_text;

    private NewsDetailPresenterImpl mPresenter;

    @Override
    public void setPresenter(NewsDetailContract.Presenter presenter) {
        mPresenter = (NewsDetailPresenterImpl) presenter;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.content_news_details;
    }

    @Override
    protected void bindView(View view) {
        ButterKnife.bind(this,view);
    }

    @Override
    protected void initView(View view) {
        mPresenter = new NewsDetailPresenterImpl(this);
    }

    @Override
    protected void initData() {
        mTv_text.setText("012 [12月]2016年12月里番合集\n" +
                "magnet:?xt=urn:btih:9150B382EE3D301094CCB80B2DACA7BAB1101DAE");

    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(TAG);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(TAG);
    }
}
