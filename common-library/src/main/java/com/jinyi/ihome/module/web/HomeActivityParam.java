/*    */ package com.jinyi.ihome.module.web;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class HomeActivityParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String apartmentRoom;
/*    */   private String ownerName;
/*    */   private Integer ownerAge;
/*    */   private String ownerPhone;
/*    */   private String ownerPhoneType;
/*    */   private String ownerWork;
/*    */   private String ownerSuggestion;
/*    */ 
/*    */   public String getApartmentRoom()
/*    */   {
/* 18 */     return this.apartmentRoom;
/*    */   }
/*    */ 
/*    */   public String getOwnerName()
/*    */   {
/* 23 */     return this.ownerName;
/*    */   }
/*    */ 
/*    */   public Integer getOwnerAge()
/*    */   {
/* 28 */     return this.ownerAge;
/*    */   }
/*    */ 
/*    */   public String getOwnerPhone()
/*    */   {
/* 33 */     return this.ownerPhone;
/*    */   }
/*    */ 
/*    */   public String getOwnerPhoneType()
/*    */   {
/* 38 */     return this.ownerPhoneType;
/*    */   }
/*    */ 
/*    */   public String getOwnerWork()
/*    */   {
/* 43 */     return this.ownerWork;
/*    */   }
/*    */ 
/*    */   public String getOwnerSuggestion()
/*    */   {
/* 48 */     return this.ownerSuggestion;
/*    */   }
/*    */ 
/*    */   public void setApartmentRoom(String apartmentRoom)
/*    */   {
/* 10 */     this.apartmentRoom = apartmentRoom; } 
/* 10 */   public void setOwnerName(String ownerName) { this.ownerName = ownerName; } 
/* 10 */   public void setOwnerAge(Integer ownerAge) { this.ownerAge = ownerAge; } 
/* 10 */   public void setOwnerPhone(String ownerPhone) { this.ownerPhone = ownerPhone; } 
/* 10 */   public void setOwnerPhoneType(String ownerPhoneType) { this.ownerPhoneType = ownerPhoneType; } 
/* 10 */   public void setOwnerWork(String ownerWork) { this.ownerWork = ownerWork; } 
/* 10 */   public void setOwnerSuggestion(String ownerSuggestion) { this.ownerSuggestion = ownerSuggestion; } 
/* 10 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof HomeActivityParam)) return false; HomeActivityParam other = (HomeActivityParam)o; if (!other.canEqual(this)) return false; Object this$apartmentRoom = getApartmentRoom(); Object other$apartmentRoom = other.getApartmentRoom(); if (this$apartmentRoom == null ? other$apartmentRoom != null : !this$apartmentRoom.equals(other$apartmentRoom)) return false; Object this$ownerName = getOwnerName(); Object other$ownerName = other.getOwnerName(); if (this$ownerName == null ? other$ownerName != null : !this$ownerName.equals(other$ownerName)) return false; Object this$ownerAge = getOwnerAge(); Object other$ownerAge = other.getOwnerAge(); if (this$ownerAge == null ? other$ownerAge != null : !this$ownerAge.equals(other$ownerAge)) return false; Object this$ownerPhone = getOwnerPhone(); Object other$ownerPhone = other.getOwnerPhone(); if (this$ownerPhone == null ? other$ownerPhone != null : !this$ownerPhone.equals(other$ownerPhone)) return false; Object this$ownerPhoneType = getOwnerPhoneType(); Object other$ownerPhoneType = other.getOwnerPhoneType(); if (this$ownerPhoneType == null ? other$ownerPhoneType != null : !this$ownerPhoneType.equals(other$ownerPhoneType)) return false; Object this$ownerWork = getOwnerWork(); Object other$ownerWork = other.getOwnerWork(); if (this$ownerWork == null ? other$ownerWork != null : !this$ownerWork.equals(other$ownerWork)) return false; Object this$ownerSuggestion = getOwnerSuggestion(); Object other$ownerSuggestion = other.getOwnerSuggestion(); return this$ownerSuggestion == null ? other$ownerSuggestion == null : this$ownerSuggestion.equals(other$ownerSuggestion); } 
/* 10 */   protected boolean canEqual(Object other) { return other instanceof HomeActivityParam; } 
/* 10 */   public int hashCode() { int PRIME = 59; int result = 1; Object $apartmentRoom = getApartmentRoom(); result = result * 59 + ($apartmentRoom == null ? 0 : $apartmentRoom.hashCode()); Object $ownerName = getOwnerName(); result = result * 59 + ($ownerName == null ? 0 : $ownerName.hashCode()); Object $ownerAge = getOwnerAge(); result = result * 59 + ($ownerAge == null ? 0 : $ownerAge.hashCode()); Object $ownerPhone = getOwnerPhone(); result = result * 59 + ($ownerPhone == null ? 0 : $ownerPhone.hashCode()); Object $ownerPhoneType = getOwnerPhoneType(); result = result * 59 + ($ownerPhoneType == null ? 0 : $ownerPhoneType.hashCode()); Object $ownerWork = getOwnerWork(); result = result * 59 + ($ownerWork == null ? 0 : $ownerWork.hashCode()); Object $ownerSuggestion = getOwnerSuggestion(); result = result * 59 + ($ownerSuggestion == null ? 0 : $ownerSuggestion.hashCode()); return result; } 
/* 10 */   public String toString() { return "HomeActivityParam(apartmentRoom=" + getApartmentRoom() + ", ownerName=" + getOwnerName() + ", ownerAge=" + getOwnerAge() + ", ownerPhone=" + getOwnerPhone() + ", ownerPhoneType=" + getOwnerPhoneType() + ", ownerWork=" + getOwnerWork() + ", ownerSuggestion=" + getOwnerSuggestion() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.web.HomeActivityParam
 * JD-Core Version:    0.6.2
 */