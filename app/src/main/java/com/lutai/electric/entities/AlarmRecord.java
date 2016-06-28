package com.lutai.electric.entities;

import java.util.List;

/**
 * Created by zhangYB on 2016/6/22.
 * <p/>
 * 报警记录
 */
public class AlarmRecord {

    /**
     * status : 1
     * size : 10
     * data : [{"id":1,"createTim":1466563544952,"comment":"开关故障","type":"通讯报警"},{"id":2,"createTim":1466563588259,"comment":"开关故障","type":"通讯报警"},{"id":3,"createTim":1466563814207,"comment":"开关故障","type":"通讯报警"},{"id":4,"createTim":1466563827185,"comment":"开关故障","type":"通讯报警"},{"id":5,"createTim":1466563832894,"comment":"开关故障","type":"通讯报警"},{"id":6,"createTim":1466563839722,"comment":"开关故障","type":"通讯报警"},{"id":7,"createTim":1466563846305,"comment":"开关故障","type":"通讯报警"},{"id":8,"createTim":1466563851705,"comment":"开关故障","type":"通讯报警"},{"id":9,"createTim":1466563857103,"comment":"报警内容","type":"系统报警"},{"id":10,"createTim":1466563862935,"comment":"报警内容","type":"系统报警"}]
     */

    private int status;
    private int size;
    /**
     * id : 1
     * createTim : 1466563544952
     * comment : 开关故障
     * type : 通讯报警
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
        private int id;
        private long createTim;
        private String comment;
        private String type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getCreateTim() {
            return createTim;
        }

        public void setCreateTim(long createTim) {
            this.createTim = createTim;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
