package com.acg233.favorites.utils;

import com.acg233.favorites.App;
import com.acg233.favorites.network.FavoritesService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Describe
 * <p>Version: v1.0</p>
 * <p>Created by: lty</p>
 * <p>Created on: 2017/1/22 上午9:58</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2017年 lty. All rights reserved.</p>
 * <p>Revision：</p>
 */

public class RetrofitManager {

    private static RetrofitManager instance = null;
    private final FavoritesService favoritesService;

    private final static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .serializeNulls()
            .create();

    private RetrofitManager() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        if (App.DEBUG){
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }
        OkHttpClient okHttpClient = builder
                .connectTimeout(App.ConnectTimeOut, TimeUnit.SECONDS)
                .readTimeout(App.ReadTimeOut,TimeUnit.SECONDS)
                .writeTimeout(App.WriteTimeOut,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(App.BASE_URL)
                .client(okHttpClient)
                //配置网络请求默认在IO线程执行
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        favoritesService = retrofit.create(FavoritesService.class);
    }

    public static RetrofitManager getInstance() {
        if (instance == null) {
            synchronized (RetrofitManager.class) {
                if (instance == null) {
                    instance = new RetrofitManager();
                }
            }
        }
        return instance;
    }

    public FavoritesService getFavoritesService() {
        return favoritesService;
    }
}
