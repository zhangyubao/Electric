package com.lutai.electric.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lutai.electric.R;
import com.lutai.electric.utils.CommonUtil;
import com.orhanobut.logger.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhangYB on 2016/6/24.
 */
public class AlarmMegAdapter extends BaseAdapter {

    private Context mContext;
    private String[] mDataBean;
    private LayoutInflater mInflater;

    public AlarmMegAdapter(Context mContext, String[] mDataBean) {
        this.mContext = mContext;
        this.mDataBean = mDataBean;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        Logger.d(mDataBean.length + " 当前警报数量!!!!!!!!!!!!!!!!!!!");
        return mDataBean != null ? mDataBean.length : 0;
    }

    @Override
    public Object getItem(int position) {
        return mDataBean[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.fault_info_layout, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (CommonUtil.isEmpty(mDataBean[position])) {
            convertView.setVisibility(View.GONE);
        } else {
            convertView.setVisibility(View.VISIBLE);
        }
        holder.mIvAlarmInfo.setText(mDataBean[position]);
        Logger.d(mDataBean[position] + "故障~~~~~~~~~~~~~~~~~");
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv_alarm_info)
        TextView mIvAlarmInfo;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
