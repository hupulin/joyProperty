package com.jinyi.ihome.module.neighbor;

import java.io.Serializable;

/**
 * Created by xz on 2016/11/7.
 */
public class NeighborhoodMediaTo implements Serializable{

    /**
     * mediaSid : b8c8de30-e250-43ce-8c78-2285ad21a770
     * mediaUrl : web_c9946396-d692-442f-94d5-83c7a205fe70
     * mediaType : 0
     * mediaWidth : 680
     * mediaHeight : 257
     * mediaCreatetime : null
     */

    private String mediaSid;
    private String mediaUrl;
    private int mediaType;
    private int mediaWidth;
    private int mediaHeight;
    private Object mediaCreatetime;

    public String getMediaSid() {
        return mediaSid;
    }

    public void setMediaSid(String mediaSid) {
        this.mediaSid = mediaSid;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public int getMediaType() {
        return mediaType;
    }

    public void setMediaType(int mediaType) {
        this.mediaType = mediaType;
    }

    public int getMediaWidth() {
        return mediaWidth;
    }

    public void setMediaWidth(int mediaWidth) {
        this.mediaWidth = mediaWidth;
    }

    public int getMediaHeight() {
        return mediaHeight;
    }

    public void setMediaHeight(int mediaHeight) {
        this.mediaHeight = mediaHeight;
    }

    public Object getMediaCreatetime() {
        return mediaCreatetime;
    }

    public void setMediaCreatetime(Object mediaCreatetime) {
        this.mediaCreatetime = mediaCreatetime;
    }

    @Override
    public String toString() {
        return "NeighborhoodMediaTo{" +
                "mediaSid='" + mediaSid + '\'' +
                ", mediaUrl='" + mediaUrl + '\'' +
                ", mediaType=" + mediaType +
                ", mediaWidth=" + mediaWidth +
                ", mediaHeight=" + mediaHeight +
                ", mediaCreatetime=" + mediaCreatetime +
                '}';
    }
}
