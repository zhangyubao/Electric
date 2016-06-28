package com.lutai.electric;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lutai.electric.adapter.AlarmRecorAdapter;
import com.lutai.electric.apiService.InternetService;
import com.lutai.electric.base.BaseActivity;
import com.lutai.electric.commonView.TopView;
import com.lutai.electric.entities.AlarmRecord;
import com.lutai.electric.network.RetrofitWapper;
import com.lutai.electric.utils.CommonUtil;
import com.orhanobut.logger.Logger;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhangYB on 2016/6/21.
 * <p/>
 * 报警记录主界面
 */
public class AlarmRecordActivity extends BaseActivity implements TopView.OnTopClickListener {

    @Bind(R.id.tv_top)
    TopView mTvTop;
    @Bind(R.id.lv_alarm_record)
    PullToRefreshListView mLvAlarmRecord;

    private AlarmRecord mRecord;
    private AlarmRecorAdapter mAdapter;
    private static final int LOADMORE = 1;
    private static final int REFRESH = 2;
    private String token;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTvTop.setTitle("报警记录");
        mTvTop.setLeftTextVisiable();
        mTvTop.setOnTopClickListener(this);
//        token = SharedPreferenceUtils.getToken(this);
        initData();
        setListener();
    }

    private void initData() {
        mLvAlarmRecord.setMode(PullToRefreshBase.Mode.BOTH);
        Call<AlarmRecord> alarmRecor = RetrofitWapper.getInstance().create(InternetService.class).getAlarmRecord("456789", "0", 1, REFRESH);
        alarmRecor.enqueue(new Callback<AlarmRecord>() {
            @Override
            public void onResponse(Call<AlarmRecord> call, Response<AlarmRecord> response) {
                mRecord = response.body();
                if (response.code() != 200) {
                    CommonUtil.showToast(mContext, "服务器错误");
                    return;
                }
                mAdapter = new AlarmRecorAdapter(mContext, mRecord.getData());
                mLvAlarmRecord.setAdapter(mAdapter);
                mLvAlarmRecord.onRefreshComplete();
            }

            @Override
            public void onFailure(Call<AlarmRecord> call, Throwable t) {
                Logger.e("加载数据失败");
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_alarm_record;
    }

    @Override
    public void onRightClick() {
        //不需要处理的函数
    }

    @Override
    public void onLeftClick() {
        finish();
    }

    public void setListener() {
        mLvAlarmRecord.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                refresh();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                loadMore();
            }
        });
    }

    /**
     * 自动加载更多
     */
    private void loadMore() {
        Call<AlarmRecord> alarmRecor = RetrofitWapper.getInstance().create(InternetService.class).getAlarmRecord("456789", "0", 1, LOADMORE);
        alarmRecor.enqueue(new Callback<AlarmRecord>() {
            @Override
            public void onResponse(Call<AlarmRecord> call, Response<AlarmRecord> response) {
                mRecord = response.body();
                if (response.code() != 200) {
                    CommonUtil.showToast(mContext, "服务器错误");
                    return;
                }
                mAdapter.addData(mRecord.getData());
                mLvAlarmRecord.onRefreshComplete();
            }


            @Override
            public void onFailure(Call<AlarmRecord> call, Throwable t) {
                Logger.e("加载数据失败");
            }
        });
    }

    /**
     * 下拉刷新获取数据
     */
    private void refresh() {
        Call<AlarmRecord> alarmRecor = RetrofitWapper.getInstance().create(InternetService.class).getAlarmRecord("456789", "0", 1, REFRESH);
        alarmRecor.enqueue(new Callback<AlarmRecord>() {
            @Override
            public void onResponse(Call<AlarmRecord> call, Response<AlarmRecord> response) {
                mRecord = response.body();
                if (response.code() != 200) {
                    CommonUtil.showToast(mContext, "服务器错误");
                    return;
                }
                mAdapter.addData(mRecord.getData());
                mLvAlarmRecord.onRefreshComplete();
            }

            @Override
            public void onFailure(Call<AlarmRecord> call, Throwable t) {
                Logger.e("加载数据失败");
            }
        });
    }
}
