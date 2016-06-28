package com.lutai.electric.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lutai.electric.R;
import com.lutai.electric.entities.DeviceParam;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhangYB on 2016/6/24.
 */
public class DeviceParamAdapter extends BaseAdapter {

    private Context mContext;
    private List<DeviceParam.DataBean> mDataBean = new ArrayList<DeviceParam.DataBean>();
    private LayoutInflater mInflater;

    public DeviceParamAdapter(Context mContext, List<DeviceParam.DataBean> mDataBean) {
        this.mContext = mContext;
        this.mDataBean = mDataBean;
        mInflater = LayoutInflater.from(mContext);
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_device_param, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DeviceParam.DataBean dataBean = mDataBean.get(position);
        if (dataBean.getIsDefault() == 1) { //默认参数
            holder.mIvAdd.setBackgroundDrawable(mContext.getResources().getDrawable(R.mipmap.home_icon_minus));
        } else {
            holder.mIvAdd.setBackgroundDrawable(mContext.getResources().getDrawable(R.mipmap.home_icon_plus));
        }
        holder.mTvParamName.setText(dataBean.getParamName());
        holder.mTvParamDesc.setText(dataBean.getParamDescs());
        holder.mIvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onAddorDeleteCilck(position);
                }
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.tv_param_name)
        TextView mTvParamName;
        @Bind(R.id.tv_param_desc)
        TextView mTvParamDesc;
        @Bind(R.id.iv_add)
        ImageView mIvAdd;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


    private OnAddorDeleteListener mListener;

    public void setListener(OnAddorDeleteListener listener) {
        mListener = listener;
    }

    public interface OnAddorDeleteListener {
        void onAddorDeleteCilck(int position);
    }
}
