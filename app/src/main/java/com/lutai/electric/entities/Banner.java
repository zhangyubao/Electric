package com.lutai.electric.entities;

import java.util.List;

/**
 * Created by zhangYB on 2016/6/19.
 */
public class Banner {


    /**
     * status : 1
     * isVisible : 1
     * size : 3
     * data : [{"urlOnClick":"","url":"http://elec2016data.oss-cn-qingdao.aliyuncs.com/advimage/2014122915442363329665.jpg"},{"urlOnClick":"","url":"http://elec2016data.oss-cn-qingdao.aliyuncs.com/advimage/2014122915462117451138.jpg"},{"urlOnClick":"","url":"http://elec2016data.oss-cn-qingdao.aliyuncs.com/advimage/2014122915540457014500.jpg"}]
     */

    private int status;
    private int isVisible;
    private int size;
    /**
     * urlOnClick :
     * url : http://elec2016data.oss-cn-qingdao.aliyuncs.com/advimage/2014122915442363329665.jpg
     */

    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(int isVisible) {
        this.isVisible = isVisible;
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
        private String urlOnClick;
        private String url;

        public String getUrlOnClick() {
            return urlOnClick;
        }

        public void setUrlOnClick(String urlOnClick) {
            this.urlOnClick = urlOnClick;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
