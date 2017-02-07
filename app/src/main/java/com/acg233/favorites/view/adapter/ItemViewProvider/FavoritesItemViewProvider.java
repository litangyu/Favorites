package com.acg233.favorites.view.adapter.ItemViewProvider;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.acg233.favorites.R;
import com.acg233.favorites.view.activities.NewsDetailActivity;
import com.acg233.favorites.view.adapter.item.FavoritesItem;
import com.facebook.drawee.view.SimpleDraweeView;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Describe
 * <p>Version: v1.0</p>
 * <p>Created by: lty</p>
 * <p>Created on: 2017/1/20 下午1:44</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2017年 lty. All rights reserved.</p>
 * <p>Revision：</p>
 */

public class FavoritesItemViewProvider extends ItemViewProvider<FavoritesItem,
        FavoritesItemViewProvider.FavoritesHolder> {

    private final Activity mActivity;

    public FavoritesItemViewProvider(Activity activity) {
        mActivity = activity;
    }

    static class FavoritesHolder extends RecyclerView.ViewHolder {

        protected TextView mTv_news_title;
        protected SimpleDraweeView mSdv_news;

        public FavoritesHolder(View itemView) {
            super(itemView);
            mTv_news_title = (TextView) itemView.findViewById(R.id.tv_news_title);
            mSdv_news = (SimpleDraweeView) itemView.findViewById(R.id.sdv_news);
        }
    }

    @NonNull
    @Override
    protected FavoritesHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull
            ViewGroup
            parent) {
        View root = inflater.inflate(R.layout.item_favorites_vertical, parent, false);
        return new FavoritesHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull FavoritesHolder holder, @NonNull final FavoritesItem
            favoritesItem) {
        holder.mTv_news_title.setText("012 [12月]2016年12月里番合集");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.startActivity(new Intent(mActivity, NewsDetailActivity.class));
            }
        });

        Uri uri = Uri.parse(
                "https://gss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/favoritesItem" +
                        "/f3d3572c11dfa9ece297da446ad0f703918fc12c.jpg");
        holder.mSdv_news.setImageURI(uri);
    }
}
