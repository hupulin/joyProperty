package com.joy.property.task;

/**
 * Created by usb on 2017/6/26.
 */

public class RefreshEventTask {
    private String mMsg;
    private Boolean noData;
    public RefreshEventTask(String msg) {
        mMsg = msg;
    }
    public RefreshEventTask(String msg,Boolean noData) {
        // TODO Auto-generated constructor stub
        this.mMsg=msg;
        this.noData=noData;
    }
    public String getMsg() {
        return mMsg;
    }



    public Boolean getNoData() {
        return noData;
    }


}
