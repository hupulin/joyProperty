package com.jinyi.ihome.module.neighbor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xz on 2016/11/9.
 **/
public class NeighborhoodUserConnectTo implements Serializable {
    private static final long serialVersionUID = 1L;
    //主键
    private String userConnectSid;

    //用户sid

    private String ownerSid;
    //被关注人的sid

    private String toOwnerSid;
    //创建时间
    private Date createdOn;

    public String getUserConnectSid() {
        return userConnectSid;
    }

    public void setUserConnectSid(String userConnectSid) {
        this.userConnectSid = userConnectSid;
    }

    public String getOwnerSid() {
        return ownerSid;
    }

    public void setOwnerSid(String ownerSid) {
        this.ownerSid = ownerSid;
    }

    public String getToOwnerSid() {
        return toOwnerSid;
    }

    public void setToOwnerSid(String toOwnerSid) {
        this.toOwnerSid = toOwnerSid;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "NeighborhoodUserConnectTo{" +
                "userConnectSid='" + userConnectSid + '\'' +
                ", ownerSid='" + ownerSid + '\'' +
                ", toOwnerSid='" + toOwnerSid + '\'' +
                ", createdOn=" + createdOn +
                '}';
    }
}

