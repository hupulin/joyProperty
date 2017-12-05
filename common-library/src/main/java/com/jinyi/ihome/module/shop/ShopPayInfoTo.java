/*    */ package com.jinyi.ihome.module.shop;
/*    */ 
/*    */

import com.jinyi.ihome.module.bill.PayPartnerTo;

import java.io.Serializable;
import java.util.List;

/*    */
/*    */

/*    */
/*    */ public class ShopPayInfoTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String name;
/*    */   private String money;
/*    */   private List<PayPartnerTo> payPartners;
/*    */ 
/*    */   public String getName()
/*    */   {
/* 17 */     return this.name; } 
/* 18 */   public String getMoney() { return this.money; }
/*    */ 
/*    */ 
/*    */   public List<PayPartnerTo> getPayPartners()
/*    */   {
/* 23 */     return this.payPartners;
/*    */   }
/*    */ 
/*    */   public void setName(String name)
/*    */   {
/* 12 */     this.name = name; } 
/* 12 */   public void setMoney(String money) { this.money = money; } 
/* 12 */   public void setPayPartners(List<PayPartnerTo> payPartners) { this.payPartners = payPartners; }
/* 12 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof ShopPayInfoTo)) return false; ShopPayInfoTo other = (ShopPayInfoTo)o; if (!other.canEqual(this)) return false; Object this$name = getName(); Object other$name = other.getName(); if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false; Object this$money = getMoney(); Object other$money = other.getMoney(); if (this$money == null ? other$money != null : !this$money.equals(other$money)) return false; Object this$payPartners = getPayPartners(); Object other$payPartners = other.getPayPartners(); return this$payPartners == null ? other$payPartners == null : this$payPartners.equals(other$payPartners); }
/* 12 */   protected boolean canEqual(Object other) { return other instanceof ShopPayInfoTo; }
/* 12 */   public int hashCode() { int PRIME = 59; int result = 1; Object $name = getName(); result = result * 59 + ($name == null ? 0 : $name.hashCode()); Object $money = getMoney(); result = result * 59 + ($money == null ? 0 : $money.hashCode()); Object $payPartners = getPayPartners(); result = result * 59 + ($payPartners == null ? 0 : $payPartners.hashCode()); return result; } 
/* 12 */   public String toString() { return "ShopPayInfoTo(name=" + getName() + ", money=" + getMoney() + ", payPartners=" + getPayPartners() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.shop.ShopPayInfoTo
 * JD-Core Version:    0.6.2
 */