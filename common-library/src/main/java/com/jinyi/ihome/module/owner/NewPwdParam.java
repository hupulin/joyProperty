package com.jinyi.ihome.module.owner;

import java.io.Serializable;

/**
 * Created by usb on 2016/6/22.
 */
public class NewPwdParam implements Serializable {
    private String phoneNo;
    private String pwd;

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
