/*    */ package com.jinyi.ihome.module.shop;
/*    */ 
/*    */ import java.io.Serializable;

/*    */
/*    */ public class ShopCommentParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String itemSid;
/*    */   private int pageIndex;
/*    */ 
/*    */   public String getItemSid()
/*    */   {
/* 16 */     return this.itemSid;
/*    */   }
/* 18 */   public int getPageIndex() { return this.pageIndex; }
/*    */ 
/*    */ 
/*    */   public void setItemSid(String itemSid)
/*    */   {
/* 10 */     this.itemSid = itemSid; } 
/* 10 */   public void setPageIndex(int pageIndex) { this.pageIndex = pageIndex; } 
/* 10 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof ShopCommentParam)) return false; ShopCommentParam other = (ShopCommentParam)o; if (!other.canEqual(this)) return false; Object this$itemSid = getItemSid(); Object other$itemSid = other.getItemSid(); if (this$itemSid == null ? other$itemSid != null : !this$itemSid.equals(other$itemSid)) return false; return getPageIndex() == other.getPageIndex(); }
/* 10 */   protected boolean canEqual(Object other) { return other instanceof ShopCommentParam; }
/* 10 */   public int hashCode() { int PRIME = 59; int result = 1; Object $itemSid = getItemSid(); result = result * 59 + ($itemSid == null ? 0 : $itemSid.hashCode()); result = result * 59 + getPageIndex(); return result; } 
/* 10 */   public String toString() { return "ShopCommentParam(itemSid=" + getItemSid() + ", pageIndex=" + getPageIndex() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.shop.ShopCommentParam
 * JD-Core Version:    0.6.2
 */