/*    */ package com.jinyi.ihome.module.purchase;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class GroupPurchasePayParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String orderSid;
/*    */   private String userSid;
/*    */   private String apartmentSid;
/*    */ 
/*    */   public String getOrderSid()
/*    */   {
/* 16 */     return this.orderSid;
/*    */   }
/* 18 */   public String getUserSid() { return this.userSid; } 
/*    */   public String getApartmentSid() {
/* 20 */     return this.apartmentSid;
/*    */   }
/*    */ 
/*    */   public void setOrderSid(String orderSid)
/*    */   {
/* 11 */     this.orderSid = orderSid; } 
/* 11 */   public void setUserSid(String userSid) { this.userSid = userSid; } 
/* 11 */   public void setApartmentSid(String apartmentSid) { this.apartmentSid = apartmentSid; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.purchase.GroupPurchasePayParam
 * JD-Core Version:    0.6.2
 */