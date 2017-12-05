/*     */ package com.jinyi.ihome.module.purchase;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class GroupPurchaseTo
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String purchaseSid;
/*     */   private String apartmentSid;
/*     */   private String purchaseTitle;
/*     */   private String purchaseImage;
/*     */   private String purchaseDesc;
/*     */   private int purchaseStatus;
/*     */   private Float unitPrice1;
/*     */   private Float unitPrice2;
/*     */   private Integer purchaseQty;
/*     */   private String dueDate;
/*     */   private Integer lowerLimitQty;
/*     */   private String reminTime;
/*     */   private String payBill;
/*     */   private String payResult;
/*     */   private Date payDateTime;
/*     */ 
/*     */   public void setPurchaseSid(String purchaseSid)
/*     */   {
/*  16 */     this.purchaseSid = purchaseSid; } 
/*  17 */   public String getPurchaseSid() { return this.purchaseSid; }
/*     */ 
/*     */ 
/*     */   public void setApartmentSid(String apartmentSid)
/*     */   {
/*  23 */     this.apartmentSid = apartmentSid; } 
/*  24 */   public String getApartmentSid() { return this.apartmentSid; }
/*     */ 
/*     */ 
/*     */   public void setPurchaseTitle(String purchaseTitle)
/*     */   {
/*  31 */     this.purchaseTitle = purchaseTitle; } 
/*  32 */   public String getPurchaseTitle() { return this.purchaseTitle; }
/*     */ 
/*     */ 
/*     */   public void setPurchaseImage(String purchaseImage)
/*     */   {
/*  38 */     this.purchaseImage = purchaseImage; } 
/*  39 */   public String getPurchaseImage() { return this.purchaseImage; }
/*     */ 
/*     */ 
/*     */   public void setPurchaseDesc(String purchaseDesc)
/*     */   {
/*  45 */     this.purchaseDesc = purchaseDesc; } 
/*  46 */   public String getPurchaseDesc() { return this.purchaseDesc; }
/*     */ 
/*     */ 
/*     */   public void setPurchaseStatus(int purchaseStatus)
/*     */   {
/*  52 */     this.purchaseStatus = purchaseStatus; } 
/*  53 */   public int getPurchaseStatus() { return this.purchaseStatus; }
/*     */ 
/*     */ 
/*     */   public void setUnitPrice1(Float unitPrice1)
/*     */   {
/*  59 */     this.unitPrice1 = unitPrice1; } 
/*  60 */   public Float getUnitPrice1() { return this.unitPrice1; }
/*     */ 
/*     */ 
/*     */   public void setUnitPrice2(Float unitPrice2)
/*     */   {
/*  66 */     this.unitPrice2 = unitPrice2; } 
/*  67 */   public Float getUnitPrice2() { return this.unitPrice2; }
/*     */ 
/*     */ 
/*     */   public void setPurchaseQty(Integer purchaseQty)
/*     */   {
/*  73 */     this.purchaseQty = purchaseQty; } 
/*  74 */   public Integer getPurchaseQty() { return this.purchaseQty; }
/*     */ 
/*     */ 
/*     */   public void setDueDate(String dueDate)
/*     */   {
/*  80 */     this.dueDate = dueDate; } 
/*  81 */   public String getDueDate() { return this.dueDate; }
/*     */ 
/*     */ 
/*     */   public void setLowerLimitQty(Integer lowerLimitQty)
/*     */   {
/*  87 */     this.lowerLimitQty = lowerLimitQty; } 
/*  88 */   public Integer getLowerLimitQty() { return this.lowerLimitQty; }
/*     */ 
/*     */ 
/*     */   public String getReminTime()
/*     */   {
/*  97 */     return this.reminTime; } 
/*  98 */   public void setReminTime(String reminTime) { this.reminTime = reminTime; }
/*     */ 
/*     */ 
/*     */   public String getPayBill()
/*     */   {
/* 105 */     return this.payBill; } 
/* 106 */   public void setPayBill(String payBill) { this.payBill = payBill; }
/*     */ 
/*     */   public String getPayResult() {
/* 109 */     return this.payResult; } 
/* 110 */   public void setPayResult(String payResult) { this.payResult = payResult; }
/*     */ 
/*     */   public Date getPayDateTime() {
/* 113 */     return this.payDateTime; } 
/* 114 */   public void setPayDateTime(Date payDateTime) { this.payDateTime = payDateTime; }
/*     */ 
/*     */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.purchase.GroupPurchaseTo
 * JD-Core Version:    0.6.2
 */