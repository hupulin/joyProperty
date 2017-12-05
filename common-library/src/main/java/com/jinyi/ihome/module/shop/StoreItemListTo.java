/*    */ package com.jinyi.ihome.module.shop;
/*    */ 
/*    */

import java.io.Serializable;
import java.math.BigDecimal;

/*    */

/*    */
/*    */ public class StoreItemListTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String itemSid;
/*    */   private String name;
/*    */   private BigDecimal price2;
/*    */   private BigDecimal price;
/*    */   private int itemQty;
/*    */   private String images;
/*    */ 
/*    */   public String getItemSid()
/*    */   {
/* 19 */     return this.itemSid;
/*    */   }
/*    */ 
/*    */   public String getName()
/*    */   {
/* 24 */     return this.name;
/*    */   }
/*    */ 
/*    */   public BigDecimal getPrice2()
/*    */   {
/* 29 */     return this.price2;
/*    */   }
/*    */ 
/*    */   public BigDecimal getPrice()
/*    */   {
/* 34 */     return this.price;
/*    */   }
/*    */ 
/*    */   public int getItemQty()
/*    */   {
/* 39 */     return this.itemQty;
/*    */   }
/*    */ 
/*    */   public String getImages()
/*    */   {
/* 44 */     return this.images;
/*    */   }
/*    */ 
/*    */   public void setItemSid(String itemSid)
/*    */   {
/* 11 */     this.itemSid = itemSid; } 
/* 11 */   public void setName(String name) { this.name = name; } 
/* 11 */   public void setPrice2(BigDecimal price2) { this.price2 = price2; } 
/* 11 */   public void setPrice(BigDecimal price) { this.price = price; } 
/* 11 */   public void setItemQty(int itemQty) { this.itemQty = itemQty; } 
/* 11 */   public void setImages(String images) { this.images = images; } 
/* 11 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof StoreItemListTo)) return false; StoreItemListTo other = (StoreItemListTo)o; if (!other.canEqual(this)) return false; Object this$itemSid = getItemSid(); Object other$itemSid = other.getItemSid(); if (this$itemSid == null ? other$itemSid != null : !this$itemSid.equals(other$itemSid)) return false; Object this$name = getName(); Object other$name = other.getName(); if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false; Object this$price2 = getPrice2(); Object other$price2 = other.getPrice2(); if (this$price2 == null ? other$price2 != null : !this$price2.equals(other$price2)) return false; Object this$price = getPrice(); Object other$price = other.getPrice(); if (this$price == null ? other$price != null : !this$price.equals(other$price)) return false; if (getItemQty() != other.getItemQty()) return false; Object this$images = getImages(); Object other$images = other.getImages(); return this$images == null ? other$images == null : this$images.equals(other$images); }
/* 11 */   protected boolean canEqual(Object other) { return other instanceof StoreItemListTo; }
/* 11 */   public int hashCode() { int PRIME = 59; int result = 1; Object $itemSid = getItemSid(); result = result * 59 + ($itemSid == null ? 0 : $itemSid.hashCode()); Object $name = getName(); result = result * 59 + ($name == null ? 0 : $name.hashCode()); Object $price2 = getPrice2(); result = result * 59 + ($price2 == null ? 0 : $price2.hashCode()); Object $price = getPrice(); result = result * 59 + ($price == null ? 0 : $price.hashCode()); result = result * 59 + getItemQty(); Object $images = getImages(); result = result * 59 + ($images == null ? 0 : $images.hashCode()); return result; } 
/* 11 */   public String toString() { return "StoreItemListTo(itemSid=" + getItemSid() + ", name=" + getName() + ", price2=" + getPrice2() + ", price=" + getPrice() + ", itemQty=" + getItemQty() + ", images=" + getImages() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.shop.StoreItemListTo
 * JD-Core Version:    0.6.2
 */