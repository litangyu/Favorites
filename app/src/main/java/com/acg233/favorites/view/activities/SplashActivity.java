package com.acg233.favorites.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.acg233.favorites.App;
import com.acg233.favorites.R;
import com.acg233.favorites.api.FavoritesService;
import com.acg233.favorites.api.RetrofitManager;
import com.acg233.favorites.tool.AuthorizationUtil;
import com.acg233.favorites.tool.ErrorHandler;
import com.umeng.analytics.MobclickAgent;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import me.lty.basemvplibrary.utils.DataKeeper;
import me.lty.basemvplibrary.utils.FileUtils;
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
        keeper = new DataKeeper(this, "app");
        Observable.timer(3, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long seconds) {
                        if (!keeper.get("isLogin", false)) {
                            //用户未登录
                            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                        } else {
                            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                        }
                        finish();
                    }
                });
//        actionStart();
    }

    /**
     * 创建令牌，检查版本，登录等操作
     */
    private void actionStart() {
        favoritesService = RetrofitManager.getInstance().getFavoritesService();
        favoritesService.getTime()
                .observeOn(Schedulers.io())
                .flatMap(new Func1<Long, Observable<?>>() {
                    @Override
                    public Observable<?> call(Long time) {
                        //创建令牌
                        keeper = new DataKeeper(App.context(), "app");
                        try {
                            token = AuthorizationUtil.EncoderToken(time);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }
                        keeper.put("token", token);
                        return favoritesService.postToken();
                    }
                })
                .flatMap(new Func1<Object, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Object o) {
                        //第一次激活（检查App版本）
                        return favoritesService.checkVersion();
                    }
                })
                .flatMap(new Func1<Integer, Observable<InputStream>>() {
                    @Override
                    public Observable<InputStream> call(Integer isNewVersion) {
                        if (isNewVersion != 1) {
                            //强制更新
                            return favoritesService.download("")
                                    .unsubscribeOn(Schedulers.io())
                                    .unsubscribeOn(Schedulers.io())
                                    .map(new Func1<ResponseBody, InputStream>() {
                                        @Override
                                        public InputStream call(ResponseBody body) {
                                            return body.byteStream();
                                        }
                                    })
                                    .observeOn(Schedulers.computation())
                                    .doOnNext(new Action1<InputStream>() {
                                        @Override
                                        public void call(InputStream inputStream) {
                                            try {
                                                File outputFile = new File(Environment
                                                        .getExternalStoragePublicDirectory
                                                                (Environment.DIRECTORY_DOWNLOADS),
                                                        "Favorites.apk");
                                                FileUtils.writeFile(inputStream, outputFile);
                                            } catch (IOException e) {
                                                e.printStackTrace();
//                                                throw new CustomizeException(e.getMessage(), e);
                                            }
                                        }
                                    })
                                    .observeOn(AndroidSchedulers.mainThread());
                        } else {
                            finish();
                            return null;
                        }
                    }
                })
                .subscribe(new Action1<InputStream>() {
                    @Override
                    public void call(InputStream inputStream) {

                    }
                }, ErrorHandler.displayErrorAction(SplashActivity.this));
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}