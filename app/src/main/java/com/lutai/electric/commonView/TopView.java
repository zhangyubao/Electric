package com.lutai.electric.commonView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lutai.electric.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhangYB on 2016/6/17.
 * 自定义头部View
 */
public class TopView extends RelativeLayout {

    @Bind(R.id.tv_back)
    TextView mTvBack;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.tv_right)
    TextView mTvRight;
    @Bind(R.id.iv_setting)
    ImageView mIvSetting;
    @Bind(R.id.rl_parent)
    RelativeLayout mRlParent;

    public TopView(Context context) {
        super(context);
    }

    public TopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //在构造函数中将Xml中定义的布局解析出来。
        View view = LayoutInflater.from(context).inflate(R.layout.common_top, this, true);
        ButterKnife.bind(view);
    }

    public TopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TopView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context, attrs, defStyleAttr);
    }

    /**
     * 左边按钮的点击事件
     */
    @OnClick(R.id.tv_back)
    public void turnBack() {
        if (mOnTopClickListener != null) {
            mOnTopClickListener.onLeftClick();
        }
    }

    /**
     * 设置顶部布局背景色
     */
    public void setTopBackground(int color) {
        mRlParent.setBackgroundColor(color);
    }

    /**
     * 右边按钮的点击事件
     */
    @OnClick(R.id.iv_setting)
    public void setRightImageListener() {
        if (mOnTopClickListener != null) {
            mOnTopClickListener.onRightClick();
        }
    }
    /**
     * 右边按钮的点击事件
     */
    @OnClick(R.id.tv_right)
    public void rightTextListener() {
        if (mOnTopClickListener != null) {
            mOnTopClickListener.onRightClick();
        }
    }

    /**
     * 设置左边文字
     *
     * @param left
     */
    public void setLeftText(String left) {
        mTvBack.setText(left);
    }

    /**
     * 设置右边文字
     *
     * @param right
     */
    public void setRightText(String right) {
        mTvRight.setText(right);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        mTvTitle.setText(title);
    }

    /**
     * 设置右边背景图
     *
     * @param id
     */
    public void setImage(int id) {
        mIvSetting.setImageResource(id);
    }

    private OnTopClickListener mOnTopClickListener;

    public interface OnTopClickListener {
        /**
         * 右边按钮的点击事件
         */
        void onRightClick();

        /**
         * 左边按钮的点击事件
         */
        void onLeftClick();
    }

    public void setOnTopClickListener(OnTopClickListener onTopClickListener) {
        mOnTopClickListener = onTopClickListener;
    }

    /**
     * 隐藏右边图片
     */
    public void setRightImgGone() {
        mIvSetting.setVisibility(View.GONE);
    }

    /**
     * 显示右边图片
     */
    public void setRightImgVisiable() {
        mIvSetting.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏左边文字
     */
    public void setLeftGone() {
        mTvBack.setVisibility(View.GONE);
    }

    /**
     * 显示左边文字
     */
    public void setLeftTextVisiable() {
        mTvBack.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏右边文字
     */
    public void setRightTextGone() {
        mTvRight.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏右边文字
     */
    public void setRightTextVisiable() {
        mTvRight.setVisibility(View.VISIBLE);
    }

}
