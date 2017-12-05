package com.jinyi.ihome.module.express;

import java.io.Serializable;

/**
 * Created by usb on 2017/6/13.
 */

public class ExpressRecordTo implements Serializable {


    private static final long serialVersionUID = 1L;
    /*    */   private String expressSid;
    /*    */   private String apartmentSid;
    /*    */   private String apartmentName;
    /*    */   private String expressCompany;
    /*    */   private String expressIcon;
    /*    */   private String expressNo;
    /*    */   private String createdOn;
    /*    */   private String expressPhone;
    public boolean isSelect() {
        return isSelect;
    }

    public void setIsSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }

    /*    */   private boolean isSelect;
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getExpressSid() {
        return expressSid;
    }

    public void setExpressSid(String expressSid) {
        this.expressSid = expressSid;
    }

    public String getApartmentSid() {
        return apartmentSid;
    }

    public void setApartmentSid(String apartmentSid) {
        this.apartmentSid = apartmentSid;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }

    public String getExpressIcon() {
        return expressIcon;
    }

    public void setExpressIcon(String expressIcon) {
        this.expressIcon = expressIcon;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getExpressPhone() {
        return expressPhone;
    }

    public void setExpressPhone(String expressPhone) {
        this.expressPhone = expressPhone;
    }
}
