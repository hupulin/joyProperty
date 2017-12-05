/*     */ package com.jinyi.ihome.module.visitor;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class VisitorCardTo
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String cardSid;
/*     */   private String cardNo;
/*     */   private Integer visitorQty;
/*     */   private String endDate;
/*     */   private Integer passCount;
/*     */   private Date createdOn;
/*     */   private Date modifiedOn;
/*     */   private String apartmentSid;
/*     */   private String apartName;
/*     */   private String apartAddress;
/*     */   private String ownerSid;
/*     */   private String ownerNo;
/*     */   private String ownerPhone;
/*     */   private String visitorName;
/*     */   private String visitorCarNo;
/*     */   private String visitorImage;
/*     */   private String imageData;
/*     */   private String visitTime;
/*     */   private String leaveTime;

    @Override
    public String toString() {
        return "VisitorCardTo{" +
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
                ", ownerName='" + ownerName + '\'' +
                ", ownerCarPlace='" + ownerCarPlace + '\'' +
                '}';
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerCarPlace() {
        return ownerCarPlace;
    }

    public void setOwnerCarPlace(String ownerCarPlace) {
        this.ownerCarPlace = ownerCarPlace;
    }

    /*     */   private String ownerName ;// 被访业主姓名
            private String  ownerCarPlace;// 被访业主车位
/*     */   public String getCardSid()
/*     */   {
/*  22 */     return this.cardSid;
/*     */   }
/*     */ 
/*     */   public String getCardNo() {
/*  26 */     return this.cardNo;
/*     */   }
/*     */ 
/*     */   public Integer getVisitorQty() {
/*  30 */     return this.visitorQty;
/*     */   }
/*     */ 
/*     */   public String getEndDate()
/*     */   {
/*  35 */     return this.endDate;
/*     */   }
/*     */ 
/*     */   public Integer getPassCount() {
/*  39 */     return this.passCount;
/*     */   }
/*     */ 
/*     */   public Date getCreatedOn() {
/*  43 */     return this.createdOn;
/*     */   }
/*     */ 
/*     */   public Date getModifiedOn() {
/*  47 */     return this.modifiedOn;
/*     */   }
/*     */ 
/*     */   public String getApartmentSid()
/*     */   {
/*  53 */     return this.apartmentSid;
/*     */   }
/*     */ 
/*     */   public String getApartName()
/*     */   {
/*  58 */     return this.apartName;
/*     */   }
/*     */ 
/*     */   public String getApartAddress()
/*     */   {
/*  63 */     return this.apartAddress;
/*     */   }
/*     */ 
/*     */   public String getOwnerSid()
/*     */   {
/*  68 */     return this.ownerSid;
/*     */   }
/*     */ 
/*     */   public String getOwnerNo()
/*     */   {
/*  73 */     return this.ownerNo;
/*     */   }
/*     */ 
/*     */   public String getOwnerPhone()
/*     */   {
/*  78 */     return this.ownerPhone;
/*     */   }
/*     */ 
/*     */   public String getVisitorName()
/*     */   {
/*  83 */     return this.visitorName;
/*     */   }
/*     */ 
/*     */   public String getVisitorCarNo()
/*     */   {
/*  89 */     return this.visitorCarNo;
/*     */   }
/*     */ 
/*     */   public String getVisitorImage()
/*     */   {
/*  94 */     return this.visitorImage;
/*     */   }
/*     */ 
/*     */   public String getImageData()
/*     */   {
/*  99 */     return this.imageData;
/*     */   }
/*     */ 
/*     */   public String getVisitTime()
/*     */   {
/* 104 */     return this.visitTime;
/*     */   }
/*     */ 
/*     */   public String getLeaveTime() {
/* 108 */     return this.leaveTime;
/*     */   }
/*     */ 
/*     */   public void setCardSid(String cardSid)
/*     */   {
/*  14 */     this.cardSid = cardSid; } 
/*  14 */   public void setCardNo(String cardNo) { this.cardNo = cardNo; } 
/*  14 */   public void setVisitorQty(Integer visitorQty) { this.visitorQty = visitorQty; } 
/*  14 */   public void setEndDate(String endDate) { this.endDate = endDate; } 
/*  14 */   public void setPassCount(Integer passCount) { this.passCount = passCount; } 
/*  14 */   public void setCreatedOn(Date createdOn) { this.createdOn = createdOn; } 
/*  14 */   public void setModifiedOn(Date modifiedOn) { this.modifiedOn = modifiedOn; } 
/*  14 */   public void setApartmentSid(String apartmentSid) { this.apartmentSid = apartmentSid; } 
/*  14 */   public void setApartName(String apartName) { this.apartName = apartName; } 
/*  14 */   public void setApartAddress(String apartAddress) { this.apartAddress = apartAddress; } 
/*  14 */   public void setOwnerSid(String ownerSid) { this.ownerSid = ownerSid; } 
/*  14 */   public void setOwnerNo(String ownerNo) { this.ownerNo = ownerNo; } 
/*  14 */   public void setOwnerPhone(String ownerPhone) { this.ownerPhone = ownerPhone; } 
/*  14 */   public void setVisitorName(String visitorName) { this.visitorName = visitorName; } 
/*  14 */   public void setVisitorCarNo(String visitorCarNo) { this.visitorCarNo = visitorCarNo; } 
/*  14 */   public void setVisitorImage(String visitorImage) { this.visitorImage = visitorImage; } 
/*  14 */   public void setImageData(String imageData) { this.imageData = imageData; } 
/*  14 */   public void setVisitTime(String visitTime) { this.visitTime = visitTime; } 
/*  14 */   public void setLeaveTime(String leaveTime) { this.leaveTime = leaveTime; } 
/*  14 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof VisitorCardTo)) return false; VisitorCardTo other = (VisitorCardTo)o; if (!other.canEqual(this)) return false; Object this$cardSid = getCardSid(); Object other$cardSid = other.getCardSid(); if (this$cardSid == null ? other$cardSid != null : !this$cardSid.equals(other$cardSid)) return false; Object this$cardNo = getCardNo(); Object other$cardNo = other.getCardNo(); if (this$cardNo == null ? other$cardNo != null : !this$cardNo.equals(other$cardNo)) return false; Object this$visitorQty = getVisitorQty(); Object other$visitorQty = other.getVisitorQty(); if (this$visitorQty == null ? other$visitorQty != null : !this$visitorQty.equals(other$visitorQty)) return false; Object this$endDate = getEndDate(); Object other$endDate = other.getEndDate(); if (this$endDate == null ? other$endDate != null : !this$endDate.equals(other$endDate)) return false; Object this$passCount = getPassCount(); Object other$passCount = other.getPassCount(); if (this$passCount == null ? other$passCount != null : !this$passCount.equals(other$passCount)) return false; Object this$createdOn = getCreatedOn(); Object other$createdOn = other.getCreatedOn(); if (this$createdOn == null ? other$createdOn != null : !this$createdOn.equals(other$createdOn)) return false; Object this$modifiedOn = getModifiedOn(); Object other$modifiedOn = other.getModifiedOn(); if (this$modifiedOn == null ? other$modifiedOn != null : !this$modifiedOn.equals(other$modifiedOn)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); if (this$apartmentSid == null ? other$apartmentSid != null : !this$apartmentSid.equals(other$apartmentSid)) return false; Object this$apartName = getApartName(); Object other$apartName = other.getApartName(); if (this$apartName == null ? other$apartName != null : !this$apartName.equals(other$apartName)) return false; Object this$apartAddress = getApartAddress(); Object other$apartAddress = other.getApartAddress(); if (this$apartAddress == null ? other$apartAddress != null : !this$apartAddress.equals(other$apartAddress)) return false; Object this$ownerSid = getOwnerSid(); Object other$ownerSid = other.getOwnerSid(); if (this$ownerSid == null ? other$ownerSid != null : !this$ownerSid.equals(other$ownerSid)) return false; Object this$ownerNo = getOwnerNo(); Object other$ownerNo = other.getOwnerNo(); if (this$ownerNo == null ? other$ownerNo != null : !this$ownerNo.equals(other$ownerNo)) return false; Object this$ownerPhone = getOwnerPhone(); Object other$ownerPhone = other.getOwnerPhone(); if (this$ownerPhone == null ? other$ownerPhone != null : !this$ownerPhone.equals(other$ownerPhone)) return false; Object this$visitorName = getVisitorName(); Object other$visitorName = other.getVisitorName(); if (this$visitorName == null ? other$visitorName != null : !this$visitorName.equals(other$visitorName)) return false; Object this$visitorCarNo = getVisitorCarNo(); Object other$visitorCarNo = other.getVisitorCarNo(); if (this$visitorCarNo == null ? other$visitorCarNo != null : !this$visitorCarNo.equals(other$visitorCarNo)) return false; Object this$visitorImage = getVisitorImage(); Object other$visitorImage = other.getVisitorImage(); if (this$visitorImage == null ? other$visitorImage != null : !this$visitorImage.equals(other$visitorImage)) return false; Object this$imageData = getImageData(); Object other$imageData = other.getImageData(); if (this$imageData == null ? other$imageData != null : !this$imageData.equals(other$imageData)) return false; Object this$visitTime = getVisitTime(); Object other$visitTime = other.getVisitTime(); if (this$visitTime == null ? other$visitTime != null : !this$visitTime.equals(other$visitTime)) return false; Object this$leaveTime = getLeaveTime(); Object other$leaveTime = other.getLeaveTime(); return this$leaveTime == null ? other$leaveTime == null : this$leaveTime.equals(other$leaveTime); } 
/*  14 */   protected boolean canEqual(Object other) { return other instanceof VisitorCardTo; } 
/*  14 */   public int hashCode() { int PRIME = 59; int result = 1; Object $cardSid = getCardSid(); result = result * 59 + ($cardSid == null ? 0 : $cardSid.hashCode()); Object $cardNo = getCardNo(); result = result * 59 + ($cardNo == null ? 0 : $cardNo.hashCode()); Object $visitorQty = getVisitorQty(); result = result * 59 + ($visitorQty == null ? 0 : $visitorQty.hashCode()); Object $endDate = getEndDate(); result = result * 59 + ($endDate == null ? 0 : $endDate.hashCode()); Object $passCount = getPassCount(); result = result * 59 + ($passCount == null ? 0 : $passCount.hashCode()); Object $createdOn = getCreatedOn(); result = result * 59 + ($createdOn == null ? 0 : $createdOn.hashCode()); Object $modifiedOn = getModifiedOn(); result = result * 59 + ($modifiedOn == null ? 0 : $modifiedOn.hashCode()); Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); Object $apartName = getApartName(); result = result * 59 + ($apartName == null ? 0 : $apartName.hashCode()); Object $apartAddress = getApartAddress(); result = result * 59 + ($apartAddress == null ? 0 : $apartAddress.hashCode()); Object $ownerSid = getOwnerSid(); result = result * 59 + ($ownerSid == null ? 0 : $ownerSid.hashCode()); Object $ownerNo = getOwnerNo(); result = result * 59 + ($ownerNo == null ? 0 : $ownerNo.hashCode()); Object $ownerPhone = getOwnerPhone(); result = result * 59 + ($ownerPhone == null ? 0 : $ownerPhone.hashCode()); Object $visitorName = getVisitorName(); result = result * 59 + ($visitorName == null ? 0 : $visitorName.hashCode()); Object $visitorCarNo = getVisitorCarNo(); result = result * 59 + ($visitorCarNo == null ? 0 : $visitorCarNo.hashCode()); Object $visitorImage = getVisitorImage(); result = result * 59 + ($visitorImage == null ? 0 : $visitorImage.hashCode()); Object $imageData = getImageData(); result = result * 59 + ($imageData == null ? 0 : $imageData.hashCode()); Object $visitTime = getVisitTime(); result = result * 59 + ($visitTime == null ? 0 : $visitTime.hashCode()); Object $leaveTime = getLeaveTime(); result = result * 59 + ($leaveTime == null ? 0 : $leaveTime.hashCode()); return result; } 
/*     */
/*     */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.visitor.VisitorCardTo
 * JD-Core Version:    0.6.2
 */