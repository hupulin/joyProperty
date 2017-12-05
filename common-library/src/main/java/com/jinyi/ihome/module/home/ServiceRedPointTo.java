package com.jinyi.ihome.module.home;

import java.io.Serializable;

/**
 * Created by usb on 2016/7/26.
 */
public class ServiceRedPointTo implements Serializable {
    private static final long serialVersionUID = 1L;
    private int success;

    private String message;

    private int total;

    private int data;

    private String tag;

    public void setSuccess(int success){
        this.success = success;
    }
    public int getSuccess(){
        return this.success;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
    public void setTotal(int total){
        this.total = total;
    }
    public int getTotal(){
        return this.total;
    }
    public void setData(int data){
        this.data = data;
    }
    public int getData(){
        return this.data;
    }
    public void setTag(String tag){
        this.tag = tag;
    }
    public String getTag(){
        return this.tag;
    }

}
