/*    */ package com.jinyi.ihome.module.purchase;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class GroupPurchaseParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String apartmentSid;
/*    */   private String purchaseTitle;
/*    */   private String purchaseImage;
/*    */   private String purchaseDesc;
/*    */   private Float unitPrice1;
/*    */   private Float unitPrice2;
/*    */   private Integer lowerLimitQty;
/*    */   private String dueDate;
/*    */   private String createdBy;
/*    */ 
/*    */   public void setApartmentSid(String apartmentSid)
/*    */   {
/* 15 */     this.apartmentSid = apartmentSid; } 
/* 16 */   public String getApartmentSid() { return this.apartmentSid; }
/*    */ 
/*    */ 
/*    */   public void setPurchaseTitle(String purchaseTitle)
/*    */   {
/* 22 */     this.purchaseTitle = purchaseTitle; } 
/* 23 */   public String getPurchaseTitle() { return this.purchaseTitle; }
/*    */ 
/*    */ 
/*    */   public void setPurchaseImage(String purchaseImage)
/*    */   {
/* 29 */     this.purchaseImage = purchaseImage; } 
/* 30 */   public String getPurchaseImage() { return this.purchaseImage; }
/*    */ 
/*    */ 
/*    */   public void setPurchaseDesc(String purchaseDesc)
/*    */   {
/* 36 */     this.purchaseDesc = purchaseDesc; } 
/* 37 */   public String getPurchaseDesc() { return this.purchaseDesc; }
/*    */ 
/*    */ 
/*    */   public void setUnitPrice1(Float unitPrice1)
/*    */   {
/* 43 */     this.unitPrice1 = unitPrice1; } 
/* 44 */   public Float getUnitPrice1() { return this.unitPrice1; }
/*    */ 
/*    */ 
/*    */   public void setUnitPrice2(Float unitPrice2)
/*    */   {
/* 50 */     this.unitPrice2 = unitPrice2; } 
/* 51 */   public Float getUnitPrice2() { return this.unitPrice2; }
/*    */ 
/*    */ 
/*    */   public void setLowerLimitQty(Integer lowerLimitQty)
/*    */   {
/* 57 */     this.lowerLimitQty = lowerLimitQty; } 
/* 58 */   public Integer getLowerLimitQty() { return this.lowerLimitQty; }
/*    */ 
/*    */ 
/*    */   public void setDueDate(String dueDate)
/*    */   {
/* 64 */     this.dueDate = dueDate; } 
/* 65 */   public String getDueDate() { return this.dueDate; }
/*    */ 
/*    */ 
/*    */   public void setCreatedBy(String createdBy)
/*    */   {
/* 71 */     this.createdBy = createdBy; } 
/* 72 */   public String getCreatedBy() { return this.createdBy; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.purchase.GroupPurchaseParam
 * JD-Core Version:    0.6.2
 */