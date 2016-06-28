package com.lutai.electric.entities;

import java.util.List;

/**
 * Created by zhangYB on 2016/6/20.
 * 电压实体
 */
public class Voltage {

    /**
     * status : 1
     * size : 3
     * data : [{"name":"30KV"},{"name":"10KV"},{"name":"0.4KV"}]
     */

    private int status;
    private int size;
    /**
     * name : 30KV
     */

    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}