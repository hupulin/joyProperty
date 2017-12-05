package com.jinyi.ihome.module.express;

import java.io.Serializable;

/**
 * Created by usb on 2017/8/3.
 */

public class ExpressNewTo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * expressSid : ee831a22-664a-4c4f-8c1b-c407e16b6138
     * apartmentSid : a241b63a-df3c-43a8-a7fd-477e5f950d9f
     * ownerSid : c6de2f10-a1cd-4dfc-a204-65bb6b8ccdb8
     * roomNo : null
     * expressCompany : 邮政包裹
     * expressNo : 111111111111
     * expressPhone : 18069765023
     * expressRemark : 140786
     * expressStatus : 2
     * createdOn : 2017-08-03 10:32:00
     * modifiedOn : 2017-08-03 10:32:00
     * submitExpress : null
     * createdby : c89e3668-7b4c-4743-bb0a-437e5e95eb02
     * userName : 郑超杰
     * expressImages : a6adf973a29cf96c610fcdbd9f2b85eb;d2ab1d77ae91b3c91d415cb7a66defd4
     * handleUserSid : null
     * receivePhoto : null
     * msgCount : null
     * isRegister : 1
     * expressIcon : youzhengguonei
     * apartmentName : 幸福家园
     */

    private String expressSid;
    private String apartmentSid;
    private String roomNo;
    private String expressCompany;
    private String expressNo;
    private String sendAgain ;//0 不可以， 1 可以
    private String expressPhone;
    private String expressRemark;
    private int    expressStatus;
    private String createdOn;
    private String modifiedOn;
    private String userName;
    private String expressImages;
    private String receivePhoto;
    private int    isRegister;
    private String expressIcon;
    private String apartmentName;
    private String ownerSid;
    public boolean isSelect() {
        return isSelect;
    }

    public void setIsSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }

    public String getSendAgain() {
        return sendAgain;
    }

    public void setSendAgain(String sendAgain) {
        this.sendAgain = sendAgain;
    }

    /*    */   private boolean isSelect;
    @Override
    public String toString() {
        return "ExpressNewTo{" +
                "expressSid='" + expressSid + '\'' +
                ", apartmentSid='" + apartmentSid + '\'' +
                ", roomNo='" + roomNo + '\'' +
                ", expressCompany='" + expressCompany + '\'' +
                ", expressNo='" + expressNo + '\'' +
                ", expressPhone='" + expressPhone + '\'' +
                ", expressRemark='" + expressRemark + '\'' +
                ", expressStatus=" + expressStatus +
                ", createdOn='" + createdOn + '\'' +
                ", modifiedOn='" + modifiedOn + '\'' +
                ", userName='" + userName + '\'' +
                ", expressImages='" + expressImages + '\'' +
                ", receivePhoto='" + receivePhoto + '\'' +
                ", isRegister=" + isRegister +
                ", expressIcon='" + expressIcon + '\'' +
                ", apartmentName='" + apartmentName + '\'' +
                ", ownerSid='" + ownerSid + '\'' +
                '}';
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

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getExpressImages() {
        return expressImages;
    }

    public void setExpressImages(String expressImages) {
        this.expressImages = expressImages;
    }

    public String getReceivePhoto() {
        return receivePhoto;
    }

    public void setReceivePhoto(String receivePhoto) {
        this.receivePhoto = receivePhoto;
    }

    public int getIsRegister() {
        return isRegister;
    }

    public void setIsRegister(int isRegister) {
        this.isRegister = isRegister;
    }

    public String getExpressIcon() {
        return expressIcon;
    }

    public void setExpressIcon(String expressIcon) {
        this.expressIcon = expressIcon;
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
}
