package com.acg233.favorites.presenter;

import android.support.annotation.NonNull;

import com.acg233.favorites.contract.SearchResultsContract;

/**
 * Describe
 * <p>Version: v1.0</p>
 * <p>Created by: lty</p>
 * <p>Created on: 2017/1/24 上午10:16</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2017年 lty. All rights reserved.</p>
 * <p>Revision：</p>
 */

public class SearchResultsPresenterImpl implements SearchResultsContract.Presenter {

    public SearchResultsPresenterImpl(@NonNull SearchResultsContract.View searchResultsView) {
        searchResultsView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
