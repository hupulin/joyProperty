/*    */ package com.jinyi.ihome.module.shop;
/*    */ 
/*    */ import java.io.Serializable;

/*    */
/*    */ public class StoreOrderStatusParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String orderSid;
/*    */   private int type;
/*    */   private String userSid;
/*    */ 
/*    */   public String getOrderSid()
/*    */   {
/* 17 */     return this.orderSid;
/*    */   }
/*    */ 
/*    */   public int getType()
/*    */   {
/* 23 */     return this.type;
/*    */   }
/*    */ 
/*    */   public String getUserSid()
/*    */   {
/* 28 */     return this.userSid;
/*    */   }
/*    */ 
/*    */   public void setOrderSid(String orderSid)
/*    */   {
/* 10 */     this.orderSid = orderSid; } 
/* 10 */   public void setType(int type) { this.type = type; } 
/* 10 */   public void setUserSid(String userSid) { this.userSid = userSid; } 
/* 10 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof StoreOrderStatusParam)) return false; StoreOrderStatusParam other = (StoreOrderStatusParam)o; if (!other.canEqual(this)) return false; Object this$orderSid = getOrderSid(); Object other$orderSid = other.getOrderSid(); if (this$orderSid == null ? other$orderSid != null : !this$orderSid.equals(other$orderSid)) return false; if (getType() != other.getType()) return false; Object this$userSid = getUserSid(); Object other$userSid = other.getUserSid(); return this$userSid == null ? other$userSid == null : this$userSid.equals(other$userSid); }
/* 10 */   protected boolean canEqual(Object other) { return other instanceof StoreOrderStatusParam; }
/* 10 */   public int hashCode() { int PRIME = 59; int result = 1; Object $orderSid = getOrderSid(); result = result * 59 + ($orderSid == null ? 0 : $orderSid.hashCode()); result = result * 59 + getType(); Object $userSid = getUserSid(); result = result * 59 + ($userSid == null ? 0 : $userSid.hashCode()); return result; } 
/* 10 */   public String toString() { return "StoreOrderStatusParam(orderSid=" + getOrderSid() + ", type=" + getType() + ", userSid=" + getUserSid() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.shop.StoreOrderStatusParam
 * JD-Core Version:    0.6.2
 */