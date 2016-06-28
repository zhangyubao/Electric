package com.lutai.electric.entities;

/**
 * Created by zhangYB on 2016/6/24.
 */
public class Result {
    //    {"status":1}
    public static final int SUCCESS = 1;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
