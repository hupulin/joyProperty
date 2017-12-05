/*    */ package com.jinyi.ihome.module.owner;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class UserVerificationParam
/*    */   implements Serializable
/*    */ {
/*    */   private String apartmentSid;
/*    */   private String ownerSid;
/*    */   private String roomTag;
/*    */   private String name;
/*    */   private String idString;
/*    */ 
/*    */   public String getApartmentSid()
/*    */   {
/* 14 */     return this.apartmentSid;
/*    */   }
/* 16 */   public String getOwnerSid() { return this.ownerSid; }
/*    */ 
/*    */   public String getRoomTag() {
/* 19 */     return this.roomTag;
/*    */   }
/*    */   public String getName() {
/* 22 */     return this.name;
/*    */   }
/*    */ 
/*    */   public String getIdString()
/*    */   {
/* 27 */     return this.idString;
/*    */   }
/*    */ 
/*    */   public void setApartmentSid(String apartmentSid)
/*    */   {
/* 11 */     this.apartmentSid = apartmentSid; } 
/* 11 */   public void setOwnerSid(String ownerSid) { this.ownerSid = ownerSid; } 
/* 11 */   public void setRoomTag(String roomTag) { this.roomTag = roomTag; } 
/* 11 */   public void setName(String name) { this.name = name; } 
/* 11 */   public void setIdString(String idString) { this.idString = idString; } 
/* 11 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof UserVerificationParam)) return false; UserVerificationParam other = (UserVerificationParam)o; if (!other.canEqual(this)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); if (this$apartmentSid == null ? other$apartmentSid != null : !this$apartmentSid.equals(other$apartmentSid)) return false; Object this$ownerSid = getOwnerSid(); Object other$ownerSid = other.getOwnerSid(); if (this$ownerSid == null ? other$ownerSid != null : !this$ownerSid.equals(other$ownerSid)) return false; Object this$roomTag = getRoomTag(); Object other$roomTag = other.getRoomTag(); if (this$roomTag == null ? other$roomTag != null : !this$roomTag.equals(other$roomTag)) return false; Object this$name = getName(); Object other$name = other.getName(); if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false; Object this$idString = getIdString(); Object other$idString = other.getIdString(); return this$idString == null ? other$idString == null : this$idString.equals(other$idString); } 
/* 11 */   protected boolean canEqual(Object other) { return other instanceof UserVerificationParam; } 
/* 11 */   public int hashCode() { int PRIME = 59; int result = 1; Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); Object $ownerSid = getOwnerSid(); result = result * 59 + ($ownerSid == null ? 0 : $ownerSid.hashCode()); Object $roomTag = getRoomTag(); result = result * 59 + ($roomTag == null ? 0 : $roomTag.hashCode()); Object $name = getName(); result = result * 59 + ($name == null ? 0 : $name.hashCode()); Object $idString = getIdString(); result = result * 59 + ($idString == null ? 0 : $idString.hashCode()); return result; } 
/* 11 */   public String toString() { return "UserVerificationParam(apartmentSid=" + getApartmentSid() + ", ownerSid=" + getOwnerSid() + ", roomTag=" + getRoomTag() + ", name=" + getName() + ", idString=" + getIdString() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.owner.UserVerificationParam
 * JD-Core Version:    0.6.2
 */