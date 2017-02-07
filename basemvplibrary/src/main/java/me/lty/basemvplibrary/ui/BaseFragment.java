package me.lty.basemvplibrary.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import me.lty.basemvplibrary.base.AppBasePresenter;
import me.lty.basemvplibrary.base.Contract;

/**
 * 描述
 * <p>Version: v1.0</p>
 * <p>Created by: litangyu</p>
 * <p>Created on: 2016/11/24 下午3:19</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2016年 litangyu. All rights reserved.</p>
 * <p>Revision：</p>
 */
public abstract class BaseFragment<T extends AppBasePresenter> extends Fragment implements
        Contract.BaseView, View.OnClickListener {

    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";
    protected Context mContext = null;
//    protected OnFragmentInteractionListener mListener;

    protected T mPresenter;

    private View rootView;

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        this.mContext = activity;
//        if (activity instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) activity;
//        } else {
//            throw new RuntimeException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //防止Fragment重叠，参考http://www.jianshu.com/p/c12a98a36b2b
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        if (getContentViewLayoutID() != 0) {
            if (rootView == null) {
                rootView = inflater.inflate(getContentViewLayoutID(), null);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout
                        .LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                rootView.setLayoutParams(params);
                bindView(rootView);
            }
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
            return rootView;
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
        setListener();
    }

    protected abstract int getContentViewLayoutID();

    /**
     * 初始化Presenter
     *
     * @return Presenter实现类
     */
    protected abstract T initPresenter();

    protected abstract void bindView(View view);

    protected abstract void initView(View view);

    protected abstract void initData();

    protected abstract void setListener();

    public abstract View getLoadingTargetView();

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }


    protected void startIntent(Class aClass) {
        startIntent(aClass, null);
    }

    public void startIntent(Class aClass, Bundle bundle) {
        Intent intent = new Intent(getActivity(), aClass);
        if (null != bundle)
            intent.putExtras(bundle);
        startActivity(intent);
    }

    protected void startForResultIntent(Class aClass, int requestCode) {
        startForResultIntent(aClass, null, requestCode);
    }

    protected void startForResultIntent(Class aClass, Bundle bundle, int requestCode) {
        Intent intent = new Intent(getActivity(), aClass);
        if (null != bundle)
            intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 隐藏控件
     */
    @Override
    public void hideView(View v) {
        v.setVisibility(View.GONE);
    }

    /**
     * 显示控件
     */
    @Override
    public void showView(View v) {
        v.setVisibility(View.VISIBLE);
    }


    @Override
    public void disappearView(View view) {
        view.setVisibility(android.view.View.GONE);
    }

    /**
     * 显示键盘
     */
    @SuppressWarnings("static-access")
    protected void showSoftInput(EditText et) {
        et.setFocusable(true);
        et.setFocusableInTouchMode(true);
        et.requestFocus();
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(mContext
                .INPUT_METHOD_SERVICE);
        imm.showSoftInput(et, 0);
    }

    /**
     * 隐藏键盘
     */
    @SuppressWarnings("static-access")
    protected void hideSoftInput(EditText et) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(mContext
                .INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

//    public interface OnFragmentInteractionListener {
//        void onFragmentInteraction(Bundle action);
//    }
}
