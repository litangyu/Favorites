package com.acg233.favorites.view.fragments;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.acg233.favorites.R;
import com.acg233.favorites.contract.HomeContract;
import com.acg233.favorites.presenter.HomePresenterImpl;
import com.acg233.favorites.view.adapter.ItemViewProvider.HistoryItemViewProvider;
import com.acg233.favorites.view.adapter.item.HistoryItem;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import me.lty.basemvplibrary.ui.BaseFragment;

/**
 * Describe 历史番
 * <p>Version: v1.0</p>
 * <p>Created by: lty</p>
 * <p>Created on: 2017/1/20 上午11:08</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2017年 lty. All rights reserved.</p>
 * <p>Revision：</p>
 */

public class HistoryFragment extends BaseFragment implements HomeContract.View{

    @BindView(R.id.swipe_refresh)
    protected SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.recycler_view)
    protected RecyclerView mRv_history;

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
        mRv_history.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv_history.setHasFixedSize(true);
        mRv_history.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = (HomePresenterImpl) presenter;
    }

    @Override
    protected void initData() {
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        HistoryItemViewProvider historyItemViewProvider = new HistoryItemViewProvider(getActivity());
        adapter.register(HistoryItem.class, historyItemViewProvider);
        mRv_history.setAdapter(adapter);
        Items historyItems = new Items();
        for (int i = 0;i<10;i++){
            historyItems.add(new HistoryItem());
        }
        adapter.setItems(historyItems);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("HistoryFragment");
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("HistoryFragment");
    }
}
