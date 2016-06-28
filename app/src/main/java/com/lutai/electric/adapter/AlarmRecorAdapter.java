package com.lutai.electric.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lutai.electric.R;
import com.lutai.electric.entities.AlarmRecord;
import com.lutai.electric.utils.TimeUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhangYB on 2016/6/22.
 */
public class AlarmRecorAdapter extends BaseAdapter {

    private Context mContext;
    private List<AlarmRecord.DataBean> mDataBean = new ArrayList<AlarmRecord.DataBean>();
    private LayoutInflater mInflater;

    public AlarmRecorAdapter(Context mContext, List<AlarmRecord.DataBean> mDataBean) {
        this.mContext = mContext;
        this.mDataBean = mDataBean;
        mInflater = LayoutInflater.from(mContext);
    }


    public void addData(List<AlarmRecord.DataBean> data) {
        if (data != null) {
            mDataBean.clear();
            mDataBean.addAll(data);
            this.notifyDataSetChanged();
        }
    }

    public void addDataMore(List<AlarmRecord.DataBean> data) {
        if (data != null) {
            mDataBean.addAll(data);
            this.notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return mDataBean.size() > 0 ? mDataBean.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mDataBean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_alarm_record, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        AlarmRecord.DataBean record = mDataBean.get(position);
        Logger.e("创建时间" + record.getCreateTim() + "======当前时间是" + System.currentTimeMillis());
        if (record != null) {
            holder.mTvType.setText(record.getType());
            holder.mTvContent.setText(record.getComment());
            holder.mTvTime.setText(TimeUtils.formatDateTime(record.getCreateTim()));
        }
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.tv_type)
        TextView mTvType;
        @Bind(R.id.tv_content)
        TextView mTvContent;
        @Bind(R.id.tv_time)
        TextView mTvTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
