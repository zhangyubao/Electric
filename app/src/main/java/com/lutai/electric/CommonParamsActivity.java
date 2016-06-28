package com.lutai.electric;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lutai.electric.adapter.DeviceParamAdapter;
import com.lutai.electric.apiService.InternetService;
import com.lutai.electric.base.BaseActivity;
import com.lutai.electric.commonView.NoScrollListView;
import com.lutai.electric.commonView.TopView;
import com.lutai.electric.entities.Device;
import com.lutai.electric.entities.DeviceParam;
import com.lutai.electric.entities.Result;
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
 * Created by zhangYB on 2016/6/23.
 * 设置参数界面
 */
public class CommonParamsActivity extends BaseActivity implements DeviceParamAdapter.OnAddorDeleteListener {

    @Bind(R.id.tv_topview)
    TopView mTvTopview;
    @Bind(R.id.list_common)
    NoScrollListView mListCommon;
    @Bind(R.id.list_all)
    NoScrollListView mListAll;
    //    默认参数标记
    private static final int DEFAULT = 1;

    private List<DeviceParam.DataBean> mParams;
    private DeviceParamAdapter defaultAdapter;
    private DeviceParamAdapter allApadter;
    private List<DeviceParam.DataBean> defaultParams;
    private Device.DataBean mBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTvTopview.setTitle("设置显示参数");
        mTvTopview.setLeftTextVisiable();
        mBean = (Device.DataBean) getIntent().getSerializableExtra("bean");
        mTvTopview.setOnTopClickListener(new TopView.OnTopClickListener() {
            @Override
            public void onRightClick() {

            }

            @Override
            public void onLeftClick() {
                finish();
            }
        });

        initData();
    }

    /**
     * 获取数据
     */
    private void initData() {
        Call<DeviceParam> deviceParam = RetrofitWapper.getInstance().create(InternetService.class).getDeviceParam("456789", mBean.getId());
        deviceParam.enqueue(new Callback<DeviceParam>() {
            @Override
            public void onResponse(Call<DeviceParam> call, Response<DeviceParam> response) {
                if (response.code() != 200) {
                    CommonUtil.showToast(mContext, "服务器错误");
                    return;
                }
                mParams = response.body().getData();
                //将数据处理成两部分  此时defaultParams  mParams为各自所需的数据
                dealData(mParams);
                allApadter = new DeviceParamAdapter(mContext, mParams);
                mListAll.setAdapter(allApadter);
                //设置默认常用参数
                setDefault();
                allApadter.setListener(CommonParamsActivity.this);
            }

            @Override
            public void onFailure(Call<DeviceParam> call, Throwable t) {
                Logger.d("数据加载失败");
            }
        });
    }

    /**
     * 将全部数据处理成默认和未添加默认
     */
    private void dealData(List<DeviceParam.DataBean> params) {
        defaultParams = new ArrayList<>();
        for (DeviceParam.DataBean bean : params) {
            if (bean.getIsDefault() == DEFAULT) {
                defaultParams.add(bean);
            }
        }
        mParams.removeAll(defaultParams);
    }

    /**
     * 设置默认参数列表
     */
    private void setDefault() {
        defaultAdapter = new DeviceParamAdapter(mContext, defaultParams);
        mListCommon.setAdapter(defaultAdapter);
        defaultAdapter.setListener(new DeviceParamAdapter.OnAddorDeleteListener() {
            @Override
            public void onAddorDeleteCilck(int position) {
                // TODO: 2016/6/24
                Logger.i(position + "删除");
                deleteCommonDdeviceParam(defaultParams.get(position));
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_common_params;
    }

    @Override
    public void onAddorDeleteCilck(int position) {
        //点击当前
        // TODO: 2016/6/24
        DeviceParam.DataBean dataBean = mParams.get(position);
        if (defaultParams.contains(dataBean)) {
            CommonUtil.showToast(mContext, "该参数已经添加");
            return;
        }
        Logger.i(position + "新增");
        addCommonDdeviceParam(dataBean);
    }

    /**
     * 删除常用参数
     *
     * @param dataBean
     */
    private void deleteCommonDdeviceParam(final DeviceParam.DataBean dataBean) {
        Call<Result> resultCall = RetrofitWapper.getInstance().create(InternetService.class).deleteCommonParams("456789", mBean.getId(), dataBean.getParamId());
        resultCall.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.code() != 200) {
                    CommonUtil.showToast(mContext, "加载失败");
                    return;
                }
                //结果处理
                if (response.body().getStatus() == Result.SUCCESS) {
                    dataBean.setIsDefault(0);
                    CommonUtil.showToast(mContext, dataBean.getParamName() + "删除成功");
                    //只有数据传递到服务器返回成功后本地才能进行相应的操作
                    defaultParams.remove(dataBean);
                    defaultAdapter.notifyDataSetChanged();
                } else {
                    CommonUtil.showToast(mContext, dataBean.getParamName() + "删除失败");
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }

    /**
     * 添加设备常用参数
     *
     * @param dataBean
     */
    private void addCommonDdeviceParam(final DeviceParam.DataBean dataBean) {
        Call<Result> resultCall = RetrofitWapper.getInstance().create(InternetService.class).AddCommonParams("456789", mBean.getId(), dataBean.getParamId());
        resultCall.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.code() != 200) {
                    CommonUtil.showToast(mContext, "加载失败");
                    return;
                }
                //结果处理
                if (response.body().getStatus() == Result.SUCCESS) {
                    CommonUtil.showToast(mContext, dataBean.getParamName() + "添加成功");
                    dataBean.setIsDefault(1);
                    defaultParams.add(dataBean);
                    defaultAdapter.notifyDataSetChanged();
                } else {
                    CommonUtil.showToast(mContext, dataBean.getParamName() + "添加失败");
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }
}
