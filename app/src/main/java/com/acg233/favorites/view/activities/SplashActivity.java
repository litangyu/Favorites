package com.acg233.favorites.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.acg233.favorites.App;
import com.acg233.favorites.R;
import com.acg233.favorites.bean.BaseRequest;
import com.acg233.favorites.network.FavoritesService;
import com.acg233.favorites.service.DownloadService;
import com.acg233.favorites.utils.AuthorizationUtil;
import com.acg233.favorites.utils.RetrofitManager;
import com.umeng.analytics.MobclickAgent;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import me.lty.basemvplibrary.utils.DataKeeper;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Describe 启动页
 * <p>Version: v1.0</p>
 * <p>Created by: lty</p>
 * <p>Created on: 2017/1/20 上午11:08</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2017年 lty. All rights reserved.</p>
 * <p>Revision：</p>
 */

public class SplashActivity extends AppCompatActivity {

    private FavoritesService favoritesService;
    private DataKeeper keeper;

    private String token = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Intent intent = new Intent(this, DownloadService.class);
        startService(intent);

        Observable.timer(3, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        if (!keeper.get("isLogin", false)) {
                            //用户未登录
                            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                        } else {
                            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                        }
                    }
                });
        actionStart();
    }

    /**
     * 创建令牌，检查版本，登录等操作
     */
    private void actionStart() {
        favoritesService = RetrofitManager.getInstance().getFavoritesService();
        favoritesService.getTime()
                .observeOn(Schedulers.io())
                .flatMap(new Func1<Long, Observable<Object>>() {
                    @Override
                    public Observable<Object> call(Long time) {
                        //创建令牌
                        keeper = new DataKeeper(App.context(), "app");
                        try {
                            String randomString = AuthorizationUtil.getRandomString(32);
                            String timeMD5 = AuthorizationUtil.EncoderByMd5(String.valueOf(time));
                            token = AuthorizationUtil.EncoderByMd5(randomString + timeMD5);
                            keeper.put("token", token);
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        return favoritesService.postToken();
                    }
                })
                .flatMap(new Func1<Object, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Object o) {
                        BaseRequest baseRequest = new BaseRequest();
                        baseRequest.setToken(token);
                        //第一次激活（检查App版本）
                        return favoritesService.checkVersion(baseRequest);
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        if (integer != 1) {
                            //强制更新
                        } else {
                            finish();
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("SplashActivity");
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onStop();
        MobclickAgent.onPageEnd("SplashActivity");
        MobclickAgent.onPause(this);
    }

}