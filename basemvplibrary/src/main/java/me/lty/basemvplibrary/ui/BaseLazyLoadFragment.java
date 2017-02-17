package me.lty.basemvplibrary.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * 描述
 * <p>Version: v1.0</p>
 * <p>Created by: litangyu</p>
 * <p>Created on: 2016/11/24 下午3:18</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2016年 litangyu. All rights reserved.</p>
 * <p>Revision：</p>
 */
public abstract class BaseLazyLoadFragment extends Fragment {

    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";
    protected Context mContext = null;
//    protected BaseFragment.OnFragmentInteractionListener mListener;

    private View rootView;

    /**
     * 是否可见状态
     */
    private boolean isVisible;
    /**
     * 是否View已经初始化完成。
     */
    private boolean isPrepared;
    /**
     * 是否第一次加载
     */
    private boolean isFirstLoad = true;

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        this.mContext = activity;
//        if (activity instanceof BaseFragment.OnFragmentInteractionListener) {
//            mListener = (BaseFragment.OnFragmentInteractionListener) activity;
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
            isFirstLoad = true;
            isPrepared = true;
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
        setListener();
        lazyLoad();
    }

    protected void lazyLoad() {
        if (!isPrepared || !isVisible || !isFirstLoad) {
            return;
        }
        isFirstLoad = false;
        initData();
    }

    protected abstract int getContentViewLayoutID();

    protected abstract void bindView(View view);

    protected abstract void initView(View view);

    protected abstract void initData();

    protected abstract void setListener();

}
