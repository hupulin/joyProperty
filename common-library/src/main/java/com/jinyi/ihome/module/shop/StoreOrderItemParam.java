/*    */ package com.jinyi.ihome.module.shop;
/*    */ 
/*    */ import java.io.Serializable;

/*    */
/*    */ public class StoreOrderItemParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String itemSid;
/*    */   private int buyQty;
/*    */   private float price;
/*    */ 
/*    */   public String getItemSid()
/*    */   {
/* 19 */     return this.itemSid;
/*    */   }
/*    */ 
/*    */   public int getBuyQty()
/*    */   {
/* 24 */     return this.buyQty;
/*    */   }
/*    */ 
/*    */   public float getPrice()
/*    */   {
/* 29 */     return this.price;
/*    */   }
/*    */ 
/*    */   public void setItemSid(String itemSid)
/*    */   {
/* 11 */     this.itemSid = itemSid; } 
/* 11 */   public void setBuyQty(int buyQty) { this.buyQty = buyQty; } 
/* 11 */   public void setPrice(float price) { this.price = price; } 
/* 11 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof StoreOrderItemParam)) return false; StoreOrderItemParam other = (StoreOrderItemParam)o; if (!other.canEqual(this)) return false; Object this$itemSid = getItemSid(); Object other$itemSid = other.getItemSid(); if (this$itemSid == null ? other$itemSid != null : !this$itemSid.equals(other$itemSid)) return false; if (getBuyQty() != other.getBuyQty()) return false; return Float.compare(getPrice(), other.getPrice()) == 0; }
/* 11 */   protected boolean canEqual(Object other) { return other instanceof StoreOrderItemParam; }
/* 11 */   public int hashCode() { int PRIME = 59; int result = 1; Object $itemSid = getItemSid(); result = result * 59 + ($itemSid == null ? 0 : $itemSid.hashCode()); result = result * 59 + getBuyQty(); result = result * 59 + Float.floatToIntBits(getPrice()); return result; } 
/* 11 */   public String toString() { return "StoreOrderItemParam(itemSid=" + getItemSid() + ", buyQty=" + getBuyQty() + ", price=" + getPrice() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.shop.StoreOrderItemParam
 * JD-Core Version:    0.6.2
 */