package com.jinyi.ihome.module.home;

import java.io.Serializable;

/**
 * Created by usb on 2017/6/15.
 */

public class ServiceMyworkParam  implements Serializable {
    /*    */   private static final long serialVersionUID = 1L;

    /**
     * categorySid : B89C08B9-CD76-4B94-AE27-2617157180EF
     * userSid :
     * pageIndex : 0
     * apartmentSid :
     * typeSid :
     * workStatus :
     * createdOn :
     */

    private String categorySid;
    private String userSid;
    private int pageIndex;
    private String apartmentSid;

    @Override
    public String toString() {
        return "ServiceMyworkParam{" +
                "categorySid='" + categorySid + '\'' +
                ", userSid='" + userSid + '\'' +
                ", pageIndex=" + pageIndex +
                ", apartmentSid='" + apartmentSid + '\'' +
                ", typeSid='" + typeSid + '\'' +
                ", typeApartmentSid='" + typeApartmentSid + '\'' +
                ", workStatus='" + workStatus + '\'' +
                ", createdOn='" + createdOn + '\'' +
                '}';
    }

    public String getTypeApartmentSid() {
        return typeApartmentSid;
    }

    public void setTypeApartmentSid(String typeApartmentSid) {
        this.typeApartmentSid = typeApartmentSid;
    }

    private String typeSid;
    private String typeApartmentSid;
    private String workStatus;
    private String createdOn;

    public String getCategorySid() {
        return categorySid;
    }

    public void setCategorySid(String categorySid) {
        this.categorySid = categorySid;
    }

    public String getUserSid() {
        return userSid;
    }

    public void setUserSid(String userSid) {
        this.userSid = userSid;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getApartmentSid() {
        return apartmentSid;
    }

    public void setApartmentSid(String apartmentSid) {
        this.apartmentSid = apartmentSid;
    }

    public String getTypeSid() {
        return typeSid;
    }

    public void setTypeSid(String typeSid) {
        this.typeSid = typeSid;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }
}
