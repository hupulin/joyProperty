package com.jinyi.ihome.module.system;

import java.io.Serializable;
import java.util.List;

/**
 * Created by usb on 2016/7/25.
 */
public class RoleRightsTo implements Serializable {
    private int success;

    private String message;

    private int total;

    private List<Data> data ;

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
    public void setData(List<Data> data){
        this.data = data;
    }
    public List<Data> getData(){
        return this.data;
    }
    public void setTag(String tag){
        this.tag = tag;
    }
    public String getTag(){
        return this.tag;
    }
    public static class Data {
        private String sid;

        private String code;

        private String name;

        private String icon;

        public void setSid(String sid){
            this.sid = sid;
        }
        public String getSid(){
            return this.sid;
        }
        public void setCode(String code){
            this.code = code;
        }
        public String getCode(){
            return this.code;
        }
        public void setName(String name){
            this.name = name;
        }
        public String getName(){
            return this.name;
        }
        public void setIcon(String icon){
            this.icon = icon;
        }
        public String getIcon(){
            return this.icon;
        }

    }
}
