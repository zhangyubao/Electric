package com.lutai.electric.entities;

import java.util.List;

/**
 * Created by zhangYB on 2016/6/20.
 * <p/>
 * 设备实体类
 */
public class CommonDevice {

    /**
     * status : 1
     * size : 1
     * data : [{"elecType":"10KV","id":8,"name":"0.4KV进线柜","param":"C相电压:1.5V A相电压:0.8V B相电压:0.5V ","url":""}]
     */

    private int status;
    private int size;
    /**
     * elecType : 10KV
     * id : 8
     * name : 0.4KV进线柜
     * param : C相电压:1.5V A相电压:0.8V B相电压:0.5V
     * url :
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
        private String elecType;
        private int id;
        private String name;
        private String param;
        private String url;

        public String getElecType() {
            return elecType;
        }

        public void setElecType(String elecType) {
            this.elecType = elecType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
