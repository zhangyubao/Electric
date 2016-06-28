package com.lutai.electric.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lutai.electric.R;
import com.lutai.electric.entities.Device;
import com.lutai.electric.utils.CommonUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhangYB on 2016/6/20.
 */
public class SystemAdapter extends BaseAdapter {

    private static final int PARALLEL = 1;
    private static final int HASCHILDREN = 1;
    private static final int SERIES = 2;
    private LayoutInflater mInflater;
    private List<Device.DataBean> mDataBeen = new ArrayList<Device.DataBean>();
    private Context mContext;

    public SystemAdapter(Context mContext, List<Device.DataBean> mDataBeen) {
        mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.mDataBeen = mDataBeen;
    }

    public void addData(List<Device.DataBean> data) {
        if (data != null) {
            mDataBeen.addAll(data);
            this.notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return mDataBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        SViewHolder sHolder;
        PViewHolder pHolder;
        Device.DataBean dataBean = mDataBeen.get(position);
        if (dataBean.getLinkStyle() == PARALLEL) {
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_system_view, null);
                pHolder = new PViewHolder(convertView);
                convertView.setTag(pHolder);
            } else {
                pHolder = (PViewHolder) convertView.getTag();
            }
            //设置界面数据
            pHolder.mTvLinkName.setText(dataBean.getName());
            pHolder.mTvInIa.setText(dataBean.getParam());
            String alarms = dataBean.getAlarms();
            if (CommonUtil.isEmpty(alarms)) {
                pHolder.mLlAlarmLayout.setVisibility(View.GONE);
            } else {
                pHolder.mLlAlarmLayout.setVisibility(View.VISIBLE);
                pHolder.mIvAlarmInfo.setText(alarms);
            }
            if (HASCHILDREN == dataBean.getHasChildren()) {
                pHolder.mLlRightArrow.setVisibility(View.VISIBLE);
            } else {
                pHolder.mLlRightArrow.setVisibility(View.INVISIBLE);
            }
            if (!CommonUtil.isEmpty(dataBean.getUrl())) {
                Glide.with(mContext).load(dataBean.getUrl()).into(pHolder.mIvLinkType);
            }
            pHolder.mLlmain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnClickListener != null) {
                        mOnClickListener.onParallelMainClick(position);
                    }
                }
            });

            pHolder.mLlRightArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnClickListener != null) {
                        mOnClickListener.onArrawClick(position);
                    }
                }
            });
            /*****************************************************************/
        } else if (dataBean.getLinkStyle() == SERIES) {
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_series_layout, null);
                sHolder = new SViewHolder(convertView);
                convertView.setTag(sHolder);
            } else {
                sHolder = (SViewHolder) convertView.getTag();
            }
            String name = dataBean.getName();
            sHolder.mTvInName.setText(name);
            sHolder.mTvSeriesInIa.setText(dataBean.getParam());
            String out = dataBean.getOutU();
            if (!CommonUtil.isEmpty(out)) {
                sHolder.mTvOutu.setVisibility(View.VISIBLE);
                sHolder.mTvOutu.setText(out);
            } else {
                sHolder.mTvOutu.setVisibility(View.GONE);
            }
            if (!CommonUtil.isEmpty(dataBean.getUrl())) {
                Glide.with(mContext).load(dataBean.getUrl()).into(sHolder.mIvDeviceImage);
            }
            sHolder.mLlSeriesMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnClickListener != null) {
                        mOnClickListener.onSeriesMainClick(position);
                    }
                }
            });
        } else {
            Logger.e("类型不匹配");
        }
        return convertView;
    }

    static class PViewHolder {
        @Bind(R.id.ll_right_arrow)
        LinearLayout mLlRightArrow;
        @Bind(R.id.iv_link_type)
        ImageView mIvLinkType;
        @Bind(R.id.tv_link_name)
        TextView mTvLinkName;
        @Bind(R.id.tv_parallel_ia)
        TextView mTvInIa;
        @Bind(R.id.iv_alarm_info)
        TextView mIvAlarmInfo;
        @Bind(R.id.ll_alarm_layout)
        LinearLayout mLlAlarmLayout;
        @Bind(R.id.ll_main)
        LinearLayout mLlmain;

        PViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class SViewHolder {
        @Bind(R.id.iv_device_image_series)
        ImageView mIvDeviceImage;
        @Bind(R.id.tv_in_name)
        TextView mTvInName;
        @Bind(R.id.tv_inseries_ia)
        TextView mTvSeriesInIa;
        @Bind(R.id.ll_series_main)
        LinearLayout mLlSeriesMain;
        @Bind(R.id.tv_outu)
        TextView mTvOutu;

        SViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private OnClickListener mOnClickListener;

    public interface OnClickListener {
        /**
         * 箭头的点击事件
         *
         * @param position
         */
        void onArrawClick(int position);

        /**
         * 并联整体框的点击事件
         *
         * @param position
         */
        void onParallelMainClick(int position);

        /**
         * 并联整体框的点击事件
         *
         * @param position
         */
        void onSeriesMainClick(int position);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }
}
