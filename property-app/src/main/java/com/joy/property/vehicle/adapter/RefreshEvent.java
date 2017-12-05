package com.joy.property.vehicle.adapter;

/**
 * Created by xz on 2016/4/6.
 */
public class RefreshEvent {
    private String mMsg;
    private int mType;
    private String mName;
    public RefreshEvent(String msg,int type,String name) {
        // TODO Auto-generated constructor stub
        mMsg = msg;
        mType=type;
        mName=name;
    }
    public String getMsg(){
        return mMsg;
    }
    public int getType(){
        return mType;
    }
    public String getmName(){
        return mName;
    }
}
