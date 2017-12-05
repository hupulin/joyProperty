package com.jinyi.ihome.module.neighbor;

import java.io.Serializable;

/**
 * Created by xz on 2016/11/10.
 */
public class NeighborhoodLogParam implements Serializable {
    private static final long serialVersionUID = 1L;

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
     * 活动状态(1 活动(1.浏览 2.我要报名 ) 2(1.看邻居怎么说 2.我想说) 3.(1.浏览 2.我要参与))
     */

    private int status;

    //用户sid

    private String ownerSid;

    private String topicTitle;

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

    public String getOwnerSid() {
        return ownerSid;
    }

    public void setOwnerSid(String ownerSid) {
        this.ownerSid = ownerSid;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    @Override
    public String toString() {
        return "NeighborhoodLogParam{" +
                "apartmentSid='" + apartmentSid + '\'' +
                ", refId='" + refId + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", ownerSid='" + ownerSid + '\'' +
                ", topicTitle='" + topicTitle + '\'' +
                '}';
    }
}
