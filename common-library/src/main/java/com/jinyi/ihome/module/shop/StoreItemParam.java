/*    */ package com.jinyi.ihome.module.shop;
/*    */ 
/*    */ import java.io.Serializable;

/*    */
/*    */ public class StoreItemParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String apartmentSid;
/*    */   private String itemSid;
/*    */ 
/*    */   public String getApartmentSid()
/*    */   {
/* 16 */     return this.apartmentSid;
/*    */   }
/* 18 */   public String getItemSid() { return this.itemSid; }
/*    */ 
/*    */ 
/*    */   public void setApartmentSid(String apartmentSid)
/*    */   {
/* 11 */     this.apartmentSid = apartmentSid; } 
/* 11 */   public void setItemSid(String itemSid) { this.itemSid = itemSid; } 
/* 11 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof StoreItemParam)) return false; StoreItemParam other = (StoreItemParam)o; if (!other.canEqual(this)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); if (this$apartmentSid == null ? other$apartmentSid != null : !this$apartmentSid.equals(other$apartmentSid)) return false; Object this$itemSid = getItemSid(); Object other$itemSid = other.getItemSid(); return this$itemSid == null ? other$itemSid == null : this$itemSid.equals(other$itemSid); }
/* 11 */   protected boolean canEqual(Object other) { return other instanceof StoreItemParam; }
/* 11 */   public int hashCode() { int PRIME = 59; int result = 1; Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); Object $itemSid = getItemSid(); result = result * 59 + ($itemSid == null ? 0 : $itemSid.hashCode()); return result; } 
/* 11 */   public String toString() { return "StoreItemParam(apartmentSid=" + getApartmentSid() + ", itemSid=" + getItemSid() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.shop.StoreItemParam
 * JD-Core Version:    0.6.2
 */