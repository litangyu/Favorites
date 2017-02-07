package com.acg233.favorites.view.adapter.ItemViewProvider;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.acg233.favorites.R;
import com.acg233.favorites.view.adapter.item.HistoryItem;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Describe
 * <p>Version: v1.0</p>
 * <p>Created by: lty</p>
 * <p>Created on: 2017/2/4 下午4:21</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2017年 lty. All rights reserved.</p>
 * <p>Revision：</p>
 */

public class HistoryItemViewProvider extends ItemViewProvider<HistoryItem,HistoryItemViewProvider.HistoryViewHolder> {

    private final Activity mActivity;

    public HistoryItemViewProvider(Activity activity) {
        mActivity = activity;
    }

    static class HistoryViewHolder extends RecyclerView.ViewHolder{
        protected TextView title;

        public HistoryViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_history_title);
        }
    }

    @NonNull
    @Override
    protected HistoryViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_history, parent, false);
        return new HistoryViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull HistoryViewHolder holder, @NonNull HistoryItem
            historyItem) {
        holder.title.setText(2017-getPosition(holder)+"年里番合集");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
