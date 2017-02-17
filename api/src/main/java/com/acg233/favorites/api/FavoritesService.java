package com.acg233.favorites.api;

import com.acg233.favorites.api.type.BadQQ;
import com.acg233.favorites.api.type.BaseRequest;
import com.acg233.favorites.api.type.Login;
import com.acg233.favorites.api.type.User;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Describe
 * <p>Version: v1.0</p>
 * <p>Created by: lty</p>
 * <p>Created on: 2017/1/22 上午11:24</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2017年 lty. All rights reserved.</p>
 * <p>Revision：</p>
 */

public interface FavoritesService {
    @GET("?type=time")
    Observable<Long> getTime();

    @GET("?type=date")
    Observable<String> getDate();

    @POST("?type=permission")
    Observable<Object> postToken();

    @POST("?type=version")
    Observable<Integer> checkVersion(@Body BaseRequest request);

    @GET("download")
    @Streaming  //@Streaming作用为在下载大文件中使用。添加了该注解后，下载文件不会将所有的下载内容加载到内存
    Observable<ResponseBody> download(@Url String url);

    @POST("?type=login")
    Observable<User> login(@Body Login login);

    @POST("?type=badqq")
    Observable<Object> postBadQQ(@Body BadQQ badQQ);
}
