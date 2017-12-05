package com.jinyi.ihome.module.neighbor;

import java.io.Serializable;

/**
 * Created by xz on 2016/11/9.
 */
public class NeighborhoodUserConnectParam implements Serializable {
    private static final long serialVersionUID = 1L;

    //用户sid
    private String ownerSid;
    //被关注人的sid

    private String toOwnerSid;


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
}
