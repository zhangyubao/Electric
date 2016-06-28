package com.lutai.electric.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lutai.electric.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by zhangYB on 2016/4/19.
 */
public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.HomeViewHoder> {

    private List<String> jokeInfs = new ArrayList<String>();

    public HomeListAdapter() {
    }

    public void setData(List<String> jokeInfs) {
        this.jokeInfs = jokeInfs;
        this.notifyDataSetChanged();
    }

    @Override
    public HomeViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_alarm, parent, false);
        return new HomeViewHoder(view);
    }

    @Override
    public void onBindViewHolder(HomeViewHoder holder, final int position) {
//        JokeInf.ResultBean.DataBean dataBean = jokeInfs.get(position);
//        holder.updatetime.setText(dataBean.getUpdatetime());
//        holder.hashId.setText(dataBean.getHashId());
//        holder.content.setText(dataBean.getContent());
//        holder.mItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mItemClickListener != null) {
//                    mItemClickListener.onItemClick(position);
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return jokeInfs.size() == 0 ? 0 : jokeInfs.size();
    }

    public class HomeViewHoder extends RecyclerView.ViewHolder {

        public HomeViewHoder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    /**
     * 条目点击回调接口
     */
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener mItemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
}
