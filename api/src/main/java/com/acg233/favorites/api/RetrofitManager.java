package com.acg233.favorites.api;

import com.acg233.favorites.api.tool.ResponseTypeAdapterFactory;
import com.acg233.favorites.api.type.Auth;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
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

    private static Auth auth = null;
    public static boolean debug = true;
    private static final String BASE_HOST = "http://acg233.com/";

    private static RetrofitManager instance = null;
    private final FavoritesService favoritesService;

    private final static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .serializeNulls()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapterFactory(new ResponseTypeAdapterFactory())
            .create();

    private RetrofitManager() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        if (debug) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }
        OkHttpClient okHttpClient = builder
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        Request.Builder builder = original.newBuilder();
                        builder.addHeader("Content-Type", "application/json; charset=utf-8");
                        builder.addHeader("Accept-Encoding", "gzip");
                        if (auth != null) {
                            builder.addHeader("Authorization", "token " + auth.getAccess_token());
                        }
                        return chain.proceed(builder.build());
                    }
                })
                .retryOnConnectionFailure(true)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpUrl.parse(BASE_HOST))
                .client(okHttpClient)
                //配置网络请求默认在IO线程执行
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                //JSON转换器
                .addConverterFactory(GsonConverterFactory.create(gson))
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

    public static void setAuth(Auth auth) {
        RetrofitManager.auth = auth;
    }

    public FavoritesService getFavoritesService() {
        return favoritesService;
    }
}
