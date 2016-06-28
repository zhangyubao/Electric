package com.lutai.electric.commonView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lutai.electric.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhangYB on 2016/6/20.
 */
public class FaultInfoView extends LinearLayout {
    @Bind(R.id.iv_alarm_info)
    TextView mIvAlarmInfo;

    public FaultInfoView(Context context) {
        super(context);
        initView(context);
    }

    public FaultInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public FaultInfoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public FaultInfoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 初始化View布局文件
     */
    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.fault_info_layout, null);
        ButterKnife.bind(context, view);
    }

    /**
     * 设置报警信息
     *
     * @param value
     */
    public void setAlarmText(String value) {
        mIvAlarmInfo.setText(value);
    }

}
