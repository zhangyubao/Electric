package com.lutai.electric;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.lutai.electric.base.BaseActivity;

import butterknife.Bind;

/**
 * Created by zhangYB on 2016/6/19.
 */
public class BannerViewerActivity extends BaseActivity {


    @Bind(R.id.wv_banner_info)
    WebView mWvBannerInfo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String clickUrl = getIntent().getStringExtra("clickUrl");
        LoadPager(clickUrl);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_banner_view;
    }


    /**
     * webView加载banner详情信息
     *
     * @param url
     */
    public void LoadPager(String url) {
        mWvBannerInfo.loadUrl(url);
    }

}
