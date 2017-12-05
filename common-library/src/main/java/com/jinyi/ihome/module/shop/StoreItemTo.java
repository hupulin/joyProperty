/*    */ package com.jinyi.ihome.module.shop;
/*    */ 
/*    */

import java.io.Serializable;
import java.math.BigDecimal;

/*    */

/*    */
/*    */ public class StoreItemTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String itemSid;
/*    */   private String name;
/*    */   private String images;
/*    */   private BigDecimal price2;
/*    */   private BigDecimal price;
/*    */   private int itemQty;
/*    */   private String content;
/*    */   private String unit;
/*    */   private int status;
/*    */   private StoreItemCommentTo comment;
/*    */ 
/*    */   public String getItemSid()
/*    */   {
/* 17 */     return this.itemSid;
/*    */   }
/*    */ 
/*    */   public String getName()
/*    */   {
/* 22 */     return this.name;
/*    */   }
/*    */ 
/*    */   public String getImages()
/*    */   {
/* 27 */     return this.images;
/*    */   }
/*    */ 
/*    */   public BigDecimal getPrice2()
/*    */   {
/* 32 */     return this.price2;
/*    */   }
/*    */ 
/*    */   public BigDecimal getPrice()
/*    */   {
/* 37 */     return this.price;
/*    */   }
/*    */ 
/*    */   public int getItemQty()
/*    */   {
/* 42 */     return this.itemQty;
/*    */   }
/*    */ 
/*    */   public String getContent()
/*    */   {
/* 48 */     return this.content;
/*    */   }
/*    */ 
/*    */   public String getUnit()
/*    */   {
/* 53 */     return this.unit;
/*    */   }
/*    */ 
/*    */   public int getStatus()
/*    */   {
/* 58 */     return this.status;
/*    */   }
/*    */ 
/*    */   public StoreItemCommentTo getComment()
/*    */   {
/* 64 */     return this.comment;
/*    */   }
/*    */ 
/*    */   public void setItemSid(String itemSid)
/*    */   {
/* 11 */     this.itemSid = itemSid; } 
/* 11 */   public void setName(String name) { this.name = name; } 
/* 11 */   public void setImages(String images) { this.images = images; } 
/* 11 */   public void setPrice2(BigDecimal price2) { this.price2 = price2; } 
/* 11 */   public void setPrice(BigDecimal price) { this.price = price; } 
/* 11 */   public void setItemQty(int itemQty) { this.itemQty = itemQty; } 
/* 11 */   public void setContent(String content) { this.content = content; } 
/* 11 */   public void setUnit(String unit) { this.unit = unit; } 
/* 11 */   public void setStatus(int status) { this.status = status; } 
/* 11 */   public void setComment(StoreItemCommentTo comment) { this.comment = comment; }
/* 11 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof StoreItemTo)) return false; StoreItemTo other = (StoreItemTo)o; if (!other.canEqual(this)) return false; Object this$itemSid = getItemSid(); Object other$itemSid = other.getItemSid(); if (this$itemSid == null ? other$itemSid != null : !this$itemSid.equals(other$itemSid)) return false; Object this$name = getName(); Object other$name = other.getName(); if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false; Object this$images = getImages(); Object other$images = other.getImages(); if (this$images == null ? other$images != null : !this$images.equals(other$images)) return false; Object this$price2 = getPrice2(); Object other$price2 = other.getPrice2(); if (this$price2 == null ? other$price2 != null : !this$price2.equals(other$price2)) return false; Object this$price = getPrice(); Object other$price = other.getPrice(); if (this$price == null ? other$price != null : !this$price.equals(other$price)) return false; if (getItemQty() != other.getItemQty()) return false; Object this$content = getContent(); Object other$content = other.getContent(); if (this$content == null ? other$content != null : !this$content.equals(other$content)) return false; Object this$unit = getUnit(); Object other$unit = other.getUnit(); if (this$unit == null ? other$unit != null : !this$unit.equals(other$unit)) return false; if (getStatus() != other.getStatus()) return false; Object this$comment = getComment(); Object other$comment = other.getComment(); return this$comment == null ? other$comment == null : this$comment.equals(other$comment); }
/* 11 */   protected boolean canEqual(Object other) { return other instanceof StoreItemTo; }
/* 11 */   public int hashCode() { int PRIME = 59; int result = 1; Object $itemSid = getItemSid(); result = result * 59 + ($itemSid == null ? 0 : $itemSid.hashCode()); Object $name = getName(); result = result * 59 + ($name == null ? 0 : $name.hashCode()); Object $images = getImages(); result = result * 59 + ($images == null ? 0 : $images.hashCode()); Object $price2 = getPrice2(); result = result * 59 + ($price2 == null ? 0 : $price2.hashCode()); Object $price = getPrice(); result = result * 59 + ($price == null ? 0 : $price.hashCode()); result = result * 59 + getItemQty(); Object $content = getContent(); result = result * 59 + ($content == null ? 0 : $content.hashCode()); Object $unit = getUnit(); result = result * 59 + ($unit == null ? 0 : $unit.hashCode()); result = result * 59 + getStatus(); Object $comment = getComment(); result = result * 59 + ($comment == null ? 0 : $comment.hashCode()); return result; } 
/* 11 */   public String toString() { return "StoreItemTo(itemSid=" + getItemSid() + ", name=" + getName() + ", images=" + getImages() + ", price2=" + getPrice2() + ", price=" + getPrice() + ", itemQty=" + getItemQty() + ", content=" + getContent() + ", unit=" + getUnit() + ", status=" + getStatus() + ", comment=" + getComment() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.shop.StoreItemTo
 * JD-Core Version:    0.6.2
 */