package com.jinyi.ihome.module.house;

import java.io.Serializable;

/**
 * Created by xz on 2016/5/16.
 */
public class AllHouseMenuTo implements Serializable{

    /**
     * sid : 19BDFF57-4A5C-4D3B-BFEB-9552B0370001
     * code : A001
     * name : 我要买房
     * icon :
     */
    private static final long serialVersionUID = 1L;
    private String sid;
    private String code;
    private String name;
    private String icon;



    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }



    @Override
    public String toString() {
        return "AllHouseMenuTo{" +
                "sid='" + sid + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
