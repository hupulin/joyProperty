/*    */ package com.jinyi.ihome.module.payment;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class PaymentParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String type;
/*    */   private String orderSid;
/*    */   private String userSid;
/*    */ 
/*    */   public String getType()
/*    */   {
/* 19 */     return this.type;
/*    */   }
/*    */ 
/*    */   public String getOrderSid()
/*    */   {
/* 24 */     return this.orderSid;
/*    */   }
/*    */ 
/*    */   public String getUserSid()
/*    */   {
/* 29 */     return this.userSid;
/*    */   }
/*    */ 
/*    */   public void setType(String type)
/*    */   {
/* 10 */     this.type = type; } 
/* 10 */   public void setOrderSid(String orderSid) { this.orderSid = orderSid; } 
/* 10 */   public void setUserSid(String userSid) { this.userSid = userSid; } 
/* 10 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof PaymentParam)) return false; PaymentParam other = (PaymentParam)o; if (!other.canEqual(this)) return false; Object this$type = getType(); Object other$type = other.getType(); if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false; Object this$orderSid = getOrderSid(); Object other$orderSid = other.getOrderSid(); if (this$orderSid == null ? other$orderSid != null : !this$orderSid.equals(other$orderSid)) return false; Object this$userSid = getUserSid(); Object other$userSid = other.getUserSid(); return this$userSid == null ? other$userSid == null : this$userSid.equals(other$userSid); } 
/* 10 */   protected boolean canEqual(Object other) { return other instanceof PaymentParam; } 
/* 10 */   public int hashCode() { int PRIME = 59; int result = 1; Object $type = getType(); result = result * 59 + ($type == null ? 0 : $type.hashCode()); Object $orderSid = getOrderSid(); result = result * 59 + ($orderSid == null ? 0 : $orderSid.hashCode()); Object $userSid = getUserSid(); result = result * 59 + ($userSid == null ? 0 : $userSid.hashCode()); return result; } 
/* 10 */   public String toString() { return "PaymentParam(type=" + getType() + ", orderSid=" + getOrderSid() + ", userSid=" + getUserSid() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.payment.PaymentParam
 * JD-Core Version:    0.6.2
 */