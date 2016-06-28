package com.lutai.electric.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by zhangYB on 2016/4/29.
 */
public class MyApplication extends Application {


    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }

}
