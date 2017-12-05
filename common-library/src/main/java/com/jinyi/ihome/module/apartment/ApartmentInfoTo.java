/*    */ package com.jinyi.ihome.module.apartment;

import java.util.Date;

/*    */
/*    */ public class ApartmentInfoTo extends ApartmentBasicTo
/*    */ {
    /*    */   private static final long serialVersionUID = 1L;
    /*    */   private ApartmentPlaceTo place;
    /*    */   private String telephone;
    /*    */   private String telephone1;
    /*    */   private String telephone2;
    /*    */   private String apartmentNo;
    /*    */   private String storeFlag;
    /*    */   private String housekeepingRemrk;
    /*    */   private String repairRemrk;
    /*    */   private String startTime;
    private String endTime;
    private String apartmentSid;
    private String apartmentName;
    private String mPinyin;
    /***
     * 服务响应的用户
     */
    private String groupUserSid;
    /***
     * 页数
     */
    private int pageIndex;
    /***
     * 响应时间  yyyy-MM-dd
     */
    private Date responseTime;
    public String getGroupUserSid() {
        return groupUserSid;
    }

    public void setGroupUserSid(String groupUserSid) {
        this.groupUserSid = groupUserSid;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Date getResponseTime() {
        return responseTime;
    }

    @Override
    public String getApartmentName() {
        return apartmentName;
    }

    @Override
    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public void setResponseTime(Date responseTime) {
        this.responseTime = responseTime;
    }
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /*    */   public ApartmentPlaceTo getPlace()
/*    */   {
/* 17 */     return this.place;
/*    */   }

    public String getmPinyin() {
        return mPinyin;
    }

    public void setmPinyin(String mPinyin) {
        this.mPinyin = mPinyin;
    }

    @Override
    public String getApartmentSid() {
        return apartmentSid;
    }

    @Override
    public void setApartmentSid(String apartmentSid) {
        this.apartmentSid = apartmentSid;
    }

    /* 19 */   public String getTelephone() { return this.telephone; }
    /*    */   public String getTelephone1() {
/* 21 */     return this.telephone1;
/*    */   }
    /* 23 */   public String getTelephone2() { return this.telephone2; }
    /*    */   public String getApartmentNo() {
/* 25 */     return this.apartmentNo;
/*    */   }
    /* 27 */   public String getStoreFlag() { return this.storeFlag; }
    /*    */   public String getHousekeepingRemrk() {
/* 29 */     return this.housekeepingRemrk;
/*    */   }
    /* 31 */   public String getRepairRemrk() { return this.repairRemrk; }
    /*    */
/*    */
/*    */   public void setPlace(ApartmentPlaceTo place)
/*    */   {
/* 11 */     this.place = place; }
    /* 11 */   public void setTelephone(String telephone) { this.telephone = telephone; }
    /* 11 */   public void setTelephone1(String telephone1) { this.telephone1 = telephone1; }
    /* 11 */   public void setTelephone2(String telephone2) { this.telephone2 = telephone2; }
    /* 11 */   public void setApartmentNo(String apartmentNo) { this.apartmentNo = apartmentNo; }
    /* 11 */   public void setStoreFlag(String storeFlag) { this.storeFlag = storeFlag; }
    /* 11 */   public void setHousekeepingRemrk(String housekeepingRemrk) { this.housekeepingRemrk = housekeepingRemrk; }
    /* 11 */   public void setRepairRemrk(String repairRemrk) { this.repairRemrk = repairRemrk; }
/* 11 */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ApartmentInfoTo that = (ApartmentInfoTo) o;

        if (pageIndex != that.pageIndex) return false;
        if (place != null ? !place.equals(that.place) : that.place != null) return false;
        if (telephone != null ? !telephone.equals(that.telephone) : that.telephone != null)
            return false;
        if (telephone1 != null ? !telephone1.equals(that.telephone1) : that.telephone1 != null)
            return false;
        if (telephone2 != null ? !telephone2.equals(that.telephone2) : that.telephone2 != null)
            return false;
        if (apartmentNo != null ? !apartmentNo.equals(that.apartmentNo) : that.apartmentNo != null)
            return false;
        if (storeFlag != null ? !storeFlag.equals(that.storeFlag) : that.storeFlag != null)
            return false;
        if (housekeepingRemrk != null ? !housekeepingRemrk.equals(that.housekeepingRemrk) : that.housekeepingRemrk != null)
            return false;
        if (repairRemrk != null ? !repairRemrk.equals(that.repairRemrk) : that.repairRemrk != null)
            return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null)
            return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (apartmentSid != null ? !apartmentSid.equals(that.apartmentSid) : that.apartmentSid != null)
            return false;
        if (groupUserSid != null ? !groupUserSid.equals(that.groupUserSid) : that.groupUserSid != null)
            return false;
        return !(responseTime != null ? !responseTime.equals(that.responseTime) : that.responseTime != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (place != null ? place.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (telephone1 != null ? telephone1.hashCode() : 0);
        result = 31 * result + (telephone2 != null ? telephone2.hashCode() : 0);
        result = 31 * result + (apartmentNo != null ? apartmentNo.hashCode() : 0);
        result = 31 * result + (storeFlag != null ? storeFlag.hashCode() : 0);
        result = 31 * result + (housekeepingRemrk != null ? housekeepingRemrk.hashCode() : 0);
        result = 31 * result + (repairRemrk != null ? repairRemrk.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (apartmentSid != null ? apartmentSid.hashCode() : 0);
        result = 31 * result + (groupUserSid != null ? groupUserSid.hashCode() : 0);
        result = 31 * result + pageIndex;
        result = 31 * result + (responseTime != null ? responseTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ApartmentInfoTo{" +
                "apartmentName=" + apartmentName +
                ", telephone='" + telephone + '\'' +
                ", telephone1='" + telephone1 + '\'' +
                ", telephone2='" + telephone2 + '\'' +
                ", apartmentNo='" + apartmentNo + '\'' +
                ", storeFlag='" + storeFlag + '\'' +
                ", housekeepingRemrk='" + housekeepingRemrk + '\'' +
                ", repairRemrk='" + repairRemrk + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", apartmentSid='" + apartmentSid + '\'' +
                ", groupUserSid='" + groupUserSid + '\'' +
                ", pageIndex=" + pageIndex +
                ", responseTime=" + responseTime +
                '}';
    }
    /*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.apartment.ApartmentInfoTo
 * JD-Core Version:    0.6.2
 */