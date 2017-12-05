package com.jinyi.ihome.module.neighbor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xz on 2016/7/29.
 */
public class NeighborMessageParam2 implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 小区SID
     */
    private String apartmentSid;


    /***
     * 用户Sid
     */
    private String userSid;
    /***
     * 最后拉取的时间
     */
    private Date lastTime;

    public String getApartmentSid() {
        return apartmentSid;
    }

    public void setApartmentSid(String apartmentSid) {
        this.apartmentSid = apartmentSid;
    }

    public String getUserSid() {
        return userSid;
    }

    public void setUserSid(String userSid) {
        this.userSid = userSid;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    @Override
    public String toString() {
        return "NeighborMessageParam2{" +
                "apartmentSid='" + apartmentSid + '\'' +
                ", userSid='" + userSid + '\'' +
                ", lastTime=" + lastTime +
                '}';
    }
}
