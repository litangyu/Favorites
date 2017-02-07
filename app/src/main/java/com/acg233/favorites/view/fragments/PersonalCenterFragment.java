package com.acg233.favorites.view.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acg233.favorites.R;
import com.acg233.favorites.bean.UserResponse;
import com.acg233.favorites.presenter.HomePresenterImpl;
import com.acg233.favorites.presenter.PersonalCenterPresenterImpl;
import com.acg233.favorites.view.activities.LoginActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.lty.basemvplibrary.ui.BaseFragment;
import me.lty.basemvplibrary.ui.BaseLazyLoadFragment;


/**
 * Describe 个人中心
 * <p>Version: v1.0</p>
 * <p>Created by: lty</p>
 * <p>Created on: 2017/1/20 上午11:08</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2017年 lty. All rights reserved.</p>
 * <p>Revision：</p>
 */

public class PersonalCenterFragment extends BaseFragment<PersonalCenterPresenterImpl> {

    @BindView(R.id.sdv_head_img)
    protected SimpleDraweeView mSdv_head_img;
    @BindView(R.id.imv_level)
    protected ImageView mImv_level;
    @BindView(R.id.tv_nick_name)
    protected TextView mTv_nick_name;
    @BindView(R.id.tv_experience)
    protected TextView mTv_experience;
    @BindView(R.id.tv_integral)
    protected TextView mTv_integral;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_personal_center;
    }

    @Override
    protected PersonalCenterPresenterImpl initPresenter() {
        return new PersonalCenterPresenterImpl(getActivity());
    }

    @Override
    protected void bindView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        UserResponse user = (UserResponse) getArguments().get("user");

        mTv_nick_name.setText(user.getNickName());
        switchLevel(user.getUserLevel());
        mTv_integral.setText(String.format("积分：%1$s", user.getIntegral()));
        mTv_experience.setText(String.format("经验：%1$s", user.getExperience()));

        //                "https://gss0.baidu
        // .com/94o3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item
        // /f3d3572c11dfa9ece297da446ad0f703918fc12c.jpg"
        Uri uri = Uri.parse(user.getHeadImgUrl());
        mSdv_head_img.setImageURI(uri);
    }

    private void switchLevel(int level) {
        switch (level) {
            case 0:
                mImv_level.setImageResource(R.mipmap.ic_lv0);
                break;
            case 1:
                mImv_level.setImageResource(R.mipmap.ic_lv1);
                break;
            case 2:
                mImv_level.setImageResource(R.mipmap.ic_lv2);
                break;
            case 3:
                mImv_level.setImageResource(R.mipmap.ic_lv3);
                break;
            case 4:
                mImv_level.setImageResource(R.mipmap.ic_lv4);
                break;
            case 5:
                mImv_level.setImageResource(R.mipmap.ic_lv5);
                break;
            case 6:
                mImv_level.setImageResource(R.mipmap.ic_lv6);
                break;
            case 7:
                mImv_level.setImageResource(R.mipmap.ic_lv7);
                break;
            case 8:
                mImv_level.setImageResource(R.mipmap.ic_lv8);
                break;
            case 9:
                mImv_level.setImageResource(R.mipmap.ic_lv9);
                break;
        }
    }

    @Override
    protected void setListener() {

    }

    @Override
    public View getLoadingTargetView() {
        return null;
    }

    @Override
    @OnClick({R.id.rl_favorites, R.id.rl_notify, R.id.rl_nick_name, R.id.rl_exchange, R.id
            .rl_invite_people, R.id.tv_modify_pwd, R.id.tv_log_off})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_favorites:
                break;
            case R.id.rl_notify:
                break;
            case R.id.rl_exchange:
                break;
            case R.id.rl_nick_name:
                break;
            case R.id.rl_invite_people:
                break;
            case R.id.tv_modify_pwd:
                if (mPresenter.getDrawerLayout().isDrawerOpen(GravityCompat.START)) {
                    mPresenter.getDrawerLayout().closeDrawer(GravityCompat.START);
                }
                break;
            case R.id.tv_log_off:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("PersonalCenterFragment");
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("PersonalCenterFragment");
    }
}
