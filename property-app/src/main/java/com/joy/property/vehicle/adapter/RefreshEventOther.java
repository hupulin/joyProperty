package com.joy.property.vehicle.adapter;

/**
 * Created by xz on 2016/4/6.
 */
public class RefreshEventOther {
    private String mMsg;
    private int mType;
    //服务名字
    private String mName;
    public Object mCommon;
    public RefreshEventOther(String msg, int type, String name,Object common) {
        // TODO Auto-generated constructor stub
        mMsg = msg;
        mType=type;
        mName=name;
        mCommon=common;
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
 public Object getmCommon(){
        return mCommon;
    }
}
