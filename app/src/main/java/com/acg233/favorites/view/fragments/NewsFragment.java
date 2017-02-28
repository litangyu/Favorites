package com.acg233.favorites.view.fragments;


import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.acg233.favorites.R;
import com.acg233.favorites.contract.HomeContract;
import com.acg233.favorites.presenter.HomePresenterImpl;
import com.acg233.favorites.view.adapter.ItemViewProvider.FavoritesItemViewProvider;
import com.acg233.favorites.view.adapter.item.FavoritesItem;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import me.lty.basemvplibrary.ui.BaseFragment;

/**
 * Describe 新推荐番
 * <p>Version: v1.0</p>
 * <p>Created by: lty</p>
 * <p>Created on: 2017/1/20 上午11:08</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2017年 lty. All rights reserved.</p>
 * <p>Revision：</p>
 */

public class NewsFragment extends BaseFragment implements HomeContract.View {

    @BindView(R.id.swipe_refresh)
    protected SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.recycler_view)
    protected RecyclerView mRv_news;

    private HomePresenterImpl mPresenter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.layout_list;
    }

    @Override
    protected void bindView(View view) {
        ButterKnife.bind(this,view);
    }

    @Override
    protected void initView(View view) {
        mSwipeRefresh.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefresh.setRefreshing(false);
                    }
                }, 6000);
            }
        });

        mRv_news.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager
                .VERTICAL));
        mRv_news.setItemAnimator(new DefaultItemAnimator());
        mRv_news.setHasFixedSize(true);
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = (HomePresenterImpl) presenter;
    }

    @Override
    protected void initData() {
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        FavoritesItemViewProvider favoritesItemViewProvider = new FavoritesItemViewProvider(getActivity());
        adapter.register(FavoritesItem.class, favoritesItemViewProvider);
        mRv_news.setAdapter(adapter);

        Items newsItems = new Items();
        for (int i = 0; i < 100; i++) {
            newsItems.add(new FavoritesItem());
        }
        adapter.setItems(newsItems);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("NewsFragment");
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("NewsFragment");
    }
}
