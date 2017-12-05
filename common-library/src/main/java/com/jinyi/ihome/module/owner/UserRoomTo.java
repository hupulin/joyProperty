/*    */ package com.jinyi.ihome.module.owner;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.math.BigDecimal;
/*    */ 
/*    */ public class UserRoomTo
/*    */   implements Serializable
/*    */ {
/*    */   private String roomNo;
/*    */   private BigDecimal roomArea;
/*    */   private BigDecimal yearPrice;
/*    */ 
/*    */   public String getRoomNo()
/*    */   {
/* 15 */     return this.roomNo;
/*    */   }
/* 17 */   public BigDecimal getRoomArea() { return this.roomArea; } 
/*    */   public BigDecimal getYearPrice() {
/* 19 */     return this.yearPrice;
/*    */   }
/*    */ 
/*    */   public void setRoomNo(String roomNo)
/*    */   {
/* 12 */     this.roomNo = roomNo; } 
/* 12 */   public void setRoomArea(BigDecimal roomArea) { this.roomArea = roomArea; } 
/* 12 */   public void setYearPrice(BigDecimal yearPrice) { this.yearPrice = yearPrice; } 
/* 12 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof UserRoomTo)) return false; UserRoomTo other = (UserRoomTo)o; if (!other.canEqual(this)) return false; Object this$roomNo = getRoomNo(); Object other$roomNo = other.getRoomNo(); if (this$roomNo == null ? other$roomNo != null : !this$roomNo.equals(other$roomNo)) return false; Object this$roomArea = getRoomArea(); Object other$roomArea = other.getRoomArea(); if (this$roomArea == null ? other$roomArea != null : !this$roomArea.equals(other$roomArea)) return false; Object this$yearPrice = getYearPrice(); Object other$yearPrice = other.getYearPrice(); return this$yearPrice == null ? other$yearPrice == null : this$yearPrice.equals(other$yearPrice); } 
/* 12 */   protected boolean canEqual(Object other) { return other instanceof UserRoomTo; } 
/* 12 */   public int hashCode() { int PRIME = 59; int result = 1; Object $roomNo = getRoomNo(); result = result * 59 + ($roomNo == null ? 0 : $roomNo.hashCode()); Object $roomArea = getRoomArea(); result = result * 59 + ($roomArea == null ? 0 : $roomArea.hashCode()); Object $yearPrice = getYearPrice(); result = result * 59 + ($yearPrice == null ? 0 : $yearPrice.hashCode()); return result; } 
/* 12 */   public String toString() { return "UserRoomTo(roomNo=" + getRoomNo() + ", roomArea=" + getRoomArea() + ", yearPrice=" + getYearPrice() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.owner.UserRoomTo
 * JD-Core Version:    0.6.2
 */