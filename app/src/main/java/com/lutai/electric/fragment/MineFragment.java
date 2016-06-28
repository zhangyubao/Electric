package com.lutai.electric.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lutai.electric.R;
import com.lutai.electric.base.BaseFragment;

/**
 * 我的
 */
public class MineFragment extends BaseFragment {


    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return mView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initData() {

//   用户登录后要保存token 调用下面的方法保存token值
//        SharedPreferenceUtils.saveToken(mContext,token);
//        在用到的地方要获取token值
//        SharedPreferenceUtils.getToken(mContext);
    }

}
