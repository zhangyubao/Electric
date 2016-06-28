package com.lutai.electric.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zhangYB on 2016/6/23.
 */
public class CommonPagerAdapter extends PagerAdapter {

    //ViewPager中列表数据
//    private List<CommonDevice.DataBean> deviceBeans;
    //    电压的类型    控制生成几个ViewPager界面
    private List<View> views;
    private Context mContext;

    public CommonPagerAdapter(Context mContext, List<View> views) {
        this.views = views;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(final View container, final int position, final Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        View view = views.get(position);
        container.addView(view);
        return view;
    }
}
