/*    */ package com.jinyi.ihome.module.purchase;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class GroupPurchaseOrderParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String purchaseSid;
/*    */   private Integer purchaseQty;
/*    */   private String contactPhone;
/*    */   private String contactName;
/*    */   private String contactAddress;
/*    */   private String purchaseOwner;
/*    */ 
/*    */   public void setPurchaseSid(String purchaseSid)
/*    */   {
/* 15 */     this.purchaseSid = purchaseSid; } 
/* 16 */   public String getPurchaseSid() { return this.purchaseSid; }
/*    */ 
/*    */ 
/*    */   public void setPurchaseQty(Integer purchaseQty)
/*    */   {
/* 22 */     this.purchaseQty = purchaseQty; } 
/* 23 */   public Integer getPurchaseQty() { return this.purchaseQty; }
/*    */ 
/*    */ 
/*    */   public void setContactPhone(String contactPhone)
/*    */   {
/* 29 */     this.contactPhone = contactPhone; } 
/* 30 */   public String getContactPhone() { return this.contactPhone; }
/*    */ 
/*    */ 
/*    */   public void setContactName(String contactName)
/*    */   {
/* 36 */     this.contactName = contactName; } 
/* 37 */   public String getContactName() { return this.contactName; }
/*    */ 
/*    */ 
/*    */   public void setContactAddress(String contactAddress)
/*    */   {
/* 43 */     this.contactAddress = contactAddress; } 
/* 44 */   public String getContactAddress() { return this.contactAddress; }
/*    */ 
/*    */ 
/*    */   public void setPurchaseOwner(String purchaseOwner)
/*    */   {
/* 50 */     this.purchaseOwner = purchaseOwner; } 
/* 51 */   public String getPurchaseOwner() { return this.purchaseOwner; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.purchase.GroupPurchaseOrderParam
 * JD-Core Version:    0.6.2
 */