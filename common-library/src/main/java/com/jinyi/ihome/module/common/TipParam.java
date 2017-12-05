/*    */ package com.jinyi.ihome.module.common;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class TipParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String apartmentSid;
/*    */   private String ownerSid;
/*    */   private Date neighborPostDate;
/*    */   private Date noticeDate;
/*    */   private Date purchaseDate;
/*    */   private Date messageDate;
/*    */ 
/*    */   public String getApartmentSid()
/*    */   {
/* 15 */     return this.apartmentSid;
/*    */   }
/* 17 */   public String getOwnerSid() { return this.ownerSid; } 
/*    */   public Date getNeighborPostDate() {
/* 19 */     return this.neighborPostDate;
/*    */   }
/* 21 */   public Date getNoticeDate() { return this.noticeDate; } 
/*    */   public Date getPurchaseDate() {
/* 23 */     return this.purchaseDate;
/*    */   }
/* 25 */   public Date getMessageDate() { return this.messageDate; }
/*    */ 
/*    */ 
/*    */   public void setApartmentSid(String apartmentSid)
/*    */   {
/* 11 */     this.apartmentSid = apartmentSid; } 
/* 11 */   public void setOwnerSid(String ownerSid) { this.ownerSid = ownerSid; } 
/* 11 */   public void setNeighborPostDate(Date neighborPostDate) { this.neighborPostDate = neighborPostDate; } 
/* 11 */   public void setNoticeDate(Date noticeDate) { this.noticeDate = noticeDate; } 
/* 11 */   public void setPurchaseDate(Date purchaseDate) { this.purchaseDate = purchaseDate; } 
/* 11 */   public void setMessageDate(Date messageDate) { this.messageDate = messageDate; } 
/* 11 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof TipParam)) return false; TipParam other = (TipParam)o; if (!other.canEqual(this)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); if (this$apartmentSid == null ? other$apartmentSid != null : !this$apartmentSid.equals(other$apartmentSid)) return false; Object this$ownerSid = getOwnerSid(); Object other$ownerSid = other.getOwnerSid(); if (this$ownerSid == null ? other$ownerSid != null : !this$ownerSid.equals(other$ownerSid)) return false; Object this$neighborPostDate = getNeighborPostDate(); Object other$neighborPostDate = other.getNeighborPostDate(); if (this$neighborPostDate == null ? other$neighborPostDate != null : !this$neighborPostDate.equals(other$neighborPostDate)) return false; Object this$noticeDate = getNoticeDate(); Object other$noticeDate = other.getNoticeDate(); if (this$noticeDate == null ? other$noticeDate != null : !this$noticeDate.equals(other$noticeDate)) return false; Object this$purchaseDate = getPurchaseDate(); Object other$purchaseDate = other.getPurchaseDate(); if (this$purchaseDate == null ? other$purchaseDate != null : !this$purchaseDate.equals(other$purchaseDate)) return false; Object this$messageDate = getMessageDate(); Object other$messageDate = other.getMessageDate(); return this$messageDate == null ? other$messageDate == null : this$messageDate.equals(other$messageDate); } 
/* 11 */   protected boolean canEqual(Object other) { return other instanceof TipParam; } 
/* 11 */   public int hashCode() { int PRIME = 59; int result = 1; Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); Object $ownerSid = getOwnerSid(); result = result * 59 + ($ownerSid == null ? 0 : $ownerSid.hashCode()); Object $neighborPostDate = getNeighborPostDate(); result = result * 59 + ($neighborPostDate == null ? 0 : $neighborPostDate.hashCode()); Object $noticeDate = getNoticeDate(); result = result * 59 + ($noticeDate == null ? 0 : $noticeDate.hashCode()); Object $purchaseDate = getPurchaseDate(); result = result * 59 + ($purchaseDate == null ? 0 : $purchaseDate.hashCode()); Object $messageDate = getMessageDate(); result = result * 59 + ($messageDate == null ? 0 : $messageDate.hashCode()); return result; } 
/* 11 */   public String toString() { return "TipParam(apartmentSid=" + getApartmentSid() + ", ownerSid=" + getOwnerSid() + ", neighborPostDate=" + getNeighborPostDate() + ", noticeDate=" + getNoticeDate() + ", purchaseDate=" + getPurchaseDate() + ", messageDate=" + getMessageDate() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.common.TipParam
 * JD-Core Version:    0.6.2
 */