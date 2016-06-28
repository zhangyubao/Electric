package com.lutai.electric.entities;

import java.util.List;

/**
 * Created by zhangYB on 2016/6/20.
 */
public class DeviceInfo {

    /**
     * status : 1
     * info : {"alarms":"开关故障","id":1,"isDefault":0,"name":"35KV进线柜","params":[{"isDefault":1,"paramName":"A相电压","paramValue":"0.8V"},{"isDefault":0,"paramName":"B相电压","paramValue":"0.5V"},{"isDefault":1,"paramName":"C相电压","paramValue":"1.5V"},{"isDefault":0,"paramName":"A相功率因数","paramValue":"2.8A"},{"isDefault":0,"paramName":"B向功率因数","paramValue":"112V"},{"isDefault":0,"paramName":"C向功率因数","paramValue":"12V"},{"isDefault":0,"paramName":"A相功率因数","paramValue":"2.8A"},{"isDefault":0,"paramName":"B向功率因数","paramValue":"112V"},{"isDefault":0,"paramName":"C向功率因数","paramValue":"12V"},{"isDefault":0,"paramName":"A相功率因数","paramValue":"2.8A"},{"isDefault":0,"paramName":"B向功率因数","paramValue":"112V"},{"isDefault":0,"paramName":"C向功率因数","paramValue":"12V"}],"time":"09:44","url":"http://elec2016data.oss-cn-qingdao.aliyuncs.com/dianrong.png"}
     */

    private int status;
    /**
     * alarms : 开关故障
     * id : 1
     * isDefault : 0
     * name : 35KV进线柜
     * params : [{"isDefault":1,"paramName":"A相电压","paramValue":"0.8V"},{"isDefault":0,"paramName":"B相电压","paramValue":"0.5V"},{"isDefault":1,"paramName":"C相电压","paramValue":"1.5V"},{"isDefault":0,"paramName":"A相功率因数","paramValue":"2.8A"},{"isDefault":0,"paramName":"B向功率因数","paramValue":"112V"},{"isDefault":0,"paramName":"C向功率因数","paramValue":"12V"},{"isDefault":0,"paramName":"A相功率因数","paramValue":"2.8A"},{"isDefault":0,"paramName":"B向功率因数","paramValue":"112V"},{"isDefault":0,"paramName":"C向功率因数","paramValue":"12V"},{"isDefault":0,"paramName":"A相功率因数","paramValue":"2.8A"},{"isDefault":0,"paramName":"B向功率因数","paramValue":"112V"},{"isDefault":0,"paramName":"C向功率因数","paramValue":"12V"}]
     * time : 09:44
     * url : http://elec2016data.oss-cn-qingdao.aliyuncs.com/dianrong.png
     */

    private InfoBean info;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {
        private String alarms;
        private int id;
        private int isDefault;
        private String name;
        private String time;
        private String url;
        /**
         * isDefault : 1
         * paramName : A相电压
         * paramValue : 0.8V
         */

        private List<ParamsBean> params;

        public String getAlarms() {
            return alarms;
        }

        public void setAlarms(String alarms) {
            this.alarms = alarms;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<ParamsBean> getParams() {
            return params;
        }

        public void setParams(List<ParamsBean> params) {
            this.params = params;
        }

        public static class ParamsBean {
            private int isDefault;
            private String paramName;
            private String paramValue;

            public int getIsDefault() {
                return isDefault;
            }

            public void setIsDefault(int isDefault) {
                this.isDefault = isDefault;
            }

            public String getParamName() {
                return paramName;
            }

            public void setParamName(String paramName) {
                this.paramName = paramName;
            }

            public String getParamValue() {
                return paramValue;
            }

            public void setParamValue(String paramValue) {
                this.paramValue = paramValue;
            }
        }
    }
}
