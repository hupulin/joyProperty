/*    */ package com.jinyi.ihome.module.car;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class CarPlaceTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String startDate;
/*    */   private String endDate;
/*    */   private String placeLocation;
/*    */   private String placeNo;
/*    */   private Integer placeStatus;
/*    */ 
/*    */   public String getStartDate()
/*    */   {
/* 16 */     return this.startDate;
/*    */   }
/* 18 */   public String getEndDate() { return this.endDate; }
/*    */ 
/*    */ 
/*    */   public String getPlaceLocation()
/*    */   {
/* 23 */     return this.placeLocation;
/*    */   }
/* 25 */   public String getPlaceNo() { return this.placeNo; }
/*    */ 
/*    */ 
/*    */   public Integer getPlaceStatus()
/*    */   {
/* 32 */     return this.placeStatus;
/*    */   }
/*    */ 
/*    */   public void setStartDate(String startDate)
/*    */   {
/* 11 */     this.startDate = startDate; } 
/* 11 */   public void setEndDate(String endDate) { this.endDate = endDate; } 
/* 11 */   public void setPlaceLocation(String placeLocation) { this.placeLocation = placeLocation; } 
/* 11 */   public void setPlaceNo(String placeNo) { this.placeNo = placeNo; } 
/* 11 */   public void setPlaceStatus(Integer placeStatus) { this.placeStatus = placeStatus; } 
/* 11 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof CarPlaceTo)) return false; CarPlaceTo other = (CarPlaceTo)o; if (!other.canEqual(this)) return false; Object this$startDate = getStartDate(); Object other$startDate = other.getStartDate(); if (this$startDate == null ? other$startDate != null : !this$startDate.equals(other$startDate)) return false; Object this$endDate = getEndDate(); Object other$endDate = other.getEndDate(); if (this$endDate == null ? other$endDate != null : !this$endDate.equals(other$endDate)) return false; Object this$placeLocation = getPlaceLocation(); Object other$placeLocation = other.getPlaceLocation(); if (this$placeLocation == null ? other$placeLocation != null : !this$placeLocation.equals(other$placeLocation)) return false; Object this$placeNo = getPlaceNo(); Object other$placeNo = other.getPlaceNo(); if (this$placeNo == null ? other$placeNo != null : !this$placeNo.equals(other$placeNo)) return false; Object this$placeStatus = getPlaceStatus(); Object other$placeStatus = other.getPlaceStatus(); return this$placeStatus == null ? other$placeStatus == null : this$placeStatus.equals(other$placeStatus); } 
/* 11 */   protected boolean canEqual(Object other) { return other instanceof CarPlaceTo; } 
/* 11 */   public int hashCode() { int PRIME = 59; int result = 1; Object $startDate = getStartDate(); result = result * 59 + ($startDate == null ? 0 : $startDate.hashCode()); Object $endDate = getEndDate(); result = result * 59 + ($endDate == null ? 0 : $endDate.hashCode()); Object $placeLocation = getPlaceLocation(); result = result * 59 + ($placeLocation == null ? 0 : $placeLocation.hashCode()); Object $placeNo = getPlaceNo(); result = result * 59 + ($placeNo == null ? 0 : $placeNo.hashCode()); Object $placeStatus = getPlaceStatus(); result = result * 59 + ($placeStatus == null ? 0 : $placeStatus.hashCode()); return result; } 
/* 11 */   public String toString() { return "CarPlaceTo(startDate=" + getStartDate() + ", endDate=" + getEndDate() + ", placeLocation=" + getPlaceLocation() + ", placeNo=" + getPlaceNo() + ", placeStatus=" + getPlaceStatus() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.car.CarPlaceTo
 * JD-Core Version:    0.6.2
 */