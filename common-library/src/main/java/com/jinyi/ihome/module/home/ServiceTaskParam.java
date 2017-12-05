package com.jinyi.ihome.module.home;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by usb on 2016/8/25.
 **/
public class ServiceTaskParam implements Serializable {
    private static final long serialVersionUID = 1L;

    /***
     * 上一次的浏览时间
     */
    private Date lastTime;

    public String getOwnerSid() {
        return ownerSid;
    }

    public void setOwnerSid(String ownerSid) {
        this.ownerSid = ownerSid;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    /***
     * 用户sid
     */
    private String ownerSid;

    @Override
    public String toString() {
        return "ServiceTaskParam{" +
                "lastTime=" + lastTime +
                ", ownerSid='" + ownerSid + '\'' +
                '}';
    }
}
