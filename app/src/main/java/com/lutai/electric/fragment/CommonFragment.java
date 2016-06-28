package com.lutai.electric.fragment;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.lutai.electric.R;
import com.lutai.electric.adapter.CommonListAdapter;
import com.lutai.electric.adapter.CommonPagerAdapter;
import com.lutai.electric.apiService.InternetService;
import com.lutai.electric.base.BaseFragment;
import com.lutai.electric.commonView.TopView;
import com.lutai.electric.entities.CommonDevice;
import com.lutai.electric.entities.Voltage;
import com.lutai.electric.network.RetrofitWapper;
import com.lutai.electric.utils.CommonUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 常用界面
 */
public class CommonFragment extends BaseFragment implements NavigationTabStrip.OnTabStripSelectedIndexListener, ViewPager.OnPageChangeListener {


    @Bind(R.id.top_view)
    TopView mTopView;
    @Bind(R.id.nts_lable)
    NavigationTabStrip mNtsLable;
    @Bind(R.id.vp_list)
    ViewPager viewPager;

    private String[] titles;

    private Voltage mVoltage;
    private List<Voltage.DataBean> voltages;
    private CommonPagerAdapter mAdapter;
    private CommonDevice mDevice;
    private List<CommonDevice.DataBean> allDeviceDatas;
    private CommonListAdapter listAdapter;
    //    private List<CommonDevice.DataBean> partDeviceDatas = new ArrayList<>();
    private List<View> views = new ArrayList<>();

    public CommonFragment() {
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
        return R.layout.fragment_common;
    }

    @Override
    public void initData() {
        mTopView.setTitle("常用");
        mNtsLable.setOnTabStripSelectedIndexListener(this);
        viewPager.addOnPageChangeListener(this);
        getCommonVoltage();
    }

    /**
     * 获取所有设备
     */
    private void getAllDevices() {
        Call<CommonDevice> devices = RetrofitWapper.getInstance().create(InternetService.class).getAllDevices("456789");
        devices.enqueue(new Callback<CommonDevice>() {
            @Override
            public void onResponse(Call<CommonDevice> call, Response<CommonDevice> response) {
                if (response.code() != 200) {
                    CommonUtil.showToast(mContext, "服务器异常");
                    return;
                }
                mDevice = response.body();
                allDeviceDatas = mDevice.getData();
                //全部数据的过滤
                CommonListAdapter listAdapter;
                for (int i = 0; i < titles.length; i++) {
                    View view = View.inflate(mContext, R.layout.item_pager_view, null);
                    ListView listview = (ListView) view.findViewById(R.id.lv_decice_list);
                    if (i == 0) {
                        listAdapter = new CommonListAdapter(mContext, allDeviceDatas);
                    } else {
                        listAdapter = new CommonListAdapter(mContext, fliter(titles[i]));
                    }
                    listview.setAdapter(listAdapter);
                    views.add(view);
                }
                mAdapter = new CommonPagerAdapter(mContext, views);
                viewPager.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<CommonDevice> call, Throwable t) {

            }
        });
    }

    /**
     * 电压类型 即tab的title
     *
     * @param title
     */
    private List<CommonDevice.DataBean> fliter(String title) {
        List<CommonDevice.DataBean> partDeviceDatas = new ArrayList<>();
        for (CommonDevice.DataBean device : allDeviceDatas) {
            if (device.getElecType().equals(title)) {
                partDeviceDatas.add(device);
            }
        }
        Logger.e(partDeviceDatas.size() + "~~~~~~~~~~~~~~");
        return partDeviceDatas;
    }

    /**
     * 获取常用电压类型
     */
    private void getCommonVoltage() {
        Call<Voltage> voltage = RetrofitWapper.getInstance().create(InternetService.class).getCommonVoltage("456789");
        voltage.enqueue(new Callback<Voltage>() {
            @Override
            public void onResponse(Call<Voltage> call, Response<Voltage> response) {
                if (response.code() != 200) {
                    CommonUtil.showToast(mContext, "服务器异常");
                    return;
                }
                mVoltage = response.body();
                voltages = mVoltage.getData();
                getAllDevices();
                initLable();
            }

            @Override
            public void onFailure(Call<Voltage> call, Throwable t) {

            }
        });
    }


    /**
     * 初始化常用电压标签
     */
    private void initLable() {
        titles = new String[voltages.size() + 1];
        titles[0] = "全部";
        for (int i = 0; i < voltages.size(); i++) {
            titles[i + 1] = voltages.get(i).getName();
        }
        mNtsLable.setTitles(titles);
        mNtsLable.setTabIndex(0, true);
    }

    /********************
     * 标题选中
     ************************/
    @Override
    public void onStartTabSelected(String title, int index) {
        Logger.d("选中的位置 start" + index);
        viewPager.setCurrentItem(index);

    }

    @Override
    public void onEndTabSelected(String title, int index) {
        Logger.d("选中的位置 end" + index);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mNtsLable.setTabIndex(position, true);
        Logger.d("选中的位置 viewPager" + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
