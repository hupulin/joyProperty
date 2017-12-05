/*    */ package com.jinyi.ihome.module.visitor;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class VisitorCardParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String sid;
/*    */   private String apartName;
/*    */   private String apartAddress;
/*    */   private String ownerSid;
/*    */   private String ownerNo;
/*    */   private String ownerPhone;
/*    */   private String visitorName;
/*    */   private String visitorCarNo;
/*    */   private String visitorImage;
/*    */   private String endDate;
/*    */   private String visitTime;
/*    */   private String leaveTime;
/*    */ 
/*    */   public String getSid()
/*    */   {
/* 20 */     return this.sid;
/*    */   }
/*    */ 
/*    */   public String getApartName()
/*    */   {
/* 25 */     return this.apartName;
/*    */   }
/*    */ 
/*    */   public String getApartAddress()
/*    */   {
/* 30 */     return this.apartAddress;
/*    */   }
/*    */ 
/*    */   public String getOwnerSid()
/*    */   {
/* 35 */     return this.ownerSid;
/*    */   }
/*    */ 
/*    */   public String getOwnerNo()
/*    */   {
/* 40 */     return this.ownerNo;
/*    */   }
/*    */ 
/*    */   public String getOwnerPhone()
/*    */   {
/* 45 */     return this.ownerPhone;
/*    */   }
/*    */ 
/*    */   public String getVisitorName()
/*    */   {
/* 50 */     return this.visitorName;
/*    */   }
/*    */ 
/*    */   public String getVisitorCarNo()
/*    */   {
/* 55 */     return this.visitorCarNo;
/*    */   }
/*    */ 
/*    */   public String getVisitorImage()
/*    */   {
/* 60 */     return this.visitorImage;
/*    */   }
/*    */ 
/*    */   public String getEndDate()
/*    */   {
/* 65 */     return this.endDate;
/*    */   }
/*    */ 
/*    */   public String getVisitTime()
/*    */   {
/* 70 */     return this.visitTime;
/*    */   }
/*    */ 
/*    */   public String getLeaveTime() {
/* 74 */     return this.leaveTime;
/*    */   }
/*    */ 
/*    */   public void setSid(String sid)
/*    */   {
/* 12 */     this.sid = sid; } 
/* 12 */   public void setApartName(String apartName) { this.apartName = apartName; } 
/* 12 */   public void setApartAddress(String apartAddress) { this.apartAddress = apartAddress; } 
/* 12 */   public void setOwnerSid(String ownerSid) { this.ownerSid = ownerSid; } 
/* 12 */   public void setOwnerNo(String ownerNo) { this.ownerNo = ownerNo; } 
/* 12 */   public void setOwnerPhone(String ownerPhone) { this.ownerPhone = ownerPhone; } 
/* 12 */   public void setVisitorName(String visitorName) { this.visitorName = visitorName; } 
/* 12 */   public void setVisitorCarNo(String visitorCarNo) { this.visitorCarNo = visitorCarNo; } 
/* 12 */   public void setVisitorImage(String visitorImage) { this.visitorImage = visitorImage; } 
/* 12 */   public void setEndDate(String endDate) { this.endDate = endDate; } 
/* 12 */   public void setVisitTime(String visitTime) { this.visitTime = visitTime; } 
/* 12 */   public void setLeaveTime(String leaveTime) { this.leaveTime = leaveTime; } 
/* 12 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof VisitorCardParam)) return false; VisitorCardParam other = (VisitorCardParam)o; if (!other.canEqual(this)) return false; Object this$sid = getSid(); Object other$sid = other.getSid(); if (this$sid == null ? other$sid != null : !this$sid.equals(other$sid)) return false; Object this$apartName = getApartName(); Object other$apartName = other.getApartName(); if (this$apartName == null ? other$apartName != null : !this$apartName.equals(other$apartName)) return false; Object this$apartAddress = getApartAddress(); Object other$apartAddress = other.getApartAddress(); if (this$apartAddress == null ? other$apartAddress != null : !this$apartAddress.equals(other$apartAddress)) return false; Object this$ownerSid = getOwnerSid(); Object other$ownerSid = other.getOwnerSid(); if (this$ownerSid == null ? other$ownerSid != null : !this$ownerSid.equals(other$ownerSid)) return false; Object this$ownerNo = getOwnerNo(); Object other$ownerNo = other.getOwnerNo(); if (this$ownerNo == null ? other$ownerNo != null : !this$ownerNo.equals(other$ownerNo)) return false; Object this$ownerPhone = getOwnerPhone(); Object other$ownerPhone = other.getOwnerPhone(); if (this$ownerPhone == null ? other$ownerPhone != null : !this$ownerPhone.equals(other$ownerPhone)) return false; Object this$visitorName = getVisitorName(); Object other$visitorName = other.getVisitorName(); if (this$visitorName == null ? other$visitorName != null : !this$visitorName.equals(other$visitorName)) return false; Object this$visitorCarNo = getVisitorCarNo(); Object other$visitorCarNo = other.getVisitorCarNo(); if (this$visitorCarNo == null ? other$visitorCarNo != null : !this$visitorCarNo.equals(other$visitorCarNo)) return false; Object this$visitorImage = getVisitorImage(); Object other$visitorImage = other.getVisitorImage(); if (this$visitorImage == null ? other$visitorImage != null : !this$visitorImage.equals(other$visitorImage)) return false; Object this$endDate = getEndDate(); Object other$endDate = other.getEndDate(); if (this$endDate == null ? other$endDate != null : !this$endDate.equals(other$endDate)) return false; Object this$visitTime = getVisitTime(); Object other$visitTime = other.getVisitTime(); if (this$visitTime == null ? other$visitTime != null : !this$visitTime.equals(other$visitTime)) return false; Object this$leaveTime = getLeaveTime(); Object other$leaveTime = other.getLeaveTime(); return this$leaveTime == null ? other$leaveTime == null : this$leaveTime.equals(other$leaveTime); } 
/* 12 */   protected boolean canEqual(Object other) { return other instanceof VisitorCardParam; } 
/* 12 */   public int hashCode() { int PRIME = 59; int result = 1; Object $sid = getSid(); result = result * 59 + ($sid == null ? 0 : $sid.hashCode()); Object $apartName = getApartName(); result = result * 59 + ($apartName == null ? 0 : $apartName.hashCode()); Object $apartAddress = getApartAddress(); result = result * 59 + ($apartAddress == null ? 0 : $apartAddress.hashCode()); Object $ownerSid = getOwnerSid(); result = result * 59 + ($ownerSid == null ? 0 : $ownerSid.hashCode()); Object $ownerNo = getOwnerNo(); result = result * 59 + ($ownerNo == null ? 0 : $ownerNo.hashCode()); Object $ownerPhone = getOwnerPhone(); result = result * 59 + ($ownerPhone == null ? 0 : $ownerPhone.hashCode()); Object $visitorName = getVisitorName(); result = result * 59 + ($visitorName == null ? 0 : $visitorName.hashCode()); Object $visitorCarNo = getVisitorCarNo(); result = result * 59 + ($visitorCarNo == null ? 0 : $visitorCarNo.hashCode()); Object $visitorImage = getVisitorImage(); result = result * 59 + ($visitorImage == null ? 0 : $visitorImage.hashCode()); Object $endDate = getEndDate(); result = result * 59 + ($endDate == null ? 0 : $endDate.hashCode()); Object $visitTime = getVisitTime(); result = result * 59 + ($visitTime == null ? 0 : $visitTime.hashCode()); Object $leaveTime = getLeaveTime(); result = result * 59 + ($leaveTime == null ? 0 : $leaveTime.hashCode()); return result; } 
/* 12 */   public String toString() { return "VisitorCardParam(sid=" + getSid() + ", apartName=" + getApartName() + ", apartAddress=" + getApartAddress() + ", ownerSid=" + getOwnerSid() + ", ownerNo=" + getOwnerNo() + ", ownerPhone=" + getOwnerPhone() + ", visitorName=" + getVisitorName() + ", visitorCarNo=" + getVisitorCarNo() + ", visitorImage=" + getVisitorImage() + ", endDate=" + getEndDate() + ", visitTime=" + getVisitTime() + ", leaveTime=" + getLeaveTime() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.visitor.VisitorCardParam
 * JD-Core Version:    0.6.2
 */