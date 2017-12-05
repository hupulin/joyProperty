/*    */ package com.jinyi.ihome.module.home;
/*    */ 
/*    */ import com.jinyi.ihome.module.owner.UserBasicTo;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ServiceMainExpandTo extends ServiceMainTo
/*    */ {
    /*    */   private static final long serialVersionUID = 1L;
    /*    */   private String ownerPhone;
    /*    */   private String apartmentName;
    /*    */   private boolean isBadEvaluate;



    public boolean getIsBadEvaluate() {
        return isBadEvaluate;
    }

    public void setIsBadEvaluate(Boolean isBadEvaluate) {
        this.isBadEvaluate = isBadEvaluate;
    }



    public String getBadEvaluationDescribe() {
        return badEvaluationDescribe;
    }

    public void setBadEvaluationDescribe(String badEvaluationDescribe) {
        this.badEvaluationDescribe = badEvaluationDescribe;
    }

    public String getBadEvaluationRemark() {
        return badEvaluationRemark;
    }

    public void setBadEvaluationRemark(String badEvaluationRemark) {
        this.badEvaluationRemark = badEvaluationRemark;
    }

    private String badEvaluationRemark;//   备注
    private String  badEvaluationDescribe ;//差评标签描述

    private String serviceEmergenctStatus;

    @Override
    public String toString() {
        return "ServiceMainExpandTo{" +
                "ownerPhone='" + ownerPhone + '\'' +
                ", apartmentName='" + apartmentName + '\'' +
                ", isBadEvaluate=" + isBadEvaluate +
                ", badEvaluationRemark='" + badEvaluationRemark + '\'' +
                ", badEvaluationDescribe='" + badEvaluationDescribe + '\'' +
                ", serviceEmergenctStatus='" + serviceEmergenctStatus + '\'' +
                ", reqAssistUser=" + reqAssistUser +
                ", histList=" + histList +
                ", serviceMainExt=" + serviceMainExt +
                ", roomNo='" + roomNo + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }

    public String getServiceEmergenctStatus() {
        return serviceEmergenctStatus;
    }

    public void setServiceEmergenctStatus(String serviceEmergenctStatus) {
        this.serviceEmergenctStatus = serviceEmergenctStatus;
    }

    /*    */   private UserBasicTo reqAssistUser;
    /*    */   private List<ServiceHistTo> histList;
    private  ServiceMainExtTo serviceMainExt;
    private String roomNo;
    private String startTime;
    private String endTime;
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public ServiceMainExtTo getServiceMainExt() {
        return serviceMainExt;
    }

    public void setServiceMainExt(ServiceMainExtTo serviceMainExt) {
        this.serviceMainExt = serviceMainExt;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    /*    */
/*    */   public void setOwnerPhone(String ownerPhone)
/*    */   {
/* 22 */     this.ownerPhone = ownerPhone; }
    /* 22 */   public String getOwnerPhone() { return this.ownerPhone; }
    /*    */
/*    */   public void setApartmentName(String apartmentName) {
/* 25 */     this.apartmentName = apartmentName; }
    /* 26 */   public String getApartmentName() { return this.apartmentName; }
    /*    */
/*    */   public UserBasicTo getReqAssistUser() {
/* 29 */     return this.reqAssistUser; }
    /* 30 */   public void setReqAssistUser(UserBasicTo reqAssistUser) { this.reqAssistUser = reqAssistUser; }
    /*    */
/*    */   public void setHistList(List<ServiceHistTo> histList) {
/* 33 */     this.histList = histList; }
    /* 34 */   public List<ServiceHistTo> getHistList() { return this.histList; }
/*    */

    /*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.home.ServiceMainExpandTo
 * JD-Core Version:    0.6.2
 */