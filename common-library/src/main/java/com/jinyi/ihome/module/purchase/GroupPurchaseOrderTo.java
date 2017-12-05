/*     */ package com.jinyi.ihome.module.purchase;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class GroupPurchaseOrderTo
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String orderSid;
/*     */   private String orderNo;
/*     */   private String orderStatus;
/*     */   private String purchaseSid;
/*     */   private Integer purchaseQty;
/*     */   private Float unitPrice;
/*     */   private Float totalPrice;
/*     */   private String contactPhone;
/*     */   private String contactName;
/*     */   private String contactAddress;
/*     */   private String purchaseOwner;
/*     */   private Date purchaseDate;
/*     */   private GroupPurchaseTo purchaseTo;
/*     */ 
/*     */   public void setOrderSid(String orderSid)
/*     */   {
/*  16 */     this.orderSid = orderSid; } 
/*  17 */   public String getOrderSid() { return this.orderSid; }
/*     */ 
/*     */ 
/*     */   public void setOrderNo(String orderNo)
/*     */   {
/*  23 */     this.orderNo = orderNo; } 
/*  24 */   public String getOrderNo() { return this.orderNo; }
/*     */ 
/*     */ 
/*     */   public void setOrderStatus(String orderStatus)
/*     */   {
/*  30 */     this.orderStatus = orderStatus; } 
/*  31 */   public String getOrderStatus() { return this.orderStatus; }
/*     */ 
/*     */ 
/*     */   public void setPurchaseSid(String purchaseSid)
/*     */   {
/*  37 */     this.purchaseSid = purchaseSid; } 
/*  38 */   public String getPurchaseSid() { return this.purchaseSid; }
/*     */ 
/*     */ 
/*     */   public void setPurchaseQty(Integer purchaseQty)
/*     */   {
/*  44 */     this.purchaseQty = purchaseQty; } 
/*  45 */   public Integer getPurchaseQty() { return this.purchaseQty; }
/*     */ 
/*     */ 
/*     */   public void setUnitPrice(Float unitPrice)
/*     */   {
/*  51 */     this.unitPrice = unitPrice; } 
/*  52 */   public Float getUnitPrice() { return this.unitPrice; }
/*     */ 
/*     */ 
/*     */   public void setTotalPrice(Float totalPrice)
/*     */   {
/*  58 */     this.totalPrice = totalPrice; } 
/*  59 */   public Float getTotalPrice() { return this.totalPrice; }
/*     */ 
/*     */ 
/*     */   public void setContactPhone(String contactPhone)
/*     */   {
/*  65 */     this.contactPhone = contactPhone; } 
/*  66 */   public String getContactPhone() { return this.contactPhone; }
/*     */ 
/*     */ 
/*     */   public void setContactName(String contactName)
/*     */   {
/*  72 */     this.contactName = contactName; } 
/*  73 */   public String getContactName() { return this.contactName; }
/*     */ 
/*     */ 
/*     */   public void setContactAddress(String contactAddress)
/*     */   {
/*  79 */     this.contactAddress = contactAddress; } 
/*  80 */   public String getContactAddress() { return this.contactAddress; }
/*     */ 
/*     */ 
/*     */   public void setPurchaseOwner(String purchaseOwner)
/*     */   {
/*  86 */     this.purchaseOwner = purchaseOwner; } 
/*  87 */   public String getPurchaseOwner() { return this.purchaseOwner; }
/*     */ 
/*     */ 
/*     */   public void setPurchaseDate(Date purchaseDate)
/*     */   {
/*  93 */     this.purchaseDate = purchaseDate; } 
/*  94 */   public Date getPurchaseDate() { return this.purchaseDate; }
/*     */ 
/*     */ 
/*     */   public void setPurchaseTo(GroupPurchaseTo purchaseTo)
/*     */   {
/* 100 */     this.purchaseTo = purchaseTo; } 
/* 101 */   public GroupPurchaseTo getPurchaseTo() { return this.purchaseTo; }
/*     */ 
/*     */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.purchase.GroupPurchaseOrderTo
 * JD-Core Version:    0.6.2
 */