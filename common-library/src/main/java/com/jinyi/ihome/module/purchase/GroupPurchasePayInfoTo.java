/*    */ package com.jinyi.ihome.module.purchase;
/*    */ 
/*    */ import com.jinyi.ihome.module.bill.PayPartnerTo;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ 
/*    */ public class GroupPurchasePayInfoTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String name;
/*    */   private String money;
/*    */   private List<PayPartnerTo> payPartners;
/*    */ 
/*    */   public String getName()
/*    */   {
/* 19 */     return this.name; } 
/* 20 */   public String getMoney() { return this.money; }
/*    */ 
/*    */ 
/*    */   public List<PayPartnerTo> getPayPartners()
/*    */   {
/* 25 */     return this.payPartners;
/*    */   }
/*    */ 
/*    */   public void setName(String name)
/*    */   {
/* 14 */     this.name = name; } 
/* 14 */   public void setMoney(String money) { this.money = money; } 
/* 14 */   public void setPayPartners(List<PayPartnerTo> payPartners) { this.payPartners = payPartners; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.purchase.GroupPurchasePayInfoTo
 * JD-Core Version:    0.6.2
 */