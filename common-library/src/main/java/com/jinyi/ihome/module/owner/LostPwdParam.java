package com.jinyi.ihome.module.owner;

import java.io.Serializable;

/**
 * Created by usb on 2016/6/22.
 */
public class LostPwdParam implements Serializable {
    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    private String phoneNo;
}
