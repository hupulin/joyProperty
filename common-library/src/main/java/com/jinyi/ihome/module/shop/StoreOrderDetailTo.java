/*    */ package com.jinyi.ihome.module.shop;
/*    */ 
/*    */ import java.io.Serializable;

/*    */
/*    */ public class StoreOrderDetailTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String detailSid;
/*    */   private Float itemPrice;
/*    */   private int itemQty;
/*    */   private int itemSeq;
/*    */   private String itemSid;
/*    */   private String orderSid;
/*    */   private String name;
/*    */   private String images;
/*    */   private StoreItemCommentTo commentTo;
/*    */ 
/*    */   public String getDetailSid()
/*    */   {
/* 16 */     return this.detailSid;
/*    */   }
/*    */ 
/*    */   public Float getItemPrice()
/*    */   {
/* 21 */     return this.itemPrice;
/*    */   }
/*    */ 
/*    */   public int getItemQty()
/*    */   {
/* 26 */     return this.itemQty;
/*    */   }
/*    */ 
/*    */   public int getItemSeq()
/*    */   {
/* 31 */     return this.itemSeq;
/*    */   }
/* 33 */   public String getItemSid() { return this.itemSid; } 
/*    */   public String getOrderSid() {
/* 35 */     return this.orderSid;
/*    */   }
/*    */ 
/*    */   public String getName()
/*    */   {
/* 41 */     return this.name;
/*    */   }
/*    */ 
/*    */   public String getImages()
/*    */   {
/* 46 */     return this.images;
/*    */   }
/*    */   public StoreItemCommentTo getCommentTo() {
/* 49 */     return this.commentTo;
/*    */   }
/*    */ 
/*    */   public void setDetailSid(String detailSid)
/*    */   {
/* 12 */     this.detailSid = detailSid; } 
/* 12 */   public void setItemPrice(Float itemPrice) { this.itemPrice = itemPrice; } 
/* 12 */   public void setItemQty(int itemQty) { this.itemQty = itemQty; } 
/* 12 */   public void setItemSeq(int itemSeq) { this.itemSeq = itemSeq; } 
/* 12 */   public void setItemSid(String itemSid) { this.itemSid = itemSid; } 
/* 12 */   public void setOrderSid(String orderSid) { this.orderSid = orderSid; } 
/* 12 */   public void setName(String name) { this.name = name; } 
/* 12 */   public void setImages(String images) { this.images = images; } 
/* 12 */   public void setCommentTo(StoreItemCommentTo commentTo) { this.commentTo = commentTo; }
/* 12 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof StoreOrderDetailTo)) return false; StoreOrderDetailTo other = (StoreOrderDetailTo)o; if (!other.canEqual(this)) return false; Object this$detailSid = getDetailSid(); Object other$detailSid = other.getDetailSid(); if (this$detailSid == null ? other$detailSid != null : !this$detailSid.equals(other$detailSid)) return false; Object this$itemPrice = getItemPrice(); Object other$itemPrice = other.getItemPrice(); if (this$itemPrice == null ? other$itemPrice != null : !this$itemPrice.equals(other$itemPrice)) return false; if (getItemQty() != other.getItemQty()) return false; if (getItemSeq() != other.getItemSeq()) return false; Object this$itemSid = getItemSid(); Object other$itemSid = other.getItemSid(); if (this$itemSid == null ? other$itemSid != null : !this$itemSid.equals(other$itemSid)) return false; Object this$orderSid = getOrderSid(); Object other$orderSid = other.getOrderSid(); if (this$orderSid == null ? other$orderSid != null : !this$orderSid.equals(other$orderSid)) return false; Object this$name = getName(); Object other$name = other.getName(); if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false; Object this$images = getImages(); Object other$images = other.getImages(); if (this$images == null ? other$images != null : !this$images.equals(other$images)) return false; Object this$commentTo = getCommentTo(); Object other$commentTo = other.getCommentTo(); return this$commentTo == null ? other$commentTo == null : this$commentTo.equals(other$commentTo); }
/* 12 */   protected boolean canEqual(Object other) { return other instanceof StoreOrderDetailTo; }
/* 12 */   public int hashCode() { int PRIME = 59; int result = 1; Object $detailSid = getDetailSid(); result = result * 59 + ($detailSid == null ? 0 : $detailSid.hashCode()); Object $itemPrice = getItemPrice(); result = result * 59 + ($itemPrice == null ? 0 : $itemPrice.hashCode()); result = result * 59 + getItemQty(); result = result * 59 + getItemSeq(); Object $itemSid = getItemSid(); result = result * 59 + ($itemSid == null ? 0 : $itemSid.hashCode()); Object $orderSid = getOrderSid(); result = result * 59 + ($orderSid == null ? 0 : $orderSid.hashCode()); Object $name = getName(); result = result * 59 + ($name == null ? 0 : $name.hashCode()); Object $images = getImages(); result = result * 59 + ($images == null ? 0 : $images.hashCode()); Object $commentTo = getCommentTo(); result = result * 59 + ($commentTo == null ? 0 : $commentTo.hashCode()); return result; } 
/* 12 */   public String toString() { return "StoreOrderDetailTo(detailSid=" + getDetailSid() + ", itemPrice=" + getItemPrice() + ", itemQty=" + getItemQty() + ", itemSeq=" + getItemSeq() + ", itemSid=" + getItemSid() + ", orderSid=" + getOrderSid() + ", name=" + getName() + ", images=" + getImages() + ", commentTo=" + getCommentTo() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.shop.StoreOrderDetailTo
 * JD-Core Version:    0.6.2
 */