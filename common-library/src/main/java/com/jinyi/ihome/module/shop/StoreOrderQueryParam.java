/*    */ package com.jinyi.ihome.module.shop;
/*    */ 
/*    */ import java.io.Serializable;

/*    */
/*    */ public class StoreOrderQueryParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String userSid;
/*    */   private String apartmentSid;
/*    */   private String orderSid;
/*    */   private int pageIndex;
/*    */ 
/*    */   public String getUserSid()
/*    */   {
/* 17 */     return this.userSid;
/*    */   }
/*    */ 
/*    */   public String getApartmentSid()
/*    */   {
/* 22 */     return this.apartmentSid;
/*    */   }
/*    */ 
/*    */   public String getOrderSid()
/*    */   {
/* 27 */     return this.orderSid;
/*    */   }
/*    */ 
/*    */   public int getPageIndex()
/*    */   {
/* 33 */     return this.pageIndex;
/*    */   }
/*    */ 
/*    */   public void setUserSid(String userSid)
/*    */   {
/* 12 */     this.userSid = userSid; } 
/* 12 */   public void setApartmentSid(String apartmentSid) { this.apartmentSid = apartmentSid; } 
/* 12 */   public void setOrderSid(String orderSid) { this.orderSid = orderSid; } 
/* 12 */   public void setPageIndex(int pageIndex) { this.pageIndex = pageIndex; } 
/* 12 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof StoreOrderQueryParam)) return false; StoreOrderQueryParam other = (StoreOrderQueryParam)o; if (!other.canEqual(this)) return false; Object this$userSid = getUserSid(); Object other$userSid = other.getUserSid(); if (this$userSid == null ? other$userSid != null : !this$userSid.equals(other$userSid)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); if (this$apartmentSid == null ? other$apartmentSid != null : !this$apartmentSid.equals(other$apartmentSid)) return false; Object this$orderSid = getOrderSid(); Object other$orderSid = other.getOrderSid(); if (this$orderSid == null ? other$orderSid != null : !this$orderSid.equals(other$orderSid)) return false; return getPageIndex() == other.getPageIndex(); }
/* 12 */   protected boolean canEqual(Object other) { return other instanceof StoreOrderQueryParam; }
/* 12 */   public int hashCode() { int PRIME = 59; int result = 1; Object $userSid = getUserSid(); result = result * 59 + ($userSid == null ? 0 : $userSid.hashCode()); Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); Object $orderSid = getOrderSid(); result = result * 59 + ($orderSid == null ? 0 : $orderSid.hashCode()); result = result * 59 + getPageIndex(); return result; } 
/* 12 */   public String toString() { return "StoreOrderQueryParam(userSid=" + getUserSid() + ", apartmentSid=" + getApartmentSid() + ", orderSid=" + getOrderSid() + ", pageIndex=" + getPageIndex() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.shop.StoreOrderQueryParam
 * JD-Core Version:    0.6.2
 */