/*    */ package com.jinyi.ihome.module.shop;
/*    */ 
/*    */ import java.io.Serializable;

/*    */
/*    */ public class OrderCommentParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String orderSid;
/*    */   private String itemSid;
/*    */   private String userSid;
/*    */   private String content;
/*    */ 
/*    */   public String getOrderSid()
/*    */   {
/* 19 */     return this.orderSid;
/*    */   }
/*    */ 
/*    */   public String getItemSid()
/*    */   {
/* 25 */     return this.itemSid;
/*    */   }
/*    */ 
/*    */   public String getUserSid()
/*    */   {
/* 30 */     return this.userSid;
/*    */   }
/*    */ 
/*    */   public String getContent()
/*    */   {
/* 35 */     return this.content;
/*    */   }
/*    */ 
/*    */   public void setOrderSid(String orderSid)
/*    */   {
/* 10 */     this.orderSid = orderSid; } 
/* 10 */   public void setItemSid(String itemSid) { this.itemSid = itemSid; } 
/* 10 */   public void setUserSid(String userSid) { this.userSid = userSid; } 
/* 10 */   public void setContent(String content) { this.content = content; } 
/* 10 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof OrderCommentParam)) return false; OrderCommentParam other = (OrderCommentParam)o; if (!other.canEqual(this)) return false; Object this$orderSid = getOrderSid(); Object other$orderSid = other.getOrderSid(); if (this$orderSid == null ? other$orderSid != null : !this$orderSid.equals(other$orderSid)) return false; Object this$itemSid = getItemSid(); Object other$itemSid = other.getItemSid(); if (this$itemSid == null ? other$itemSid != null : !this$itemSid.equals(other$itemSid)) return false; Object this$userSid = getUserSid(); Object other$userSid = other.getUserSid(); if (this$userSid == null ? other$userSid != null : !this$userSid.equals(other$userSid)) return false; Object this$content = getContent(); Object other$content = other.getContent(); return this$content == null ? other$content == null : this$content.equals(other$content); }
/* 10 */   protected boolean canEqual(Object other) { return other instanceof OrderCommentParam; }
/* 10 */   public int hashCode() { int PRIME = 59; int result = 1; Object $orderSid = getOrderSid(); result = result * 59 + ($orderSid == null ? 0 : $orderSid.hashCode()); Object $itemSid = getItemSid(); result = result * 59 + ($itemSid == null ? 0 : $itemSid.hashCode()); Object $userSid = getUserSid(); result = result * 59 + ($userSid == null ? 0 : $userSid.hashCode()); Object $content = getContent(); result = result * 59 + ($content == null ? 0 : $content.hashCode()); return result; } 
/* 10 */   public String toString() { return "OrderCommentParam(orderSid=" + getOrderSid() + ", itemSid=" + getItemSid() + ", userSid=" + getUserSid() + ", content=" + getContent() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.shop.OrderCommentParam
 * JD-Core Version:    0.6.2
 */