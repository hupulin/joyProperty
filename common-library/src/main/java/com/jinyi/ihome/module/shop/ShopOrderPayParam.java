/*    */ package com.jinyi.ihome.module.shop;
/*    */ 
/*    */ import java.io.Serializable;

/*    */
/*    */ public class ShopOrderPayParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String orderSid;
/*    */   private String userSid;
/*    */   private String apartmentSid;
/*    */ 
/*    */   public String getOrderSid()
/*    */   {
/* 15 */     return this.orderSid;
/*    */   }
/* 17 */   public String getUserSid() { return this.userSid; } 
/*    */   public String getApartmentSid() {
/* 19 */     return this.apartmentSid;
/*    */   }
/*    */ 
/*    */   public void setOrderSid(String orderSid)
/*    */   {
/* 10 */     this.orderSid = orderSid; } 
/* 10 */   public void setUserSid(String userSid) { this.userSid = userSid; } 
/* 10 */   public void setApartmentSid(String apartmentSid) { this.apartmentSid = apartmentSid; } 
/* 10 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof ShopOrderPayParam)) return false; ShopOrderPayParam other = (ShopOrderPayParam)o; if (!other.canEqual(this)) return false; Object this$orderSid = getOrderSid(); Object other$orderSid = other.getOrderSid(); if (this$orderSid == null ? other$orderSid != null : !this$orderSid.equals(other$orderSid)) return false; Object this$userSid = getUserSid(); Object other$userSid = other.getUserSid(); if (this$userSid == null ? other$userSid != null : !this$userSid.equals(other$userSid)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); return this$apartmentSid == null ? other$apartmentSid == null : this$apartmentSid.equals(other$apartmentSid); }
/* 10 */   protected boolean canEqual(Object other) { return other instanceof ShopOrderPayParam; }
/* 10 */   public int hashCode() { int PRIME = 59; int result = 1; Object $orderSid = getOrderSid(); result = result * 59 + ($orderSid == null ? 0 : $orderSid.hashCode()); Object $userSid = getUserSid(); result = result * 59 + ($userSid == null ? 0 : $userSid.hashCode()); Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); return result; } 
/* 10 */   public String toString() { return "ShopOrderPayParam(orderSid=" + getOrderSid() + ", userSid=" + getUserSid() + ", apartmentSid=" + getApartmentSid() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.shop.ShopOrderPayParam
 * JD-Core Version:    0.6.2
 */