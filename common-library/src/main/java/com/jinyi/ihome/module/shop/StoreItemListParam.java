/*    */ package com.jinyi.ihome.module.shop;
/*    */ 
/*    */ import java.io.Serializable;

/*    */
/*    */ public class StoreItemListParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String apartmentSid;
/*    */   private String categoryCode;
/*    */   private int pageIndex;
/*    */ 
/*    */   public String getApartmentSid()
/*    */   {
/* 15 */     return this.apartmentSid;
/*    */   }
/* 17 */   public String getCategoryCode() { return this.categoryCode; } 
/*    */   public int getPageIndex() {
/* 19 */     return this.pageIndex;
/*    */   }
/*    */ 
/*    */   public void setApartmentSid(String apartmentSid)
/*    */   {
/* 10 */     this.apartmentSid = apartmentSid; } 
/* 10 */   public void setCategoryCode(String categoryCode) { this.categoryCode = categoryCode; } 
/* 10 */   public void setPageIndex(int pageIndex) { this.pageIndex = pageIndex; } 
/* 10 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof StoreItemListParam)) return false; StoreItemListParam other = (StoreItemListParam)o; if (!other.canEqual(this)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); if (this$apartmentSid == null ? other$apartmentSid != null : !this$apartmentSid.equals(other$apartmentSid)) return false; Object this$categoryCode = getCategoryCode(); Object other$categoryCode = other.getCategoryCode(); if (this$categoryCode == null ? other$categoryCode != null : !this$categoryCode.equals(other$categoryCode)) return false; return getPageIndex() == other.getPageIndex(); }
/* 10 */   protected boolean canEqual(Object other) { return other instanceof StoreItemListParam; }
/* 10 */   public int hashCode() { int PRIME = 59; int result = 1; Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); Object $categoryCode = getCategoryCode(); result = result * 59 + ($categoryCode == null ? 0 : $categoryCode.hashCode()); result = result * 59 + getPageIndex(); return result; } 
/* 10 */   public String toString() { return "StoreItemListParam(apartmentSid=" + getApartmentSid() + ", categoryCode=" + getCategoryCode() + ", pageIndex=" + getPageIndex() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.shop.StoreItemListParam
 * JD-Core Version:    0.6.2
 */