package com.lutai.electric.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangYB on 2016/6/19.
 */
public class BaseEntity<T> {
    //返回状态
    private int status;
    //返回数据的数量
    private int size;
    //是否显示，1显示；0不显示
    private int isVisible;
    //    车间名称
    private String deviceName;
    //数据实体
    private List<T> datas = new ArrayList<T>();

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(int isVisible) {
        this.isVisible = isVisible;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }
}
