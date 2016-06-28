package com.lutai.electric.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lutai.electric.R;
import com.lutai.electric.entities.DeviceInfo;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhangYB on 2016/6/20.
 */
public class DevicesInfoAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<DeviceInfo.InfoBean.ParamsBean> beans;
    private Context mContext;

    public DevicesInfoAdapter(Context mContext, List<DeviceInfo.InfoBean.ParamsBean> beans) {
        mInflater = LayoutInflater.from(mContext);
        this.beans = beans;
        this.mContext = mContext;
    }

    public void addData(List<DeviceInfo.InfoBean.ParamsBean> data) {
        if (data != null) {
            beans.clear();
            beans.addAll(data);
            this.notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return beans == null ? 0 : beans.size();
    }

    @Override
    public Object getItem(int position) {
        return beans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        DeviceInfo.InfoBean.ParamsBean paramsBean = beans.get(position);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_device_info, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String paramName = mContext.getResources().getString(R.string.system_common_paramname);
        String paramValue = paramsBean.getParamValue();
        paramName = StringUtils.replaceEach(paramName, new String[]{"PARAMNAME"}, new String[]{paramsBean.getParamName()});
        holder.mTvUa.setText(Html.fromHtml(paramName) + "\n" + paramValue);
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.tv_ua)
        TextView mTvUa;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
