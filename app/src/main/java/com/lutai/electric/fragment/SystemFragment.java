package com.lutai.electric.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.lutai.electric.ChildDeviceActivity;
import com.lutai.electric.DeviceInformationActivity;
import com.lutai.electric.R;
import com.lutai.electric.adapter.SystemAdapter;
import com.lutai.electric.apiService.InternetService;
import com.lutai.electric.base.BaseFragment;
import com.lutai.electric.commonView.TopView;
import com.lutai.electric.entities.Device;
import com.lutai.electric.network.RetrofitWapper;
import com.lutai.electric.utils.CommonUtil;
import com.lutai.electric.utils.SharedPreferenceUtils;
import com.orhanobut.logger.Logger;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 系统图界面
 */
public class SystemFragment extends BaseFragment implements SystemAdapter.OnClickListener {

    @Bind(R.id.top_view)
    TopView mTopView;
    LinearLayout mLlContainerAll;
    @Bind(R.id.lv_param)
    ListView mLvParam;
    private Device mDevice;

    private SystemAdapter mAdapter;

    private LayoutInflater mInflater;
    private Timer mTimer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mInflater = LayoutInflater.from(mContext);
        getActivity();
        return mView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_system;
    }

    @Override
    public void initData() {
        getDeviceList();
    }

    /**
     * 获取设备列表
     */
    private void getDeviceList() {
        Call<Device> deviceList = RetrofitWapper.getInstance().create(InternetService.class).getDeviceList("456789", 0);
        deviceList.enqueue(new Callback<Device>() {
            @Override
            public void onResponse(Call<Device> call, Response<Device> response) {
                if (response.code() != 200) {
                    CommonUtil.showToast(mContext, "服务器连接错误");
                    return;
                }
                mDevice = response.body();
                mTopView.setTitle(mDevice.getDeviceName());
                mAdapter = new SystemAdapter(mContext, mDevice.getData());
                mAdapter.setOnClickListener(SystemFragment.this);
                mLvParam.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<Device> call, Throwable t) {
                CommonUtil.showToast(mContext, "数据加载失败");
                Logger.e(t.getMessage());
            }
        });
    }

    @Override
    public void onArrawClick(int position) {
        Logger.d(position + "~~~~~~~~~~~~~~~~~~~~~~~~");
        Intent intent = new Intent(mContext, ChildDeviceActivity.class);
        intent.putExtra("bean", mDevice.getData().get(position));
        startActivity(intent);
    }

    @Override
    public void onParallelMainClick(int position) {
        Intent intent = new Intent(mContext, DeviceInformationActivity.class);
        intent.putExtra("device", mDevice);
        intent.putExtra("bean", mDevice.getData().get(position));
        startActivity(intent);
    }

    @Override
    public void onSeriesMainClick(int position) {
        Intent intent = new Intent(mContext, DeviceInformationActivity.class);
        intent.putExtra("device", mDevice);
        intent.putExtra("bean", mDevice.getData().get(position));
        startActivity(intent);
    }


    @Override
    public void onResume() {
        super.onResume();
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                getDeviceList();
            }
        }, SharedPreferenceUtils.getRefreshTime(mContext));
    }

    @Override
    public void onPause() {
        super.onPause();
        mTimer.cancel();
    }
}
