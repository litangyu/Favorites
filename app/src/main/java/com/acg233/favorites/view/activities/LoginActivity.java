package com.acg233.favorites.view.activities;

import com.acg233.favorites.R;
import com.acg233.favorites.tool.ActivityUtil;
import com.acg233.favorites.view.fragments.LoginFragment;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import me.lty.basemvplibrary.ui.BaseActivity;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {

    @Override
    protected int initContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        LoginFragment login = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id
                .content_frame);
        if (login == null) {
            login = new LoginFragment();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(),login,R.id.content_frame);
        }
    }

    @Override
    protected void bindView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}

