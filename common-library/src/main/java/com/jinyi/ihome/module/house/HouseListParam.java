/*    */ package com.jinyi.ihome.module.house;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class HouseListParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String apartmentSid;
/*    */   private String ownerSid;
/*    */   private String filter;
/*    */   private int pageIndex;
/*    */   private int publishType;
/*    */ 
/*    */   public String getApartmentSid()
/*    */   {
/* 17 */     return this.apartmentSid;
/*    */   }
/*    */ 
/*    */   public String getOwnerSid() {
/* 21 */     return this.ownerSid;
/*    */   }
/*    */ 
/*    */   public String getFilter() {
/* 25 */     return this.filter;
/*    */   }
/*    */ 
/*    */   public int getPageIndex() {
/* 29 */     return this.pageIndex;
/*    */   }
/*    */ 
/*    */   public int getPublishType()
/*    */   {
/* 38 */     return this.publishType;
/*    */   }
/*    */ 
/*    */   public void setApartmentSid(String apartmentSid)
/*    */   {
/* 10 */     this.apartmentSid = apartmentSid; } 
/* 10 */   public void setOwnerSid(String ownerSid) { this.ownerSid = ownerSid; } 
/* 10 */   public void setFilter(String filter) { this.filter = filter; } 
/* 10 */   public void setPageIndex(int pageIndex) { this.pageIndex = pageIndex; } 
/* 10 */   public void setPublishType(int publishType) { this.publishType = publishType; } 
/* 10 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof HouseListParam)) return false; HouseListParam other = (HouseListParam)o; if (!other.canEqual(this)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); if (this$apartmentSid == null ? other$apartmentSid != null : !this$apartmentSid.equals(other$apartmentSid)) return false; Object this$ownerSid = getOwnerSid(); Object other$ownerSid = other.getOwnerSid(); if (this$ownerSid == null ? other$ownerSid != null : !this$ownerSid.equals(other$ownerSid)) return false; Object this$filter = getFilter(); Object other$filter = other.getFilter(); if (this$filter == null ? other$filter != null : !this$filter.equals(other$filter)) return false; if (getPageIndex() != other.getPageIndex()) return false; return getPublishType() == other.getPublishType(); } 
/* 10 */   protected boolean canEqual(Object other) { return other instanceof HouseListParam; } 
/* 10 */   public int hashCode() { int PRIME = 59; int result = 1; Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); Object $ownerSid = getOwnerSid(); result = result * 59 + ($ownerSid == null ? 0 : $ownerSid.hashCode()); Object $filter = getFilter(); result = result * 59 + ($filter == null ? 0 : $filter.hashCode()); result = result * 59 + getPageIndex(); result = result * 59 + getPublishType(); return result; } 
/* 10 */   public String toString() { return "HouseListParam(apartmentSid=" + getApartmentSid() + ", ownerSid=" + getOwnerSid() + ", filter=" + getFilter() + ", pageIndex=" + getPageIndex() + ", publishType=" + getPublishType() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.house.HouseListParam
 * JD-Core Version:    0.6.2
 */