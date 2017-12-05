package com.jinyi.ihome.module.neighbor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xz on 2016/11/18.
 **/

    public class NeighborCommentAndLikeParam implements Serializable {
        private static final long serialVersionUID = 1L;

        /**
         * 小区ID
         */
        private String apartmentSid;
        /**
         * 用户ID
         */
        private String postOwner;
        /**
         * 邻居圈类型
         */
        private String postType;
        /**
         * (1:全部 2:单个发帖类型)
         */
        private int type;

        /**
         * 页数
         */
        private int pageIndex;

        /**
         * 时间
         */
        private Date dateTime;

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

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "NeighborCommentAndLikeParam{" +
                "apartmentSid='" + apartmentSid + '\'' +
                ", postOwner='" + postOwner + '\'' +
                ", postType='" + postType + '\'' +
                ", type=" + type +
                ", pageIndex=" + pageIndex +
                ", dateTime=" + dateTime +
                '}';
    }
}

