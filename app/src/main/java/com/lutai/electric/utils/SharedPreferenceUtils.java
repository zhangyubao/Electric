package com.lutai.electric.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by zhangYB on 2016/5/4.
 */
public class SharedPreferenceUtils {

    //工具类不允许创建对象
    private SharedPreferenceUtils() {
        throw new AssertionError("No instances.");
    }

    /**
     * 保存安装状态
     *
     * @param context
     * @param state
     */
    public static void saveInstallState(Context context, boolean state) {
        SharedPreferences isInstall = context.getSharedPreferences("install", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = isInstall.edit();
        editor.putBoolean("state", state);
        editor.commit();
    }

    /**
     * 获取安装状态(是否是第一次安装)
     *
     * @param context
     * @return
     */
    public static boolean getInstallState(Context context) {
        SharedPreferences isInstall = context.getSharedPreferences("install", Context.MODE_PRIVATE);
        return isInstall.getBoolean("state", false);
    }

    /**
     * 保存token
     *
     * @param context
     * @param value
     */
    public static void saveToken(Context context, String value) {
        SharedPreferences token = context.getSharedPreferences("token", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = token.edit();
        editor.putString("token", value);
        editor.commit();
    }


    /**
     * 获取token
     *
     * @param context
     * @return
     */
    public static String getToken(Context context) {
        SharedPreferences token = context.getSharedPreferences("token", Context.MODE_PRIVATE);
        return token.getString("token", "456789");
    }

    /**
     * 保存刷新时间
     *
     * @param context
     * @param value
     */
    public static void saveRefreshTime(Context context, String value) {
        SharedPreferences refreshTime = context.getSharedPreferences("deviceRefresh", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = refreshTime.edit();
        editor.putString("refreshTime", value);
        editor.commit();
    }

    /**
     * 获取刷新时间
     *
     * @param context
     * @return
     */
    public static int getRefreshTime(Context context) {
        SharedPreferences refreshTime = context.getSharedPreferences("deviceRefresh", Context.MODE_PRIVATE);
        return refreshTime.getInt("refreshTime", 10 * 1000);
    }
}
