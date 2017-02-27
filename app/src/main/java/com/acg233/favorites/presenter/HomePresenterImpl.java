package com.acg233.favorites.presenter;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.acg233.favorites.api.RetrofitManager;
import com.acg233.favorites.api.type.BadQQ;
import com.acg233.favorites.contract.HomeContract;
import com.acg233.favorites.tool.ErrorHandler;
import com.acg233.favorites.tool.RegexUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.lty.basemvplibrary.tool.DataKeeper;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Describe
 * <p>Version: v1.0</p>
 * <p>Created by: lty</p>
 * <p>Created on: 2017/1/23 上午9:19</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2017年 lty. All rights reserved.</p>
 * <p>Revision：</p>
 */

public class HomePresenterImpl implements HomeContract.Presenter {

    public HomePresenterImpl(@NonNull HomeContract.View homeView) {
        homeView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    public Subscription actionFour(final Context context) {
        //获取手机\Tencent\MobileQQ\artfilter\*.config 提取QQ号
        File pathQQ = new File(Environment.getExternalStorageDirectory() + "/tencent/MobileQQ");
        final BadQQ badQQ = new BadQQ();
        final List<Long> badQQList = new ArrayList<>();
        File[] files = pathQQ.listFiles();
        if (files != null ) {
            Observable.from(files)
                    .map(new Func1<File, String>() {
                        @Override
                        public String call(File file) {
                            String fileName = file.getName();
                            if (RegexUtil.isQQNumValid(fileName)) {
                                return fileName;
                            } else {
                                return null;
                            }
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe(new Action1<String>() {
                        @Override
                        public void call(String qq) {
                            if (qq != null) {
                                badQQList.add(Long.parseLong(qq));
                            }
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {

                        }
                    });
        }
        DataKeeper keeper = new DataKeeper(context, "app");
        String uid = (String) keeper.get("uid");
        badQQ.setBad(uid);
        return RetrofitManager.getInstance().getFavoritesService()
                .postBadQQ(badQQ)
                .observeOn(Schedulers.io())
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {

                    }
                }, ErrorHandler.displayErrorAction(context));

    }
}
