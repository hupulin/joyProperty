package com.jinyi.ihome.module.visitor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xz on 2016/5/23.
 */
public class MyGuestRecordTo implements Serializable{

    /**
     * cardSid : 8a11dfa4-f5f8-4618-94a0-46377dfdc3d9
     * cardNo : 96057718
     * visitorQty : null
     * endDate : 2016-01-23
     * passCount : 0
     * createdOn : 2016-01-23 13:44:33
     * modifiedOn : null
     * apartmentSid : null
     * apartName : null
     * apartAddress : null
     * ownerSid : ea4e2e53-245c-4bf7-9c76-1848ed3dc6b9
     * ownerNo : null
     * ownerPhone : null
     * visitorName : 李东佛先生
     * visitorCarNo : 浙A12345
     * visitorImage : null
     * imageData : null
     * visitTime : 2016-01-23 上午
     * leaveTime : 2016-01-23 下午
     */

    private String cardSid;
    private String cardNo;
    private Integer visitorQty;
    private String endDate;
    private Integer passCount;
    private Date createdOn;
    private Date modifiedOn;
    private String apartmentSid;
    private String apartName;
    private String apartAddress;
    private String ownerSid;
    private String ownerNo;
    private String ownerPhone;
    private String visitorName;
    private String visitorCarNo;
    private String visitorImage;
    private String imageData;
    private String visitTime;
    private String leaveTime;

    public String getCardSid() {
        return cardSid;
    }

    public void setCardSid(String cardSid) {
        this.cardSid = cardSid;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Integer getVisitorQty() {
        return visitorQty;
    }

    public void setVisitorQty(Integer visitorQty) {
        this.visitorQty = visitorQty;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getPassCount() {
        return passCount;
    }

    public void setPassCount(Integer passCount) {
        this.passCount = passCount;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getApartmentSid() {
        return apartmentSid;
    }

    public void setApartmentSid(String apartmentSid) {
        this.apartmentSid = apartmentSid;
    }

    public String getApartName() {
        return apartName;
    }

    public void setApartName(String apartName) {
        this.apartName = apartName;
    }

    public String getApartAddress() {
        return apartAddress;
    }

    public void setApartAddress(String apartAddress) {
        this.apartAddress = apartAddress;
    }

    public String getOwnerSid() {
        return ownerSid;
    }

    public void setOwnerSid(String ownerSid) {
        this.ownerSid = ownerSid;
    }

    public String getOwnerNo() {
        return ownerNo;
    }

    public void setOwnerNo(String ownerNo) {
        this.ownerNo = ownerNo;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getVisitorCarNo() {
        return visitorCarNo;
    }

    public void setVisitorCarNo(String visitorCarNo) {
        this.visitorCarNo = visitorCarNo;
    }

    public String getVisitorImage() {
        return visitorImage;
    }

    public void setVisitorImage(String visitorImage) {
        this.visitorImage = visitorImage;
    }

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }

    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }

    public String getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime;
    }

    @Override
    public String toString() {
        return "MyGuestRecordTo{" +
                "cardSid='" + cardSid + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", visitorQty=" + visitorQty +
                ", endDate='" + endDate + '\'' +
                ", passCount=" + passCount +
                ", createdOn=" + createdOn +
                ", modifiedOn=" + modifiedOn +
                ", apartmentSid='" + apartmentSid + '\'' +
                ", apartName='" + apartName + '\'' +
                ", apartAddress='" + apartAddress + '\'' +
                ", ownerSid='" + ownerSid + '\'' +
                ", ownerNo='" + ownerNo + '\'' +
                ", ownerPhone='" + ownerPhone + '\'' +
                ", visitorName='" + visitorName + '\'' +
                ", visitorCarNo='" + visitorCarNo + '\'' +
                ", visitorImage='" + visitorImage + '\'' +
                ", imageData='" + imageData + '\'' +
                ", visitTime='" + visitTime + '\'' +
                ", leaveTime='" + leaveTime + '\'' +
                '}';
    }
}
