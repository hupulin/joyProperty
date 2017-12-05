package com.jinyi.ihome.module.house;

import java.io.Serializable;

/**
 * Created by xz on 2016/5/27.
 */
public class SearchHouseLabelParam implements Serializable{
     private static final long serialVersionUID = 1L;
     private String apartmentSid;
     private String ownerSid;
     private String filter;
     private int pageIndex;
     private int publishType;
     private String configHouse;

    public String getApartmentSid() {
        return apartmentSid;
    }

    public void setApartmentSid(String apartmentSid) {
        this.apartmentSid = apartmentSid;
    }

    public String getOwnerSid() {
        return ownerSid;
    }

    public void setOwnerSid(String ownerSid) {
        this.ownerSid = ownerSid;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPublishType() {
        return publishType;
    }

    public void setPublishType(int publishType) {
        this.publishType = publishType;
    }

    public String getConfigHouse() {
        return configHouse;
    }

    public void setConfigHouse(String configHouse) {
        this.configHouse = configHouse;
    }

    @Override
    public String toString() {
        return "SearchHouseLabelParam{" +
                "apartmentSid='" + apartmentSid + '\'' +
                ", ownerSid='" + ownerSid + '\'' +
                ", filter='" + filter + '\'' +
                ", pageIndex=" + pageIndex +
                ", publishType=" + publishType +
                ", configHouse='" + configHouse + '\'' +
                '}';
    }
}
