package com.lutai.electric;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.lutai.electric.adapter.AlarmMegAdapter;
import com.lutai.electric.adapter.DevicesInfoAdapter;
import com.lutai.electric.apiService.InternetService;
import com.lutai.electric.base.BaseActivity;
import com.lutai.electric.commonView.TopView;
import com.lutai.electric.entities.Device;
import com.lutai.electric.entities.DeviceInfo;
import com.lutai.electric.entities.Result;
import com.lutai.electric.network.RetrofitWapper;
import com.lutai.electric.utils.CommonUtil;
import com.lutai.electric.utils.SharedPreferenceUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhangYB on 2016/6/20.
 */
public class DeviceInformationActivity extends BaseActivity {


    @Bind(R.id.tv_topview)
    TopView mTvTopview;
    @Bind(R.id.tv_alarm_record)
    TextView mTvAlarmRecord;
    @Bind(R.id.ll_fault_container)
    GridView mLlFaultContainer;
    @Bind(R.id.tv_time)
    TextView mTvTime;
    @Bind(R.id.tv_more)
    TextView mTvMore;
    @Bind(R.id.gv_list)
    GridView mLvList;
    @Bind(R.id.btn_setting)
    Button mBtnSetting;

    private Device device;
    private Device.DataBean mBean;
    private DeviceInfo mDeviceInfo;
    private DevicesInfoAdapter mInfoAdapter;
    private List<DeviceInfo.InfoBean.ParamsBean> params;
    private Timer mTimer;

    private List<DeviceInfo.InfoBean.ParamsBean> defaultParam = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        device = (Device) getIntent().getSerializableExtra("device");
        mBean = (Device.DataBean) getIntent().getSerializableExtra("bean");
        mTvTopview.setTitle(mBean.getName() + "-" + mBean.getId() + "-" + mBean.getElecType());
        mTvTopview.setLeftTextVisiable();
        mTvTopview.setRightImgVisiable();
        mTvTopview.setRightImageListener();
        mTvTopview.setOnTopClickListener(new TopView.OnTopClickListener() {
            @Override
            public void onRightClick() {
                Intent intent = new Intent(mContext, CommonParamsActivity.class);
                intent.putExtra("bean", mBean);
                startActivity(intent);
            }

            @Override
            public void onLeftClick() {
                finish();
            }
        });
//        setErrorData();
        getDeviceInfo();
    }

    /**
     * 获取设备详情
     */
    private void getDeviceInfo() {
        final Call<DeviceInfo> deviceInfo = RetrofitWapper.getInstance().create(InternetService.class).getDeviceInfo("456789", 1);
        deviceInfo.enqueue(new Callback<DeviceInfo>() {
            @Override
            public void onResponse(Call<DeviceInfo> call, Response<DeviceInfo> response) {
                if (response.code() != 200) {
                    CommonUtil.showToast(mContext, "加载失败");
                    return;
                }
                if (defaultParam != null) {
                    defaultParam.clear();
                }
                DeviceInfo info = response.body();
                params = info.getInfo().getParams();
                for (int i = 0; i < params.size(); i++) {
                    if (params.get(i).getIsDefault() == 1) {
                        defaultParam.add(params.get(i));
                    }
                }
                mInfoAdapter = new DevicesInfoAdapter(DeviceInformationActivity.this, defaultParam);
                mLvList.setAdapter(mInfoAdapter);
                setErrorData(info);
            }

            @Override
            public void onFailure(Call<DeviceInfo> call, Throwable t) {
                CommonUtil.showToast(mContext, "请求数据失败");
            }
        });
    }

    /**
     * 设置报警信息
     */
    private void setErrorData(DeviceInfo info) {
        String[] alarmSplits = info.getInfo().getAlarms().split(";");
        mLlFaultContainer.setAdapter(new AlarmMegAdapter(mContext, alarmSplits));
    }

    @Override
    public int getLayout() {
        return R.layout.activity_device_info;
    }

    @OnClick(R.id.tv_alarm_record)
    public void alarmRecord() {
        Intent intent = new Intent(this, AlarmRecordActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tv_more)
    public void LoadMore() {
        Logger.e("显示更多");
        mInfoAdapter.addData(params);
    }

    @OnClick(R.id.btn_setting)
    public void addToCommon() {
        Call<Result> resultCall = RetrofitWapper.getInstance().create(InternetService.class).setCommonDevice("456789", mBean.getId());
        resultCall.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.code() != 200) {
                    CommonUtil.showToast(mContext, "加载失败");
                    return;
                }
                if (response.body().getStatus() == Result.SUCCESS) {
                    CommonUtil.showToast(mContext, "添加常用设备成功");
                } else {
                    CommonUtil.showToast(mContext, "添加常用设备失败");
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
        Logger.d("设为常用设备");
    }

    @Override
    public void onResume() {
        super.onResume();
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                getDeviceInfo();
            }
        }, SharedPreferenceUtils.getRefreshTime(mContext));
    }

    @Override
    public void onPause() {
        super.onPause();
        mTimer.cancel();
    }
}
