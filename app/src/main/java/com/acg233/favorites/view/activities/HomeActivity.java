package com.acg233.favorites.view.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import com.acg233.favorites.R;
import com.acg233.favorites.contract.HomeContract;
import com.acg233.favorites.presenter.HomePresenterImpl;
import com.acg233.favorites.view.adapter.HomeFragmentAdapter;
import com.acg233.favorites.view.fragments.HistoryFragment;
import com.acg233.favorites.view.fragments.NewsFragment;
import com.acg233.favorites.view.fragments.PersonalCenterFragment;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.lty.basemvplibrary.utils.Check;
import rx.Subscription;

/**
 * Describe 首页
 * <p>Version: v1.0</p>
 * <p>Created by: lty</p>
 * <p>Created on: 2017/1/20 上午11:08</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2017年 lty. All rights reserved.</p>
 * <p>Revision：</p>
 */

public class HomeActivity extends AppCompatActivity implements HomeContract.View {

    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;

    @BindView(R.id.tab_home)
    protected TabLayout mTab_home;
    @BindView(R.id.vp_home)
    protected ViewPager mVp_home;
    @BindView(R.id.drawer_layout)
    protected DrawerLayout mDrawerLayout;


    private HomePresenterImpl mPresenter;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private Subscription mActionFourSubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initView();
        initData();
        setListener();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    protected void initView() {
        mToolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(mToolbar);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        PersonalCenterFragment personalCenter = new PersonalCenterFragment();
        personalCenter.setArguments(getIntent().getExtras());
        transaction.replace(R.id.frame_layout, personalCenter);
        transaction.commit();

        //Toolbar上面最左边显示三杠图标监听DrawerLayout
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = (HomePresenterImpl) presenter;
    }

    protected void initData() {
        mTab_home.setTabMode(TabLayout.MODE_FIXED);

        mFragmentList.add(new NewsFragment());
        mFragmentList.add(new HistoryFragment());
        if (!Check.isEmpty(mFragmentList)) {
            mVp_home.setAdapter(new HomeFragmentAdapter(getSupportFragmentManager(),
                    mFragmentList));
        }
        mTab_home.setupWithViewPager(mVp_home);

        mPresenter.start();
        mActionFourSubscription = mPresenter.actionFour(this);
    }

    protected void setListener() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        //setSearchableInfo(）方法成功的话则会根据XML文件产生一个SearchableInfo对象
        // 当在SearchView中进行搜索时，则会通过一个包含ACTION_SEARCH的intent来启动一个Activity
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onStop();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.mActionFourSubscription.unsubscribe();
    }
}
