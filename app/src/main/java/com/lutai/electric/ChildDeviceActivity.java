package com.lutai.electric;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.lutai.electric.adapter.SystemAdapter;
import com.lutai.electric.apiService.InternetService;
import com.lutai.electric.base.BaseActivity;
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
 * Created by zhangYB on 2016/6/21.
 */
public class ChildDeviceActivity extends BaseActivity implements SystemAdapter.OnClickListener, TopView.OnTopClickListener {

    @Bind(R.id.top_view)
    TopView mTopView;
    LinearLayout mLlContainerAll;
    @Bind(R.id.lv_param)
    ListView mLvParam;
    private Device.DataBean mDataBean;
    private SystemAdapter mAdapter;
    private LayoutInflater mInflater;
    private Device mDevice;
    private Timer mTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBean = (Device.DataBean) getIntent().getSerializableExtra("bean");
        mTopView.setLeftTextVisiable();
        mTopView.setOnTopClickListener(this);
        getDeviceList();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_system;
    }

    /**
     * 获取设备列表
     */
    private void getDeviceList() {
        Call<Device> deviceList = RetrofitWapper.getInstance().create(InternetService.class).getDeviceList("456789", mDataBean.getId());
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
                mAdapter.setOnClickListener(ChildDeviceActivity.this);
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
    public void onRightClick() {

    }

    @Override
    public void onLeftClick() {
        finish();
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
