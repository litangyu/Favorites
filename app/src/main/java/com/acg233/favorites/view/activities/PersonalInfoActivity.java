package com.acg233.favorites.view.activities;

import android.support.v7.widget.Toolbar;

import com.acg233.favorites.R;
import com.acg233.favorites.tool.ActivityUtil;
import com.acg233.favorites.view.fragments.PersonalInfoFragment;

import butterknife.BindView;

/**
 * Describe
 * <p>Version: v1.0</p>
 * <p>Created by: lty</p>
 * <p>Created on: 2017/2/24 上午11:14</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2017年 lty. All rights reserved.</p>
 * <p>Revision：</p>
 */

public class PersonalInfoActivity extends ToolbarActivity {

    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;

    @Override
    protected int initContentView() {
        return R.layout.base_toolbar_activity;
    }

    @Override
    protected void initView() {
        super.initView();
        mToolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        PersonalInfoFragment personalInfo = (PersonalInfoFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);
        if (personalInfo == null) {
            personalInfo = new PersonalInfoFragment();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), personalInfo, R.id
                    .content_frame);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }
}
