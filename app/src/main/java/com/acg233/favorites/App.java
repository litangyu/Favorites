package com.acg233.favorites;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.umeng.analytics.MobclickAgent;

/**
 * Describe
 * <p>Version: v1.0</p>
 * <p>Created by: lty</p>
 * <p>Created on: 2017/1/20 上午11:08</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2017年 lty. All rights reserved.</p>
 * <p>Revision：</p>
 */

public class App extends MultiDexApplication {

    private static App mInstance;

    public static boolean DEBUG = true;
    public static String HOST = "http://acg233.com/";
    public static int ReadTimeOut = 10;
    public static int WriteTimeOut = 10;
    public static int ConnectTimeOut = 15;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        Fresco.initialize(this);

        MobclickAgent.setScenarioType(this,MobclickAgent.EScenarioType.E_UM_NORMAL);
        //禁止默认的页面统计方式，这样将不会再自动统计Activity
        MobclickAgent.openActivityDurationTrack(false);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static synchronized App context() {
        return mInstance;
    }
}
