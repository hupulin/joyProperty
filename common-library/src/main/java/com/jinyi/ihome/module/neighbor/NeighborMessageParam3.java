package com.jinyi.ihome.module.neighbor;

import java.io.Serializable;

/**
 * Created by xz on 2016/7/29.
 */
public class NeighborMessageParam3 implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 小区SID
     */
    private String apartmentSid;
    private int pageIndex;

    /***
     * 用户Sid
     */
    private String userSid;
    /***
     * 最后拉取的时间
     */
    private String typeSid;

    public String getApartmentSid() {
        return apartmentSid;
    }

    public void setApartmentSid(String apartmentSid) {
        this.apartmentSid = apartmentSid;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getUserSid() {
        return userSid;
    }

    public void setUserSid(String userSid) {
        this.userSid = userSid;
    }

    public String getTypeSid() {
        return typeSid;
    }

    public void setTypeSid(String typeSid) {
        this.typeSid = typeSid;
    }
}
