package com.lutai.electric.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.lutai.electric.BannerViewerActivity;
import com.lutai.electric.R;
import com.lutai.electric.adapter.AlarmRecorAdapter;
import com.lutai.electric.apiService.InternetService;
import com.lutai.electric.base.BaseFragment;
import com.lutai.electric.commonView.TopView;
import com.lutai.electric.commonView.bannerView.BannerEntity;
import com.lutai.electric.commonView.bannerView.BannerView;
import com.lutai.electric.commonView.bannerView.OnBannerClickListener;
import com.lutai.electric.entities.AlarmRecord;
import com.lutai.electric.entities.Banner;
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
 * 首页
 */
public class HomeFragment extends BaseFragment {

    @Bind(R.id.tv_top)
    TopView mTvTop;
    @Bind(R.id.banner_view)
    BannerView mBannerView;
    @Bind(R.id.rv_alarm)
    ListView mRvAlarm;
    @Bind(R.id.prs_parent)
    PullToRefreshScrollView mScrollView;

    private AlarmRecord mRecord;
    private AlarmRecorAdapter mAdapter;
    private static final int LOADMORE = 1;
    private static final int REFRESH = 2;

    private List<BannerEntity> entities = new ArrayList<BannerEntity>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mTvTop.setTitle("首页");
        return mView;
    }

    /**
     * 获取报警项目列表数据
     */
    public void getAlarmList() {
        Call<AlarmRecord> alarmRecord = RetrofitWapper.getInstance().create(InternetService.class).getAlarmRecord("456789", "0", 1);
        alarmRecord.enqueue(new Callback<AlarmRecord>() {
            @Override
            public void onResponse(Call<AlarmRecord> call, Response<AlarmRecord> response) {
                mRecord = response.body();
                if (response.code() != 200) {
                    CommonUtil.showToast(mContext, "服务器错误");
                    return;
                }
                mAdapter = new AlarmRecorAdapter(mContext, mRecord.getData());
                mRvAlarm.setAdapter(mAdapter);
                Logger.d("报警数量====" + mRecord.getData());
            }

            @Override
            public void onFailure(Call<AlarmRecord> call, Throwable t) {
                Logger.e("加载数据失败");
            }
        });
    }

    /**
     * bannerView点击事件
     */
    public void setBannerViewClickListener() {
        mBannerView.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void onClick(int position) {
                BannerEntity bannerEntity = entities.get(position);
                if (!CommonUtil.isEmpty(bannerEntity.clickurl)) {
                    Intent intent = new Intent(mContext, BannerViewerActivity.class);
                    intent.putExtra("clickUrl", bannerEntity.clickurl);
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * 获取Banner轮播图数据
     */
    public void getBannerData() {
        Call<Banner> banners = RetrofitWapper.getInstance().create(InternetService.class).getBanners("456789");
        banners.enqueue(new Callback<Banner>() {
            @Override
            public void onResponse(Call<Banner> call, Response<Banner> response) {
                if (response.code() != 200) {
                    CommonUtil.showToast(mContext, "服务器错误");
                    return;
                }
                Banner ban = response.body();
                if (ban.getIsVisible() == 1) {
                    List<Banner.DataBean> data = ban.getData();
                    for (int i = 0; i < data.size(); i++) {
                        Banner.DataBean dataBean = data.get(i);
                        BannerEntity entity = new BannerEntity();
                        entity.imageUrl = dataBean.getUrl();
                        entity.clickurl = dataBean.getUrlOnClick();
                        entities.add(entity);
                    }
                    mBannerView.setEntities(entities);
                } else {
                    mBannerView.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<Banner> call, Throwable t) {
                Logger.d("数据加载失败");
            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_enhance;
    }

    @Override
    public void initData() {
        getBannerData();
        getAlarmList();
        setBannerViewClickListener();
        mScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                refresh();
                Logger.d("下拉刷新~~~~~~~~~~~~~~~~~~~~~");
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                loadMore();
                Logger.d("加载更多~~~~~~~~~~~~~~~~~~~~~");
            }
        });
    }

    /**
     * 加载更多
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
                mScrollView.onRefreshComplete();
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
                mScrollView.onRefreshComplete();
            }

            @Override
            public void onFailure(Call<AlarmRecord> call, Throwable t) {
                Logger.e("加载数据失败");
            }
        });
    }
}
