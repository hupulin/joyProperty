/*    */ package com.jinyi.ihome.module.house;
/*    */ 
/*    */ import com.jinyi.ihome.module.apartment.ApartmentDetailTo;
/*    */ import com.jinyi.ihome.module.owner.UserRoomTo;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class HouseMainTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private ApartmentDetailTo apartmentDetailTo;
/*    */   private UserRoomTo userRoomTo;
/*    */ 
/*    */   public ApartmentDetailTo getApartmentDetailTo()
/*    */   {
/* 24 */     return this.apartmentDetailTo;
/*    */   }
/*    */ 
/*    */   public UserRoomTo getUserRoomTo()
/*    */   {
/* 29 */     return this.userRoomTo;
/*    */   }
/*    */ 
/*    */   public void setApartmentDetailTo(ApartmentDetailTo apartmentDetailTo)
/*    */   {
/* 16 */     this.apartmentDetailTo = apartmentDetailTo; } 
/* 16 */   public void setUserRoomTo(UserRoomTo userRoomTo) { this.userRoomTo = userRoomTo; } 
/* 16 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof HouseMainTo)) return false; HouseMainTo other = (HouseMainTo)o; if (!other.canEqual(this)) return false; Object this$apartmentDetailTo = getApartmentDetailTo(); Object other$apartmentDetailTo = other.getApartmentDetailTo(); if (this$apartmentDetailTo == null ? other$apartmentDetailTo != null : !this$apartmentDetailTo.equals(other$apartmentDetailTo)) return false; Object this$userRoomTo = getUserRoomTo(); Object other$userRoomTo = other.getUserRoomTo(); return this$userRoomTo == null ? other$userRoomTo == null : this$userRoomTo.equals(other$userRoomTo); } 
/* 16 */   protected boolean canEqual(Object other) { return other instanceof HouseMainTo; } 
/* 16 */   public int hashCode() { int PRIME = 59; int result = 1; Object $apartmentDetailTo = getApartmentDetailTo(); result = result * 59 + ($apartmentDetailTo == null ? 0 : $apartmentDetailTo.hashCode()); Object $userRoomTo = getUserRoomTo(); result = result * 59 + ($userRoomTo == null ? 0 : $userRoomTo.hashCode()); return result; } 
/* 16 */   public String toString() { return "HouseMainTo(apartmentDetailTo=" + getApartmentDetailTo() + ", userRoomTo=" + getUserRoomTo() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.house.HouseMainTo
 * JD-Core Version:    0.6.2
 */