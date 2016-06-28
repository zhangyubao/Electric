package com.lutai.electric.entities;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangYB on 2016/6/20.
 * 设备实体类
 */
public class Device implements Serializable{


    /**
     * status : 1
     * size : 3
     * deviceName : 鲁泰电气设备
     * data : [{"alarms":"开关故障","createTim":"1466220856530","deviceTypeId":1,"elecType":"30KV","hasChildren":0,"id":1,"isDefault":0,"labelType":"30KV","linkStyle":2,"name":"35KV进线柜","outU":"","param":"activePower:98V powerFactorA:0.8A activePowerC:10V ","sort":1,"type":"电容器","updateTime":"2016-06-21 09:45:16.0","url":""},{"alarms":"开关故障","createTim":"1466220856530","deviceTypeId":1,"elecType":"10KV","hasChildren":1,"id":2,"isDefault":0,"labelType":"10KV","linkStyle":1,"name":"10KV 1-1出线柜","outU":"","param":"activePowerA:112V powerFactorA:0.8A powerFactorB:0.5A ","sort":2,"type":"电容器","updateTime":"2016-06-21 09:45:16.0","url":""},{"alarms":"开关故障","createTim":"1466220856530","deviceTypeId":1,"elecType":"10KV","hasChildren":0,"id":3,"isDefault":1,"labelType":"10KV","linkStyle":1,"name":"10KV 1-2出线柜","outU":"","param":"activePowerA:112V powerFactorA:0.8A powerFactorB:0.5A ","sort":3,"type":"电容器","updateTime":"2016-06-21 09:45:16.0","url":""}]
     */

    private int status;
    private int size;
    private String deviceName;
    /**
     * alarms : 开关故障
     * createTim : 1466220856530
     * deviceTypeId : 1
     * elecType : 30KV
     * hasChildren : 0
     * id : 1
     * isDefault : 0
     * labelType : 30KV
     * linkStyle : 2
     * name : 35KV进线柜
     * outU :
     * param : activePower:98V powerFactorA:0.8A activePowerC:10V
     * sort : 1
     * type : 电容器
     * updateTime : 2016-06-21 09:45:16.0
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

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        private String alarms;
        private String createTim;
        private int deviceTypeId;
        private String elecType;
        private int hasChildren;
        private int id;
        private int isDefault;
        private String labelType;
        private int linkStyle;
        private String name;
        private String outU;
        private String param;
        private int sort;
        private String type;
        private String updateTime;
        private String url;

        public String getAlarms() {
            return alarms;
        }

        public void setAlarms(String alarms) {
            this.alarms = alarms;
        }

        public String getCreateTim() {
            return createTim;
        }

        public void setCreateTim(String createTim) {
            this.createTim = createTim;
        }

        public int getDeviceTypeId() {
            return deviceTypeId;
        }

        public void setDeviceTypeId(int deviceTypeId) {
            this.deviceTypeId = deviceTypeId;
        }

        public String getElecType() {
            return elecType;
        }

        public void setElecType(String elecType) {
            this.elecType = elecType;
        }

        public int getHasChildren() {
            return hasChildren;
        }

        public void setHasChildren(int hasChildren) {
            this.hasChildren = hasChildren;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(int isDefault) {
            this.isDefault = isDefault;
        }

        public String getLabelType() {
            return labelType;
        }

        public void setLabelType(String labelType) {
            this.labelType = labelType;
        }

        public int getLinkStyle() {
            return linkStyle;
        }

        public void setLinkStyle(int linkStyle) {
            this.linkStyle = linkStyle;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOutU() {
            return outU;
        }

        public void setOutU(String outU) {
            this.outU = outU;
        }

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
