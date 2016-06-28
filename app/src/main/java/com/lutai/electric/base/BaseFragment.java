package com.lutai.electric.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import butterknife.ButterKnife;

/**
 * Fragment 基类. 设置Fragment模式为懒加载模式
 */
public abstract class BaseFragment extends Fragment {

    protected View mView;
    //   界面是否显示
    protected boolean isVisiable;
    //布局是否加载完成
    protected boolean isPrepared;

    public Context mContext;

    public BaseFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        createPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), container, false);
        mContext = getActivity();
        Logger.init().hideThreadInfo().setMethodCount(3).setLogLevel(LogLevel.FULL);
        ButterKnife.bind(this, mView);
        isPrepared = true;
        isVisiable = true;
//        loadLayout();
        initData();
        return mView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //在ViewPager这种预加载的控件中使用 Fragment的懒加载模式
            //isVisiable = true;
//            onVisiable();
        } else {
            //isVisiable = false;
//            onInvisiable();
        }
    }


//    /**
//     * Fragment显示
//     */
//    protected void onVisiable() {
//        loadLayout();
//    }
//
//    /**
//     * Fragment 不显示
//     */
//    protected void onInvisiable() {
//    }
//
//    /**
//     * 初始化布局
//     */
//    public abstract void loadLayout();

    /**
     * 加载布局文件
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化数据
     */
    public abstract void initData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
