package com.lutai.electric.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lutai.electric.R;
import com.lutai.electric.entities.CommonDevice;
import com.lutai.electric.utils.CommonUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhangYB on 2016/6/23.
 */
public class CommonListAdapter extends BaseAdapter {

    private Context mContext;
    private List<CommonDevice.DataBean> datas;

    public CommonListAdapter(Context mContext, List<CommonDevice.DataBean> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }


    @Override
    public int getCount() {
        return datas != null ? datas.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_common_device, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        CommonDevice.DataBean dataBean = datas.get(position);
        holder.mTvDeviceName.setText(dataBean.getName());
        holder.mTvDescription.setText(dataBean.getParam());
        if (!CommonUtil.isEmpty(dataBean.getUrl())) {
            Glide.with(mContext).load(dataBean.getUrl()).into(holder.mIvDeviceImage);
        }
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv_device_image)
        ImageView mIvDeviceImage;
        @Bind(R.id.tv_device_name)
        TextView mTvDeviceName;
        @Bind(R.id.tv_description)
        TextView mTvDescription;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
