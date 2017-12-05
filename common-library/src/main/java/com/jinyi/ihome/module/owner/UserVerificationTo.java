/*    */ package com.jinyi.ihome.module.owner;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class UserVerificationTo
/*    */   implements Serializable
/*    */ {
/*    */   private String apartmentSid;
/*    */   private String ownerSid;
/*    */   private String roomTag;
/*    */   private String verificationTag;
/*    */ 
/*    */   public String getApartmentSid()
/*    */   {
/* 14 */     return this.apartmentSid;
/*    */   }
/* 16 */   public String getOwnerSid() { return this.ownerSid; } 
/*    */   public String getRoomTag() {
/* 18 */     return this.roomTag;
/*    */   }
/* 20 */   public String getVerificationTag() { return this.verificationTag; }
/*    */ 
/*    */ 
/*    */   public void setApartmentSid(String apartmentSid)
/*    */   {
/* 11 */     this.apartmentSid = apartmentSid; } 
/* 11 */   public void setOwnerSid(String ownerSid) { this.ownerSid = ownerSid; } 
/* 11 */   public void setRoomTag(String roomTag) { this.roomTag = roomTag; } 
/* 11 */   public void setVerificationTag(String verificationTag) { this.verificationTag = verificationTag; } 
/* 11 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof UserVerificationTo)) return false; UserVerificationTo other = (UserVerificationTo)o; if (!other.canEqual(this)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); if (this$apartmentSid == null ? other$apartmentSid != null : !this$apartmentSid.equals(other$apartmentSid)) return false; Object this$ownerSid = getOwnerSid(); Object other$ownerSid = other.getOwnerSid(); if (this$ownerSid == null ? other$ownerSid != null : !this$ownerSid.equals(other$ownerSid)) return false; Object this$roomTag = getRoomTag(); Object other$roomTag = other.getRoomTag(); if (this$roomTag == null ? other$roomTag != null : !this$roomTag.equals(other$roomTag)) return false; Object this$verificationTag = getVerificationTag(); Object other$verificationTag = other.getVerificationTag(); return this$verificationTag == null ? other$verificationTag == null : this$verificationTag.equals(other$verificationTag); } 
/* 11 */   protected boolean canEqual(Object other) { return other instanceof UserVerificationTo; } 
/* 11 */   public int hashCode() { int PRIME = 59; int result = 1; Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); Object $ownerSid = getOwnerSid(); result = result * 59 + ($ownerSid == null ? 0 : $ownerSid.hashCode()); Object $roomTag = getRoomTag(); result = result * 59 + ($roomTag == null ? 0 : $roomTag.hashCode()); Object $verificationTag = getVerificationTag(); result = result * 59 + ($verificationTag == null ? 0 : $verificationTag.hashCode()); return result; } 
/* 11 */   public String toString() { return "UserVerificationTo(apartmentSid=" + getApartmentSid() + ", ownerSid=" + getOwnerSid() + ", roomTag=" + getRoomTag() + ", verificationTag=" + getVerificationTag() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.owner.UserVerificationTo
 * JD-Core Version:    0.6.2
 */