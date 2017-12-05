package com.jinyi.ihome.module.neighbor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xz on 2016/11/16.
 */
public class NeighborJoinMessageParam implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 小区SID
     */
    private String apartmentSid;

    /***
     * 用户Sid
     */
    private String postOwner;
    /***
     * 最后拉取的时间
     */
    private Date lastTime;
    /***
     * 页码
     */
    private int pageIndex;

    public String getApartmentSid() {
        return apartmentSid;
    }

    public void setApartmentSid(String apartmentSid) {
        this.apartmentSid = apartmentSid;
    }

    public String getPostOwner() {
        return postOwner;
    }

    public void setPostOwner(String postOwner) {
        this.postOwner = postOwner;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    @Override
    public String toString() {
        return "NeighborJoinMessageParam{" +
                "apartmentSid='" + apartmentSid + '\'' +
                ", postOwner='" + postOwner + '\'' +
                ", lastTime=" + lastTime +
                ", pageIndex=" + pageIndex +
                '}';
    }
}
