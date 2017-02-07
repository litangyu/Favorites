package com.acg233.favorites.view.adapter.item;

/**
 * Describe
 * <p>Version: v1.0</p>
 * <p>Created by: lty</p>
 * <p>Created on: 2017/1/20 下午1:52</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2017年 lty. All rights reserved.</p>
 * <p>Revision：</p>
 */
public class FavoritesItem {

    private long id;
    private String imgUrl;
    private String title;
    private String info;

    public long getNewsId() {
        return id;
    }

    public void setNewsId(long newsId) {
        this.id = newsId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNewsInfo() {
        return info;
    }

    public void setNewsInfo(String newsInfo) {
        this.info = newsInfo;
    }
}
