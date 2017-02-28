package com.acg233.favorites.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.acg233.favorites.R;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Describe
 * <p>Version: v1.0</p>
 * <p>Created by: lty</p>
 * <p>Created on: 2017/2/28 上午11:00</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2017年 lty. All rights reserved.</p>
 * <p>Revision：</p>
 */

public class WelcomeActivity extends AppCompatActivity {

    private static final String TAG = WelcomeActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_register,R.id.btn_login})
    public void onClick(View view){
        if (view.getId() == R.id.btn_login){
            startActivity(new Intent(this,LoginActivity.class));
        }else {
            startActivity(new Intent(this,RegisterActivity.class));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(TAG);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onStop();
        MobclickAgent.onPageEnd(TAG);
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
