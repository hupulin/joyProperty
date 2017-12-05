/*    */ package com.jinyi.ihome.module.web;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class HomeActivityDataTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String joinSid;
/*    */   private String apartmentRoom;
/*    */   private String ownerName;
/*    */   private Integer ownerAge;
/*    */   private String ownerPhone;
/*    */   private String ownerPhoneType;
/*    */   private String ownerWork;
/*    */   private String ownerSuggestion;
/*    */   private Date createdOn;
/*    */ 
/*    */   public String getJoinSid()
/*    */   {
/* 19 */     return this.joinSid;
/*    */   }
/*    */ 
/*    */   public String getApartmentRoom()
/*    */   {
/* 24 */     return this.apartmentRoom;
/*    */   }
/*    */ 
/*    */   public String getOwnerName()
/*    */   {
/* 29 */     return this.ownerName;
/*    */   }
/*    */ 
/*    */   public Integer getOwnerAge()
/*    */   {
/* 34 */     return this.ownerAge;
/*    */   }
/*    */ 
/*    */   public String getOwnerPhone()
/*    */   {
/* 39 */     return this.ownerPhone;
/*    */   }
/*    */ 
/*    */   public String getOwnerPhoneType()
/*    */   {
/* 44 */     return this.ownerPhoneType;
/*    */   }
/*    */ 
/*    */   public String getOwnerWork()
/*    */   {
/* 49 */     return this.ownerWork;
/*    */   }
/*    */ 
/*    */   public String getOwnerSuggestion()
/*    */   {
/* 54 */     return this.ownerSuggestion;
/*    */   }
/*    */ 
/*    */   public Date getCreatedOn()
/*    */   {
/* 59 */     return this.createdOn;
/*    */   }
/*    */ 
/*    */   public void setJoinSid(String joinSid)
/*    */   {
/* 11 */     this.joinSid = joinSid; } 
/* 11 */   public void setApartmentRoom(String apartmentRoom) { this.apartmentRoom = apartmentRoom; } 
/* 11 */   public void setOwnerName(String ownerName) { this.ownerName = ownerName; } 
/* 11 */   public void setOwnerAge(Integer ownerAge) { this.ownerAge = ownerAge; } 
/* 11 */   public void setOwnerPhone(String ownerPhone) { this.ownerPhone = ownerPhone; } 
/* 11 */   public void setOwnerPhoneType(String ownerPhoneType) { this.ownerPhoneType = ownerPhoneType; } 
/* 11 */   public void setOwnerWork(String ownerWork) { this.ownerWork = ownerWork; } 
/* 11 */   public void setOwnerSuggestion(String ownerSuggestion) { this.ownerSuggestion = ownerSuggestion; } 
/* 11 */   public void setCreatedOn(Date createdOn) { this.createdOn = createdOn; } 
/* 11 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof HomeActivityDataTo)) return false; HomeActivityDataTo other = (HomeActivityDataTo)o; if (!other.canEqual(this)) return false; Object this$joinSid = getJoinSid(); Object other$joinSid = other.getJoinSid(); if (this$joinSid == null ? other$joinSid != null : !this$joinSid.equals(other$joinSid)) return false; Object this$apartmentRoom = getApartmentRoom(); Object other$apartmentRoom = other.getApartmentRoom(); if (this$apartmentRoom == null ? other$apartmentRoom != null : !this$apartmentRoom.equals(other$apartmentRoom)) return false; Object this$ownerName = getOwnerName(); Object other$ownerName = other.getOwnerName(); if (this$ownerName == null ? other$ownerName != null : !this$ownerName.equals(other$ownerName)) return false; Object this$ownerAge = getOwnerAge(); Object other$ownerAge = other.getOwnerAge(); if (this$ownerAge == null ? other$ownerAge != null : !this$ownerAge.equals(other$ownerAge)) return false; Object this$ownerPhone = getOwnerPhone(); Object other$ownerPhone = other.getOwnerPhone(); if (this$ownerPhone == null ? other$ownerPhone != null : !this$ownerPhone.equals(other$ownerPhone)) return false; Object this$ownerPhoneType = getOwnerPhoneType(); Object other$ownerPhoneType = other.getOwnerPhoneType(); if (this$ownerPhoneType == null ? other$ownerPhoneType != null : !this$ownerPhoneType.equals(other$ownerPhoneType)) return false; Object this$ownerWork = getOwnerWork(); Object other$ownerWork = other.getOwnerWork(); if (this$ownerWork == null ? other$ownerWork != null : !this$ownerWork.equals(other$ownerWork)) return false; Object this$ownerSuggestion = getOwnerSuggestion(); Object other$ownerSuggestion = other.getOwnerSuggestion(); if (this$ownerSuggestion == null ? other$ownerSuggestion != null : !this$ownerSuggestion.equals(other$ownerSuggestion)) return false; Object this$createdOn = getCreatedOn(); Object other$createdOn = other.getCreatedOn(); return this$createdOn == null ? other$createdOn == null : this$createdOn.equals(other$createdOn); } 
/* 11 */   protected boolean canEqual(Object other) { return other instanceof HomeActivityDataTo; } 
/* 11 */   public int hashCode() { int PRIME = 59; int result = 1; Object $joinSid = getJoinSid(); result = result * 59 + ($joinSid == null ? 0 : $joinSid.hashCode()); Object $apartmentRoom = getApartmentRoom(); result = result * 59 + ($apartmentRoom == null ? 0 : $apartmentRoom.hashCode()); Object $ownerName = getOwnerName(); result = result * 59 + ($ownerName == null ? 0 : $ownerName.hashCode()); Object $ownerAge = getOwnerAge(); result = result * 59 + ($ownerAge == null ? 0 : $ownerAge.hashCode()); Object $ownerPhone = getOwnerPhone(); result = result * 59 + ($ownerPhone == null ? 0 : $ownerPhone.hashCode()); Object $ownerPhoneType = getOwnerPhoneType(); result = result * 59 + ($ownerPhoneType == null ? 0 : $ownerPhoneType.hashCode()); Object $ownerWork = getOwnerWork(); result = result * 59 + ($ownerWork == null ? 0 : $ownerWork.hashCode()); Object $ownerSuggestion = getOwnerSuggestion(); result = result * 59 + ($ownerSuggestion == null ? 0 : $ownerSuggestion.hashCode()); Object $createdOn = getCreatedOn(); result = result * 59 + ($createdOn == null ? 0 : $createdOn.hashCode()); return result; } 
/* 11 */   public String toString() { return "HomeActivityDataTo(joinSid=" + getJoinSid() + ", apartmentRoom=" + getApartmentRoom() + ", ownerName=" + getOwnerName() + ", ownerAge=" + getOwnerAge() + ", ownerPhone=" + getOwnerPhone() + ", ownerPhoneType=" + getOwnerPhoneType() + ", ownerWork=" + getOwnerWork() + ", ownerSuggestion=" + getOwnerSuggestion() + ", createdOn=" + getCreatedOn() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.web.HomeActivityDataTo
 * JD-Core Version:    0.6.2
 */