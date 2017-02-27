package com.acg233.favorites.view.fragments;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.acg233.favorites.R;
import com.acg233.favorites.api.type.User;
import com.acg233.favorites.contract.HomeContract;
import com.acg233.favorites.presenter.HomePresenterImpl;
import com.acg233.favorites.tool.ResourceHelper;
import com.acg233.favorites.view.activities.LoginActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.lty.basemvplibrary.ui.BaseFragment;


/**
 * Describe 个人中心
 * <p>Version: v1.0</p>
 * <p>Created by: lty</p>
 * <p>Created on: 2017/1/20 上午11:08</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2017年 lty. All rights reserved.</p>
 * <p>Revision：</p>
 */

public class PersonalInfoFragment extends BaseFragment implements HomeContract.View {

    private static final String TAG = PersonalInfoFragment.class.getSimpleName();

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

    private HomePresenterImpl mPresenter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.content_personal_info;
    }

    @Override
    protected void bindView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = (HomePresenterImpl) presenter;
    }

    @Override
    protected void initData() {
        if (getArguments() != null) {
            User user = (User) getArguments().get("user");

            mTv_nick_name.setText(user.getNickName());
            ResourceHelper.switchLevel(mImv_level,user.getUserLevel());
            mTv_integral.setText(String.format("积分：%1$s", user.getIntegral()));
            mTv_experience.setText(String.format("经验：%1$s", user.getExperience()));

            //                "https://gss0.baidu
            // .com/94o3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item
            // /f3d3572c11dfa9ece297da446ad0f703918fc12c.jpg"
            Uri uri = Uri.parse(user.getHeadImgUrl());
            mSdv_head_img.setImageURI(uri);
        }
    }

    @Override
    protected void setListener() {

    }

    @OnClick({ R.id.rl_nick_name, R.id.rl_modify_password, R.id.tv_log_off})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_nick_name:
                break;
            case R.id.rl_modify_password:
                break;
            case R.id.tv_log_off:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(TAG);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(TAG);
    }
}
