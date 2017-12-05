package com.jinyi.ihome.module.express;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by usb on 2017/6/13.
 */

public class ExpressToNew   implements Serializable
/*    */ {
    /*    */   private static final long serialVersionUID = 1L;
    /*    */   private String expressSid;
    /*    */   private String apartmentSid;
    /*    */   private String apartmentName;
    /*    */   private String ownerSid;
    /*    */   private String expressOwnerNo;
    /*    */   private String expressCompany;
    /*    */   private String expressIcon;
    /*    */   private String expressNo;
    /*    */   private String expressPhone;
    /*    */   private String expressRemark;
    /*    */   private int expressStatus;
    /*    */   private String createdOn;
    /*    */   private String modifiedOn;
    /*    */   private String submitExpress;
    /*    */   private String flow;

    @Override
    public String toString() {
        return "ExpressToNew{" +
                "expressSid='" + expressSid + '\'' +
                ", apartmentSid='" + apartmentSid + '\'' +
                ", apartmentName='" + apartmentName + '\'' +
                ", ownerSid='" + ownerSid + '\'' +
                ", expressOwnerNo='" + expressOwnerNo + '\'' +
                ", expressCompany='" + expressCompany + '\'' +
                ", expressIcon='" + expressIcon + '\'' +
                ", expressNo='" + expressNo + '\'' +
                ", expressPhone='" + expressPhone + '\'' +
                ", expressRemark='" + expressRemark + '\'' +
                ", expressStatus='" + expressStatus + '\'' +
                ", createdOn='" + createdOn + '\'' +
                ", modifiedOn='" + modifiedOn + '\'' +
                ", submitExpress='" + submitExpress + '\'' +
                ", flow='" + flow + '\'' +
                '}';
    }

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

    public String getOwnerSid() {
        return ownerSid;
    }

    public void setOwnerSid(String ownerSid) {
        this.ownerSid = ownerSid;
    }

    public String getExpressOwnerNo() {
        return expressOwnerNo;
    }

    public void setExpressOwnerNo(String expressOwnerNo) {
        this.expressOwnerNo = expressOwnerNo;
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

    public String getExpressPhone() {
        return expressPhone;
    }

    public void setExpressPhone(String expressPhone) {
        this.expressPhone = expressPhone;
    }

    public String getExpressRemark() {
        return expressRemark;
    }

    public void setExpressRemark(String expressRemark) {
        this.expressRemark = expressRemark;
    }

    public int getExpressStatus() {
        return expressStatus;
    }

    public void setExpressStatus(int expressStatus) {
        this.expressStatus = expressStatus;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getSubmitExpress() {
        return submitExpress;
    }

    public void setSubmitExpress(String submitExpress) {
        this.submitExpress = submitExpress;
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }
}
/*    */
