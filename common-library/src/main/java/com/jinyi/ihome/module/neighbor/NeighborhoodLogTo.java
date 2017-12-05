package com.jinyi.ihome.module.neighbor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xz on 2016/11/10.
 */
public class NeighborhoodLogTo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String logSid;

    /**
     * 小区Id
     */
    private String apartmentSid;
    /**
     * 调查、活动或者话题的id
     */

    private String refId;
    /**
     * 互动类型（2，话题；1，活动；3，调查)
     */
    private int type;

    /**
     * 活动状态(1:浏览 2:报名 2:已参与 )
     */

    private int status;


    private Date createdOn;

    private String ownerSid;

    public String getLogSid() {
        return logSid;
    }

    public void setLogSid(String logSid) {
        this.logSid = logSid;
    }

    public String getApartmentSid() {
        return apartmentSid;
    }

    public void setApartmentSid(String apartmentSid) {
        this.apartmentSid = apartmentSid;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getOwnerSid() {
        return ownerSid;
    }

    public void setOwnerSid(String ownerSid) {
        this.ownerSid = ownerSid;
    }

    @Override
    public String toString() {
        return "NeighborhoodLogTo{" +
                "logSid='" + logSid + '\'' +
                ", apartmentSid='" + apartmentSid + '\'' +
                ", refId='" + refId + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", createdOn=" + createdOn +
                ", ownerSid='" + ownerSid + '\'' +
                '}';
    }
}